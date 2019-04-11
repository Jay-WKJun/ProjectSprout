package com.team.sprout.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DocumentFileManagerRepository {
	
	@Autowired
	SqlSession session;
	
	//DocumentFileManagerDAO dao=session.getMapper(DocumentFileManagerDAO.class);
}
