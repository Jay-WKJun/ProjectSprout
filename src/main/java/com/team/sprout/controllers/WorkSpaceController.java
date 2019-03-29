package com.team.sprout.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.team.sprout.dao.ProjectMemberRepository;
import com.team.sprout.vo.Member;
import com.team.sprout.vo.ProjectMember;

@Controller
public class WorkSpaceController {
	@Autowired
	ProjectMemberRepository prrepo;
	
	//프로젝트 시작하기를 누르면, 같은 프로젝트의 참여인원 리스트를 뿌린다.
	@RequestMapping(value = "/project_go", method = RequestMethod.POST)
	public String project(String mainproject_projectnum,Model model) {

	List<Member> projectMembers = prrepo.projectmemberSelectAll(mainproject_projectnum);
	
	model.addAttribute("projectMembersList", projectMembers);

		
		return "project/project";
	}
	
	@RequestMapping(value = "/whiteBoard", method = RequestMethod.GET)
	public String whiteBoard() {
		
		return "project/whiteBoard";
	}
}