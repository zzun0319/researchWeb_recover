import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.junhee.researchWeb.user.model.UserVO;
import com.junhee.researchWeb.user.repository.IUserMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class UserMapperTest {
	
	@Autowired
	private IUserMapper mapper;
	
	// ȸ������ ���� �׽�Ʈ
	@Test
	public void insertTest() {
		UserVO user1 = new UserVO();
		user1.setMemberType("�кλ�");
		user1.setUserId("participant12");
		user1.setUserPw("participant1");
		user1.setIdentifiedNum("201121725");
		user1.setUserName("ȫ�浿");
		user1.setEmail("participant1@naver.com");
		user1.setPhoneNum("01011112222");
		mapper.Register(user1);
		//////////////////////////////
		UserVO user2 = new UserVO();
		user2.setMemberType("���п���"); 
		user2.setUserId("researcher1");
		user2.setUserPw("researcher1");
		user2.setIdentifiedNum("201812345");
		user2.setUserName("�̼���");
		user2.setEmail("researcher1@naver.com");
		user2.setPhoneNum("01033334444");
		user2.setMajor("���������ɸ�");
		mapper.Register(user2);
		///////////////////////////////
		UserVO user3 = new UserVO();
		user3.setMemberType("����"); 
		user3.setUserId("instructor1");
		user3.setUserPw("instructor1");
		user3.setIdentifiedNum("201899999");
		user3.setUserName("�̰���");
		user3.setEmail("instructor1@naver.com");
		user3.setPhoneNum("01055556666");
		mapper.Register(user3);
		///////////////////////////////
		UserVO user4 = new UserVO();
		user4.setMemberType("��������"); 
		user4.setUserId("advisor1");
		user4.setUserPw("advisor1");
		user4.setIdentifiedNum("200100011");
		user4.setUserName("�����");
		user4.setEmail("advisor1@naver.com");
		user4.setPhoneNum("01099998888");
		user4.setMajor("���������ɸ�");
		mapper.Register(user4);
	}
	
	@Test
	public void LoginCheckTest() {
		
		//String id = "researcher1"; 
		//String pw = "researcher1";
		//int type = "���п���";
		String id = "participant1"; 
		String pw = "participant1";
		UserVO user = new UserVO();
		user.setUserId(id);
		user.setUserPw(pw);
		user.setMemberType("�кλ�");
		UserVO user2 = mapper.getOneUserInfo(user.getUserId());
		if(user2 == null) { // ���̵� ���� ��
			System.out.println("��ġ�ϴ� id�� �����ϴ�." ); // Ȯ�� �Ϸ�
		} else { // ��ġ�ϴ� ���̵� ���� ��
			if(!user2.getMemberType().equals(user.getMemberType())) { // ȸ���з��� �ٸ� ��
				System.out.println("ȸ���з��� ��ġ���� �ʽ��ϴ�." ); // Ȯ�� �Ϸ�
			} else if(!user2.getUserPw().equals(user.getUserPw())) { // ��й�ȣ�� ��ġ���� ���� ��
				System.out.println("��й�ȣ�� ��ġ���� �ʽ��ϴ�." ); // Ȯ�� �Ϸ�
			} else if(user2.getPermit() == 0) { // ������ ���� �ʾ��� ��
				System.out.println("���� ���Խ��ε��� ���� ������Դϴ�." ); // Ȯ�� �Ϸ�
			} else {
				System.out.println("�α��ο� �����Ͽ����ϴ�."); // Ȯ�� �Ϸ�
			}
		}
	}
	
	@Test
	public void UpdateUserInfoTest() {
		UserVO user = new UserVO();
		user.setUserId("participant1");
		user.setEmail("abc9999@gmail.com");
		user.setPhoneNum("01011111111");
		mapper.UpdateUserInfo(user);
		System.out.println(mapper.getOneUserInfo("participant1"));
	}
	
	@Test
	public void UpdatePasswordTest() {
		String userId = "advisor1";
		String userPw = "123456789";
		mapper.UpdatePassword(userId, userPw);
		System.out.println(mapper.getOneUserInfo(userId));
	}
	
	@Test
	public void DeleteUserTest() {
		mapper.DeleteUser("instructor1");
		if(mapper.getOneUserInfo("instructor1") == null) {
			System.out.println("��������");
		} else {
			System.out.println("��������");
		}
	}
	
	@Test
	public void getMajorTest() {
		List<UserVO> list = mapper.getMajors();
		for(UserVO u:list) {
			System.out.println(u.getMajor());
		}
		System.out.println(list.size());
	}
	
	@Test
	public void getUserInfoByIDNUM() {
		System.out.println(mapper.checkIdNum("201128888")); 
	}
	
	@Test
	public void getStudentsInfoTest() {
		List<UserVO> list = mapper.getGStudentsInfo("���������ɸ�");
		for(UserVO user : list) {
			System.out.println(user);
		}
	}

}
