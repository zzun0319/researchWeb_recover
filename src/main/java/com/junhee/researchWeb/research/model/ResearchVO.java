package com.junhee.researchWeb.research.model;

public class ResearchVO {
	
	private int researchId;
	private String researchTitle;
	private String researchPurpose;
	private String researchMethod;
	private String researcher;
	private String rewardType;
	private int rewardValue; // ������, ����������
	private int takeTime;
	private String researchType; // �������� ����, �������� ����, �¶��� ����, �¶��� ����, ��Ÿ(��������), ��Ÿ(�¶���)
	private int permit;
	
	public ResearchVO() {
		// TODO Auto-generated constructor stub
	}

	public ResearchVO(int researchId, String researchTitle, String researchPurpose, String researchMethod,
			String researcher, String rewardType, int rewardValue, int takeTime, String researchType, int permit) {
		this.researchId = researchId;
		this.researchTitle = researchTitle;
		this.researchPurpose = researchPurpose;
		this.researchMethod = researchMethod;
		this.researcher = researcher;
		this.rewardType = rewardType;
		this.rewardValue = rewardValue;
		this.takeTime = takeTime;
		this.researchType = researchType;
		this.permit = permit;
	}

	public int getResearchId() {
		return researchId;
	}

	public void setResearchId(int researchId) {
		this.researchId = researchId;
	}

	public String getResearchTitle() {
		return researchTitle;
	}

	public void setResearchTitle(String researchTitle) {
		this.researchTitle = researchTitle;
	}

	public String getResearchPurpose() {
		return researchPurpose;
	}

	public void setResearchPurpose(String researchPurpose) {
		this.researchPurpose = researchPurpose;
	}

	public String getResearchMethod() {
		return researchMethod;
	}

	public void setResearchMethod(String researchMethod) {
		this.researchMethod = researchMethod;
	}

	public String getResearcher() {
		return researcher;
	}

	public void setResearcher(String researcher) {
		this.researcher = researcher;
	}

	public String getRewardType() {
		return rewardType;
	}

	public void setRewardType(String rewardType) {
		this.rewardType = rewardType;
	}

	public int getRewardValue() {
		return rewardValue;
	}

	public void setRewardValue(int rewardValue) {
		this.rewardValue = rewardValue;
	}

	public int getTakeTime() {
		return takeTime;
	}

	public void setTakeTime(int takeTime) {
		this.takeTime = takeTime;
	}

	public String getResearchType() {
		return researchType;
	}

	public void setResearchType(String researchType) {
		this.researchType = researchType;
	}

	public int getPermit() {
		return permit;
	}

	public void setPermit(int permit) {
		this.permit = permit;
	}

	@Override
	public String toString() {
		return "�������� [researchId=" + researchId + ", researchTitle=" + researchTitle + ", researchPurpose="
				+ researchPurpose + ", researchMethod=" + researchMethod + ", researcher=" + researcher
				+ ", rewardType=" + rewardType + ", rewardValue=" + rewardValue + ", takeTime=" + takeTime
				+ ", researchType=" + researchType + ", permit=" + permit + "]";
	}

	
	
	
}
