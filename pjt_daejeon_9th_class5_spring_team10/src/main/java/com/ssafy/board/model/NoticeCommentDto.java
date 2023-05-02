package com.ssafy.board.model;

public class NoticeCommentDto {
	private int boardId;
	private int userId;
	private String content;
	public NoticeCommentDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "NoticeCommentDto [boardId=" + boardId + ", userId=" + userId + ", content=" + content + "]";
	}
	
	
}
