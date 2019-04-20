package com.team.sprout.controllers;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

@Component
public class MailManager {
	public boolean sendMail(String title, String contents, String target) {
		boolean result = false;
		Properties p = System.getProperties();
		p.put("mail.smtp.starttls.enable", "true"); // gmail : true
		p.put("mail.smtp.host", "smtp.gmail.com"); // smtp server address
		p.put("mail.smtp.auth", "true"); // gmail : true
		p.put("mail.smtp.port", "587"); // gmail port 587 465

		Authenticator auth = new MyAuthentication();

		// create session & MimeMessage
		Session session = Session.getDefaultInstance(p, auth);
		MimeMessage msg = new MimeMessage(session);

		try {
			// sendTime
			msg.setSentDate(new Date());

			InternetAddress from = new InternetAddress();
			//test  <-- sendName
			from = new InternetAddress("natcho9010@gmail.com");

			// From
			msg.setFrom(from);

			// to
			InternetAddress to = new InternetAddress(target);
			msg.setRecipient(Message.RecipientType.TO, to);

			// title
			msg.setSubject(title, "UTF-8");

			// contents
			msg.setText(contents, "UTF-8");

			// set mail header
			msg.setHeader("content-Type", "text/html");

			// send mail
			javax.mail.Transport.send(msg);

		} catch (AddressException addr_e) {
			addr_e.printStackTrace();
		} catch (MessagingException msg_e) {
			msg_e.printStackTrace();
		}
		return result;
	}
}
class MyAuthentication extends Authenticator {
	
	PasswordAuthentication pa;
	
	public MyAuthentication() {
		
		String id = "natcho9010@gmail.com"; // ID
		String pw = "w!95759713"; // PW
		
		// input account info
		pa = new PasswordAuthentication(id, pw);
		
	}
	
	// system used info
	public PasswordAuthentication getPasswordAuthentication() {
		return pa;
	}
	
}