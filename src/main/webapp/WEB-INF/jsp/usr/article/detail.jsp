<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="title" value="${board.name} 게시물 상세페이지"/>
<%@ include file="../part/head.jspf" %>
<script>

var $addedReply = $('div[data-id="' + ${param.replyId} + '"]');
console.log($addedReply);
$addedReply.addClass("a");


</script>

번호 : ${article.id}<br>
작성날짜 : ${article.regDate}<br>
수정날짜 : ${article.updateDate}<br>
작성자 : ${article.extra.writer}<br>
제목 : ${article.title}<br>
내용 : ${article.body}<br>
<a href="${listUrl}">리스트</a>
<c:if test="${article.extra.actorCanDelete}">
<a onclick="if(confirm('삭제하시겠습니까?') == false) return false;" href="doDelete?id=${article.id}">삭제</a>
</c:if>
<c:if test="${article.extra.actorCanModify}">
	<a href="modify?id=${article.id}">수정</a>
</c:if>
<a href="write">글작성</a>


<form action ='../reply/doWrite' method = 'post'>
	<input type="hidden" name= "redirectUrl" value = "${currentUri}">
	<input type="hidden" name= "relId" value = "${article.id}">
	<input type="hidden" name= "relTypeCode" value = "article">
	<h2>댓글 작성</h2>
	<div>
	<textarea rows = "8" placeholder="댓글 내용을 입력해주세요" name="body"></textarea>
	</div>
	<input type="submit" value="작성">
</form>

<h2>댓글</h2>
<c:forEach items= "${articleReplies}" var = "articleReply">
<div data-id="${articleReply.id}">
작성자 : ${articleReply.extra.writer}<br>
내용 : ${articleReply.body}<br>
작성날짜 :${articleReply.regDate}
<c:if test="${articleReply.extra.actorCanDelete}">
	<a onclick="if(confirm('삭제하시겠습니까?') == false) return false;" href="../reply/doDelete?id=${articleReply.id}&redirectUrl=${encodedCurrentUri}">삭제</a>
</c:if>
<c:if test="${articleReply.extra.actorCanModify}">
	<a href="../reply/modify?id=${articleReply.id}&redirectUrl=${encodedCurrentUri}">수정</a>
</c:if>
<hr>

</div>
</c:forEach>
<%@ include file="../part/foot.jspf" %>
