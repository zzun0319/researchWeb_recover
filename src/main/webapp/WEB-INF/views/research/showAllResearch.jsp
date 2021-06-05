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
	<h2>개설 중인 연구들</h2>
	<p>
		<table border="1">
			<tr>
				<td>연구 제목</td>
				<td>보상</td>
				<td>타임 슬롯</td>
			</tr>
			<c:forEach var="research" items="${allResearch}">
				<tr>
					<td><a href="/research/showOneResearch?researchId=${research.researchId}">${research.researchTitle}</a></td>
					<td>${research.rewardType} ${research.rewardValue} ${research.rewardType == "학점"? "점" : research.rewardType == "참가비"? "원" : "개"}</td>
					<td> <button onclick="location.href='/research/selectDate?researchId=${research.researchId}&studentId=${member.userId}'">연구참여 날짜선택</button></td>
				</tr>
			</c:forEach>
		</table>
	</p>
	<hr style="border: solid 5px black;">
	<br>
	<a href="/research/inquireDate">날짜로 참여가능한 연구 조회하기</a>
	
	<jsp:include page="../include/footer.jsp" />

</body>
</html>