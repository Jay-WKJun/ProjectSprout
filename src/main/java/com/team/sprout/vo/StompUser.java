package com.team.sprout.vo;

public class StompUser {
	private String member_num;
	private String stomp_id;
	private String stomp_pw;
	private String stomp_nm;
	public StompUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StompUser(String member_num, String stomp_id, String stomp_pw, String stomp_nm) {
		super();
		this.member_num = member_num;
		this.stomp_id = stomp_id;
		this.stomp_pw = stomp_pw;
		this.stomp_nm = stomp_nm;
	}
	public String getMember_num() {
		return member_num;
	}
	public void setMember_num(String member_num) {
		this.member_num = member_num;
	}
	public String getStomp_id() {
		return stomp_id;
	}
	public void setStomp_id(String stomp_id) {
		this.stomp_id = stomp_id;
	}
	public String getStomp_pw() {
		return stomp_pw;
	}
	public void setStomp_pw(String stomp_pw) {
		this.stomp_pw = stomp_pw;
	}
	public String getStomp_nm() {
		return stomp_nm;
	}
	public void setStomp_nm(String stomp_nm) {
		this.stomp_nm = stomp_nm;
	}
	@Override
	public String toString() {
		return "StompUser [member_num=" + member_num + ", stomp_id=" + stomp_id + ", stomp_pw=" + stomp_pw
				+ ", stomp_nm=" + stomp_nm + "]";
	}
	
}
