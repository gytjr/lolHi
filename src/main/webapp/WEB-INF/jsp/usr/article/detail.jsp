<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="title" value="게시물 상세페이지"/>
<%@ include file="../part/head.jspf" %>


번호 : ${article.id}<br>
작성날짜 : ${article.regDate}<br>
수정날짜 : ${article.updateDate}<br>
작성자 : ${article.extra.writer}<br>
제목 : ${article.title}<br>
내용 : ${article.body}<br>
<a onclick="if(confirm('삭제하시겠습니까?') == false) return false;" href="doDelete?id=${article.id}">삭제</a>
	<a href="modify?id=${article.id}">수정</a>


<form action ='../reply/doWrite' method = 'post'>
	<input type="hidden" name= "relId" value = "${article.id}">
	<input type="hidden" name= "relTypeCode" value = "article">
	<h2>댓글 작성</h2>
	<div>
	<textarea rows = "8" placeholder="댓글 내용을 입력해주세요" name="body"></textarea>
	</div>
	<input type = "submit" value = "작성">
</form>

<h2>댓글</h2>
<c:forEach items= "${articleReplies}" var = "articleReply">
작성자 : ${articleReply.extra.writer}<br>
내용 : ${articleReply.body}<br>
작성날짜 :${articleReply.regDate}<hr>
</c:forEach>
<%@ include file="../part/foot.jspf" %>
