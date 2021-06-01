package com.junhee.researchWeb.research.repository;

import java.util.List;

import com.junhee.researchWeb.research.model.ResearchVO;

public interface IResearchMapper {

	// ���� ���� ��û
	void registerResearch(ResearchVO rvo);
		
	// ���� ���� ����
	ResearchVO getResearchInfo(int researchId);
		
	// ��� ���� ������
	List<ResearchVO> getAllResearch();
		
	// ���� ���� ���� �������� ������ ���� ���п����� ������ ������
	//List<ResearchVO> getSameMajorResearch(String major);
	
}
