package com.team.sprout.dao;

import java.util.List;
import java.util.Map;

import com.team.sprout.vo.MainProject;
import com.team.sprout.vo.Member;
import com.team.sprout.vo.Notice;
import com.team.sprout.vo.ProjectMember;

public interface ProjectMemberDAO {
	
	public int ProjectMemberRegist(ProjectMember prMember);


	public List<Member> projectmemberSelectAll(String mainProjectNum);


	public List<ProjectMember> findManager(String mainproject_projectnum);


	public List<MainProject> findProjectName(String mainproject_projectnum);


	public ProjectMember findAlreadynum(int member_num);


	public List<ProjectMember> findInProjectNum(String mainproject_projectnum);


	public int registNotice(Notice notice);


	public List<Notice> noticeList(String mainProject_ProjectNum);


	public int kickMember(ProjectMember member);
	


}
