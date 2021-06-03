<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="icon" type="image/x-icon" href="<c:url value="/assets/favicon.ico" />" />

<!-- Core theme CSS (includes Bootstrap)-->
<link href="<c:url value="/css/styles.css" />" rel="stylesheet" />
<link href="<c:url value="/css/styles2.css" />" rel="stylesheet" />
<script type="text/javascript" src="<c:url value="/js/jquery-3.5.1.js" />"></script>

<style type="text/css">
#rightColumn{
	width: 100%;
}

</style>
<title>연구 참여 날짜 선택하기</title>
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
			alert("접근권한이 없습니다.");
			location.href="/user/login";
		</script>
	</c:if>

	<jsp:include page="../include/header.jsp" />
	
		<div class="calendar">
			<div class="header">
				<div class="year-month"></div>
				<div class="nav">
					<button class="nav-btn go-prev" onclick="prevMonth()">&lt;</button>
					<button class="nav-btn go-today" onclick="goToday()">Today</button>
					<button class="nav-btn go-next" onclick="nextMonth()">&gt;</button>
				</div>
			</div>
			<div class="main">
				<div class="days">
					<div class="day">일</div>
					<div class="day">월</div>
					<div class="day">화</div>
					<div class="day">수</div>
					<div class="day">목</div>
					<div class="day">금</div>
					<div class="day">토</div>
				</div>
				<div class="dates"></div>
			</div>
		</div>
		<script src="<c:url value="/js/index.js" />">
		</script>
	<jsp:include page="../include/footer.jsp" />

</body>
</html>