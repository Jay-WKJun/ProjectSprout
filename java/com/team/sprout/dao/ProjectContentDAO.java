package com.team.sprout.dao;

import java.util.List;

import com.team.sprout.vo.ProjectContent;

public interface ProjectContentDAO {

	public int ProjectContentRegist(ProjectContent pc);

	public List<ProjectContent> projectContentSelectAll(String mainProjectNum);
}
