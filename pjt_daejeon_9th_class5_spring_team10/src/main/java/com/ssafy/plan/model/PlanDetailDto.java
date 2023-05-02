package com.ssafy.plan.model;

public class PlanDetailDto {
	private int planId;
	private int planDetailId;
	private int attractionId;
	private int restAreaId;
	private int areaType;
	private int planOrder;
	
	public PlanDetailDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getPlanId() {
		return planId;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
	}

	public int getPlanDetailId() {
		return planDetailId;
	}

	public void setPlanDetailId(int planDetailId) {
		this.planDetailId = planDetailId;
	}

	public int getAttractionId() {
		return attractionId;
	}

	public void setAttractionId(int attractionId) {
		this.attractionId = attractionId;
	}

	public int getRestAreaId() {
		return restAreaId;
	}

	public void setRestAreaId(int restAreaId) {
		this.restAreaId = restAreaId;
	}

	public int getAreaType() {
		return areaType;
	}

	public void setAreaType(int areaType) {
		this.areaType = areaType;
	}

	public int getPlanOrder() {
		return planOrder;
	}

	public void setPlanOrder(int planOrder) {
		this.planOrder = planOrder;
	}

	@Override
	public String toString() {
		return "PlanDetailDto [planId=" + planId + ", planDetailId=" + planDetailId + ", attractionId=" + attractionId
				+ ", restAreaId=" + restAreaId + ", areaType=" + areaType + ", planOrder=" + planOrder + "]";
	}
	
	
}
