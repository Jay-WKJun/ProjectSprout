package com.team.sprout.dao;




import java.util.List;

import com.team.sprout.vo.MainProject;

public interface MainProjectDAO {
	
	public int mainProjectRegist(MainProject project);
	
	public List<MainProject> projectList(int num);
}
