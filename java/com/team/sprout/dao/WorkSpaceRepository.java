package com.team.sprout.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.team.sprout.vo.Postit;

@Repository
public class WorkSpaceRepository {
	
	@Autowired
	SqlSession session;

	public int addPostit(Postit postit) {
		WorkSpaceDAO dao=session.getMapper(WorkSpaceDAO.class);
		int result=dao.addPostit(postit);
		return result;
	}

	public int getPostitNum() {
		WorkSpaceDAO dao=session.getMapper(WorkSpaceDAO.class);
		int postit_num=dao.getPostitNum();
		return postit_num;
	}

	public List<Postit> getPostitList(String MainProject_ProjectNum) {
		WorkSpaceDAO dao=session.getMapper(WorkSpaceDAO.class);
		List<Postit> postitList=dao.getPostitList(MainProject_ProjectNum);
		return postitList;
	}

	public int postitPositionUpdate(Postit postit) {
		WorkSpaceDAO dao=session.getMapper(WorkSpaceDAO.class);
		int result=dao.postitPositionUpdate(postit);
		return result;
	}

	public int deletePostit(int postit_num) {
		WorkSpaceDAO dao=session.getMapper(WorkSpaceDAO.class);
		int result=dao.deletePostit(postit_num);
		return result;
	}

	public int postitContentSave(Postit postit) {
		WorkSpaceDAO dao=session.getMapper(WorkSpaceDAO.class);
		int result=dao.postitContentSave(postit);
		return result;
	};
}
