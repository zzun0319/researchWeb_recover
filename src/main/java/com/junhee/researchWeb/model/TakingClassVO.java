package com.junhee.researchWeb.model;

/*
 * CREATE TABLE taking_class (
    pair_number NUMBER PRIMARY KEY,
    student_id VARCHAR(14) NOT NULL,
    student_name VARCHAR(20) NOT NULL,
    class_id NUMBER NOT NULL,
    required_credit NUMBER NOT NULL,
    fill_credit NUMBER DEFAULT 0
);
 */

public class TakingClassVO {

	private int pairNumber;
	private String studentId;
	private String studentName;
	private int classId;
	private int requiredCredit;
	private int fillCredit;
	
	public TakingClassVO() {}

	public TakingClassVO(int pairNumber, String studentId, String studentName, int classId, int requiredCredit,
			int fillCredit) {
		this.pairNumber = pairNumber;
		this.studentId = studentId;
		this.studentName = studentName;
		this.classId = classId;
		this.requiredCredit = requiredCredit;
		this.fillCredit = fillCredit;
	}

	public int getPairNumber() {
		return pairNumber;
	}

	public void setPairNumber(int pairNumber) {
		this.pairNumber = pairNumber;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public int getRequiredCredit() {
		return requiredCredit;
	}

	public void setRequiredCredit(int requiredCredit) {
		this.requiredCredit = requiredCredit;
	}

	public int getFillCredit() {
		return fillCredit;
	}

	public void setFillCredit(int fillCredit) {
		this.fillCredit = fillCredit;
	}

	@Override
	public String toString() {
		return "TakingClassVO [pairNumber=" + pairNumber + ", studentId=" + studentId + ", studentName=" + studentName
				+ ", classId=" + classId + ", requiredCredit=" + requiredCredit + ", fillCredit=" + fillCredit + "]";
	}

	
	
	
	
	
}
