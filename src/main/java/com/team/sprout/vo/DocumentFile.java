package com.team.sprout.vo;

public class DocumentFile {
	private int document_file_num;
	private String document_file_originalFileName;
	private String document_file_location;
	private String document_file_extension;
	
	public DocumentFile() {
		
	}

	public DocumentFile(int document_file_num, String document_file_originalFileName, String document_file_location,
			String document_file_extension) {
		
		this.document_file_num = document_file_num;
		this.document_file_originalFileName = document_file_originalFileName;
		this.document_file_location = document_file_location;
		this.document_file_extension = document_file_extension;
	}

	public int getDocument_file_num() {
		return document_file_num;
	}

	public void setDocument_file_num(int document_file_num) {
		this.document_file_num = document_file_num;
	}

	public String getDocument_file_originalFileName() {
		return document_file_originalFileName;
	}

	public void setDocument_file_originalFileName(String document_file_originalFileName) {
		this.document_file_originalFileName = document_file_originalFileName;
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

	@Override
	public String toString() {
		return "DocumentFile [document_file_num=" + document_file_num + ", document_file_originalFileName="
				+ document_file_originalFileName + ", document_file_location=" + document_file_location
				+ ", document_file_extension=" + document_file_extension + "]";
	}
	
}
