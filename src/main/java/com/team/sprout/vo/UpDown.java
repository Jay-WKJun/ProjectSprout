package com.team.sprout.vo;

public class UpDown {
	private int updown_num;
	private String originalfile;
	private String savedfile;
	public UpDown() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UpDown(int updown_num, String originalfile, String savedfile) {
		super();
		this.updown_num = updown_num;
		this.originalfile = originalfile;
		this.savedfile = savedfile;
	}
	public int getUpdown_num() {
		return updown_num;
	}
	public void setUpdown_num(int updown_num) {
		this.updown_num = updown_num;
	}
	public String getOriginalfile() {
		return originalfile;
	}
	public void setOriginalfile(String originalfile) {
		this.originalfile = originalfile;
	}
	public String getSavedfile() {
		return savedfile;
	}
	public void setSavedfile(String savedfile) {
		this.savedfile = savedfile;
	}
	@Override
	public String toString() {
		return "UpDown [updown_num=" + updown_num + ", originalfile=" + originalfile + ", savedfile=" + savedfile + "]";
	}
	
}
