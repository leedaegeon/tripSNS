package com.ssafy.member.model;

import io.swagger.annotations.ApiModel;

@ApiModel(value="MemberDto: 회원 정보", description="회원의 상세 정보를 나타낸다")

public class MemberDto {

	private String userId;
	private String userPw;
	private String userName;
	private String userEmail;
	private int userAge;
	private int userType;
	public MemberDto() {
		super();
		// TODO Auto-generated constructor stub
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
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public int getUserAge() {
		return userAge;
	}
	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	@Override
	public String toString() {
		return "MemberDto [userId=" + userId + ", userName=" + userName + ", userPw=" + userPw + ", userEmail="
				+ userEmail + ", userAge=" + userAge + ", userType=" + userType + "]";
	}
	
	
}
