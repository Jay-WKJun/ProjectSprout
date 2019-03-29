package com.team.sprout.vo;

public class ChatRoom {
	private int chatRoom_num;
	private String chatRoom_name;
	public ChatRoom() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ChatRoom(int chatRoom_num, String chatRoom_name) {
		super();
		this.chatRoom_num = chatRoom_num;
		this.chatRoom_name = chatRoom_name;
	}
	public int getChatRoom_num() {
		return chatRoom_num;
	}
	public void setChatRoom_num(int chatRoom_num) {
		this.chatRoom_num = chatRoom_num;
	}
	public String getChatRoom_name() {
		return chatRoom_name;
	}
	public void setChatRoom_name(String chatRoom_name) {
		this.chatRoom_name = chatRoom_name;
	}
	@Override
	public String toString() {
		return "ChatRoom [chatRoom_num=" + chatRoom_num + ", chatRoom_name=" + chatRoom_name + "]";
	}
	
	
}
