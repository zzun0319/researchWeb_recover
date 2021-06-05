package com.junhee.researchWeb.research.controller;



import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.junhee.researchWeb.model.ClassVO;
import com.junhee.researchWeb.model.ResearchListVO;
import com.junhee.researchWeb.model.ResearchVO;
import com.junhee.researchWeb.model.TakingClassVO;
import com.junhee.researchWeb.model.TimeSlotVO;
import com.junhee.researchWeb.research.service.ResearchService;
import com.junhee.researchWeb.user.model.UserVO;

@Controller
@RequestMapping("/research")
public class ResearchController {

	@Autowired
	private ResearchService service;
	
	@GetMapping("/register1")
	public void register1GET() {
		System.out.println("연구 개설 신청1 페이지로 이동! GET");
	}
	
	@PostMapping("/register1")
	public String register1Post(@ModelAttribute("rewardType") String rewardType, @ModelAttribute("researchType") String researchType) {
		System.out.println("연구 개설 신청2 페이지로 이동! 연구 유형: " + researchType + "보상 유형: " + rewardType);
		return "research/register2";
	}
	
	@PostMapping("/register2")
	public String register2Post(ResearchVO rvo, RedirectAttributes ra) {
		System.out.println("연구 개설 최종 요청.");
		System.out.println(rvo);
		service.registerResearch(rvo);
		ra.addFlashAttribute("msg", "연구 개설 신청 완료. 지도 교수 승인 후에 타임슬롯을 열 수 있습니다.");
		return "redirect:/user/mypage";
	}
	
	@GetMapping("/showMyResearch")
	public void showMyResearch(Model model, String researcher) {
		System.out.println("내가 개설한 연구 목록 요청.");
		model.addAttribute("myResearchList", service.getMyResearch(researcher));
	}
	
	@GetMapping("/showOneResearch")
	public void showOneResearch(int researchId, Model model) {
		System.out.println("연구 상세 보기 요청.");
		model.addAttribute("selectedResearch", service.getResearchInfo(researchId));
	}
	
	@GetMapping("/acceptResearch")
	public void acceptResearchPage(String major, Model model) {
		System.out.println("지도교수 밑에 있는 대학원생들의 개설된 연구 목록 요청");
		model.addAttribute("registedResearchList", service.getSameMajorResearch(major));
	}
	
	@PostMapping("/acceptResearch2")
	public String AcceptResearch(ResearchListVO researchList, String major, RedirectAttributes ra) {
		System.out.println("개설 연구 승인 상태 변경 요청 / major: " + major);
		List<ResearchVO> rList = researchList.getrList();
		for(ResearchVO r : rList) {
			System.out.println(r);
		}
		service.ChangePermitStatus(rList);;
		ra.addFlashAttribute("msg", "개설 연구 승인 정보가 변경되었습니다.");
		return "redirect:/user/mypage";
	}
	
	@GetMapping("/showAllResearch")
	public void showAllResearch(Model model) {
		System.out.println("승인된 모든 연구 보기 요청");
		model.addAttribute("allResearch", service.getAllResearch());
	}
	
	@GetMapping("/addClassForResearch")
	public void addClassPage(){
		System.out.println("연구 참여 과목 등록 페이지로 이동");
	}
	
	@PostMapping("/addClassForResearch")
	public String addClassForResearch(ClassVO cvo, RedirectAttributes ra) {
		System.out.println("다음 수업을 연구 참여 과목으로 등록");
		System.out.println(cvo);
		service.registerClass(cvo);
		ra.addFlashAttribute("msg", "연구 참여 과목으로 등록되었습니다.");
		return "redirect:/user/mypage";
	}
	
	@GetMapping("/showMyClasses")
	public void showMyClass(String teacherId, Model model) {
		model.addAttribute("myClasses", service.getMyClasses(teacherId));
	}
	
	@GetMapping("/DeleteClass")
	public String deleteClass(ClassVO cvo) {
		System.out.println("다음 과목의 연구참여과목리스트 삭제 요청이 들어옴, 과목번호: " + cvo.getClassId());
		service.deleteClass(cvo);
		return "redirect:/research/showMyClasses?teacherId=" + cvo.getTeacherId();
	}
	
	@GetMapping("/studentAddClass")
	public void studentAddClassPage(String studentId, Model model) {
		System.out.println("수강 과목 추가 페이지 이동");
		model.addAttribute("allClasses", service.getAllClassList());
		for(ClassVO cvo : service.getAllClassList()) {
			System.out.println(cvo);
		}
		model.addAttribute("takingClasses", service.getTakingClassList(studentId));
		for(TakingClassVO tcvo : service.getTakingClassList(studentId)) {
			System.out.println(tcvo);
		}
	}
	
	@PostMapping("/studentAddClass")
	public String studentAddClass(TakingClassVO tcvo) {
		System.out.println("수강 과목 등록 요청");
		System.out.println(tcvo);
		service.insertClassStudentPair(tcvo);
		return "redirect:/research/studentAddClass?studentId=" + tcvo.getStudentId();
	}
	
	@GetMapping("/makeTimeSlot")
	public void makeTimeSlotPage(int researchId, Model model) {
		System.out.println("타임슬롯 만들기 페이지, 연구번호: " + researchId);
		model.addAttribute("researchInfo", service.getResearchInfo(researchId));
		model.addAttribute("locations", service.getAllLocationInfo());
		model.addAttribute("timeslotList", service.getTimeslotsByResearchId(researchId));
	}
	
	@PostMapping("/makeTimeSlot")
	public String makeTimeSlot(int researchId, String researcher, String researchDate, String startTime,
			int peopleLimit, String locationName, RedirectAttributes ra) {
		// service로부터 뭔가 받자 int든 bool이든
		System.out.println("타임슬롯 만들기 요청");
		System.out.println(researchId + ", " + researcher + ", " + researchDate + ", " + startTime + ", " + peopleLimit + ", " + locationName);
		String result = service.registerTimeslot(researchId, researcher, researchDate, startTime, peopleLimit, locationName);
		System.out.println("타임슬롯 등록 결과: " + result);
		ra.addFlashAttribute("msg", result);
		return "redirect:/research/makeTimeSlot?researchId=" + researchId;
	}
	
	@GetMapping("/pickTimeSlot")
	public void pickTimeSlotPage(int researchId) {
		System.out.println("연구 번호 " + researchId + "의 스케줄 보기");
	}
	
	
}
