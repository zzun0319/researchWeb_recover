package com.junhee.researchWeb.research.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.junhee.researchWeb.model.ClassVO;
import com.junhee.researchWeb.model.LocationVO;
import com.junhee.researchWeb.model.ResearchVO;
import com.junhee.researchWeb.model.TakingClassVO;
import com.junhee.researchWeb.model.TimeSlotVO;
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
			mapper.registerTimeslot(tsvo);
			return "타임슬롯이 등록되었습니다.";
		}
		
	}

}
