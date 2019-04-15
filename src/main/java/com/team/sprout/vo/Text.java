package com.team.sprout.vo;

public class Text {
	private int chat_num;
	private String chat_date;
	private String chat_content;
	private int chatRoom_num;
	private int member_num;
	private String member_name;
	private String  chatRoom_name;
	public Text() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Text(int chat_num, String chat_date, String chat_content, int chatRoom_num, int member_num,
			String member_name, String chatRoom_name) {
		super();
		this.chat_num = chat_num;
		this.chat_date = chat_date;
		this.chat_content = chat_content;
		this.chatRoom_num = chatRoom_num;
		this.member_num = member_num;
		this.member_name = member_name;
		this.chatRoom_name = chatRoom_name;
	}
	public int getChat_num() {
		return chat_num;
	}
	public void setChat_num(int chat_num) {
		this.chat_num = chat_num;
	}
	public String getChat_date() {
		return chat_date;
	}
	public void setChat_date(String chat_date) {
		this.chat_date = chat_date;
	}
	public String getChat_content() {
		return chat_content;
	}
	public void setChat_content(String chat_content) {
		this.chat_content = chat_content;
	}
	public int getChatRoom_num() {
		return chatRoom_num;
	}
	public void setChatRoom_num(int chatRoom_num) {
		this.chatRoom_num = chatRoom_num;
	}
	public int getMember_num() {
		return member_num;
	}
	public void setMember_num(int member_num) {
		this.member_num = member_num;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getChatRoom_name() {
		return chatRoom_name;
	}
	public void setChatRoom_name(String chatRoom_name) {
		this.chatRoom_name = chatRoom_name;
	}
	@Override
	public String toString() {
		return "Text [chat_num=" + chat_num + ", chat_date=" + chat_date + ", chat_content=" + chat_content
				+ ", chatRoom_num=" + chatRoom_num + ", member_num=" + member_num + ", member_name=" + member_name
				+ ", chatRoom_name=" + chatRoom_name + "]";
	}
	
}
