package com.junhee.researchWeb.model;

public class TsReserveVO {
	private int reserveNum;
	private int timeslotId;
	private int researchId;
	private String studentId;
	private int takingClassId;
	private String status;
	
	public TsReserveVO() {}

	public TsReserveVO(int reserveNum, int timeslotId, int researchId, String studentId, int takingClassId,
			String status) {
		this.reserveNum = reserveNum;
		this.timeslotId = timeslotId;
		this.researchId = researchId;
		this.studentId = studentId;
		this.takingClassId = takingClassId;
		this.status = status;
	}

	public int getReserveNum() {
		return reserveNum;
	}

	public void setReserveNum(int reserveNum) {
		this.reserveNum = reserveNum;
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

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public int getTakingClassId() {
		return takingClassId;
	}

	public void setTakingClassId(int takingClassId) {
		this.takingClassId = takingClassId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "타임슬롯 예약 정보 [reserveNum=" + reserveNum + ", timeslotId=" + timeslotId + ", researchId=" + researchId
				+ ", studentId=" + studentId + ", takingClassId=" + takingClassId + ", status=" + status + "]";
	}

}
