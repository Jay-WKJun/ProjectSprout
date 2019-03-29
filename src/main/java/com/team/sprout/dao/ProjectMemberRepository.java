package com.team.sprout.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.team.sprout.vo.Member;
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
	
	/*
	  * 특정 메인프로젝트에 속해있는 사람들을 찾아옵니다. - 우경준
	  */
	 public List<Member> projectmemberSelectAll(String mainProjectNum) {
	
	  ProjectMemberDAO dao = session.getMapper(ProjectMemberDAO.class);

	  List<Member> projectMembers = dao.projectmemberSelectAll(mainProjectNum);

	  
	  return projectMembers;
	 }
	
}
