package com.team.sprout.dao;

import java.util.List;

import com.team.sprout.vo.Text;

public interface TextMapper {

	public int TextInsert(Text text);

	public List<Text> printMessage(int chatRoom_num);
	
}
