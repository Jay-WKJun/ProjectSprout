package com.team.sprout.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.javassist.bytecode.LineNumberAttribute.Pc;
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
import com.team.sprout.util.TempJson;
import com.team.sprout.vo.MainProject;
import com.team.sprout.vo.Member;
import com.team.sprout.vo.ProjectContent;

@Controller
public class TimeTableController {
	
	//TODO:날짜가 겹치지 않게 validate해주는 것 필요

	@Autowired
	MemberRepository repo;
	@Autowired
	ProjectContentRepository pcRepo;
	@Autowired
	MainProjectRepository mainrepo;
	@Autowired
	ProjectMemberRepository prrepo;
	
	/*
	 * 접속한 프로젝트의 일을 모두 불러오고 타임테이블까지 만드는 메소드
	 */
	@RequestMapping(value = "/timetable", method = RequestMethod.GET)
	public String table(Locale locale, Model model, HttpSession session) {
		System.out.println("타임테이블 컨트롤러 시작");
		
		//TODO:열자마자 schdule이 하나도 없는지 확인합니다. 없으면 table대신에 다른 것을 띄워줍니다. 추가해야함!!!!
		
		String mainProjectNum = (String)session.getAttribute("mainproject_projectnum");
		//프로젝트 상세정보에 들어올때 session으로 해당 프로젝트 num을 등록하고 꺼내서 쓴다.
		//project.jsp에 hidden으로 숨겨둔 곳에서 가져다가 사용할 수도 있다.
				
				//"6da5455f-abe5-4390-81c6-e05e3f7e1ddc";
		
		List<ProjectContent> pcList = pcRepo.projectContentSelectAll(mainProjectNum);
		for (ProjectContent projectContent : pcList) {
			System.out.println(projectContent.toString());
		}
		
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
				object.addProperty("customClass", pc.getProjectContent_color());
				object.addProperty("dataObj", pc.getProjectContent_num());
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
				Member member = repo.searchMember(pc.getMember_num());
				
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
				object.addProperty("customClass",pc.getProjectContent_color());
				object.addProperty("dataObj",  pc.getProjectContent_num());
				
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
		
		return "timetable/timetable";
	}
	
	/*
	 * task 만드는 popup의 ajax pc데이터를 받아 클릭한 해당 task의 정보를 가져온다.
	 */
	@RequestMapping(value = "/timetableMake", method = RequestMethod.GET)
	@ResponseBody
	public List<Member> tableMakeGo(Model model, HttpSession session) {
	
		//Map<String, Object> results = new HashMap<>();
		//여기서 메인프로젝트랑 프로젝트에 소속되있는 member 넘겨주기
		
		String mainProjectNum = (String)session.getAttribute("mainproject_projectnum");
		
		//"6da5455f-abe5-4390-81c6-e05e3f7e1ddc";
		//프로젝트 상세정보에 들어올때 session으로 해당 프로젝트 num을 등록하고 꺼내서 쓴다.
		
		//해당프로젝트의 이름을 찾는다.
		/*MainProject mp = mainrepo.forgoproject(mainProjectNum);
		results.put("mainproject_title", mp.getMainproject_title());*/
		
		List<Member> proMemberList = prrepo.projectmemberSelectAll(mainProjectNum);
		//results.put("proMemberList", proMemberList);
		
		//이걸로 해당프로젝트에 해당하는 인원만 select할 수 있게 제한한다.
		//List<ProjectMember> projectMembers = prrepo.projectmemberSelectAll(mainProjectNum);
		
		model.addAttribute("projectNumTest", mainProjectNum);
		
		//member객체를 가져와서 그것의 번호를 사용한다.
		model.addAttribute("memberNumTest", 4);
		
		
		/*나중에 멤버가 많아질대 이걸로 쓴다.
		 * int i = 0;
		
		for (ProjectMember projectMember : projectMembers) {
			String ii = Integer.toString(i);
			model.addAttribute(ii, projectMember);
			i++;
		}*/
		
		return proMemberList;
	}
	
	
	/*
	 * Task 한개 등록 메소드
	 * 
	 */
	@RequestMapping(value = "/timetableMake", method = RequestMethod.POST)
	public String tableMake(ProjectContent pc, Model model, HttpSession session) {
		
		String mainProjectNum =	(String)session.getAttribute("mainproject_projectnum");
		//"6da5455f-abe5-4390-81c6-e05e3f7e1ddc";
		System.out.println("타임테이블 만들기 컨트롤러 시작");
		pc.setMainproject_projectNum(mainProjectNum);
		pc.setProejctcontent_categori("아아아아아아");
		pc.setProjectcontent_finishchecked(0);
		pc.setProjectcontent_finishreport(0);
		pc.setProjectcontent_finishdate("090301");
		System.out.println(pc.toString());

		//그냥 바로 DB에 쓰고 redirect로 timetable로딩하는 컨트롤러 실행시켜서 전체불러오기 다시하자.
		int result = pcRepo.ProjectContentRegist(pc);
		System.out.println("pc등록 = "+result);
		
		return "redirect:/project_go";
	}
	
	/*
	 * 자세히보여주는 페이지로 간다.
	 */
	@RequestMapping(value = "/timetableDetail", method = RequestMethod.GET)
	public String tableDetailGo() {
		
		return "showDetail";
	}
	
	@RequestMapping(value = "/tablememberSelect", method = RequestMethod.GET)
	@ResponseBody
	public Member tablememberSelect(int memberNum) {
		System.out.println(memberNum);
		Member result = repo.searchMember(memberNum);
		return result;
	}
	
	/*
	 * 디테일을 빠져나왔으면 다시 타임테이블로 돌아간다.
	 */
	@RequestMapping(value = "/timetableDetail", method = RequestMethod.POST)
	public String tableDetail() {
		
		return "redirect:/project_go";
	}
	
	/*
	 * projectContentNum으로 projectContent를 하나 찾아온다.
	 * 시간표 바가 가지고 있는 projectContentNum을 받아서 projectContent를 찾아 ajax로 보내준다.
	 */
	@RequestMapping(value = "/checkPC", method = RequestMethod.GET)
	@ResponseBody
	public ProjectContent checkPC(int pcNum) {
		System.out.println(pcNum + "  pc체크를 위한 멤버아이디 에이젝스에서 받기중");
		ProjectContent pc = pcRepo.projectContentSelectOne(pcNum);
		
		return pc;
	}
	
	/*
	 * 완료버튼으로 이곳에 옴.
	 * 업데이트 후에 리다이렉트
	 */
	@RequestMapping(value = "/updateContent", method = RequestMethod.POST)
	public String updateContent(ProjectContent pc) {
		int result = pcRepo.projectContentUpdate(pc);
		System.out.println("update 성공 " + result);
		
		
		return "redirect:/project_go";
	}
	
	
	/*
	 * 삭제버튼후 이곳으로 온다.
	 */
	@RequestMapping(value = "/deleteContent", method = RequestMethod.GET)
	public String deleteContent(int projectContent_num) {
		int result = pcRepo.projectContentDelete(projectContent_num);
		System.out.println("delete 성공 " + result);
		
		
		return "redirect:/project_go";
	}
	
}
