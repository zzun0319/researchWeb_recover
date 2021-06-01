package com.junhee.researchWeb.user.controller;



import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.junhee.researchWeb.user.model.UserListVO;
import com.junhee.researchWeb.user.model.UserVO;
import com.junhee.researchWeb.user.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;
	
	@GetMapping("/login") // �α��� �������� �̵�
	public void Login() {
		System.out.println("�α��� ��û: GET");
	}
	
	@PostMapping("/login")
	public String LoginProcess(UserVO user, RedirectAttributes ra, HttpSession session) {
		System.out.println("�α��� ��û: POST");
		System.out.println(user);
		String msg = service.getLoginCheckMessage(user);
		if(!msg.equals("�α��� ����")) {
			System.out.println("��Ʈ�ѷ�: " + msg);
			ra.addFlashAttribute("msg", msg);
			return "redirect:/user/login";
		} else {
			System.out.println("��Ʈ�ѷ�: " + msg);
			System.out.println(service.getOneUserInfo(user));
			session.setAttribute("member", service.getOneUserInfo(user));
			return "redirect:/user/mypage";
		}
			
	}
	
	@GetMapping("/register")
	public void RegisterReq() {}
	
	@PostMapping("/register")
	public String RegisterReq(@ModelAttribute("memberType") String memberType, Model model) {
		model.addAttribute("majorList", service.getMajors());
		return "user/register2";
	}
	
	@ResponseBody
	@PostMapping("/checkId")
	public String checkId(@RequestParam("id") String id) {
		System.out.println("���̵� �ߺ� üũ ��û!" + id);
		UserVO user = new UserVO();
		user.setUserId(id);
		if(service.getOneUserInfo(user) != null) {
			return "NO";
		}
		else {
			return "OK";
		}
	}
	
	@ResponseBody
	@PostMapping("/checkIdNum")
	public String checkIdNum(@RequestParam("idNum") String idNum) {
		System.out.println("�й� �ߺ� üũ ��û!" + idNum);
		return service.checkIdNum(idNum);
	}
	
	@PostMapping("/register2")
	public String RegisterInsert(UserVO user, RedirectAttributes ra) { // ��� ��쿡 �����̷�Ʈ �ϴ���..
		System.out.println("ȸ������ ���� ��û!");
		System.out.println(user + "�� ��Ʈ�ѷ��� ����");
		service.Register(user);
		ra.addFlashAttribute("msg", "ȸ������ ����!");
		return "redirect:/user/login";
	}
	
	@GetMapping("/mypage")
	public void mypage() {}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/user/login";
	}
	
	@GetMapping("updatePw")
	public void updatePw1() {}
	
	@PostMapping("/updatePw")
	public String updatePw2(UserVO user, String newPw, RedirectAttributes ra) {
		System.out.println("��й�ȣ ���� ��û�� ����");
		System.out.println("Id: " + user.getUserId() + ", newPw: " + newPw );
		String msg = service.getLoginCheckMessage(user);
		if(!msg.equals("�α��� ����")) {
			ra.addFlashAttribute("msg", "���� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
		}
		else {
			service.UpdatePassword(user.getUserId(), newPw);
			ra.addFlashAttribute("msg", "��й�ȣ ������ �Ϸ�Ǿ����ϴ�.");
		}
		return "redirect:/user/mypage";
	}
	
	@GetMapping("/updateInfo")
	public void updateUserInfo1() {}
	
	@GetMapping("/acceptResearcher")
	public String acceptResearcher1(@RequestParam("major") String major, Model m) {
		System.out.println("���п��� ����Ʈ ��û�� ����" + major);
		m.addAttribute("GStudentList", service.getGStudentsInfo(major));
		return "user/acceptResearcher";
	}
	
	//@PostMapping("/acceptResearcher")
	@RequestMapping(value="/acceptResearcher2", method=RequestMethod.POST)
	public String acceptResearcher2(@ModelAttribute(value="UserListVO") UserListVO userList, String major, RedirectAttributes ra) {
		System.out.println("���п��� ���� ���� ���� ��û / major: " + major);
		List<UserVO> uList = userList.getuList();
		for(UserVO u : uList) {
			System.out.println(u);
		}
		service.ChangePermit(uList);
		ra.addFlashAttribute("msg", "���� ���� ������ ����Ǿ����ϴ�.");
		return "redirect:/user/mypage";
	}
	
	@GetMapping("/deleteUser")
	public void deleteUser1() {}
	
	@PostMapping("/deleteUser")
	@ResponseBody
	public String deleteUser2(String userId) {
		System.out.println("ȸ�� Ż�� ��û: " + userId);
		service.DeleteUser(userId);
		return "OK";
	}
	
}
