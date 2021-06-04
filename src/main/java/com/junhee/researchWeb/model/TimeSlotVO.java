package com.junhee.researchWeb.model;

import java.sql.Timestamp;

public class TimeSlotVO {
	
	private int timeslotId;
	private int researchId;
	private String researcher;
	private Timestamp researchDate;
	private Timestamp startTime;
	private Timestamp endTime;
	private int peopleLimit;
	private String locationName;
	
	public TimeSlotVO() {}

	public TimeSlotVO(int timeslotId, int researchId, String researcher, Timestamp researchDate, Timestamp startTime,
			Timestamp endTime, int peopleLimit, String locationName) {
		this.timeslotId = timeslotId;
		this.researchId = researchId;
		this.researcher = researcher;
		this.researchDate = researchDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.peopleLimit = peopleLimit;
		this.locationName = locationName;
	}

	public int getTimeslotId() {
		return timeslotId;
	}

	public void setTimeslotId(int timeslotId) {
		this.timeslotId = timeslotId;
	}

	public int getResearchId() {
		return researchId;
	}

	public void setResearchId(int researchId) {
		this.researchId = researchId;
	}

	public String getResearcher() {
		return researcher;
	}

	public void setResearcher(String researcher) {
		this.researcher = researcher;
	}

	public Timestamp getResearchDate() {
		return researchDate;
	}

	public void setResearchDate(Timestamp researchDate) {
		this.researchDate = researchDate;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public int getPeopleLimit() {
		return peopleLimit;
	}

	public void setPeopleLimit(int peopleLimit) {
		this.peopleLimit = peopleLimit;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	@Override
	public String toString() {
		return "타임슬롯 정보 [timeslotId=" + timeslotId + ", researchId=" + researchId + ", researcher=" + researcher
				+ ", researchDate=" + researchDate + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", peopleLimit=" + peopleLimit + ", locationName=" + locationName + "]";
	}

}
