<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
	width: 100%;
}

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
			alert("접근 권한이 없습니다.");
			location.href="/user/login";
		</script>
	</c:if>

	<jsp:include page="../include/header.jsp" />
	<h2>[참여 중인 연구들]</h2>
	<table border="1">
	<tr>
		<td>연구 제목</td>
		<td>날짜 및 시간</td>
		<td>비고</td>
	</tr>
	<c:forEach var="research" items="${rList}">
		<c:forEach var="Tslot" items="${applyList}">
			<c:if test="${Tslot.researchId == research.researchId}">
				<tr>
					<td>${research.researchTitle}</td>
					<td><fmt:formatDate value="${Tslot.startTime}" pattern="yyyy년 MM월 dd일 HH시 mm분" /> ~ <fmt:formatDate value="${Tslot.endTime}" pattern="HH시 mm분" /></td>
					<td>(취소/취소불가)버튼 &nbsp;&nbsp; (대기/참여완료/불참/무단불참)</td>
				</tr>
			</c:if>
		</c:forEach>
	</c:forEach>
	</table>
	<hr>
		수강 중인 클래스 <br>
		<c:forEach var="classes" items="${allClasses}">
			<c:forEach var="tClass" items="${takingClasses}">
			 	<c:if test="${classes.classId == tClass.classId}">
			 	- ${classes.className}(${classes.classSchedule}), ${classes.teacherName} : ${tClass.fillCredit}점 / ${tClass.requiredCredit}점 <br>
			 	</c:if>
			</c:forEach>
		</c:forEach>
	<jsp:include page="../include/footer.jsp" />
	
</body>
</html>

<script>
	const result = "${msg}"
	if(result != "" && result.length > 0){
		alert(result);
	}
</script>