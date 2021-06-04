import java.sql.Timestamp;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.junhee.researchWeb.model.ClassVO;
import com.junhee.researchWeb.model.LocationVO;
import com.junhee.researchWeb.model.ResearchVO;
import com.junhee.researchWeb.model.TakingClassVO;
import com.junhee.researchWeb.model.TimeSlotVO;
import com.junhee.researchWeb.research.repository.IResearchMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class ResearchMapperTest {

	@Autowired
	IResearchMapper mapper;
	
	@Test
	public void registerTest() {
		ResearchVO rvo = new ResearchVO();
		rvo.setResearchTitle("연구1");
		rvo.setResearchPurpose("그냥1");
		rvo.setResearchMethod("방법1");
		rvo.setResearcher("bhc1234");
		rvo.setRewardType("참가비");
		rvo.setRewardValue(10000);
		rvo.setTakeTime(30);
		rvo.setResearchType("오프라인 실험");
		mapper.registerResearch(rvo);
	}
	
	@Test
	public void getMyResearchTest() {
		List<ResearchVO> list = mapper.getMyResearch("ppppp1111");
		if(list != null) {
			if(list.size() != 0) {
				for(ResearchVO rvo : list) {
					System.out.println(rvo);
				}
			}
			else {
				System.out.println("연구가 없습니다.");
			}
		}
		else {
			System.out.println("연구 리스트를 얻어오는데에 실패했습니다..");
		}
	}
	
	@Test
	public void getOneResearchInfoTest() {
		ResearchVO rvo = mapper.getResearchInfo(2);
		System.out.println(rvo);
	}
	
	@Test
	public void getAllResearchInfoTest() {
		List<ResearchVO> list = mapper.getAllResearch();
		if(list != null) {
			if(list.size() != 0) {
				for(ResearchVO rvo : list) {
					System.out.println(rvo);
				}
			}
			else {
				System.out.println("연구가 없습니다.");
			}
		}
		else {
			System.out.println("연구 리스트를 얻어오는데에 실패했습니다..");
		}
	}
	
	@Test
	public void getSameMajorResearchTest() {
		List<ResearchVO> list = mapper.getSameMajorResearch("응용인지심리");
		if(list != null) {
			if(list.size() != 0) {
				for(ResearchVO rvo : list) {
					System.out.println(rvo);
				}
			}
			else {
				System.out.println("연구가 없습니다.");
			}
		}
		else {
			System.out.println("연구 리스트를 얻어오는데에 실패했습니다..");
		}
	}
	
	@Test
	public void changePermitTest() {
		ResearchVO rvo = new ResearchVO();
		rvo.setPermit(1);
		rvo.setResearchId(2);
		mapper.ChangePermitStatus(rvo);
		System.out.println(mapper.getResearchInfo(rvo.getResearchId()));
	}
	
	@Test
	public void registerClassTest() {
		ClassVO cvo = new ClassVO();
		cvo.setClassName("우주의 세계");
		cvo.setClassSchedule("월E수E");
		cvo.setNeedCredit(20);
		cvo.setTeacherId("teacher1");
		cvo.setTeacherName("박선생");
		mapper.registerClass(cvo);
	}
	
	@Test
	public void getMyClassesTest() {
		for(ClassVO cvo : mapper.getMyClasses("teacher1")) {
			System.out.println(cvo);
		}
	}
	
	@Test
	public void deleteClassTest() {
		for(ClassVO cvo : mapper.getMyClasses("teacher1")) {
			System.out.println(cvo);
		}
		System.out.println("삭제");
		ClassVO cvo2 = new ClassVO();
		cvo2.setClassId(2);
		cvo2.setTeacherId("teacher1");
		mapper.deleteClass(cvo2);
		for(ClassVO cvo : mapper.getMyClasses("teacher1")) {
			System.out.println(cvo);
		}
	}
	
	@Test
	public void insertClassStudentPairTest() {
		TakingClassVO tcvo = new TakingClassVO();
		tcvo.setClassId(7);
		tcvo.setRequiredCredit(mapper.getClassInfo(7).getNeedCredit());
		tcvo.setStudentId("abc1234");
		tcvo.setStudentName("김준");
		mapper.insertClassStudentPair(tcvo);
	}
	
	@Test
	public void getTakingClassListTest() {
		
		for(TakingClassVO tcvo : mapper.getTakingClassList("abc1234")) {
			System.out.println(tcvo);
		}
	}
	
	@Test
	public void getAllLocationTest() {
		for(LocationVO lvo : mapper.getAllLocationInfo()) {
			System.out.println(lvo);
		}
	}
	
	@Test
	public void getMaxPeopleTest() {
		System.out.println(mapper.getMaxLimitPeople("율곡관 370호"));
	}
	
	@Test
	public void registerTimeslotTest() {
		TimeSlotVO tsvo = new TimeSlotVO();
		tsvo.setResearchId(2);
		tsvo.setResearcher("ppppp1111");
		tsvo.setResearchDate(Timestamp.valueOf("2021-06-12 00:00:00"));
		tsvo.setStartTime(Timestamp.valueOf("2021-06-12 13:00:00"));
		tsvo.setEndTime(Timestamp.valueOf("2021-06-12 13:30:00"));
		tsvo.setPeopleLimit(4);
		tsvo.setLocationName("율곡관 370호");
		mapper.registerTimeslot(tsvo);
	}
	
}
