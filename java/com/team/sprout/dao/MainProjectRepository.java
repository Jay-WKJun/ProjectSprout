package com.team.sprout.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.team.sprout.vo.MainProject;
import com.team.sprout.vo.Member;

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


	public MainProject forgoproject(String mainproject_projectnum) {
		// 
	MainProject result = null;
		MainProjectDAO dao = session.getMapper(MainProjectDAO.class);
		result = dao.forgoproject(mainproject_projectnum);
		return result;
	}


	public Member formembername(int member_num) {
		Member result = null;
		MainProjectDAO dao = session.getMapper(MainProjectDAO.class);
		result = dao.formembername(member_num);
				
		return result;
	}

}
