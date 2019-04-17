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
	
	public List<WantedBoard> selectAll_crawlling(String searchItem, String searchWord, int startRecord, int countPerPage) {
		WantedBoardDAO dao = session.getMapper(WantedBoardDAO.class);
		
		// offset : 얼마만큼 떨어져 있냐? // limit : 몇개?
		RowBounds rb = new RowBounds(startRecord, countPerPage);

		Map<String, String> map = new HashMap<>(); // VO를 하나 더 만들지 않기 위해서.
		map.put("searchItem", searchItem);
		map.put("searchWord", searchWord);

		List<WantedBoard> result = dao.selectAll_crawlling(map, rb);
		
		return result;
	}

	public List<WantedBoard> selectAll_DB() {
		WantedBoardDAO dao = session.getMapper(WantedBoardDAO.class);
		
		List<WantedBoard> result = dao.selectAll_DB();
		
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

	public int insertBoard_directly(WantedBoard board) {
		WantedBoardDAO dao = session.getMapper(WantedBoardDAO.class);
		dao.insertBoarddirectly(board);
		int result = dao.select_last_seq(); // 공고글이나 지원글의 sequence 잡아주기.
		
		return result;
	}

	public WantedBoard selectOneBoard_by_id(String wantedBoard_num) {
		WantedBoardDAO dao = session.getMapper(WantedBoardDAO.class);
		WantedBoard info = dao.selectOneBoard_by_id(wantedBoard_num);
		return info;
	}

	public void deleteBoard_by_num(String the_wanted) {
		WantedBoardDAO dao = session.getMapper(WantedBoardDAO.class);
		int result = dao.deleteOneBoard_by_id(the_wanted);
		
	}

	public void update_ApplyBorad(WantedBoard wanb) {
		WantedBoardDAO dao = session.getMapper(WantedBoardDAO.class);
		int result = dao.update_ApplyBorad(wanb);
		
	}

}
