<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="title" value="${board.name} 게시물 작성" />
<%@ include file="../part/head.jspf"%>

<form action="doWrite" method="post">

	<div>
		제목 : <input type="text" maxlength="30" placeholder="제목" name="title">
		<br>
		내용 : <input type="text" maxlength="30" placeholder="내용" name="body">
		<br>
		<input type="submit" value="작성">
	</div>
	<a href="list">리스트</a>
</form>

<%@ include file="../part/foot.jspf"%>
