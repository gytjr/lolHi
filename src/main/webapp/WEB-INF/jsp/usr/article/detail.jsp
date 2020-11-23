<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="title" value="${board.name} 게시물 상세페이지" />
<%@ include file="../part/head.jspf"%>
<script>

var $addedReply = $('div[data-id="' + ${param.replyId} + '"]');
console.log($addedReply);
$addedReply.addClass("a");


</script>


<div>
	<div>
		<span style="display: inline-block; width: 100px; font-weight: bold;">
			번호 </span>
		${article.id}
	</div>
	<div>
		<span style="display: inline-block; width: 100px; font-weight: bold;">
			작성날짜 </span>
		${article.regDate}
	</div>
	<div>
		<span style="display: inline-block; width: 100px; font-weight: bold;">
			수정날짜 </span>
		${article.updateDate}
	</div>
	<div>
		<span style="display: inline-block; width: 100px; font-weight: bold;">
			작성자 </span>
		${article.extra.writer}
	</div>
	<div>
		<span style="display: inline-block; width: 100px; font-weight: bold;">
			제목 </span>
		${article.forPrintTitle}
	</div>
</div>
<div class="my-5">
		${article.forPrintBody}
</div>

<c:if test="${article.extra.actorCanDelete}">
	<a onclick="if(confirm('삭제하시겠습니까?') == false) return false;"
		href="doDelete?id=${article.id}" class="btn btn-danger btn-sm">삭제</a>
</c:if>
<c:if test="${article.extra.actorCanModify}">
	<a href="modify?id=${article.id}" class="btn btn-primary btn-sm">수정</a>
</c:if>
<div class="my-3">
	<a href="${listUrl}" class="btn btn-primary btn-sm">리스트</a>
	<a href="write" class="btn btn-primary btn-sm">글작성</a>
</div>


<form action='../reply/doWrite' method='post'>
	<input type="hidden" name="redirectUrl" value="${currentUri}">
	<input type="hidden" name="relId" value="${article.id}"> <input
		type="hidden" name="relTypeCode" value="article">
	
	<div class="input-group my-5">
	<span class="input-group-text">댓글 작성</span>
  <input type="text" class="form-control" name="body" placeholder="댓글 내용을 작성해주세요" aria-label="Recipient's username" aria-describedby="button-addon2">
  <div class="input-group-append">
  	<input type="submit" value="작성" class="btn btn-outline-secondary">
  </div>
  
</div>
</form>

<h3>댓글</h3>
<c:forEach items="${articleReplies}" var="articleReply">
	<div data-id="${articleReply.id}">
		작성자 : ${articleReply.extra.writer}
		<br>
		내용 : ${articleReply.body}
		<br>
		작성날짜 :${articleReply.regDate}
		<c:if test="${articleReply.extra.actorCanDelete}">
			<a onclick="if(confirm('삭제하시겠습니까?') == false) return false;"
				href="../reply/doDelete?id=${articleReply.id}&redirectUrl=${encodedCurrentUri}" class="btn btn-danger btn-sm">삭제</a>
		</c:if>
		<c:if test="${articleReply.extra.actorCanModify}">
			<a
				href="../reply/modify?id=${articleReply.id}&redirectUrl=${encodedCurrentUri}" class="btn btn-primary btn-sm">수정</a>
		</c:if>
		<hr>

	</div>
</c:forEach>
<%@ include file="../part/foot.jspf"%>
