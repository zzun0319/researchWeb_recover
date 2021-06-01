package com.junhee.researchWeb.research.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.junhee.researchWeb.model.ResearchVO;
import com.junhee.researchWeb.research.service.ResearchService;

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
	
	
}
