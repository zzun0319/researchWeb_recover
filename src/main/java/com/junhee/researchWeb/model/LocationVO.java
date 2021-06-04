package com.junhee.researchWeb.model;

public class LocationVO {
	private String locationName;
	private int accommodate;
	
	public LocationVO() {}

	public LocationVO(String locationName, int accommodate) {
		super();
		this.locationName = locationName;
		this.accommodate = accommodate;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public int getAccommodate() {
		return accommodate;
	}

	public void setAccommodate(int accommodate) {
		this.accommodate = accommodate;
	}

	@Override
	public String toString() {
		return "장소 정보 [locationName=" + locationName + ", accommodate=" + accommodate + "]";
	}
	
}
