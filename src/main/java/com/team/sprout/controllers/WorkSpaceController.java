package com.team.sprout.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WorkSpaceController {
	
	@RequestMapping(value = "/project", method = RequestMethod.GET)
	public String project() {
		
		return "project/project";
	}
	
	@RequestMapping(value = "/whiteBoard", method = RequestMethod.GET)
	public String whiteBoard() {
		
		return "project/whiteBoard";
	}
}