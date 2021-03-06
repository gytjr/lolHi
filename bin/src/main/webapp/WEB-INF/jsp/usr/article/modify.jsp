<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="title" value="게시물 수정" />
<%@ include file="../part/head.jspf"%>


<form action="doModify" method="post">

	<div>
		<input type="hidden" name="id" value="${article.id}"> <input
			type="hidden" name="updateDate" value="now()"> 번호 :
		${article.id}
		<br>
		작성날짜 : ${article.regDate}
		<br>
		수정날짜 : ${article.updateDate}
		<br>
		제목 : <input type="text" maxlength="30" value="${article.title}"
			name="title">
		<br>
		내용 : <input type="text" maxlength="30" value="${article.body}"
			name="body">
		<br>
		<input type="submit" value="수정">
		<br>
		<a href="list">리스트</a>
	</div>
</form>

<%@ include file="../part/foot.jspf"%>
