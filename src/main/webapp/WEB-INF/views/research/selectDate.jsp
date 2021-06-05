<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>

<!-- Core theme CSS (includes Bootstrap)-->
<link rel="icon" type="image/x-icon" href="<c:url value="/assets/favicon.ico" />" />
<link href="<c:url value="/css/styles.css" />" rel="stylesheet" />
<script type="text/javascript" src="<c:url value="/js/jquery-3.5.1.js" />"></script>

</head>
<body>
	<c:if test="${member == null}">
		<script>
			alert("로그인 후 이용 가능합니다.");
			location.href="/user/login";
		</script>
	</c:if>
	<c:if test="${member.memberType != '학부생'}">
		<script>
			alert("접근 권한이 없습니다.");
			location.href="/user/mypage";
		</script>
	</c:if>


	<jsp:include page="../include/header.jsp" />
	<div>
	<h3>연구 정보</h3>
	- 연구제목: ${researchInfo.researchTitle} <br>
	- 연구 종류: ${researchInfo.researchType} <br>
	- 연구 목적: ${researchInfo.researchPurpose} <br>
	- 보상: ${researchInfo.rewardType} ${researchInfo.rewardValue} ${researchInfo.rewardType == '참가비' ? "원" : researchInfo.rewardType == '학점' ? "학점" : "개"}<br>
	</div>
	
	<div>
			<h3>[${researchInfo.researchTitle}의 타임슬롯]</h3>
			<form action="/research/applyTimeslot" method="post">
			<input type="hidden" name="researchId" value="${researchInfo.researchId}">
			<input type="hidden" name="studentId" value="${member.userId}">
			<table border="1">
				<tr>
					<td>날짜 및 시간</td>
					<td>참가인원</td>
					<td>연구 장소</td>
					<td>참가하기</td>
				</tr>
				<c:forEach var="timeslot" items="${timeslots}">
					<tr>
						<td><fmt:formatDate value="${timeslot.startTime}" pattern="yyyy년 MM월 dd일 HH시 mm분"/>~<fmt:formatDate value="${timeslot.endTime}" pattern="HH시 mm분" /></td>
						<td>${timeslot.applyNumber}명 / ${timeslot.peopleLimit}명</td>
						<td>${timeslot.locationName}</td>
						<td><input type="radio" name="timeslotId" value="${timeslot.timeslotId}">참가</td>
					</tr>
				</c:forEach>
			</table>
			연구 참여 학점을 받는다면 어느 클래스의 학점에 반영시킬 것인가요? 
			<select name="takingClassId">
				<option> == 연구 참여 점수를 받을 클래스 선택 == </option>
				<c:forEach var="tClass" items="${takingClasses}">
					<c:forEach var="cls" items="${cList}">
					<c:if test="${cls.classId == tClass.classId}">
					<option value="${tClass.pairNumber}">${cls.className}(${cls.teacherName}) / ${cls.classSchedule}</option>
					</c:if>
					</c:forEach>
				</c:forEach>
			</select>
			<input type="submit" value="참여하기" onclick="confirm('정말 참여하시겠습니까?')">
			</form>
		</div>
	
	<jsp:include page="../include/footer.jsp" />
</body>
</html>