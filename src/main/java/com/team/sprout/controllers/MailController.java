package com.team.sprout.controllers;

import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.team.sprout.service.MailService;
import com.team.sprout.vo.Member;

public class MailController {
	private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	
	@Resource(name="userDAO")
	private Member member;
	
	@Autowired
	private MailService service;
	

	@RequestMapping(value = "/emailConfirm", method = RequestMethod.GET)
	public String emailConfirm(String user_email, Model model) throws Exception { // 이메일인증
		service.userAuth(user_email);
		model.addAttribute("user_email", user_email);

		return "/emailConfirm";
	}

	/* @RequestMapping(value = "/checkMail", method=RequestMethod.POST, produces="application/text; charset=utf8")
	 @ResponseBody
	 private boolean sendMail(HttpSession session, @RequestParam String email) {
		 int randomCode = new Random().nextInt(10000) + 1000;
		 String joinCode = String.valueOf(randomCode);
		 session.setAttribute("joinCode", joinCode);
		 
		 String subject = "회원가입 승인 번호 입니다.";
		 StringBuilder sb = new StringBuilder();
		 sb.append("회원가입 승인 번호는 ").append(joinCode).append(" 입니다.");
		 return mailService.send(subject, sb.toString(), "natcho9010@gmail.com", email);
	 }*/
	
	
	
}
