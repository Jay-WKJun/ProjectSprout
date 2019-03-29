package com.team.sprout.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.team.sprout.dao.WorkSpaceRepository;
import com.team.sprout.vo.Postit;

@Controller
public class WorkSpaceController {
	
	@Autowired
	WorkSpaceRepository workrepo; 
	
	@RequestMapping(value = "/project", method = RequestMethod.GET)
	public String project() {
		
		return "project/project";
	}
	
	//화이트 보드 접속
	@RequestMapping(value = "/whiteBoard", method = RequestMethod.GET)
	public String whiteBoard(Model model, String postitNumFromProjectNum) {
		if(postitNumFromProjectNum!=null) {
			model.addAttribute("projectNum",postitNumFromProjectNum);
		}
		
		return "project/whiteBoard";
	}
	
	//
	@RequestMapping(value = "/addPostit", method = RequestMethod.POST)
	public @ResponseBody String addPostit(Postit postit) {
		if(postit.getPostit_content()==null) {
			postit.setPostit_content("");
		}
		String resp="success";
		int result=workrepo.addPostit(postit);
		if(result==0) {
			resp="fail";
		}
		return resp;
	}
	
	@RequestMapping(value = "/getPostitList", method = RequestMethod.POST)
	public @ResponseBody List<Postit> getPostitList(String MainProject_ProjectNum) {
		List<Postit> postitList =workrepo.getPostitList(MainProject_ProjectNum);
		return postitList;
	}
	
	@RequestMapping(value = "/postitMove", method = RequestMethod.POST)
	public @ResponseBody String postitMove(Postit postit) {
		int result=workrepo.postitPositionUpdate(postit);
		String resp="success";
		if(result==0) {
			resp="fail";
		}
		return resp;
	}
	
	@RequestMapping(value = "/deletePostit", method = RequestMethod.POST)
	public @ResponseBody String deletePostit(int postit_num) {
		int result=workrepo.deletePostit(postit_num);
		String resp="success";
		if(result==0) {
			resp="fail";
		}
		return resp;
	}
	
	@RequestMapping(value = "/postitContentSave", method = RequestMethod.POST)
	public @ResponseBody String postitContentSave(Postit postit) {
		if(postit.getPostit_content()==null) {
			postit.setPostit_content("");
		}
		int result=workrepo.postitContentSave(postit);
		String resp="success";
		if(result==0) {
			resp="fail";
		}
		return resp;
	}
}