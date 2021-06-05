package com.junhee.researchWeb.research.service;

import java.util.HashMap;
import java.util.List;

import com.junhee.researchWeb.model.ClassVO;
import com.junhee.researchWeb.model.LocationVO;
import com.junhee.researchWeb.model.ResearchVO;
import com.junhee.researchWeb.model.TakingClassVO;
import com.junhee.researchWeb.model.TimeSlotVO;
import com.junhee.researchWeb.model.TsReserveVO;

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
	
	// 연구 참여 과목 등록
	void registerClass(ClassVO cvo);
	
	// 자신의 연구참여 과목 리스트 보기
	List<ClassVO> getMyClasses(String teacherId);
	
	// 연구 참여 과목 삭제
	void deleteClass(ClassVO cvo);
	
	// 모든 연구참여 과목 리스트 보기
	List<ClassVO> getAllClassList();
	
	// 수강 과목 추가
	void insertClassStudentPair(TakingClassVO tcvo);
		
	// 클래스 정보 얻기
	ClassVO getClassInfo(int classId);
	
	// 수강 중인 클래스 정보 얻기
	List<TakingClassVO> getTakingClassList(String studentId);
	
	// 모든 장소 정보 가져오기
	List<LocationVO> getAllLocationInfo();
	
	// 연구 타임슬롯 등록하기
	String registerTimeslot(int researchId, String researcher, String researchDate, String startTime, int peopleLimit, String locationName);
	
	// 연구별 타임슬롯 가져오기
	List<TimeSlotVO> getTimeslotsByResearchId(int researchId);
	
	// 날짜 기간 사이에 예약된 타임슬롯 리스트 가져오기
	List<TimeSlotVO> getTimeslotListsByPeriod(String startDate, String endDate);
	
	// 타임슬롯 예약하기
	String reserveTimeslot(TsReserveVO tsvo);
	
	// 참여중인 타임슬롯 리스트 가져오기
	List<TsReserveVO> getAppliedTimeslots(String studentId);
	
	// 참여중인 타임슬롯 리스트 가져오기
	List<TimeSlotVO> getTimeslotsApplied(String studentId);
	
	
}
