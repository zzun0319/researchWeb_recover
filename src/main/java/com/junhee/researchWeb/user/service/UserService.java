package com.junhee.researchWeb.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.junhee.researchWeb.user.model.UserVO;
import com.junhee.researchWeb.user.repository.IUserMapper;

@Service
public class UserService implements IUserService {
	
	@Autowired
	private IUserMapper mapper;
	
	@Override
	public void Register(UserVO user) {
		System.out.println("���񽺱��� ȸ������ ���� ��û�� ����.");
		mapper.Register(user);
	}
	
	@Override
	public UserVO getOneUserInfo(UserVO user) {
		return mapper.getOneUserInfo(user.getUserId());
	}

	@Override
	public String getLoginCheckMessage(UserVO user) {
		UserVO userCheck = mapper.getOneUserInfo(user.getUserId());
		System.out.println("���� ��ü �α��� ��û ó��\n" + user);
		if(userCheck == null) {
			System.out.println("����: ��ġ�ϴ� id�� �����ϴ�.");
			return "��ġ�ϴ� id�� �����ϴ�.";
		} else { 
			if(!userCheck.getMemberType().equals(user.getMemberType())) { 
				System.out.println("����: ȸ���з��� ��ġ���� �ʽ��ϴ�.");
				return "ȸ���з��� ��ġ���� �ʽ��ϴ�.";
			} else if(!userCheck.getUserPw().equals(user.getUserPw())) {
				System.out.println("����: ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
				return "��й�ȣ�� ��ġ���� �ʽ��ϴ�.";
			} else if(userCheck.getPermit() != 1) {
				System.out.println("����: ���� ������ ���� �ʾ� �α����Ͻ� �� �����ϴ�.");
				return "���� ������ ���� �ʾ� �α����Ͻ� �� �����ϴ�.";
			} else {
				System.out.println("����: �α��� ����");
				return "�α��� ����";
			}
		}
	}

	@Override
	public void UpdateUserInfo(UserVO user) {
		mapper.UpdateUserInfo(user);
	}

	@Override
	public void UpdatePassword(String userId, String userPw) {
		mapper.UpdatePassword(userId, userPw);
	}

	@Override
	public void DeleteUser(String userId) {
		mapper.DeleteUser(userId);
	}

	@Override
	public List<String> getMajors() {
		List<UserVO> uList = mapper.getMajors();
		List<String> majorList = new ArrayList<>();
		for(UserVO user : uList) {
			System.out.println(user);
			if(user.getPermit() == 1) {
				majorList.add(user.getMajor());
			}
		}
		System.out.println("uList: " + uList.size());
		System.out.println("majorList: " + majorList.size());
		return majorList;
	}

	@Override
	public String checkIdNum(String identifiedNum) {
		UserVO user = mapper.checkIdNum(identifiedNum);
		if(user == null) {
			return "OK";
		} else {
			return "NO";
		}
	}

	@Override
	public List<UserVO> getGStudentsInfo(String major) {
		return mapper.getGStudentsInfo(major);
	}

	@Override
	public void ChangePermit(List<UserVO> uList) {
		
		for(UserVO user : uList) {
			mapper.ChangePermit(user);
		}
		
	}

}
