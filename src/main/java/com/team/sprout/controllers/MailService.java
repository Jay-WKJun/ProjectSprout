package com.team.sprout.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.team.sprout.dao.MemberRepository;

@Controller
public class MailService {

	@Autowired
	MailManager mm;
	@Autowired
	MemberRepository memRepo;
	
	@RequestMapping(value = "/mailService", method = RequestMethod.GET)
	public String certificateMail(
			//String id,
			//String target
			) {
		String target = "natcho90@naver.com";
		boolean result = false;
		String defaultTitle = "Welcome to Atelier! ";
		String defaultMessage = "click this link for certification \r\r";
		UUID uid = UUID.randomUUID();
		String code= uid.toString().substring(0, 7);
		//boolean res = memRepo.insertCertificate(id, code);
		/*if(!res) {
			return result;
		}*/
		String url = "여기 뭐가오는지 보자";
		defaultMessage+=url;
		result = mm.sendMail(defaultTitle, defaultMessage, target);
		
		return "redirect:/";
	}
}
