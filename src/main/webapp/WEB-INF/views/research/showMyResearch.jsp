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
<script type="text/javascript" src="<c:url value="/js/jQuery.form.min.js" />"></script>

<style type="text/css">
#rightColumn{
	width: 80%;
}

*{padding:0; margin:0}

input{width: 100%;}
textarea{width: 100%; height: 100px;}

td{width:40%;}

</style>

</head>
<body>

	<c:if test="${member == null}">
		<script>
			alert("로그인 후 이용 가능합니다.");
			location.href="/user/login";
		</script>
	</c:if>
	<c:if test="${member.memberType != '대학원생'}">
		<script>
			alert("대학원생이 아닙니다.");
			location.href="/user/login";
		</script>
	</c:if>

	<jsp:include page="../include/header.jsp" />
	
		<p><h2>나의 연구들</h2></p>
		<p>
			<c:if test="${myResearchList == null}">
				개설 신청한 연구가 없습니다.
			</c:if>
			<c:if test="${myResearchList != null}">
			<table border="1">
				<tr>
					<td><b>연구제목</b></td>
					<td><b>상태</b></td>
					<td><b>스케줄 보기</b></td>
				</tr>
				<c:forEach var="research" items="${myResearchList}">
					<tr>
						<td><a href="/research/showOneResearch?researchId=${research.researchId}">${research.researchTitle}</a></td>
						<td>${research.permit == 0 ? "승인 대기 중" : research.permit == 1 ? "승인 완료" : "미승인"}</td>
						<td>${research.permit == 1 ? "스케줄 보기" : ""}</td>
					</tr>
				</c:forEach>
			</table>
			</c:if>
		</p>
	
	<jsp:include page="../include/footer.jsp" />

</body>
</html>