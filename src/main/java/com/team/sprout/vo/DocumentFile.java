package com.team.sprout.vo;

public class DocumentFile {
	private int document_file_num;
	private String document_file_originalfileName;
	private String document_file_location;
	private String document_file_extension;
	private String document_file_date;
	private String document_board_num;
	
	public DocumentFile() {

	}

	public DocumentFile(int document_file_num, String document_file_originalfileName, String document_file_location,
			String document_file_extension, String document_file_date, String document_board_num) {
		super();
		this.document_file_num = document_file_num;
		this.document_file_originalfileName = document_file_originalfileName;
		this.document_file_location = document_file_location;
		this.document_file_extension = document_file_extension;
		this.document_file_date = document_file_date;
		this.document_board_num = document_board_num;
	}

	public int getDocument_file_num() {
		return document_file_num;
	}

	public void setDocument_file_num(int document_file_num) {
		this.document_file_num = document_file_num;
	}

	public String getDocument_file_originalfileName() {
		return document_file_originalfileName;
	}

	public void setDocument_file_originalfileName(String document_file_originalfileName) {
		this.document_file_originalfileName = document_file_originalfileName;
	}

	public String getDocument_file_location() {
		return document_file_location;
	}

	public void setDocument_file_location(String document_file_location) {
		this.document_file_location = document_file_location;
	}

	public String getDocument_file_extension() {
		return document_file_extension;
	}

	public void setDocument_file_extension(String document_file_extension) {
		this.document_file_extension = document_file_extension;
	}

	public String getDocument_file_date() {
		return document_file_date;
	}

	public void setDocument_file_date(String document_file_date) {
		this.document_file_date = document_file_date;
	}

	public String getDocument_board_num() {
		return document_board_num;
	}

	public void setDocument_board_num(String document_board_num) {
		this.document_board_num = document_board_num;
	}

	@Override
	public String toString() {
		return "DocumentFile [document_file_num=" + document_file_num + ", document_file_originalfileName="
				+ document_file_originalfileName + ", document_file_location=" + document_file_location
				+ ", document_file_extension=" + document_file_extension + ", document_file_date=" + document_file_date
				+ ", document_board_num=" + document_board_num + "]";
	}

}
