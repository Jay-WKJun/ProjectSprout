package com.team.sprout.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.team.sprout.vo.ChatRoom;
import com.team.sprout.vo.StompUser;


@Repository
public class StompDAO {
	@Autowired
	private SqlSession sqlsession;
	
	public StompUser selectUser(String userId){
		StompMapper mapper = sqlsession.getMapper(StompMapper.class);
		StompUser user = null;
		
		try{
			user = mapper.selectUser(userId);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return user;
	}

	public int insertRoomnum(ChatRoom cr) {
		StompMapper mapper = sqlsession.getMapper(StompMapper.class);
		int result = mapper.insertcr(cr);
		return result;
	}


	
}
