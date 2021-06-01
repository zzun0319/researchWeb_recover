package com.junhee.researchWeb.user.service;

import java.util.List;

import com.junhee.researchWeb.user.model.UserVO;

public interface IUserService {
	
	// ȸ�� ���� ���
	void Register(UserVO user);
		
	// �α��� üũ ��� (���� ���� ���ε� üũ�ؾ���)
	UserVO getOneUserInfo(UserVO user);
	
	//�α��� üũ ���
	String getLoginCheckMessage(UserVO user);
		
	// ȸ�� ���� ���� ���
	void UpdateUserInfo(UserVO user);
		
	// ��� ��ȣ ���� ���
	void UpdatePassword(String userId, String userPw);
	
	// ȸ�� Ż�� ���
	void DeleteUser(String userId);
	
	// ���� ����Ʈ ��������
	List<String> getMajors();
	
	// �й� �ߺ� üũ
	String checkIdNum(String identifiedNum);
	
	// ���п��� ����Ʈ ��ȯ
	List<UserVO> getGStudentsInfo(String major);
	
	// ���� �㰡 ����
	void ChangePermit(List<UserVO> uList);
	
}
