import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.junhee.researchWeb.model.ResearchVO;
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
	
}
