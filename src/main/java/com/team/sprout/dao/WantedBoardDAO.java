package com.team.sprout.dao;

import java.util.List;

import com.team.sprout.vo.WantedBoard;

public interface WantedBoardDAO {

	public List<WantedBoard> selectAll();

	public WantedBoard selectOneBoard(String string);

}
