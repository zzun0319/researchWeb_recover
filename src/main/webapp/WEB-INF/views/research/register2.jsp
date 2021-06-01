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
	width: 100%;
}

*{padding:0; margin:0}
html, body, .wrap{width: 100%;}
.clear{clear:both;}
.wrap>.fileBox{padding: 20px;}
.fileBox input, textarea{width: 100%;}
.fileBox .fileDrop{display: inline-block;width: 700px;height: 75px;border: 1px solid #000;overflow: auto;}
.fileDrop .fileList .fileName{padding-left: 20px;}
.fileDrop .fileList .fileSize{padding-right: 20px; float:right;}

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
	
	<div class="wrap">
        <div class="fileBox">
            <form method="post">
            <input type="hidden" name="researcher" value="${member.userId}">
            <c:if test="${rewardType != '기타'}">
            	<input type="hidden" name="rewardType" value="${rewardType}">
            </c:if>
            <input type="hidden" name="researchType" value="${researchType}">
                <table border="1">
                    <tr>
                    	<td>연구 제목</td>
                        <td><input type="text" name="researchTitle"></td>
                    </tr>
                    <tr>
                    	<td>연구 목적</td>
                        <td><input type="text" name="researchPurpose"></td>
                    </tr>
                    <tr>
                    	<td>연구 방법 및 절차</td>
                        <td><textarea name="researchMethod"></textarea></td>
                    </tr>
                    <tr>
                    	<td>소요시간</td>
                        <td><input type="number" name="takeTime"> 분</td>
                        <!-- 학점은 그냥 소요시간으로 알아서 서비스에서 계산해서 넣어버리기 -->
                    </tr>
                    <c:if test="${rewardType=='참가비'}">
                    <tr>
                    	<td>참가비</td>
                    	<td><input type="number" name="rewardValue"> 원</td>
                    </tr>
                    </c:if>
                    <c:if test="${rewardType=='기타'}">
                    <tr>
                    	<td>무엇을 (ex 초코파이를)</td>
                    	<td><input type="text" name="rewardType"></td>
                    </tr>
                    <tr>
                    	<td>얼마나</td>
                    	<td><input type="number" name="rewardValue"></td>
                    </tr>
                    </c:if>
                </table>
                <div class="buttonBox">
                    <button type="submit" id="save">연구 신청 완료하기</button>
                </div>
            </form>
        </div>
    </div>

	<jsp:include page="../include/footer.jsp" />

</body>
</html>