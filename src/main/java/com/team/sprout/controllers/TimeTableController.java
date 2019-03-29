package com.team.sprout.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
import com.team.sprout.vo.ProjectMember;

@Controller
public class TimeTableController {

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
	public String table(Locale locale, Model model) {
		System.out.println("타임테이블 컨트롤러 시작");
		
		String mainProjectNum = "6da5455f-abe5-4390-81c6-e05e3f7e1ddc";
		//프로젝트 상세정보에 들어올때 session으로 해당 프로젝트 num을 등록하고 꺼내서 쓴다.
		
		List<ProjectContent> pcList = pcRepo.projectContentSelectAll(mainProjectNum); 
		//pc가 여러개 있는 arrayList를 가져와서 foreach로 전부 하나씩 꺼내서 만들어 넣어준다.
		
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
				//시간이 겹치지 않도록 유효성 검사 필요
				
				String starttimeCh = Long.toString(start.getTime());
				String endtimeCh = Long.toString(end.getTime());
				
				object.addProperty("from", "/Date("+starttimeCh+")/");
				object.addProperty("to", "/Date("+endtimeCh+")/");
				object.addProperty("label", pc.getProjectContent_title());
				object.addProperty("customClass", pc.getProjectContent_color());
				//여기까지 생성 완료
				
				values.add(object);
				//만든 value객체 values 리스트에 추가
				
				memberNumSave = pc.getMember_num();
				
			} else {
				//이곳이 새로운 줄을 만드는 곳이다.
				System.out.println("새로운거 만드는 곳 옴...");
				//만약 새로 시작한 것이 아니라면(비어있지 않다면) 최종 리스트에 넣고 시작해야한다.
				if(count != 0) {
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
				object.addProperty("label", pc.getProjectContent_title());
				object.addProperty("customClass", pc.getProjectContent_color());
				
				System.out.println(object.toString());
				values.add(object);
				
				tj.setValues(values);
				
				memberNumSave = pc.getMember_num();
				count++;
			}
			
		}//foreach
		System.out.println("마지막 들어갑니다.");
		//여기서 마지막으로 만들어진 객체까지 넣고 끝낸다.
		tj.setValues(values);
		
		String result = gson.toJson(tj);
		
		System.out.println(result.toString());
		
		resultMix.add(result);

		model.addAttribute("jsonTest", resultMix);
		
		return "timetable/timetable";
	}
	
	/*
	 * task 만드는 jsp로 가는 메소드
	 */
	@RequestMapping(value = "/timetableMake", method = RequestMethod.GET)
	public String tableMakeGo(ProjectContent pc, Model model) {
	
		//여기서 메인프로젝트랑 프로젝트에 소속되있는 member 넘겨주기
		String mainProjectNum = "6da5455f-abe5-4390-81c6-e05e3f7e1ddc";
		//프로젝트 상세정보에 들어올때 session으로 해당 프로젝트 num을 등록하고 꺼내서 쓴다.
		
		MainProject mp = mainrepo.forgoproject(mainProjectNum);
		
		model.addAttribute("modelTest", mp.getMainproject_title());
		
		//미현상이 구현중... memberselectAll
		//List<ProjectMember> projectMembers = prrepo.projectmemberSelectAll(mainProjectNum);
		
		//ProjectMember mem = projectMembers.get(0);
		
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
		
		return "timetable/createTask";
	}
	
	
	/*
	 * Task 한개 등록 메소드
	 * 
	 */
	@RequestMapping(value = "/timetableMake", method = RequestMethod.POST)
	public String tableMake(ProjectContent pc, Model model) {
		
		System.out.println("타임테이블 만들기 컨트롤러 시작");
		System.out.println(pc.toString());
		pc.setProjectContent_color("앞으로 이거는 줄위에 간단하게 표현되는 글자로 사용합니다(타이틀 대신)");
		//그냥 바로 DB에 쓰고 redirect로 timetable로딩하는 컨트롤러 실행시켜서 전체불러오기 다시하자.
		int result = pcRepo.ProjectContentRegist(pc);
		System.out.println(result);
		
		
		return "redirect:/timetable";
	}
}
