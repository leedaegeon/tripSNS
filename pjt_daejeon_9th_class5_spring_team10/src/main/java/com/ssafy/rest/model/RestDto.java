package com.ssafy.rest.model;

public class RestDto {
	private int restId;
	private String restName;
	private String roadDirection;
	private double lat;
	private double lng;
	private String convFacility;
	private String bestFood;
	private String phoneNum;
	public RestDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getRestId() {
		return restId;
	}
	public void setRestId(int restId) {
		this.restId = restId;
	}
	public String getRestName() {
		return restName;
	}
	public void setRestName(String restName) {
		this.restName = restName;
	}
	public String getRoadDirection() {
		return roadDirection;
	}
	public void setRoadDirection(String roadDirection) {
		this.roadDirection = roadDirection;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	public String getConvFacility() {
		return convFacility;
	}
	public void setConvFacility(String convFacility) {
		this.convFacility = convFacility;
	}
	public String getBestFood() {
		return bestFood;
	}
	public void setBestFood(String bestFood) {
		this.bestFood = bestFood;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	@Override
	public String toString() {
		return "RestDto [restId=" + restId + ", restName=" + restName + ", roadDirection=" + roadDirection + ", lat="
				+ lat + ", lng=" + lng + ", convFacility=" + convFacility + ", bestFood=" + bestFood + ", phoneNum="
				+ phoneNum + "]";
	}
	
	
}
