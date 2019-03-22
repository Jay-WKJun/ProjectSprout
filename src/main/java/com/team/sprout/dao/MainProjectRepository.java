package com.team.sprout.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.team.sprout.vo.MainProject;

@Repository
public class MainProjectRepository {
	
	@Autowired
	SqlSession session;

	public int mainProjectRegist(MainProject project) {
		int result = 0;
		MainProjectDAO dao = session.getMapper(MainProjectDAO.class);
		result = dao.mainProjectRegist(project);
		return result;
		
	};
	

	public List<MainProject> projectList(int num){
		
		List<MainProject> result = null;
		MainProjectDAO dao = session.getMapper(MainProjectDAO.class);
		result = dao.projectList(num);
		System.out.println("resultttttttttttttt"+result);
		return result;
		
	}

}
