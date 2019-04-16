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
	
	public int selectFileNum() {
		DocumentFileManagerDAO dao=session.getMapper(DocumentFileManagerDAO.class);
		int result = dao.selectFileNum();
		return result;
	}

	public int insertDocumentBoard(DocumentBoard documentBoard) {
		DocumentFileManagerDAO dao=session.getMapper(DocumentFileManagerDAO.class);
		int result = dao.insertDocumentBoard(documentBoard);
		return result;
		
	}

	public int selectBoardNum() {
		DocumentFileManagerDAO dao=session.getMapper(DocumentFileManagerDAO.class);
		int result = dao.selectBoardNum();
		return result;
	}

	public int insertDocumentFolder(DocumentFolder documentFolder) {
		DocumentFileManagerDAO dao=session.getMapper(DocumentFileManagerDAO.class);
		int result = dao.insertDocumentFolder(documentFolder);
		return result;
	}
	
	//DocumentFileManagerDAO dao=session.getMapper(DocumentFileManagerDAO.class);
}
