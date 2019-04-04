package com.team.sprout.vo;

import java.time.LocalDateTime;

public class Message {
	private String id;
	private String username;
	private String message;
	private LocalDateTime chatdate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public LocalDateTime getChatdate() {
		return chatdate;
	}
	public void setChatdate(LocalDateTime chatdate) {
		this.chatdate = chatdate;
	}
	@Override
	public String toString() {
		return "Message [id=" + id + ", username=" + username + ", message=" + message + ", chatdate=" + chatdate + "]";
	}
	
}
