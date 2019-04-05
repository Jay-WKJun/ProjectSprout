
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

import com.team.sprout.dao.TextDAO;
import com.team.sprout.vo.Text;



@Controller
public class TextController {
	
	@Autowired
	TextDAO dao;
	private static final Logger logger = LoggerFactory.getLogger(TextController.class);
	
		@RequestMapping(value="/tinsert", method = RequestMethod.POST)
		public @ResponseBody String textinsert(Text text, HttpSession session) {
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
		
}
