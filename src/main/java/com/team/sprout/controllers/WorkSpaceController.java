package com.team.sprout.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.team.sprout.dao.MainProjectRepository;
import com.team.sprout.dao.MemberRepository;
import com.team.sprout.dao.ProjectContentRepository;
import com.team.sprout.dao.ProjectMemberRepository;
import com.team.sprout.dao.WorkSpaceRepository;
import com.team.sprout.util.TempJson;
import com.team.sprout.vo.MainProject;
import com.team.sprout.vo.Member;
import com.team.sprout.vo.Notice;
import com.team.sprout.vo.Postit;
import com.team.sprout.vo.ProjectContent;
import com.team.sprout.vo.ProjectMember;


@Controller
public class WorkSpaceController {
	@Autowired
	ProjectMemberRepository prrepo;
	@Autowired
	WorkSpaceRepository workrepo;
	@Autowired
	MemberRepository memRepo;
	
	@Autowired
	MemberRepository repo;
	@Autowired
	ProjectContentRepository pcRepo;
	@Autowired
	MainProjectRepository mainrepo;
	
	List<Member> projectMembers;

	// 프로젝트 시작하기를 누르면, 같은 프로젝트의 참여인원 리스트를 뿌린다.
	@RequestMapping(value = "/project_go", method = RequestMethod.POST)
	public String project(String mainproject_projectnum, Model model, ProjectMember prMember,HttpSession session) {

		projectMembers = prrepo.projectmemberSelectAll(mainproject_projectnum);

		  // 프로젝트의 리더를 찾기 위한 메서드
		  List<ProjectMember> prlist = prrepo.findManager(mainproject_projectnum);
		  for (ProjectMember projectMember : prlist) {

		   model.addAttribute("member_rank", projectMember.getMember_rank());
		   System.out.println(projectMember.getMember_rank());
		  }

		  List<MainProject> mainProjects = prrepo.findProjectName(mainproject_projectnum);
		  for (MainProject mainProject : mainProjects) {

		   model.addAttribute("MainProject_title", mainProject.getMainproject_title());
		  }

		  model.addAttribute("mainproject_projectnum", mainproject_projectnum);
		  session.setAttribute("mainproject_projectnum", mainproject_projectnum);
		  model.addAttribute("projectMembersList", projectMembers);
		  
		//이하로 타임테이블
			System.out.println("타임테이블 컨트롤러 시작");
			
			//TODO:열자마자 schdule이 하나도 없는지 확인합니다. 없으면 table대신에 다른 것을 띄워줍니다. 추가해야함!!!!
			
			String mainProjectNum = (String)session.getAttribute("mainproject_projectnum");
			//프로젝트 상세정보에 들어올때 session으로 해당 프로젝트 num을 등록하고 꺼내서 쓴다.
			//project.jsp에 hidden으로 숨겨둔 곳에서 가져다가 사용할 수도 있다.
					
					//"6da5455f-abe5-4390-81c6-e05e3f7e1ddc";
			
			List<ProjectContent> pcList = pcRepo.projectContentSelectAll(mainProjectNum); 
			
			Gson gson = new Gson();
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			JsonObject object = new JsonObject();
			TempJson tj = new TempJson();
			ArrayList<JsonObject> values = new ArrayList<JsonObject>();
			//여기에 이 메소드에서 쓸 모든 객체를 선언해놓는다.
			//일을 줄때 input select로 같은  프로젝트 사람들로 제한한다.
			
			int memberNumSave = 0;
			int count = 0;
			
			ArrayList<String> resultMix = new ArrayList<String>();
			//객체 하나씩 최종적으로 이곳에 넣고 보내준다.
			
			//여기서부터 foreach로 하나씩 꺼내가면서...
			for (ProjectContent pc : pcList) {
				System.out.println("foreach시작");
				//객체생성이 끝나고 저장한 멤버의 넘버가 새로 가져온 넘버와 같다면 value만 넣고 끝내라.(같은 사람이면 value만 늘려야하기 때문) 
				if (pc.getMember_num() == memberNumSave) {
					//이곳은 같은 줄에 일만 추가해주는 곳
					System.out.println("value추가하는 곳 옴");
					
					object = new JsonObject();
					
					//여기서 부터 넣어줄 value객체 생성
					Date start = null;
					Date end = null;
					
					try {
						start = transFormat.parse(pc.getProjectContent_startDate());
						end = transFormat.parse(pc.getProjectContent_endDate());
					} catch (ParseException e) {
						System.out.println("데이트 변환 중 에러입니다.......");
						e.printStackTrace();
					}
					//!!!!시간이 겹치지 않도록 유효성 검사 필요
					
					String starttimeCh = Long.toString(start.getTime());
					String endtimeCh = Long.toString(end.getTime());
					
					object.addProperty("from", "/Date("+starttimeCh+")/");
					object.addProperty("to", "/Date("+endtimeCh+")/");
					object.addProperty("desc", pc.getProjectContent_content());
					object.addProperty("label", pc.getProjectContent_title());
					object.addProperty("customClass", pc.getProjectContent_num());
					object.addProperty("dataObj", pc.getProjectContent_content());
					//여기까지 생성 완료
					
					System.out.println(object.toString());
					
					values.add(object);
					//만든 value객체 values 리스트에 추가
					
					memberNumSave = pc.getMember_num();
					
				} else {
					//이곳이 새로운 줄을 만드는 곳이다.
					System.out.println("새로운거 만드는 곳 옴...");
					//만약 새로 시작한 것이 아니라면(비어있지 않다면) 최종 리스트에 넣고 시작해야한다.
					if(count != 0) {
						//!이거 아직 테스트 안 끝남.
						System.out.println("중복되는 데이터를 모두 넣고 끝낸다.");
						tj.setValues(values);
						
						String result = gson.toJson(tj);
						
						System.out.println(result.toString());
						
						resultMix.add(result);
					}
					
					//해당 멤버 한명의 객체를 가져온다.
					Member member = repo.searchMember(4);//try 아이디 메인키 번호는 4이다.
					
					System.out.println(member.toString());
					
					tj = new TempJson();
					//임시 JSON객체 초기화
					values = new ArrayList<JsonObject>();
					//value 리스트 초기화
					object = new JsonObject();
					//json초기화
					
					tj.setName(member.getMember_name()); //수행하는 사람 이름이 들어간다.
					tj.setDesc("...");//안쓰는 데이터
					
					Date start = null;
					Date end = null;
					
					try {
						start = transFormat.parse(pc.getProjectContent_startDate());
						end = transFormat.parse(pc.getProjectContent_endDate());
					} catch (ParseException e) {
						System.out.println("데이트 변환 중 에러입니다.......");
						e.printStackTrace();
					}
					//시간이 겹치지 않도록 유효성 검사 필요
					
					String starttimeCh = Long.toString(start.getTime());
					String endtimeCh = Long.toString(end.getTime());
					
					object.addProperty("from", "/Date("+starttimeCh+")/");
					object.addProperty("to", "/Date("+endtimeCh+")/");
					object.addProperty("desc", pc.getProjectContent_content());
					object.addProperty("label", pc.getProjectContent_title());
					object.addProperty("customClass", pc.getProjectContent_num());
					object.addProperty("dataObj", pc.getProjectContent_content());
					
					System.out.println(object.toString());
					values.add(object);
					
					memberNumSave = pc.getMember_num();
					count++;
				}
				
			}//foreach
			System.out.println("마지막 들어갑니다.");
			//여기서 마지막으로 만들어진 객체까지 넣고 끝낸다.
			for (JsonObject value : values) {
				System.out.println(value.toString());
			}
			tj.setValues(values);
			
			String result = gson.toJson(tj);
			
			System.out.println(result.toString());
			
			resultMix.add(result);

			model.addAttribute("jsonTest", resultMix);
		
		return "project/project";
	}

	
	//갱신전용
	@RequestMapping(value = "/project_go", method = RequestMethod.GET)
	public String project(Model model, HttpSession session) {
		String mpNum = (String)session.getAttribute("mainproject_projectnum");
		
		projectMembers = prrepo.projectmemberSelectAll(mpNum);

		// 프로젝트의 리더를 찾기 위한 메서드
		List<ProjectMember> prlist = prrepo.findManager(mpNum);
		for (ProjectMember projectMember : prlist) {
			
			model.addAttribute("member_rank", projectMember.getMember_rank());
		}

		List<MainProject> mainProjects = prrepo.findProjectName(mpNum);
		for (MainProject mainProject : mainProjects) {

			model.addAttribute("MainProject_title", mainProject.getMainproject_title());
		}

		model.addAttribute("mainproject_projectnum", mpNum);
		model.addAttribute("projectMembersList", projectMembers);
		
		
		return "project/project";
	}
	
	
	
	

	// 화이트 보드 접속
	@RequestMapping(value = "/whiteBoard", method = RequestMethod.GET)
	public String whiteBoard(Model model, String postitNumFromProjectNum) {
		if (postitNumFromProjectNum != null) {
			model.addAttribute("projectNum", postitNumFromProjectNum);
		}

		return "project/whiteBoard";
	}

	//
	@RequestMapping(value = "/addPostit", method = RequestMethod.POST)
	public @ResponseBody String addPostit(Postit postit) {
		if (postit.getPostit_content() == null) {
			postit.setPostit_content("");
		}
		String resp = "success";
		int result = workrepo.addPostit(postit);
		if (result == 0) {
			resp = "fail";
		}
		return resp;
	}

	@RequestMapping(value = "/getPostitList", method = RequestMethod.POST)
	public @ResponseBody List<Postit> getPostitList(String MainProject_ProjectNum) {
		List<Postit> postitList = workrepo.getPostitList(MainProject_ProjectNum);
		return postitList;
	}

	@RequestMapping(value = "/postitMove", method = RequestMethod.POST)
	public @ResponseBody String postitMove(Postit postit) {
		int result = workrepo.postitPositionUpdate(postit);
		String resp = "success";
		if (result == 0) {
			resp = "fail";
		}
		return resp;
	}

	@RequestMapping(value = "/deletePostit", method = RequestMethod.POST)
	public @ResponseBody String deletePostit(int postit_num) {
		int result = workrepo.deletePostit(postit_num);
		String resp = "success";
		if (result == 0) {
			resp = "fail";
		}
		return resp;
	}

	@RequestMapping(value = "/postitContentSave", method = RequestMethod.POST)
	public @ResponseBody String postitContentSave(Postit postit) {
		if (postit.getPostit_content() == null) {
			postit.setPostit_content("");
		}
		int result = workrepo.postitContentSave(postit);
		String resp = "success";
		if (result == 0) {
			resp = "fail";
		}
		return resp;
	}

	// 참여멤버 추가하기 위해 추가가능하는지 알려주는 메서드
	@RequestMapping(value = "/checkForAddMember", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> checkForAddMember(String addMember, HttpSession session,
			ProjectMember member, Model model) {
		//admember 아이디 
		Member result = memRepo.checkId(addMember);
		
		
		//result.getMember_num();
		String id = (String) session.getAttribute("loginId");
		int resp = 0;
		Map<String, Object> map = new HashMap<>();
		
		if (result != null) {
			// ok

			if (addMember.equals(id)) {
				resp = 2;
				map.put("resp", resp);
				return map;
				// 자기자신이면 실패
			}
			for (Member member2 : projectMembers) {
				if (member2.getMember_id().equals(addMember)) {
					resp = 2;
					// 이미 추가된 아이디면 실패
					map.put("resp", resp);
					return map;
				}
			}
			
			map.put("add_num", result.getMember_num());
			map.put("add_prNum", session.getAttribute("mainproject_projectnum"));
			resp = 1;
			map.put("resp", resp);
			return map;
		} 
		
			resp = 0;
			// 추가 불가
			map.put("resp", resp);
			return map;
	

	}

	// 멤버를 추가하기 위한 메서드
	@RequestMapping(value = "/addProjectMember", method = RequestMethod.GET)
	public String addProjectMember(String addMember, HttpSession session) {
		ProjectMember prMember = new ProjectMember();
		Member member = memRepo.checkId(addMember); //아이디
		prMember.setMember_num(member.getMember_num()); //멤버 넘버
		prMember.setMember_rank(0); //멤버 랭크 
		String prnum = (String) session.getAttribute("mainproject_projectnum");
		prMember.setMainProject_ProjectNum(prnum); //프로젝트 넘버 
		prrepo.ProjectMemberRegist(prMember);
		return "redirect:/project_go";
	}
	
	//공지사항을 등록할 메서드 
	@ResponseBody
	@RequestMapping(value="/registNotice",method= RequestMethod.GET)
	public String registNotice(String notice_content ,HttpSession session ) {
		
		String result="success";
		Notice notice = new Notice();
		String MainProject_ProjectNum =(String) session.getAttribute("mainproject_projectnum");
		int memberRank_num = (int) session.getAttribute("loginNum");
		System.out.println("mainproject_projectnum"+MainProject_ProjectNum);
		System.out.println("notice_content"+notice_content);
		System.out.println(memberRank_num);
		//공지사항 
		notice.setMainProject_ProjectNum(MainProject_ProjectNum);
		notice.setMemberRank_num(memberRank_num);
		notice.setNotice_content(notice_content);
		int resp=prrepo.registNotice(notice);
		if(resp==0) {
			result="fail";
		}
/*		List<Notice> noList = prrepo.noticeList(MainProject_ProjectNum);*/

		return result;
	}
	
	
	// 공지사항 리스트
	@ResponseBody
	@RequestMapping(value="/gonoList",method= RequestMethod.GET)
	public List<Notice> gonoList(HttpSession session){
		String MainProject_ProjectNum =(String) session.getAttribute("mainproject_projectnum");
		List<Notice> noList = prrepo.noticeList(MainProject_ProjectNum);
		return noList;
	}
	
	//강퇴하는
	@ResponseBody
	@RequestMapping(value="/kickMember",method= RequestMethod.GET)
	public String kickMember(int member_num,HttpSession session) {
		System.out.println("member_num"+member_num);
		ProjectMember member = new ProjectMember();
		String result = "success";
		String MainProject_ProjectNum =(String) session.getAttribute("mainproject_projectnum");
		int my_num = (int) session.getAttribute("loginNum");
		if (my_num == member_num ) {
			return "fail";
		}
		System.out.println(member_num);
		member.setMainProject_ProjectNum(MainProject_ProjectNum);
		member.setMember_num(member_num);
		int prmember = prrepo.kickMember(member);
		System.out.println("prmember"+prmember);
		
		if (prmember ==0) {
			return "fail";
		}
		
		return result;
	}
	

}