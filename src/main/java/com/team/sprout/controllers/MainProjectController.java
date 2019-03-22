package com.team.sprout.controllers;

import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
		System.out.println(project);
		model.addAttribute("member_num", member.getMember_num());
		System.out.println("멤버수수수수수ㅜ:"+member.getMember_num());

		String uuid = UUID.randomUUID().toString();
		project.setMainproject_projectnum(uuid);
		
		//mainproject 정보 insert 
		int result =MainRepo.mainProjectRegist(project);
		int  Member_num= (int) session.getAttribute("loginNum");
		
		//projectMember 정보 insert 
		
		prMember.setMainProject_ProjectNum(uuid);
		prMember.setMember_num(Member_num);
		prMember.setMember_rank(5);
	
		
		int result_prMember= prRepo.ProjectMemberRegist(prMember);
		
		return "redirect:/";
	}
	
	
}
