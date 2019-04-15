package com.team.sprout.vo;

public class Member {
	private int member_num;
	private String member_id;
	private String member_password;
	private String member_name;
	private int member_phone;
	private String member_address;
	private String member_loginDate;
	private String memberImage_saveAddress;
	private String member_join_date;
	private String member_authkey;
	
	public Member() {
	}

	public Member(int member_num, String member_id, String member_password, String member_name, int member_phone,
			String member_address, String member_loginDate, String memberImage_saveAddress, String member_join_date,
			String member_authkey) {
		this.member_num = member_num;
		this.member_id = member_id;
		this.member_password = member_password;
		this.member_name = member_name;
		this.member_phone = member_phone;
		this.member_address = member_address;
		this.member_loginDate = member_loginDate;
		this.memberImage_saveAddress = memberImage_saveAddress;
		this.member_join_date = member_join_date;
		this.member_authkey = member_authkey;
	}

	public int getMember_num() {
		return member_num;
	}

	public void setMember_num(int member_num) {
		this.member_num = member_num;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getMember_password() {
		return member_password;
	}

	public void setMember_password(String member_password) {
		this.member_password = member_password;
	}

	public String getMember_name() {
		return member_name;
	}

	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}

	public int getMember_phone() {
		return member_phone;
	}

	public void setMember_phone(int member_phone) {
		this.member_phone = member_phone;
	}

	public String getMember_address() {
		return member_address;
	}

	public void setMember_address(String member_address) {
		this.member_address = member_address;
	}

	public String getMember_loginDate() {
		return member_loginDate;
	}

	public void setMember_loginDate(String member_loginDate) {
		this.member_loginDate = member_loginDate;
	}

	public String getMemberImage_saveAddress() {
		return memberImage_saveAddress;
	}

	public void setMemberImage_saveAddress(String memberImage_saveAddress) {
		this.memberImage_saveAddress = memberImage_saveAddress;
	}

	public String getMember_join_date() {
		return member_join_date;
	}

	public void setMember_join_date(String member_join_date) {
		this.member_join_date = member_join_date;
	}

	public String getMember_authkey() {
		return member_authkey;
	}

	public void setMember_authkey(String member_authkey) {
		this.member_authkey = member_authkey;
	}

	@Override
	public String toString() {
		return "Member [member_num=" + member_num + ", member_id=" + member_id + ", member_password=" + member_password
				+ ", member_name=" + member_name + ", member_phone=" + member_phone + ", member_address="
				+ member_address + ", member_loginDate=" + member_loginDate + ", memberImage_saveAddress="
				+ memberImage_saveAddress + ", member_join_date=" + member_join_date + ", member_authkey="
				+ member_authkey + "]";
	}

}