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
		<h2>[연구 참여 중인 과목]</h2>
		<c:if test="${myClasses == null}">
			연구 참여 중인 과목이 없습니다. <br>
		</c:if>
		<c:if test="${myClasses != null}">
		<table border="1">
			<tr>
				<td>과목명</td>
				<td>삭제하기</td>
			</tr>
			<c:forEach var="myClass" items="${myClasses}">
			<tr>
				<td>${myClass.className}, ${myClass.classSchedule}</td>
				<td><button onclick="location.href='/research/DeleteClass?classId=${myClass.classId}&teacherId=${myClass.teacherId}'">삭제</button></td>
			</tr>
			</c:forEach>
		</table>
		</c:if>
	<jsp:include page="../include/footer.jsp" />

</body>
</html>