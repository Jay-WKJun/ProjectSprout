package com.team.sprout.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.team.sprout.vo.ProjectContent;

@Repository
public class ProjectContentRepository {

	@Autowired
	SqlSession session;
	
	/*
	 * 만든 Content를 등록한다.
	 */
	public int ProjectContentRegist(ProjectContent pc) {
		ProjectContentDAO dao = session.getMapper(ProjectContentDAO.class);
		int result = dao.ProjectContentRegist(pc);
				
		return result;
		
	}
	
	/*
	 * 해당 프로젝트의 모든 프로젝트 일을 불러옵니다.
	 */
	public List<ProjectContent> projectContentSelectAll(String mainProjectNum) {
		ProjectContentDAO dao = session.getMapper(ProjectContentDAO.class);
		List<ProjectContent> listAll = dao.projectContentSelectAll(mainProjectNum);
		
		return listAll;
	}
	
	/*
	 * pc를 하나 골라온다
	 */
	public ProjectContent projectContentSelectOne(int projectContentNum) {
		ProjectContentDAO dao = session.getMapper(ProjectContentDAO.class);
		ProjectContent pc = dao.projectContentSelectOne(projectContentNum);
		
		return pc;
	}

	/*
	 * 업데이트 메소드
	 */
	public int projectContentUpdate(ProjectContent pc) {
		ProjectContentDAO dao = session.getMapper(ProjectContentDAO.class);
		int result = dao.projectContentUpdate(pc);
		return result;
	}

	public int projectContentDelete(int projectContent_num) {
		ProjectContentDAO dao = session.getMapper(ProjectContentDAO.class);
		int result = dao.projectContentDelete(projectContent_num);
		return result;
	}
}
