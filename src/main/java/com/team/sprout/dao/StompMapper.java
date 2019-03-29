package com.team.sprout.dao;

import com.team.sprout.vo.ChatRoom;
import com.team.sprout.vo.StompUser;

public interface StompMapper {
	
	public StompUser selectUser(String userId);

	public int insertcr(ChatRoom cr);


}
