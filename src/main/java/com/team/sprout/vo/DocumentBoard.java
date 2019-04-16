package com.team.sprout.vo;

public class DocumentBoard {
	private int document_board_num;
	private String document_board_title;
	private String document_board_content;
	private int document_folder_num;
	
	public DocumentBoard() {
		
	}

	public DocumentBoard(int document_board_num, String document_board_title, String document_board_content,
			int document_folder_num) {
		super();
		this.document_board_num = document_board_num;
		this.document_board_title = document_board_title;
		this.document_board_content = document_board_content;
		this.document_folder_num = document_folder_num;
	}

	public int getDocument_board_num() {
		return document_board_num;
	}

	public void setDocument_board_num(int document_board_num) {
		this.document_board_num = document_board_num;
	}

	public String getDocument_board_title() {
		return document_board_title;
	}

	public void setDocument_board_title(String document_board_title) {
		this.document_board_title = document_board_title;
	}

	public String getDocument_board_content() {
		return document_board_content;
	}

	public void setDocument_board_content(String document_board_content) {
		this.document_board_content = document_board_content;
	}

	public int getDocument_folder_num() {
		return document_folder_num;
	}

	public void setDocument_folder_num(int document_folder_num) {
		this.document_folder_num = document_folder_num;
	}

	@Override
	public String toString() {
		return "DocumentBoard [document_board_num=" + document_board_num + ", document_board_title="
				+ document_board_title + ", document_board_content=" + document_board_content + ", document_folder_num="
				+ document_folder_num + "]";
	}

}
