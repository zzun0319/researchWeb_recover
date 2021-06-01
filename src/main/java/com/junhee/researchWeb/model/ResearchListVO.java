package com.junhee.researchWeb.model;

import java.util.ArrayList;
import java.util.List;


public class ResearchListVO {

	private List<ResearchVO> rList;
	
	public ResearchListVO() {rList = new ArrayList<ResearchVO>();}

	public ResearchListVO(List<ResearchVO> rList) {
		this.rList = rList;
	}

	public List<ResearchVO> getrList() {
		return rList;
	}

	public void setrList(List<ResearchVO> rList) {
		this.rList = rList;
	}
	
	public void addResearchInList(ResearchVO rvo) {
		rList.add(rvo);
	}
	
}
