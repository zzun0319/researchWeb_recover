<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>연구개설 페이지</title>

<link rel="icon" type="image/x-icon" href="<c:url value="/assets/favicon.ico" />" />

<!-- Core theme CSS (includes Bootstrap)-->
<link href="<c:url value="/css/styles.css" />" rel="stylesheet" />
<script type="text/javascript" src="<c:url value="/js/jquery-3.5.1.js" />"></script>

<style type="text/css">
#rightColumn{
	width: 80%;
}

input{width:100%;}

</style>

</head>
<body>

	<c:if test="${member == null}">
		<script>
			alert("로그인 후 이용 가능합니다.");
			location.href="/user/login";
		</script>
	</c:if>
	<c:if test="${member.memberType == '대학원생' || member.memberType == '학부생'}">
		<script>
			alert("해당 페이지의 접근 권한이 없습니다.");
			location.href="/user/mypage";
		</script>
	</c:if>

	<jsp:include page="../include/header.jsp" />
		<h2> 연구 참여 과목 신청하기 </h2>
		<form action="/research/addClassForResearch" method="post">
		
			<input type="hidden" name="teacherName" value="${member.userName}">
			<input type="hidden" name="teacherId" value="${member.userId}">
			<table border="1">
				<tr>
					<td>과목명</td>
					<td id="rightColumn"><input name="className" placeholder="과목명을 입력해주세요."></td>
				</tr>
				<tr>
					<td>시간표</td>
					<td id="rightColumn"><input name="classSchedule" placeholder="예) 월D목D"></td>
				</tr>
				<tr>
					<td>채워야 할 연구 학점</td>
					<td><input type="number" name="needCredit" placeholder="학생들이 채워야 할 총 연구학점을 입력해주세요."></td>
				</tr>
			</table>
			<input type="submit" value="연구 참여 과목 추가하기">
		</form>
	<jsp:include page="../include/footer.jsp" />

</body>
</html>