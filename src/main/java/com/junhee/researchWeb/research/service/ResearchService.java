package com.junhee.researchWeb.research.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.junhee.researchWeb.model.ClassVO;
import com.junhee.researchWeb.model.ResearchVO;
import com.junhee.researchWeb.model.TakingClassVO;
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

}
