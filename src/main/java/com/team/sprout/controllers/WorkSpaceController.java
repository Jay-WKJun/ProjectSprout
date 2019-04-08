package com.team.sprout.controllers;

import java.lang.ProcessBuilder.Redirect;
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

import com.team.sprout.dao.MemberRepository;
import com.team.sprout.dao.ProjectMemberRepository;
import com.team.sprout.dao.WorkSpaceRepository;
import com.team.sprout.vo.MainProject;
import com.team.sprout.vo.Member;
import com.team.sprout.vo.Postit;
import com.team.sprout.vo.ProjectMember;

@Controller
public class WorkSpaceController {
	@Autowired
	ProjectMemberRepository prrepo;
	@Autowired
	WorkSpaceRepository workrepo;
	@Autowired
	MemberRepository memRepo;
	
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
		Member member = memRepo.checkId(addMember);
		prMember.setMember_num(member.getMember_num()); //멤버 넘버
		prMember.setMember_rank(0); //멤버 랭크 
		String prnum = (String) session.getAttribute("mainproject_projectnum");
		prMember.setMainProject_ProjectNum(prnum); //프로젝트 넘버 
		prrepo.ProjectMemberRegist(prMember);
		return "redirect:/project_go";
	}

}