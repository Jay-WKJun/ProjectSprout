package com.team.sprout.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.team.sprout.vo.WantedBoard;

public interface WantedBoardDAO {

	public List<WantedBoard> selectAll_crawlling(Map<String, String> map, RowBounds rb);

	public List<WantedBoard> selectAll_DB();

	public WantedBoard selectOneBoard(String string);

	public int insertBoard(WantedBoard wantedBoard); // 공고글 클롤링으로 입력

	public int deleteBoard(String wantedBoard_title);

	public int totalBoardCount(Map<String, String> map);

	public int insertBoarddirectly(WantedBoard board); // 공고글 직접 입력


}
