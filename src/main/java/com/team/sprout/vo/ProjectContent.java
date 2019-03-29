package com.team.sprout.vo;

public class ProjectContent {
	private int projectContent_num;
	private String projectContent_title;
	private String projectContent_content;
	private int projectContent_status;
	private String projectContent_startDate;
	private String projectContent_endDate;
	private String projectContent_color;
	private String mainproject_projectNum;
	private int member_num;
	
	
	public ProjectContent() {
		
	}


	public ProjectContent(int projectContent_num, String projectContent_title, String projectContent_content,
			int projectContent_status, String projectContent_startDate, String projectContent_endDate,
			String projectContent_color, String mainproject_projectNum, int member_num) {
		
		this.projectContent_num = projectContent_num;
		this.projectContent_title = projectContent_title;
		this.projectContent_content = projectContent_content;
		this.projectContent_status = projectContent_status;
		this.projectContent_startDate = projectContent_startDate;
		this.projectContent_endDate = projectContent_endDate;
		this.projectContent_color = projectContent_color;
		this.mainproject_projectNum = mainproject_projectNum;
		this.member_num = member_num;
	}


	public int getProjectContent_num() {
		return projectContent_num;
	}


	public void setProjectContent_num(int projectContent_num) {
		this.projectContent_num = projectContent_num;
	}


	public String getProjectContent_title() {
		return projectContent_title;
	}


	public void setProjectContent_title(String projectContent_title) {
		this.projectContent_title = projectContent_title;
	}


	public String getProjectContent_content() {
		return projectContent_content;
	}


	public void setProjectContent_content(String projectContent_content) {
		this.projectContent_content = projectContent_content;
	}


	public int getProjectContent_status() {
		return projectContent_status;
	}


	public void setProjectContent_status(int projectContent_status) {
		this.projectContent_status = projectContent_status;
	}


	public String getProjectContent_startDate() {
		return projectContent_startDate;
	}


	public void setProjectContent_startDate(String projectContent_startDate) {
		this.projectContent_startDate = projectContent_startDate;
	}


	public String getProjectContent_endDate() {
		return projectContent_endDate;
	}


	public void setProjectContent_endDate(String projectContent_endDate) {
		this.projectContent_endDate = projectContent_endDate;
	}


	public String getProjectContent_color() {
		return projectContent_color;
	}


	public void setProjectContent_color(String projectContent_color) {
		this.projectContent_color = projectContent_color;
	}


	public String getMainproject_projectNum() {
		return mainproject_projectNum;
	}


	public void setMainproject_projectNum(String mainproject_projectNum) {
		this.mainproject_projectNum = mainproject_projectNum;
	}


	public int getMember_num() {
		return member_num;
	}


	public void setMember_num(int member_num) {
		this.member_num = member_num;
	}


	@Override
	public String toString() {
		return "ProjectContent [projectContent_num=" + projectContent_num + ", projectContent_title="
				+ projectContent_title + ", projectContent_content=" + projectContent_content
				+ ", projectContent_status=" + projectContent_status + ", projectContent_startDate="
				+ projectContent_startDate + ", projectContent_endDate=" + projectContent_endDate
				+ ", projectContent_color=" + projectContent_color + ", mainproject_projectNum="
				+ mainproject_projectNum + ", member_num=" + member_num + "]";
	}
	
}
