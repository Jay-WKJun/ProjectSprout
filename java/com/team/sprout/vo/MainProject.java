package com.team.sprout.vo;

public class MainProject {

	private String mainproject_projectnum;
	private String mainproject_title;
	private String mainproject_memo;
	public String getMainproject_projectnum() {
		return mainproject_projectnum;
	}
	public void setMainproject_projectnum(String mainproject_projectnum) {
		this.mainproject_projectnum = mainproject_projectnum;
	}
	public String getMainproject_title() {
		return mainproject_title;
	}
	public void setMainproject_title(String mainproject_title) {
		this.mainproject_title = mainproject_title;
	}
	public String getMainproject_memo() {
		return mainproject_memo;
	}
	public void setMainproject_memo(String mainproject_memo) {
		this.mainproject_memo = mainproject_memo;
	}
	public MainProject(String mainproject_projectnum, String mainproject_title, String mainproject_memo) {
		super();
		this.mainproject_projectnum = mainproject_projectnum;
		this.mainproject_title = mainproject_title;
		this.mainproject_memo = mainproject_memo;
	}
	
	public MainProject() {
		
	}
}
