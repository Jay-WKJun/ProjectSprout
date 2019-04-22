package com.team.sprout.controllers;

import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.team.sprout.dao.MemberRepository;
import com.team.sprout.vo.Member;

@Controller
public class MailService {

	@Autowired
	MailManager mm;
	@Autowired
	MemberRepository memRepo;
	
	//인증메일쏘기
	@RequestMapping(value = "/mailService", method = RequestMethod.POST)
	@ResponseBody
	public String certificateMail(
			//String id,
			//String target,
			HttpSession session
			) {
		String id = (String)session.getAttribute("loginId");
		String target = id;
		boolean result = false;
		String defaultTitle = "Welcome to SPROUT!";
		String defaultMessage = "click this link for certification \r\r";
		UUID uid = UUID.randomUUID();
		String code= uid.toString().substring(0, 7);
		int res = memRepo.insertCertificate(id, code);
		if(res == 0) {
			return "redirect:/";
		}
		String url = "<a href='/certification?code="+code+"'>인증하기</a>";
		defaultMessage+=url;
		result = mm.sendMail(defaultTitle, defaultMessage, target);
		
		return "redirect:/";
	}
	
	//인증하고 홈으로
	@RequestMapping(value = "/certification", method = RequestMethod.GET)
	public String certification(String code, HttpSession session) {
		
		String id = (String)session.getAttribute("loginId");
		
		Member member = memRepo.checkId(id);
		
		String authKey = member.getMember_authkey();
		
		if (authKey.equals(code)) {
			return "redirect:/";
		} else {
			return "redirect:/";
		}
	}
	
}
