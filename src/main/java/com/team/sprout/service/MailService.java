package com.team.sprout.service;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.team.sprout.dao.MemberRepository;
import com.team.sprout.vo.Member;

@Service
public class MailService {

	@Inject
	private JavaMailSender mailSender;
	
	@Autowired
	MemberRepository mRepo;
	
	/*@Inject
	private SignUpDao signUpDAO;*/
	
	/*public MailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}*/
	
	/*@Override
	public boolean send(String subject, String text, String from, String to) {
		MimeMessage message = javaMailSender.createMimeMessage();
		
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
			helper.setSubject(subject);
			helper.setText(text);
			helper.setFrom(from);
			helper.setTo(to);
			
			javaMailSender.send(message);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}*/
	
	/*@Override
    public void signUp(Member member) throws MessagingException, UnsupportedEncodingException {
        //signUpDao.insertUser(userVo);
        String key = new TempKey().getKey(50, false);
        //signUpDao.insertEmailConfirm(userVo.getId(), key);
        MailHandler sendMail = new MailHandler(mailSender);
        sendMail.setSubject("[이메일 인증]");
        sendMail.setText(new StringBuffer().append("<h1>메일인증</h1>")
                .append("<a href='http://localhost:8080/spring/emailConfirm?key=")
                .append(key)
                .append("' target='_blenk'>이메일 인증 확인</a>")
                .toString());
        sendMail.setFrom("보낸이메일", "이름");
        sendMail.setTo(member.getEmail());
        sendMail.send();
    }*/
	
	@Transactional
	public void create(Member member) throws Exception {

	String key = new TempKey().getKey(50, false); // 인증키 생성
	
	member.setMember_authkey(key);// 인증키 DB저장
	
	mRepo.memberJoin(member); // 회원가입 DAO

	MailHandler sendMail = new MailHandler(mailSender);
	sendMail.setSubject("[ALMOM 서비스 이메일 인증]");
	sendMail.setText(
			new StringBuffer().append("<h1>메일인증</h1>").append("<a href='http://localhost/sprout/emailConfirm?user_email=").append(member.getMember_id()).append("&key=").append(key).append("' target='_blenk'>이메일 인증 확인</a>").toString());
	sendMail.setFrom("natcho9010@gmail.com", "알몸개발자");
	sendMail.setTo(member.getMember_id());
	sendMail.send();
	}
	
	public void userAuth(String userEmail) throws Exception {
		mRepo.userAuth(userEmail);
	}


}
