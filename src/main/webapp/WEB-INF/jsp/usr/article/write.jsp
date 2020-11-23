<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="title" value="${board.name} 게시물 작성" />
<%@ include file="../part/head.jspf"%>

<form action="doWrite" method="post">


	<div class="form-group">
		<label for="exampleInputTitle">제목</label>
		<input type="text" class="form-control" name="title"> <small id="emailHelp"
			class="form-text text-muted">게시물 제목을 입력해주세요</small>
	</div>
	<div class="form-group">
		<label for="exampleInputbody">내용</label>
		<textarea class="form-control" name="body" id="exampleInputbody" rows="3"></textarea>
	</div>

	<button type="submit" class="btn btn-primary">작성</button>
	<a href="list" class="btn btn-primary">리스트</a>
</form>

<%@ include file="../part/foot.jspf"%>
