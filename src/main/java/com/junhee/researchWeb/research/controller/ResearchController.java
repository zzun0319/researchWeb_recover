package com.junhee.researchWeb.research.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.junhee.researchWeb.model.ResearchListVO;
import com.junhee.researchWeb.model.ResearchVO;
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
	
}
