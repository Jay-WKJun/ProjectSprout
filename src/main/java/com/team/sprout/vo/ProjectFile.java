package com.team.sprout.vo;

public class ProjectFile {

	public int projectFile_num;
	public String projectFile_originalname;
	public String projectFile_date;
	public String projectFile_location;
	public String MainProject_ProjectNum;
	public int projectFile_type;
	public String projectFile_extension;
	public String projectFile_member;

	public ProjectFile() {
	}

	public ProjectFile(int projectFile_num, String projectFile_originalname, String projectFile_date,
			String projectFile_location, String mainProject_ProjectNum, int projectFile_type,
			String projectFile_extension, String projectFile_member) {
		super();
		this.projectFile_num = projectFile_num;
		this.projectFile_originalname = projectFile_originalname;
		this.projectFile_date = projectFile_date;
		this.projectFile_location = projectFile_location;
		MainProject_ProjectNum = mainProject_ProjectNum;
		this.projectFile_type = projectFile_type;
		this.projectFile_extension = projectFile_extension;
		this.projectFile_member = projectFile_member;
	}

	public int getProjectFile_num() {
		return projectFile_num;
	}

	public void setProjectFile_num(int projectFile_num) {
		this.projectFile_num = projectFile_num;
	}

	public String getProjectFile_originalname() {
		return projectFile_originalname;
	}

	public void setProjectFile_originalname(String projectFile_originalname) {
		this.projectFile_originalname = projectFile_originalname;
	}

	public String getProjectFile_date() {
		return projectFile_date;
	}

	public void setProjectFile_date(String projectFile_date) {
		this.projectFile_date = projectFile_date;
	}

	public String getProjectFile_location() {
		return projectFile_location;
	}

	public void setProjectFile_location(String projectFile_location) {
		this.projectFile_location = projectFile_location;
	}

	public String getMainProject_ProjectNum() {
		return MainProject_ProjectNum;
	}

	public void setMainProject_ProjectNum(String mainProject_ProjectNum) {
		MainProject_ProjectNum = mainProject_ProjectNum;
	}

	public int getProjectFile_type() {
		return projectFile_type;
	}

	public void setProjectFile_type(int projectFile_type) {
		this.projectFile_type = projectFile_type;
	}

	public String getProjectFile_extension() {
		return projectFile_extension;
	}

	public void setProjectFile_extension(String projectFile_extension) {
		this.projectFile_extension = projectFile_extension;
	}

	public String getProjectFile_member() {
		return projectFile_member;
	}

	public void setProjectFile_member(String projectFile_member) {
		this.projectFile_member = projectFile_member;
	}

	@Override
	public String toString() {
		return "ProjectFile [projectFile_num=" + projectFile_num + ", projectFile_originalname="
				+ projectFile_originalname + ", projectFile_date=" + projectFile_date + ", projectFile_location="
				+ projectFile_location + ", MainProject_ProjectNum=" + MainProject_ProjectNum + ", projectFile_type="
				+ projectFile_type + ", projectFile_extension=" + projectFile_extension + ", projectFile_member="
				+ projectFile_member + "]";
	}

}
