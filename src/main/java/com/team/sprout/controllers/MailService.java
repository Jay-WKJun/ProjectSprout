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
		
		UUID uid = UUID.randomUUID();
		String code= uid.toString().substring(0, 7);
		System.out.println(code);
		int res = memRepo.updateCertificate(id, code);
		if(res == 0) {
			return false;
		}
		String url = "<a style=\"color:black;font-size:35px\" href='http://localhost:2848/sprout/certification?code="+code+"&id="+id+"'>認証する</a>";
		
		String defaultMessage ="<div style=\"background-color:#e0ebff;padding:10px;flex:1\">\r\n" + 
				"					<div style=\"margin:10px;display:flex\">\r\n" + 
				"						<div style=\"flex:1\"></div>\r\n" + 
				"						<div style=\"flex:initial\">\r\n" + 
				"							<h1 style=\"color:#5680c4;font-size:45px\">ようこそSPOUTへ</h1>\r\n" + 
				"						</div>\r\n" + 
				"						<div style=\"flex:1\"></div>\r\n" + 
				"					</div>\r\n" + 
				"					<div style=\"display:flex\">\r\n" + 
				"						<div style=\"flex:1\"></div>\r\n" + 
				"						<div style=\"color:#59658c;text-align:center;flex:initial;font-size:30px\">\r\n" + 
				"							このメールはSprout会員登録に対する認証のため送りました。<br>\r\n" + 
				"							本人であれば“認証する”をクリックしてください。\r\n" + 
				"						</div>\r\n" + 
				"						<div style=\"flex:1\"></div>\r\n" + 
				"					</div>\r\n" + 
				"					<div style=\"margin:40px;display:flex\">\r\n" + 
				"						<div style=\"flex:1\"></div>\r\n" + 
				"						<div style=\"color:#59658c;text-align:center;flex:initial\">\r\n" + 
				"							\r\n" + url+
				"						</div>\r\n" + 
				"						<div style=\"flex:1\"></div>\r\n" + 
				"					</div>\r\n" + 
				"					\r\n" + 
				"				</div>";
		
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
