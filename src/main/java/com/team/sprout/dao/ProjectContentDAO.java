package com.team.sprout.dao;

import java.util.List;

import com.team.sprout.vo.ProjectContent;

public interface ProjectContentDAO {

	public int ProjectContentRegist(ProjectContent pc);

	public List<ProjectContent> projectContentSelectAll(String mainProjectNum);

	public ProjectContent projectContentSelectOne(int projectContentNum);

	public int projectContentUpdate(ProjectContent pc);

	public int projectContentDelete(int projectContent_num);
}
