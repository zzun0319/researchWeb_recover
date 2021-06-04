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

*{padding:0; margin:0}

input{width: 90%;}
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
			alert("해당 페이지의 접근 권한이 없습니다.");
			location.href="/user/mypage";
		</script>
	</c:if>

	<jsp:include page="../include/header.jsp" />
	
		<div>
		여기다가 만들어져있는 타임 슬롯 뿌릴거야
		</div>
		<form id="makeTimeslot" method="post">
		<input type="hidden" name="researchId" value="${researchInfo.researchId}">
		<input type="hidden" name="researcher" value="${researchInfo.researcher}">
			<c:if test="${researchInfo.researchType == '오프라인 실험' || researchInfo.researchType == '오프라인 설문' || researchInfo.researchType == '기타(오프라인)'}">
			<p>날짜 선택: <input type="date" name="researchDate"></p>
			<p>연구 시작 시간: <input type="time" name="startTime" id="startTime"></p>
			<p>연구 종료 시간: 연구 초기에 설정된 시간으로 자동으로 계산됩니다. </p>
				<p>연구 장소: 
				<select name="locationName" id="locationName">
					<option>=== 장소 선택 ===</option>
					<c:forEach var="location" items="${locations}">
					<option value="${location.locationName}">${location.locationName}(최대 수용인원: ${location.accommodate}명)</option>
					</c:forEach>
				</select>
				</p>
			<p>참가자 수: <input type="number" name="peopleLimit" id="peopleLimit"> 명</p>
			<input type="submit" value="타임슬롯 생성">
			</c:if>
		</form>
	<jsp:include page="../include/footer.jsp" />

</body>
</html>