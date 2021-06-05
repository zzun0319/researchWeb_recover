package com.junhee.researchWeb.research.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.junhee.researchWeb.model.ClassVO;
import com.junhee.researchWeb.model.LocationVO;
import com.junhee.researchWeb.model.ResearchVO;
import com.junhee.researchWeb.model.TakingClassVO;
import com.junhee.researchWeb.model.TimeSlotVO;
import com.junhee.researchWeb.model.TsReserveVO;
import com.junhee.researchWeb.research.repository.IResearchMapper;

@Service
public class ResearchService implements IResearchService {
	
	@Autowired
	private IResearchMapper mapper;

	@Override
	public void registerResearch(ResearchVO rvo) {
		if(rvo.getRewardType() == "학점") {
			if(rvo.getTakeTime() <= 30) {
				rvo.setRewardValue(1);
			} else if(rvo.getTakeTime() > 30 && rvo.getTakeTime() <= 60) {
				rvo.setRewardValue(2);
			} else if(rvo.getTakeTime() > 60 && rvo.getTakeTime() <= 90) {
				rvo.setRewardValue(3);
			} else {
				rvo.setRewardValue(4);
			}
		}
		mapper.registerResearch(rvo);
	}

	@Override
	public List<ResearchVO> getMyResearch(String researcher) {
		return mapper.getMyResearch(researcher);
	}
	
	@Override
	public ResearchVO getResearchInfo(int researchId) {
		return mapper.getResearchInfo(researchId);
	}

	@Override
	public List<ResearchVO> getAllResearch() {
		return mapper.getAllResearch();
	}

	@Override
	public List<ResearchVO> getSameMajorResearch(String major) {
		return mapper.getSameMajorResearch(major);
	}

	@Override
	public void ChangePermitStatus(List<ResearchVO> researchList) {
		for(ResearchVO rvo : researchList) {
			mapper.ChangePermitStatus(rvo);	
		}
		
	}

	@Override
	public void registerClass(ClassVO cvo) {
		mapper.registerClass(cvo);
	}

	@Override
	public List<ClassVO> getMyClasses(String teacherId) {
		return mapper.getMyClasses(teacherId);
	}

	@Override
	public void deleteClass(ClassVO cvo) {
		mapper.deleteClass(cvo);
	}

	@Override
	public List<ClassVO> getAllClassList() {
		return mapper.getAllClassList();
	}

	@Override
	public void insertClassStudentPair(TakingClassVO tcvo) {
		ClassVO tmp = mapper.getClassInfo(tcvo.getClassId());
		tcvo.setRequiredCredit(tmp.getNeedCredit()); // 필요 연구학점을 넣어줘야 함.
		mapper.insertClassStudentPair(tcvo);
	}

	@Override
	public ClassVO getClassInfo(int classId) {
		return mapper.getClassInfo(classId);
	}

	@Override
	public List<TakingClassVO> getTakingClassList(String studentId) {
		return mapper.getTakingClassList(studentId);
	}

	@Override
	public List<LocationVO> getAllLocationInfo() {
		return mapper.getAllLocationInfo();
	}

	@Override
	public String registerTimeslot(int researchId, String researcher, String researchDate, String startTime, int peopleLimit, String locationName) {
		if(peopleLimit > mapper.getMaxLimitPeople(locationName)) {
			return "최대 수용 가능한 인원 수를 초과하여 타임슬롯 등록에 실패";
		}
		
		
		// 조건들 추가해야함. 장소예약 체크(그 날짜, 그 시간에 해당 장소가 예약되어있으면 타임슬롯 못 만들도록, 이보다 더 나은 방법은 애초에 날짜, 시간 겹치면 장소가 선택지에 안 뜨도록). 
		// 아니면 타임슬롯 만드는 것도 나눠서 첫번째 페이지에서는 날짜 기간 조회하면 예약 현황 보여주고, 그거 보고 날짜, 시작시간, 장소 결정할 수 있도록 makeTimeSlot페이지에 조회기능을 넣어..
		
		else {
			TimeSlotVO tsvo = new TimeSlotVO();
			tsvo.setResearchId(researchId);
			tsvo.setResearcher(researcher);
			tsvo.setResearchDate(Timestamp.valueOf(researchDate + " 00:00:00"));
			tsvo.setStartTime(Timestamp.valueOf(researchDate + " " + startTime + ":00"));
			ResearchVO rvo = mapper.getResearchInfo(researchId); 
			int totaltime = rvo.getTakeTime();
			int originalHour = totaltime / 60;
			int originalMinutes = totaltime % 60;
			String times[] = startTime.split(":");
			int hour = Integer.parseInt(times[0]) + originalHour;
			int minute = Integer.parseInt(times[1]) + originalMinutes;
			if(minute >= 60) {
				hour++;
				minute -= 60;
				if(hour >= 24) hour -= 24;
			}
			String endTime = researchDate + " " + hour + ":" + minute + ":00";
			tsvo.setEndTime(Timestamp.valueOf(endTime));
			tsvo.setPeopleLimit(peopleLimit);
			tsvo.setLocationName(locationName);
			
			for(TimeSlotVO tmp : mapper.getAllTimeslots()) { // 기한 겹치면서 && 연구실이 같은 이전 타임슬롯이 있는지 검사
				System.out.println(tmp.getStartTime().before(Timestamp.valueOf(endTime)));
				System.out.println(Timestamp.valueOf(researchDate + " " + startTime + ":00").before(tmp.getEndTime()));
				System.out.println(tmp.getLocationName().equals(locationName));
				if((tmp.getStartTime().before(Timestamp.valueOf(endTime)) || Timestamp.valueOf(researchDate + " " + startTime + ":00").before(tmp.getEndTime())) && tmp.getLocationName().equals(locationName)) {
					return "해당 장소는 이미 존재하는 타임슬롯과 연구 기한이 겹칩니다. 타임슬롯을 만들 수 없습니다.";
				} 
			}
			
			mapper.registerTimeslot(tsvo);
			return "타임슬롯이 등록되었습니다.";
			
		}
	}

	@Override
	public List<TimeSlotVO> getTimeslotsByResearchId(int researchId) {
		return mapper.getTimeslotsByResearchId(researchId);
	}

	@Override
	public List<TimeSlotVO> getTimeslotListsByPeriod(String startDate, String endDate) {
		Map<String, String> periods = new HashMap<String, String>();
		periods.put("startDate", startDate + " 00:00:00");
		periods.put("endDate", endDate + " 23:59:59");
		return mapper.getTimeslotListsByPeriod(periods);
	}

	@Override
	public String reserveTimeslot(TsReserveVO trvo) {
		List<TsReserveVO> tsList = mapper.getAppliedTimeslots(trvo.getStudentId());
		int cnt = 0;
		for(TsReserveVO tsvo : tsList) {
			if(tsvo.getResearchId() == trvo.getResearchId()) {
				cnt++;
			}
		}
		if(cnt > 0) {
			return "이미 참여 중인 연구입니다. 같은 연구를 여러 번 참여할 수 없습니다.";
		} else {
			mapper.reserveTimeslot(trvo);
			return "연구 참여 신청이 완료되었습니다.";
		}
		
	}

	@Override
	public List<TsReserveVO> getAppliedTimeslots(String studentId) {
		return mapper.getAppliedTimeslots(studentId);
	}

	@Override
	public List<TimeSlotVO> getTimeslotsApplied(String studentId) {
		return mapper.getTimeslotsApplied(studentId);
	}

}
