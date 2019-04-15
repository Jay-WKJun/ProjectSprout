package com.team.sprout.service;

import java.io.UnsupportedEncodingException;

import javax.activation.DataSource;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.MessagingException;

/*
 * 메일 인증 서비스 사용하기
 */
public class MailHandler {
    private JavaMailSender mailSender;
       private MimeMessage message;
       private MimeMessageHelper messageHelper;


       public MailHandler(JavaMailSender mailSender) throws MessagingException {
           this.mailSender = mailSender;
           message = this.mailSender.createMimeMessage();
           	try {
				messageHelper = new MimeMessageHelper(message, true, "UTF-8");
			} catch (javax.mail.MessagingException e) {
				e.printStackTrace();
			}
       }


       public void setSubject(String subject) throws MessagingException {
           try {
			messageHelper.setSubject(subject);
		} catch (javax.mail.MessagingException e) {
			e.printStackTrace();
		}
           
           // 이메일 타이틀 
       }
       public void setText(String htmlContent) throws MessagingException {
           try {
			messageHelper.setText(htmlContent, true);
		} catch (javax.mail.MessagingException e) {
			e.printStackTrace();
		}
           
           //  이메일 TEXT 부분 
       }
       public void setFrom(String email, String name) throws UnsupportedEncodingException, MessagingException {
           try {
			messageHelper.setFrom(email, name);
		} catch (javax.mail.MessagingException e) {
			e.printStackTrace();
		}
           // 보내는 사람 이메일 
       }
       public void setTo(String email) throws MessagingException {
           try {
			messageHelper.setTo(email);
		} catch (javax.mail.MessagingException e) {
			e.printStackTrace();
		}
           //받는 사람 이메일 
       }
       public void addInline(String contentId, DataSource dataSource) throws MessagingException {
           try {
			messageHelper.addInline(contentId, dataSource);
		} catch (javax.mail.MessagingException e) {
			e.printStackTrace();
		}
       }
       public void send() {
           try {
               mailSender.send(message);
           }catch (Exception e) {
               e.printStackTrace();
           }
       }
}