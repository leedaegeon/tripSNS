package com.ssafy.plan.model;

import java.util.List;

//plan_board_tb
public class PlanDto {
	private int planId;
	private int userId;
	private String userName;
	private String planTitle;
	private String planContent;
	private int likeCnt;
	private List<PlanDetailDto> planDetailList;
	
	public PlanDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getPlanId() {
		return planId;
	}
	public void setPlanId(int planId) {
		this.planId = planId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
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
	public List<PlanDetailDto> getPlanDetailList() {
		return planDetailList;
	}
	public void setPlanDetailList(List<PlanDetailDto> planDetailList) {
		this.planDetailList = planDetailList;
	}
	@Override
	public String toString() {
		return "PlanDto [planId=" + planId + ", userId=" + userId + ", userName=" + userName + ", planTitle="
				+ planTitle + ", planContent=" + planContent + ", likeCnt=" + likeCnt + ", planDetailList="
				+ planDetailList + "]";
	}
	
	
	
}
