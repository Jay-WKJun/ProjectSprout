package com.team.sprout.vo;

public class Postit {
	public int postit_num;
	public String MainProject_ProjectNum;
	public int postit_top;
	public int postit_left;
	public String postit_color;
	public String postit_shape;
	public String postit_content;

	public Postit() {
	}

	public Postit(int postit_num, String mainProject_ProjectNum, int postit_top, int postit_left, String postit_color,
			String postit_shape, String postit_content) {
		super();
		this.postit_num = postit_num;
		MainProject_ProjectNum = mainProject_ProjectNum;
		this.postit_top = postit_top;
		this.postit_left = postit_left;
		this.postit_color = postit_color;
		this.postit_shape = postit_shape;
		this.postit_content = postit_content;
	}

	public int getPostit_num() {
		return postit_num;
	}

	public void setPostit_num(int postit_num) {
		this.postit_num = postit_num;
	}

	public String getMainProject_ProjectNum() {
		return MainProject_ProjectNum;
	}

	public void setMainProject_ProjectNum(String mainProject_ProjectNum) {
		MainProject_ProjectNum = mainProject_ProjectNum;
	}

	public int getPostit_top() {
		return postit_top;
	}

	public void setPostit_top(int postit_top) {
		this.postit_top = postit_top;
	}

	public int getPostit_left() {
		return postit_left;
	}

	public void setPostit_left(int postit_left) {
		this.postit_left = postit_left;
	}

	public String getPostit_color() {
		return postit_color;
	}

	public void setPostit_color(String postit_color) {
		this.postit_color = postit_color;
	}

	public String getPostit_shape() {
		return postit_shape;
	}

	public void setPostit_shape(String postit_shape) {
		this.postit_shape = postit_shape;
	}

	public String getPostit_content() {
		return postit_content;
	}

	public void setPostit_content(String postit_content) {
		this.postit_content = postit_content;
	}

	@Override
	public String toString() {
		return "Postit [postit_num=" + postit_num + ", MainProject_ProjectNum=" + MainProject_ProjectNum
				+ ", postit_top=" + postit_top + ", postit_left=" + postit_left + ", postit_color=" + postit_color
				+ ", postit_shape=" + postit_shape + ", postit_content=" + postit_content + "]";
	}

}
