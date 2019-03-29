package com.team.sprout.dao;

import java.util.List;

import com.team.sprout.vo.ProjectMember;

public interface ProjectMemberDAO {
	
	public int ProjectMemberRegist(ProjectMember prMember);

	public List<ProjectMember> projectmemberSelectAll(String mainProjectNum);

}
