package com.team.sprout.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.team.sprout.dao.MainProjectRepository;
import com.team.sprout.dao.MemberRepository;
import com.team.sprout.dao.ProjectMemberRepository;
import com.team.sprout.vo.MainProject;
import com.team.sprout.vo.Member;
import com.team.sprout.vo.ProjectMember;


@Controller
public class MainProjectController {
	
	@Autowired
	MainProjectRepository MainRepo;
	@Autowired
	ProjectMemberRepository prRepo;
	@Autowired
	MemberRepository mem;
	
	//자아아아아아 버전을 바꿔봅시다
	
	@RequestMapping(value="/mainProjectRegist", method=RequestMethod.POST)

	public String mainProjectRegist(MainProject project, ProjectMember prMember,Model model, Member member, HttpSession session) {
		
		int  Member_num= (int) session.getAttribute("loginNum");
		model.addAttribute("member_num", Member_num);
		String uuid = UUID.randomUUID().toString();
		project.setMainproject_projectnum(uuid);
		//mainproject 정보 insert 
		int result =MainRepo.mainProjectRegist(project);
		
		//projectMember 정보 insert 
		
		prMember.setMainProject_ProjectNum(uuid);
		prMember.setMember_num(Member_num);
		prMember.setMember_rank(5);

		int result_prMember= prRepo.ProjectMemberRegist(prMember);
		
		return "redirect:/";
	}
	
	
	//프로젝스 시작을 위한 메서드. 프로젝트 넘을 통해 원하는 누른 프로젝트의 정보를 제공 
	@ResponseBody
	@RequestMapping(value="/startproject_go", method=RequestMethod.GET)
	public Map<String,Object> startproject_go(String mainproject_projectnum, HttpSession session,Model model) {
		MainProject mainproject =  MainRepo.forgoproject(mainproject_projectnum);
		List<Member> memberList = prRepo.projectmemberSelectAll(mainproject_projectnum);
/*		model.addAttribute("memberList", memberList);*/
		
		
		Map<String, Object> map = new HashMap<>();
		map.put("memberList", memberList);
		int num = (int)session.getAttribute("loginNum");
		Member member = MainRepo.formembername(num);
		
		map.put("goproject_title", mainproject.getMainproject_title());
		map.put("goproject_content",mainproject.getMainproject_memo());
		
		return map;
		
	}
	
	@RequestMapping(value = "/signinCheck", method = RequestMethod.GET)
	public @ResponseBody String signinCheck(HttpSession session) {
		String loginNum=(String)session.getAttribute("loginId");
		String result="success";
		if(loginNum==null) {
			result="fail";
		}
			
		return result;
	}
}
