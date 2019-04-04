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
		return result;
	}

	public List<Text> printMessage(int chatRoom_num) {
		// TODO Auto-generated method stub
		TextMapper mapper = sqlsession.getMapper(TextMapper.class);
		List<Text> text  = mapper.printMessage(chatRoom_num);
		return text;
	}
	

	
	
}
