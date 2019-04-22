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
	public boolean certificateMail(
			String id
			) {
		System.out.println("email ID = "+id);
		String target = id;
		boolean result = false;
		String defaultTitle = "Welcome to SPROUT!";
		String defaultMessage = "click this link for certification \r\r";
		UUID uid = UUID.randomUUID();
		String code= uid.toString().substring(0, 7);
		System.out.println(code);
		int res = memRepo.updateCertificate(id, code);
		if(res == 0) {
			return false;
		}
		String url = "<a href='http://localhost:2848/sprout/certification?code="+code+"&id="+id+"'>인증하기</a>";
		defaultMessage+=url;
		result = mm.sendMail(defaultTitle, defaultMessage, target);
		
		return true;
	}
	
	//인증하고 홈으로
	@RequestMapping(value = "/certification", method = RequestMethod.GET)
	public String certification(String code, String id) {
		
		Member member = memRepo.checkId(id);
		
		String authKey = member.getMember_authkey();
		
		//인증하면 성공페이지로 거기서 바로 홈으로
		if (authKey.equals(code)) {
			int result = memRepo.updateAuthKey(id, "success");
			return "certification/success";
		} else {
			return "certification/fail";
		}
	}
	
	//비번찾기
		@RequestMapping(value = "/findPassword", method = RequestMethod.GET)
		@ResponseBody
		public String findPassword(String id) {
			Member member = memRepo.checkId(id);
			String target = id;
			String defaultTitle = "Your Password";
			String defaultMessage = "<h1 style='text-align:center;'>Your Password is</h1> \r\r";
			
			String url = "<p style='text-align:center;'>"+member.getMember_password()+"</p>";
			defaultMessage+=url;
			boolean result = mm.sendMail(defaultTitle, defaultMessage, target);
			
			if (result) {
				return "success";
			} else {
				return "fail";
			}
		}
}
