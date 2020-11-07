<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="title" value="댓글 수정" />
<%@ include file="../part/head.jspf"%>


<form action="doModify" method="post">

	<div>
	<input type="hidden" name= "redirectUrl" value = "${param.redirectUrl}">
		<input type="hidden" name="id" value="${reply.id}"> <input
			type="hidden" name="updateDate" value="now()"> 
		번호 : ${reply.id}
		<br>
		작성날짜 : ${reply.regDate}
		<br>
		수정날짜 : ${reply.updateDate}
		<br>
		내용 : <input type="text" maxlength="30" value="${reply.body}"
			name="body">
		<br>
		<input type="submit" value="수정">
		<br>
		<a href="${param.redirectUrl}">돌아가기</a>
	</div>
</form>

<%@ include file="../part/foot.jspf"%>
