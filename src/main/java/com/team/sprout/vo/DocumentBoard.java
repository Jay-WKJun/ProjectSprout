package com.team.sprout.vo;

public class DocumentBoard {
	private int document_board_num;
	private String document_board_title;
	private String document_board_content;
	private String document_file_originalfile;
	private String document_file_location;
	private String doucument_file_extension;
	private String document_file_date;
	
	public DocumentBoard() {
		
	}

	public DocumentBoard(int document_board_num, String document_board_title, String document_board_content,
			String document_file_originalfile, String document_file_location, String doucument_file_extension,
			String document_file_date) {
		this.document_board_num = document_board_num;
		this.document_board_title = document_board_title;
		this.document_board_content = document_board_content;
		this.document_file_originalfile = document_file_originalfile;
		this.document_file_location = document_file_location;
		this.doucument_file_extension = doucument_file_extension;
		this.document_file_date = document_file_date;
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

	public String getDocument_file_originalfile() {
		return document_file_originalfile;
	}

	public void setDocument_file_originalfile(String document_file_originalfile) {
		this.document_file_originalfile = document_file_originalfile;
	}

	public String getDocument_file_location() {
		return document_file_location;
	}

	public void setDocument_file_location(String document_file_location) {
		this.document_file_location = document_file_location;
	}

	public String getDoucument_file_extension() {
		return doucument_file_extension;
	}

	public void setDoucument_file_extension(String doucument_file_extension) {
		this.doucument_file_extension = doucument_file_extension;
	}

	public String getDocument_file_date() {
		return document_file_date;
	}

	public void setDocument_file_date(String document_file_date) {
		this.document_file_date = document_file_date;
	}

	@Override
	public String toString() {
		return "DocumentBoard [document_board_num=" + document_board_num + ", document_board_title="
				+ document_board_title + ", document_board_content=" + document_board_content
				+ ", document_file_originalfile=" + document_file_originalfile + ", document_file_location="
				+ document_file_location + ", doucument_file_extension=" + doucument_file_extension
				+ ", document_file_date=" + document_file_date + "]";
	}
	
}
