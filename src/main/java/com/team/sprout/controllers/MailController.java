package com.team.sprout.controllers;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
public class MailController {
	@RequestMapping(value="/emailSend", method= RequestMethod.GET)
	public String emailSend(HttpServletRequest request, ModelMap mo, String email, HttpSession Sendsession) throws AddressException, MessagingException {
		String host = "smtp.naver.com"; 
		final String username = "natcho90"; 			 //네이버 아이디를 입력. @naver.com은 입력하지 않는다.
		final String password = "w!95759713"; 			//네이버 이메일 비밀번호 입력 
		int port=465; //포트번호 
		
		// 수신자의 메일주소, 메일제목, 메일 내용을 입력한다. 
		String recipient = "natcho9010@gmail.com"; 	// 받는 사람 
		//받는 사람의 메일주소를 입력해주세요. 
		String subject = "test"; 
		//메일 제목 입력해주세요. 
		
		String body = username+"received "; 
		//메일 내용 입력해주세요. 
		Properties props = System.getProperties(); 
		// 정보를 담기 위한 객체 생성 
		// SMTP 서버 정보 설정 
		props.put("mail.smtp.host", host); 
		props.put("mail.smtp.port", port); 
		props.put("mail.smtp.auth", "true"); 
		props.put("mail.smtp.ssl.enable", "true"); 
		props.put("mail.smtp.ssl.trust", host); 

		//Session 생성 
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() 
		{ 
			String un=username; 
			String pw=password; 
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() { 
				return new javax.mail.PasswordAuthentication(un, pw); 
			} 
		}); 
		session.setDebug(true); 

		Message mimeMessage = new MimeMessage(session); 
		//MimeMessage 생성
		mimeMessage.setFrom(new InternetAddress("natcho90@naver.com")); 
		//발신자 셋팅 , 보내는 사람의 이메일주소를 한번 더 입력합니다. 이때는 이메일 풀 주소를 다 작성해주세요. 
		mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient)); 
		//수신자셋팅 //.TO 외에 .CC(참조) .BCC(숨은참조) 도 있음 
		mimeMessage.setSubject(subject);
		//제목셋팅 
		mimeMessage.setText(body); 
		//내용셋팅 
		
		System.out.println();
		Transport.send(mimeMessage); 
		//javax.mail.Transport.send() 이용


		return "home";

	}
}
	
	//