package com.ssafy.plan.model;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "WishListDto: 찜하기", description = "플랜 게시판의 찜하기 정보를 나타낸다")
public class WishListDto {
	private String userId;
	private String planId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

}
