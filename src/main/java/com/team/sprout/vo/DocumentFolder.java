package com.team.sprout.vo;

public class DocumentFolder {
	private int document_folder_num;
	private int document_board_num;
	private String mainProject_projectNum;
	private String document_folder_title;
	
	public DocumentFolder() {
	}

	public DocumentFolder(int document_folder_num, int document_board_num, String mainProject_projectNum,
			String document_folder_title) {
		super();
		this.document_folder_num = document_folder_num;
		this.document_board_num = document_board_num;
		this.mainProject_projectNum = mainProject_projectNum;
		this.document_folder_title = document_folder_title;
	}

	public int getDocument_folder_num() {
		return document_folder_num;
	}

	public void setDocument_folder_num(int document_folder_num) {
		this.document_folder_num = document_folder_num;
	}

	public int getDocument_board_num() {
		return document_board_num;
	}

	public void setDocument_board_num(int document_board_num) {
		this.document_board_num = document_board_num;
	}

	public String getMainProject_projectNum() {
		return mainProject_projectNum;
	}

	public void setMainProject_projectNum(String mainProject_projectNum) {
		this.mainProject_projectNum = mainProject_projectNum;
	}

	public String getDocument_folder_title() {
		return document_folder_title;
	}

	public void setDocument_folder_title(String document_folder_title) {
		this.document_folder_title = document_folder_title;
	}

	@Override
	public String toString() {
		return "DocumentFolder [document_folder_num=" + document_folder_num + ", document_board_num="
				+ document_board_num + ", mainProject_projectNum=" + mainProject_projectNum + ", document_folder_title="
				+ document_folder_title + "]";
	}
	
}
