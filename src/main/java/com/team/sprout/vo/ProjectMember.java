package com.team.sprout.vo;

public class ProjectMember {

	
    private int projectMember_num;
    private String MainProject_ProjectNum; 
    private int  member_num;
    private int member_rank;
	public int getProjectMember_num() {
		return projectMember_num;
	}
	public void setProjectMember_num(int projectMember_num) {
		this.projectMember_num = projectMember_num;
	}
	public String getMainProject_ProjectNum() {
		return MainProject_ProjectNum;
	}
	public void setMainProject_ProjectNum(String mainProject_ProjectNum) {
		MainProject_ProjectNum = mainProject_ProjectNum;
	}
	public int getMember_num() {
		return member_num;
	}
	public void setMember_num(int member_num) {
		this.member_num = member_num;
	}
	public int getMember_rank() {
		return member_rank;
	}
	public void setMember_rank(int member_rank) {
		this.member_rank = member_rank;
	}
	public ProjectMember(int projectMember_num, String mainProject_ProjectNum, int member_num, int member_rank) {
		super();
		this.projectMember_num = projectMember_num;
		MainProject_ProjectNum = mainProject_ProjectNum;
		this.member_num = member_num;
		this.member_rank = member_rank;
	}
	
    
	public ProjectMember() {
		
	}
    
    
}
