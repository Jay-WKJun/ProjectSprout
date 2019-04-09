
package com.team.sprout.controllers;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.team.sprout.dao.MemberRepository;
import com.team.sprout.dao.TextDAO;
import com.team.sprout.vo.Member;
import com.team.sprout.vo.Text;



@Controller
public class TextController {
	
	@Autowired
	TextDAO dao;
	
	@Autowired
	MemberRepository repo;
	private static final Logger logger = LoggerFactory.getLogger(TextController.class);
	
		@RequestMapping(value="/tinsert", method = RequestMethod.POST)
		public @ResponseBody String textinsert(Text text, HttpSession session) {
			String member_name=(String)session.getAttribute("loginName");
			text.setMember_name(member_name);
			System.out.println(text.toString());
			int result = dao.TextInsert(text);
			return "success";
		}
		
		//시작시 메시지 출력 불러오기.
		@RequestMapping(value="/printMessage", method = RequestMethod.POST)
		public @ResponseBody List<Text> printMessage(int chatRoom_num, HttpSession session) {
			System.out.println("chat num" + chatRoom_num);
				
			List<Text> text = dao.printMessage(chatRoom_num);
			return text;
		}
		
		//대화상대 초대
		
		@RequestMapping(value="/invitation", method = RequestMethod.POST)
		public @ResponseBody Member invitation(String member_name, HttpSession session) {
			 Member member = repo.selectOneWebsocket(member_name);
			return member;
		}
}
