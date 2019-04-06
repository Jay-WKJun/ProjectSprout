package com.team.sprout.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.team.sprout.vo.WantedBoard;


@Repository
public class WantedBoardRepository {

	@Autowired
	SqlSession session;
	
	public List<WantedBoard> selectAll(String searchItem, String searchWord, int startRecord, int countPerPage) {
		WantedBoardDAO dao = session.getMapper(WantedBoardDAO.class);
		
		// offset : 얼마만큼 떨어져 있냐? // limit : 몇개?
		RowBounds rb = new RowBounds(startRecord, countPerPage);

		Map<String, String> map = new HashMap<>(); // VO를 하나 더 만들지 않기 위해서.
		map.put("searchItem", searchItem);
		map.put("searchWord", searchWord);

		List<WantedBoard> result = dao.selectAll(map, rb);
		
		return result;
	}

	public WantedBoard selectOneBoard(String string) {
		WantedBoardDAO dao = session.getMapper(WantedBoardDAO.class);
		WantedBoard result = dao.selectOneBoard(string);
		return result;
	}

	public int insertBoard(WantedBoard wantedBoard) {
		WantedBoardDAO dao = session.getMapper(WantedBoardDAO.class);
		int result = dao.insertBoard(wantedBoard);
		return result;
	}

	public int deleteBoard(String wantedBoard_title) {
		WantedBoardDAO dao = session.getMapper(WantedBoardDAO.class);
		int result = dao.deleteBoard(wantedBoard_title);
		return result;
	}

	public int totalBoardCount(String searchItem, String searchWord) {
		WantedBoardDAO dao = session.getMapper(WantedBoardDAO.class);
		Map<String, String> map = new HashMap<>();
		map.put("searchItem", searchItem);
		map.put("searchWord", searchWord);
		int boardCount = dao.totalBoardCount(map);
		
		return boardCount;
	}

}
