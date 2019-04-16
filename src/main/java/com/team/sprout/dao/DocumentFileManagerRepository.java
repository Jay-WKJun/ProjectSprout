package com.team.sprout.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.team.sprout.vo.DocumentBoard;
import com.team.sprout.vo.DocumentFile;
import com.team.sprout.vo.DocumentFolder;

@Repository
public class DocumentFileManagerRepository {
	
	@Autowired
	SqlSession session;
	
	public int insertDocumentFile(DocumentFile documentFile) {
		DocumentFileManagerDAO dao=session.getMapper(DocumentFileManagerDAO.class);
		int result = dao.insertDocumentFile(documentFile);
		return result;
		
	}
	
	public DocumentFolder selectFolderNum(String folder_title) {
		DocumentFileManagerDAO dao=session.getMapper(DocumentFileManagerDAO.class);
		DocumentFolder result = dao.selectFolderNum(folder_title);
		return result;
	}

	public int insertDocumentBoard(DocumentBoard documentBoard) {
		DocumentFileManagerDAO dao=session.getMapper(DocumentFileManagerDAO.class);
		int result = dao.insertDocumentBoard(documentBoard);
		return result;
		
	}

	public DocumentBoard selectBoardNum(String board_title) {
		DocumentFileManagerDAO dao=session.getMapper(DocumentFileManagerDAO.class);
		DocumentBoard result = dao.selectBoardNum(board_title);
		return result;
	}

	public int insertDocumentFolder(DocumentFolder documentFolder) {
		DocumentFileManagerDAO dao=session.getMapper(DocumentFileManagerDAO.class);
		int result = dao.insertDocumentFolder(documentFolder);
		return result;
	}
	
	//DocumentFileManagerDAO dao=session.getMapper(DocumentFileManagerDAO.class);
}
