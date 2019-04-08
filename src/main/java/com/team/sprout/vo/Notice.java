package com.team.sprout.vo;

public class Notice {

	
	 // notice_num 공지사항 시퀀스
    private int notice_num;

    // notice_content 공지사항 내용
    private String notice_content;

    // notice_date 공지사항 날짜
    private String notice_Date;

    // MainProject_ProjectNum 프로젝트 시퀀스
    private String MainProject_ProjectNum;

    // memberRank_num 회원 시퀀스
    private int memberRank_num;
    

	public Notice() {
		
	}

	public Notice(int notice_num, String notice_content, String notice_Date, String mainProject_ProjectNum,
			int memberRank_num) {
		super();
		this.notice_num = notice_num;
		this.notice_content = notice_content;
		this.notice_Date = notice_Date;
		MainProject_ProjectNum = mainProject_ProjectNum;
		this.memberRank_num = memberRank_num;
	}

	public int getNotice_num() {
		return notice_num;
	}

	public void setNotice_num(int notice_num) {
		this.notice_num = notice_num;
	}

	public String getNotice_content() {
		return notice_content;
	}

	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}

	public String getNotice_Date() {
		return notice_Date;
	}

	public void setNotice_Date(String notice_Date) {
		this.notice_Date = notice_Date;
	}

	public String getMainProject_ProjectNum() {
		return MainProject_ProjectNum;
	}

	public void setMainProject_ProjectNum(String mainProject_ProjectNum) {
		MainProject_ProjectNum = mainProject_ProjectNum;
	}

	public int getMemberRank_num() {
		return memberRank_num;
	}

	public void setMemberRank_num(int memberRank_num) {
		this.memberRank_num = memberRank_num;
	}

	@Override
	public String toString() {
		return "Notice [notice_num=" + notice_num + ", notice_content=" + notice_content + ", notice_Date="
				+ notice_Date + ", MainProject_ProjectNum=" + MainProject_ProjectNum + ", memberRank_num="
				+ memberRank_num + "]";
	}

	

}
