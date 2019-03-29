package com.team.sprout.controllers;

import java.util.ArrayList;
import java.util.HashMap;
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
	

	
	@RequestMapping(value="/mainProjectRegist", method=RequestMethod.POST)

	public String mainProjectRegist(MainProject project, ProjectMember prMember,Model model, Member member, HttpSession session) {
		
		int  Member_num= (int) session.getAttribute("loginNum");
		model.addAttribute("member_num", Member_num);
		System.out.println(Member_num);



		String uuid = UUID.randomUUID().toString();
		project.setMainproject_projectnum(uuid);
		//session.setAttribute("MainProject_ProjectNum", uuid);
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
	public Map<String,String> startproject_go(String mainproject_projectnum, HttpSession session) {
		MainProject mainproject =  MainRepo.forgoproject(mainproject_projectnum);
		
		
		int num = (int)session.getAttribute("loginNum");
		Member member = MainRepo.formembername(num);
		
		
		Map<String, String> map = new HashMap<>();
		map.put("goproject_title", mainproject.getMainproject_title());
		map.put("goproject_content",mainproject.getMainproject_memo());
		map.put("goprojet_membername", member.getMember_name());
	
				
		System.out.println(member.getMember_name());
		return map;
		
	}
}
