package com.team.sprout.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.team.sprout.vo.WantedBoard;

@Repository
public class WantedBoardRepository {

	@Autowired
	SqlSession session;
	
	public List<WantedBoard> selectAll() {
		WantedBoardDAO dao = session.getMapper(WantedBoardDAO.class);
		List<WantedBoard> result = dao.selectAll();
		return result;
	}

	public WantedBoard selectOneBoard(String string) {
		WantedBoardDAO dao = session.getMapper(WantedBoardDAO.class);
		WantedBoard result = dao.selectOneBoard(string);
		return result;
	}

}
