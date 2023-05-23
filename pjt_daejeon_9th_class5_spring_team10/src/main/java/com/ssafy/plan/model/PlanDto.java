package com.ssafy.plan.model;

import java.util.List;
import java.util.Map;

import com.ssafy.attraction.model.AttractionDto;
import com.ssafy.plan.model.mapper.CommentMapper;

import io.swagger.annotations.ApiModel;

//plan_board_tb
@ApiModel(value = "PlanDto: 플랜게시판", description = "플랜의 상세 정보를 나타낸다")

public class PlanDto {
	private int planId;
	private String userId;
	private String userName;
	private String planTitle;
	private String planContent;
	private String planDate;
	private List<Map<String, Integer>> plans;
	private List<AttractionDto> attrInfos;
	private List<CommentDto> comments;
	
	
	private int likeCnt;
	private boolean isWish;

	
	public List<CommentDto> getComments() {
		return comments;
	}

	public void setComments(List<CommentDto> comments) {
		this.comments = comments;
	}

	public List<AttractionDto> getAttrInfos() {
		return attrInfos;
	}

	public void setAttrInfos(List<AttractionDto> attrInfos) {
		this.attrInfos = attrInfos;
	}

	public PlanDto() {
		super();
	}

	public List<Map<String, Integer>> getPlans() {
		return plans;
	}

	public void setPlans(List<Map<String, Integer>> plans) {
		this.plans = plans;
	}

	public String getPlanDate() {
		return planDate;
	}

	public void setPlanDate(String planDate) {
		this.planDate = planDate;
	}

	public int getPlanId() {
		return planId;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPlanTitle() {
		return planTitle;
	}

	public void setPlanTitle(String planTitle) {
		this.planTitle = planTitle;
	}

	public String getPlanContent() {
		return planContent;
	}

	public void setPlanContent(String planContent) {
		this.planContent = planContent;
	}

	public int getLikeCnt() {
		return likeCnt;
	}

	public void setLikeCnt(int likeCnt) {
		this.likeCnt = likeCnt;
	}

	public boolean isWish() {
		return isWish;
	}

	public void setWish(boolean isWish) {
		this.isWish = isWish;
	}

	@Override
	public String toString() {
		return "PlanDto [planId=" + planId + ", userId=" + userId + ", userName=" + userName + ", planTitle="
				+ planTitle + ", planContent=" + planContent + ", planDate=" + planDate + ", plans=" + plans
				+ ", attrInfos=" + attrInfos + ", likeCnt=" + likeCnt + ", isWish=" + isWish + "]";
	}

}
