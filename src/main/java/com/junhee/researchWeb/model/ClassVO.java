package com.junhee.researchWeb.model;

public class ClassVO {

	private int classId;
	private String teacherName;
	private String teacherId;
	private String className;
	private String classSchedule;
	private int needCredit;
	
	public ClassVO() {}

	public ClassVO(int classId, String teacherName, String teacherId, String className, String classSchedule,
			int needCredit) {
		this.classId = classId;
		this.teacherName = teacherName;
		this.teacherId = teacherId;
		this.className = className;
		this.classSchedule = classSchedule;
		this.needCredit = needCredit;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassSchedule() {
		return classSchedule;
	}

	public void setClassSchedule(String classSchedule) {
		this.classSchedule = classSchedule;
	}

	public int getNeedCredit() {
		return needCredit;
	}

	public void setNeedCredit(int needCredit) {
		this.needCredit = needCredit;
	}

	@Override
	public String toString() {
		return "클래스 정보 [classId=" + classId + ", teacherName=" + teacherName + ", teacherId=" + teacherId
				+ ", className=" + className + ", classSchedule=" + classSchedule + ", needCredit=" + needCredit + "]";
	}
	
	
	
}
