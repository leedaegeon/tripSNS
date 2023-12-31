package com.ssafy.attraction.model;

import io.swagger.annotations.ApiModel;

@ApiModel(value="AttractionDto: 여행지 정보", description="여행지의 상세 정보를 나타낸다")
public class AttractionDto{
	private int contentId;
	private int contentTypeId;
	private String title;
	private String addr1;
	private String addr2;
	
	private String image;
	private String smallImage;
	private int sidoCode;
	private int gugunCode;
	private double latitude;
	private double longitude;
	private String overview;
	private String keyword;
	
 	public String getSmallImage() {
		return smallImage;
	}
	public void setSmallImage(String smallImage) {
		this.smallImage = smallImage;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public AttractionDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getContentId() {
		return contentId;
	}
	public void setContentId(int contentId) {
		this.contentId = contentId;
	}
	public int getContentTypeId() {
		return contentTypeId;
	}
	public void setContentTypeId(int contentTypeId) {
		this.contentTypeId = contentTypeId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getSidoCode() {
		return sidoCode;
	}
	public void setSidoCode(int sidoCode) {
		this.sidoCode = sidoCode;
	}
	public int getGugunCode() {
		return gugunCode;
	}
	public void setGugunCode(int gugunCode) {
		this.gugunCode = gugunCode;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	@Override
	public String toString() {
		return "AttractionDto [contentId=" + contentId + ", contentTypeId=" + contentTypeId + ", title=" + title
				+ ", addr1=" + addr1 + ", addr2=" + addr2 + ", image=" + image + ", smallImage=" + smallImage
				+ ", sidoCode=" + sidoCode + ", gugunCode=" + gugunCode + ", latitude=" + latitude + ", longitude="
				+ longitude + ", overview=" + overview + ", keyword=" + keyword + "]";
	}
	

}
