package com.team.sprout.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.team.sprout.dao.MemberRepository;
import com.team.sprout.dao.ProjectMemberRepository;
import com.team.sprout.dao.WorkSpaceRepository;
import com.team.sprout.vo.MainProject;
import com.team.sprout.vo.Member;
import com.team.sprout.vo.ProjectMember;

@Controller
public class ProjectDetailCotroller {
	@Autowired
	ProjectMemberRepository prrepo;
	@Autowired
	WorkSpaceRepository workrepo;
	@Autowired
	MemberRepository memRepo;	
	List<Member> projectMembers;
	
	
	@RequestMapping(value ="/detailPage", method = RequestMethod.GET)
	public String project_detail() {

	
		return "project/detailPage";
	}

}
