package com.team.sprout.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.team.sprout.vo.Text;




@Repository
public class TextDAO {

	@Autowired
	private SqlSession sqlsession;

	public int TextInsert(Text text) {
		// TODO Auto-generated method stub
		TextMapper mapper = sqlsession.getMapper(TextMapper.class);
		int result = mapper.TextInsert(text);
		System.out.println("text 내용 : " +text.toString());
		return result;
	}

	public List<Text> printMessage(int chatRoom_num) {
		// TODO Auto-generated method stub
		TextMapper mapper = sqlsession.getMapper(TextMapper.class);
		List<Text> text  = mapper.printMessage(chatRoom_num);
		return text;
	}

	public List<Text> MList(String member_name) {
		TextMapper mapper = sqlsession.getMapper(TextMapper.class);
		List<Text> memberList  = mapper.MList(member_name);
		return memberList;
	}

	public int insertChatRoomName(Text text) {
		// TODO Auto-generated method stub
		TextMapper mapper = sqlsession.getMapper(TextMapper.class);
		int memberList  = mapper.insertChatRoomName(text);
		return memberList;
	}

	public int selectchatRoomNum() {
		// TODO Auto-generated method stub
		TextMapper mapper = sqlsession.getMapper(TextMapper.class);
		int result  = mapper.selectchatRoomNum();
		
		return result;
	}

	public int invitation(Text text) {
		// TODO Auto-generated method stub
		TextMapper mapper = sqlsession.getMapper(TextMapper.class);
		int results  = mapper.invitation(text);
		return results;
	}
	

	
	
}
