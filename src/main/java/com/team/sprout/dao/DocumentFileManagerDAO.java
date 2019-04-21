package com.team.sprout.dao;

import com.team.sprout.vo.DocumentBoard;
import com.team.sprout.vo.DocumentFolder;

public interface DocumentFileManagerDAO {

	public int insertDocumentBoard(DocumentBoard documentBoard);

	public int selectBoardNum();

	public int insertDocumentFolder(DocumentFolder documentFolder);

}
