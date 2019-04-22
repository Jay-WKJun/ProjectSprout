package com.team.sprout.controllers;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.team.sprout.dao.MemberRepository;
import com.team.sprout.dao.TextDAO;
import com.team.sprout.vo.Member;
import com.team.sprout.vo.Text;

//TODO:20190411 채팅리스트 member 리스트에 있는거 내아이디랑 비교해서 모두출력.

@Controller
public class TextController {
	
	@Autowired
	TextDAO dao;
	
	@Autowired
	MemberRepository repo;
	
	private static final Logger logger = LoggerFactory.getLogger(TextController.class);
		
	//채팅방이름 등록/맴버넘 등록
		@RequestMapping(value="/roomname", method = RequestMethod.POST)
		public @ResponseBody String roomname(String chatRoom_name, int member_num,Text text, HttpSession session) {
			String member_name=(String)session.getAttribute("loginName");
			text.setMember_name(member_name);
			text.setChatRoom_name(chatRoom_name);
			text.setMember_num(member_num);
			/*int chatRoom_num=(int) session.getAttribute("chatRoom_num");
			text.setChatRoom_num(chatRoom_num);*/
			System.out.println("roomname1"+text.toString());
			int chatroomname= dao.insertChatRoomName(text);
			String iasasdad = text.getChatRoom_name();
			System.out.println("roomname2"+text.getMember_name());
			
			
			//챗룸numㄱㄱ
			 int result = dao.selectchatRoomNum();
			 text.setChatRoom_num(result);
			 session.setAttribute("chatRoom_num", text.getChatRoom_num());
			 
			 
			 
			return iasasdad;
	}
	
		
		//채팅초대 
				@RequestMapping(value="/invitationss", method = RequestMethod.POST)
				public @ResponseBody String invitationss(String member_name, String chatRoom_name, int member_num,Text text, HttpSession session) {
					System.out.println("오냐1");
					text.setMember_name(member_name);
					text.setChatRoom_name(chatRoom_name);
					text.setMember_num(member_num);
					text.setChat_content("");
					int chatRoom_num=(int) session.getAttribute("chatRoom_num");
					System.out.println("오냐2"+chatRoom_name);
					text.setChatRoom_num(chatRoom_num);
					System.out.println("invitationss쪽"+text.toString());
					
					int results = dao.invitation(text);
					
					
					String iasasdad = text.getChatRoom_name();
					System.out.println("invitationss?"+text.getMember_name());
					
					
					 
					return "success";
			}
		
		
	
		@RequestMapping(value="/tinsert", method = RequestMethod.POST)
		public @ResponseBody String textinsert(Text text, HttpSession session) {
			String member_name=(String)session.getAttribute("loginName");
			text.setMember_name(member_name);
			System.out.println(text.toString());
			int result = dao.TextInsert(text);
			System.out.println("tinsert?"+text.getMember_name());
			return "success";
		}
		
		//시작시 메시지 출력 불러오기.
		@RequestMapping(value="/printMessage", method = RequestMethod.POST)
		public @ResponseBody List<Text> printMessage(int chatRoom_num, HttpSession session) {
			System.out.println("chat num" + chatRoom_num);
				
			List<Text> text = dao.printMessage(chatRoom_num);
			
			for (Text text2 : text) {
				Member member = repo.searchMember(text2.getMember_num());
				text2.setMember_id(member.getMember_id());
			}
			
			return text;
		}
		
		@RequestMapping(value="/chatRoomM", method = RequestMethod.POST)
		public @ResponseBody List<Text> chatRoomM(Text text, String member_name, HttpSession session) {
			
			List<Text> memberList = dao.MList(member_name);
			/*String member_name=(String)session.getAttribute("loginName");
			text.setMember_name(member_name);
			System.out.println(text.toString());
			int result = dao.TextInsert(text);
			System.out.println("체팅안에있는 아이디랄까?"+text.getMember_name());*/
		
			return memberList;
		}
		
		//채팅에있는 맴버list 전부 출력하기 
		@RequestMapping(value="/InMemberList", method = RequestMethod.POST)
		public @ResponseBody List<Text> InMemberList(int chatRoom_num, HttpSession session) {
			System.out.println("채팅방안에 chat num" + chatRoom_num);
				
			List<Text> text = dao.InMemberList(chatRoom_num);
			return text;
		} 
}
