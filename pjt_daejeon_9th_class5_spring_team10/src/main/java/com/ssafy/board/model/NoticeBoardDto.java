package com.ssafy.board.model;

public class NoticeBoardDto {

	private int noticeNo;
	private int noticeCategory;
	private int writerId;
	private String writerName;
	private String noticeTitle;
	private String noticeContent;
	private String registerTime;
	private int likeCnt;
	public NoticeBoardDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public int getNoticeNo() {
		return noticeNo;
	}


	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}


	public int getNoticeCategory() {
		return noticeCategory;
	}


	public void setNoticeCategory(int noticeCategory) {
		this.noticeCategory = noticeCategory;
	}


	public int getWriterId() {
		return writerId;
	}


	public void setWriterId(int writerId) {
		this.writerId = writerId;
	}


	public String getWriterName() {
		return writerName;
	}


	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}


	public String getNoticeTitle() {
		return noticeTitle;
	}


	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}


	public String getNoticeContent() {
		return noticeContent;
	}


	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}


	public String getRegisterTime() {
		return registerTime;
	}


	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}


	public int getLikeCnt() {
		return likeCnt;
	}


	public void setLikeCnt(int likeCnt) {
		this.likeCnt = likeCnt;
	}


	@Override
	public String toString() {
		return "NoticeBoardDto [noticeNo=" + noticeNo + ", noticeCategory=" + noticeCategory + ", writerId=" + writerId
				+ ", writerName=" + writerName + ", noticeTitle=" + noticeTitle + ", noticeContent=" + noticeContent
				+ ", registerTime=" + registerTime + ", likeCnt=" + likeCnt + "]";
	}
	
	
}
