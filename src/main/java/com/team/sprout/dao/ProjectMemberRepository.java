package com.team.sprout.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.team.sprout.vo.ProjectMember;
@Repository
public class ProjectMemberRepository {

	
	@Autowired
	SqlSession session; 
	
	public int ProjectMemberRegist(ProjectMember prMember) {
		ProjectMemberDAO dao = session.getMapper(ProjectMemberDAO.class);
		int result = dao.ProjectMemberRegist(prMember);
		
		return result;
		
	}
}
