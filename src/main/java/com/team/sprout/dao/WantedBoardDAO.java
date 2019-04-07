package com.team.sprout.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.team.sprout.vo.WantedBoard;

public interface WantedBoardDAO {

	public List<WantedBoard> selectAll(Map<String, String> map, RowBounds rb);

	public WantedBoard selectOneBoard(String string);

	public int insertBoard(WantedBoard wantedBoard);

	public int deleteBoard(String wantedBoard_title);

	public int totalBoardCount(Map<String, String> map);

}
