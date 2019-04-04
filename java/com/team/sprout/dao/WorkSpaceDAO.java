package com.team.sprout.dao;

import java.util.List;

import com.team.sprout.vo.Postit;

public interface WorkSpaceDAO {

	public int addPostit(Postit postit);

	public int getPostitNum();

	public List<Postit> getPostitList(String MainProject_ProjectNum);

	public int postitPositionUpdate(Postit postit);

	public int deletePostit(int postit_num);

	public int postitContentSave(Postit postit);
	
}
