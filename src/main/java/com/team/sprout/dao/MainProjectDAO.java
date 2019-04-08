package com.team.sprout.dao;




import java.util.List;

import com.team.sprout.vo.MainProject;
import com.team.sprout.vo.Member;

public interface MainProjectDAO {
	
	public int mainProjectRegist(MainProject project);
	
	public List<MainProject> projectList(int num);

	public MainProject forgoproject(String mainproject_projectnum);

	public Member formembername(int member_num);

	public List<MainProject> findInMember(String mainproject_projectnum);
}
