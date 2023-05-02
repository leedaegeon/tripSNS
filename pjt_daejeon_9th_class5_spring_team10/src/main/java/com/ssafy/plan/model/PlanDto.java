package com.ssafy.plan.model;

import java.util.List;

public class PlanDto {
	private int planId;
	private int userId;
	private String userName;
	private String planTitle;
	private String planContent;
	private int likeCnt;
	private List<PlanDetailDto> planDetailList;
}
