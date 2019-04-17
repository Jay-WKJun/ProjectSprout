package com.team.sprout.dao;

import java.util.List;

import com.team.sprout.vo.DocumentBoard;
import com.team.sprout.vo.DocumentFile;
import com.team.sprout.vo.DocumentFolder;

public interface DocumentFileManagerDAO {

	public int insertDocumentBoard(DocumentBoard documentBoard);

	public DocumentBoard selectBoardNum(String board_title);

	public int insertDocumentFolder(DocumentFolder documentFolder);

	public int insertDocumentFile(DocumentFile documentFile);

	public DocumentFolder selectFolderNum(String folder_title);

	public List<DocumentFolder> selectfolderList();

	public List<DocumentBoard> selectSameFolderBoardList(int folder_num);

	public DocumentFolder selectFolderObject(String folder_name);

}
