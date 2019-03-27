package com.team.sprout.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.team.sprout.dao.MainProjectRepository;
import com.team.sprout.vo.MainProject;

@Controller
public class HomeController {
	
	@Autowired
	MainProjectRepository mainRepo;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model,HttpSession session) {
		System.out.println("= = = = = = = = = = = = = = = = = = = = =");
		System.out.println("           start the server !! ");
		System.out.println("= = = = = = = = = = = = = = = = = = = = =");
			//session이 존재하는지 확인
			if(session.getAttribute("loginNum")!=null){
				int loginNum=(int) session.getAttribute("loginNum");
				
				//멤버가 참여중인 프로젝트 가져옴
				List<MainProject> projectList= mainRepo.projectList(loginNum);
				model.addAttribute("projectList", projectList);
			}
			
		return "main/index";
	}
	
}
