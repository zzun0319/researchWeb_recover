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
	<c:if test="${member.memberType != '학부생'}">
		<script>
			alert("해당 페이지의 접근 권한이 없습니다.");
			location.href="/user/mypage";
		</script>
	</c:if>

	<jsp:include page="../include/header.jsp" />
		<c:if test="${allClasses == null}">
			연구 참여 과목으로 등록된 클래스가 없습니다.
		</c:if>
		<c:if test="${allClasses != null}">
			<form method="post">
			<input type="hidden" name="studentId" value="${member.userId}">
			<input type="hidden" name="studentName" value="${member.userName}">
			내가 수강 중인 과목 추가 
				<select name="classId">
					<option>=== 수강 중인 과목 선택 ===</option>
					<c:forEach var="classes" items="${allClasses}">
					<option value="${classes.classId}">${classes.className} / ${classes.classSchedule} / ${classes.teacherName}</option>
					</c:forEach>
				</select>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button type="submit">+</button>
			</form>
		</c:if>
		<hr>
		수강 중인 클래스 <br>
		<c:forEach var="classes" items="${allClasses}">
			<c:forEach var="tClass" items="${takingClasses}">
			 	<c:if test="${classes.classId == tClass.classId}">
			 	- ${classes.className}(${classes.classSchedule}), ${classes.teacherName} : ${tClass.fillCredit}점 / ${tClass.requiredCredit}점
			 	</c:if>
			</c:forEach>
		</c:forEach>
	<jsp:include page="../include/footer.jsp" />

</body>
</html>