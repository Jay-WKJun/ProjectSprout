package com.team.sprout.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.team.sprout.vo.MainProject;
import com.team.sprout.vo.Member;
import com.team.sprout.vo.Notice;
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

	//프로젝트의 리더를 찾기 위한 메서드 
	public List<ProjectMember> findManager(String mainproject_projectnum ) {
		  ProjectMemberDAO dao = session.getMapper(ProjectMemberDAO.class);

		  List<ProjectMember> projectMembers = dao.findManager(mainproject_projectnum);

		  
		  return projectMembers;
	}


	public List<MainProject> findProjectName(String mainproject_projectnum) {
		 ProjectMemberDAO dao = session.getMapper(ProjectMemberDAO.class);
		 List<MainProject> result = dao.findProjectName(mainproject_projectnum);
		 return result;
		
	}

	//이미 프로젝트에 있는 사람 
	public ProjectMember findAlreadynum(int member_num) {
		ProjectMemberDAO dao = session.getMapper(ProjectMemberDAO.class);
		ProjectMember result = dao.findAlreadynum(member_num);
		
		
		return result;
		
		
	}


	public List<ProjectMember> findInProjectNum(String mainproject_projectnum) {
		 ProjectMemberDAO dao = session.getMapper(ProjectMemberDAO.class);
		 List<ProjectMember> result = dao.findInProjectNum(mainproject_projectnum);
		 return result;
	}


	public int registNotice(Notice notice) {

		ProjectMemberDAO dao = session.getMapper(ProjectMemberDAO.class);
		int result = dao.registNotice(notice);
		return result;
	}

	//노티스 리스트 출력 
	public List<Notice> noticeList(String mainProject_ProjectNum) {
		ProjectMemberDAO dao = session.getMapper(ProjectMemberDAO.class);
		 List<Notice> result = dao.noticeList(mainProject_ProjectNum);
		
		return result;
	}


	public int kickMember(ProjectMember member) {
		ProjectMemberDAO dao = session.getMapper(ProjectMemberDAO.class);
		int result = dao.kickMember(member);
		
		return result;
	}



	

}
