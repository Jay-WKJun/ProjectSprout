package com.team.sprout.vo;

public class WantedBoard {

	private int wantedBoard_num;
	private String wantedBoard_from;
	private String wantedBoard_title;
	private String wantedBoard_content;
	private String wantedBoard_date;
	private int wantedBoard_hitCount;
	private int applyBoard_num;
	private int wantedboard_source;

	public WantedBoard() {}

	public WantedBoard(int wantedBoard_num, String wantedBoard_from, String wantedBoard_title,
			String wantedBoard_content, String wantedBoard_date, int wantedBoard_hitCount, int applyBoard_num,
			int wantedboard_source) {
		super();
		this.wantedBoard_num = wantedBoard_num;
		this.wantedBoard_from = wantedBoard_from;
		this.wantedBoard_title = wantedBoard_title;
		this.wantedBoard_content = wantedBoard_content;
		this.wantedBoard_date = wantedBoard_date;
		this.wantedBoard_hitCount = wantedBoard_hitCount;
		this.applyBoard_num = applyBoard_num;
		this.wantedboard_source = wantedboard_source;
	}

	public int getWantedBoard_num() {
		return wantedBoard_num;
	}

	public void setWantedBoard_num(int wantedBoard_num) {
		this.wantedBoard_num = wantedBoard_num;
	}

	public String getWantedBoard_from() {
		return wantedBoard_from;
	}

	public void setWantedBoard_from(String wantedBoard_from) {
		this.wantedBoard_from = wantedBoard_from;
	}

	public String getWantedBoard_title() {
		return wantedBoard_title;
	}

	public void setWantedBoard_title(String wantedBoard_title) {
		this.wantedBoard_title = wantedBoard_title;
	}

	public String getWantedBoard_content() {
		return wantedBoard_content;
	}

	public void setWantedBoard_content(String wantedBoard_content) {
		this.wantedBoard_content = wantedBoard_content;
	}

	public String getWantedBoard_date() {
		return wantedBoard_date;
	}

	public void setWantedBoard_date(String wantedBoard_date) {
		this.wantedBoard_date = wantedBoard_date;
	}

	public int getWantedBoard_hitCount() {
		return wantedBoard_hitCount;
	}

	public void setWantedBoard_hitCount(int wantedBoard_hitCount) {
		this.wantedBoard_hitCount = wantedBoard_hitCount;
	}

	public int getApplyBoard_num() {
		return applyBoard_num;
	}

	public void setApplyBoard_num(int applyBoard_num) {
		this.applyBoard_num = applyBoard_num;
	}

	public int getWantedboard_source() {
		return wantedboard_source;
	}

	public void setWantedboard_source(int wantedboard_source) {
		this.wantedboard_source = wantedboard_source;
	}

	@Override
	public String toString() {
		return "WantedBoard [wantedBoard_num=" + wantedBoard_num + ", wantedBoard_from=" + wantedBoard_from
				+ ", wantedBoard_title=" + wantedBoard_title + ", wantedBoard_content=" + wantedBoard_content
				+ ", wantedBoard_date=" + wantedBoard_date + ", wantedBoard_hitCount=" + wantedBoard_hitCount
				+ ", applyBoard_num=" + applyBoard_num + ", wantedboard_source=" + wantedboard_source + "]";
	}
	
	
}
