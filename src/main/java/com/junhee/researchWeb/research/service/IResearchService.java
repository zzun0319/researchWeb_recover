package com.junhee.researchWeb.research.service;

import java.util.List;

import com.junhee.researchWeb.research.model.ResearchVO;

public interface IResearchService {

	// ���� ���� ��û
	void registerResearch(ResearchVO rvo);
			
	// ���� ���� ����
	ResearchVO getResearchInfo(int researchId);
			
	// ��� ���� ������
	List<ResearchVO> getAllResearch();
			
	// ���� ���� ���� �������� ������ ���� ���п����� ������ ������
	//List<ResearchVO> getSameMajorResearch(String major);
	
}
