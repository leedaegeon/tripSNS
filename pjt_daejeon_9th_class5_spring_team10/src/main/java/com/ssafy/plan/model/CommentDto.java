package com.ssafy.plan.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "CommentDto : 플랜 게시판의 댓글")
public class CommentDto {

	@ApiModelProperty(value = "댓글 번호")
	private int commentNo;
	@ApiModelProperty(value = "작성자 아이디")
	private String userId;
	@ApiModelProperty(value = "댓글 내용")
	private String commentContent;
	@ApiModelProperty(value = "작성시각")
	private String commentDate;
	@ApiModelProperty(value = "플랜 번호")
	private String planId;

	public int getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public String getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	@Override
	public String toString() {
		return "CommentDto [commentNo=" + commentNo + ", userId=" + userId + ", commentContent=" + commentContent
				+ ", commentDate=" + commentDate + ", planId=" + planId + "]";
	}

}
