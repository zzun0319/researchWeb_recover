package com.junhee.researchWeb.research.service;

import java.util.List;

import com.junhee.researchWeb.model.ResearchVO;

public interface IResearchService {

	// 연구 개설 신청
	void registerResearch(ResearchVO rvo);
	
	// 내가 개설한 연구 보기
	List<ResearchVO> getMyResearch(String researcher);
		
	// 연구 상세 보기
	ResearchVO getResearchInfo(int researchId);
		
	// 모든 연구 보기
	List<ResearchVO> getAllResearch();
		
	// 연구 승인 위해 지도교수 전공과 같은 대학원생의 연구만 얻어오기
	List<ResearchVO> getSameMajorResearch(String major);
	
	// 연구 승인 상태 변경
	void ChangePermitStatus(List<ResearchVO> researchList);
	
}
