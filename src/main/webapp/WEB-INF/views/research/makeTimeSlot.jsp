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
	width: 80%;
}

*{padding:0; margin:0}

input{width: 90%;}
textarea{width: 100%; height: 100px;}

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
			<h3>[타임슬롯]</h3>
			<table border="1">
				<tr>
					<td>날짜 및 시간</td>
					<td>참가인원</td>
					<td>신청 인원</td>
					<td>연구 장소</td>
					<td>상태변경</td>
				</tr>
				<c:forEach var="timeslot" items="${timeslotList}">
					<tr>
						<td><fmt:formatDate value="${timeslot.startTime}" pattern="yyyy년 MM월 dd일 HH시 mm분"/>~<fmt:formatDate value="${timeslot.endTime}" pattern="HH시 mm분" /></td>
						<td>${timeslot.applyNumber}명 / ${timeslot.peopleLimit}명</td>
						<td>아직 구현 안 됨</td>
						<td>${timeslot.locationName}</td>
						<td>상태변경(버튼 구현 안 됨)</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		
		<hr style="border: solid 5px black;">
		<c:if test="${researchInfo.researchType == '오프라인 실험' || researchInfo.researchType == '오프라인 설문' || researchInfo.researchType == '기타(오프라인)'}">
			<br> <h3>[타임슬롯 만들기]</h3>
			<form id="makeTimeslot" method="post">
			<input type="hidden" name="researchId" value="${researchInfo.researchId}">
			<input type="hidden" name="researcher" value="${researchInfo.researcher}">
			<table border="1">
				<tr>
					<td>날짜 선택</td>
					<td id="rightColumn"><input type="date" name="researchDate"></td>
				</tr>
				<tr>
					<td>연구 시작 시간</td>
					<td id="rightColumn"><input type="time" name="startTime" id="startTime"></td>
				</tr>
				<tr>
					<td>연구 종료 시간</td>
					<td id="rightColumn">연구 초기에 설정된 시간으로 자동으로 계산됩니다.</td>
				</tr>
				<tr>
					<td>연구 장소</td>
					<td id="rightColumn">
						<select name="locationName" id="locationName">
							<option>=== 장소 선택 ===</option>
							<c:forEach var="location" items="${locations}">
							<option value="${location.locationName}">${location.locationName}(최대 수용인원: ${location.accommodate}명)</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td>참가자 수</td>
					<td id="rightColumn"><input type="number" name="peopleLimit" id="peopleLimit"> 명</td>
				</tr>
				<tr><td colspan="2" align="center"><input type="submit" value="타임슬롯 생성" style="background-color: skyblue;"></td></tr>
			</table>
			</form>
		</c:if>
		<c:if test="${researchInfo.researchType == '온라인 실험' || researchInfo.researchType == '온라인 설문' || researchInfo.researchType == '기타(온라인)'}">
			<form method="post" action="/research/attachUrl">
				<p>링크 첨부하기: <input type="url" name="researchUrl"></p>
				<input type="submit" value="링크 첨부">
			</form>
		</c:if>
		
	<jsp:include page="../include/footer.jsp" />

</body>
</html>

<script>
	const result = "${msg}"
	if(result != "" && result.length > 0){
		alert(result);
	}
</script>