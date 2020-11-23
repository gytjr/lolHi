<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="title" value="${board.name} 게시물 목록" />
<%@ include file="../part/head.jspf"%>




<div class="d-flex justify-content-between my-3">
	
	<form>
		<div>
			<input type="text" name="searchKeyword" placeholder="검색어"
				value="${param.searchKeyword}" class="form-control col-md-6 d-inline">
			<input type="submit" value="검색" class="btn btn-primary btn-sm">
		</div>
	</form>
	<button type="button" class="btn btn-primary">
 	 총 게시물 수 <span class="badge badge-light">${totalCount}</span>
	</button>
</div>

<c:forEach items="${articles}" var="article">
	<c:set var="detailUrl"
		value="/usr/article-${board.code}/detail?id=${article.id}&listUrl=${encodedCurrentUri}" />
	<div class="card">
		<div class="card-body">
			<h5 class="card-title">
				<a href="${detailUrl}">${article.forPrintTitle}</a>
			</h5>
			<p class="card-text">${article.forPrintBody}</p>
		</div>
		<div class="card-footer">
			<small class="text-muted">작성자 : ${article.extra.writer}</small>
			<small class="text-muted"><c:if test="${article.extra.actorCanDelete}">
				<br>
				<a onclick="if(confirm('삭제하시겠습니까?') == false) return false;"
					href="doDelete?id=${article.id}" class="btn btn-danger btn-sm">삭제</a>
			</c:if>
			<c:if test="${article.extra.actorCanModify}">
				<a href="modify?id=${article.id}" class="btn btn-primary btn-sm">수정</a>
			</c:if></small>
		</div>

		
	</div>
	<div>

		<hr>
	</div>
</c:forEach>



<a href="write" class="btn btn-primary">글쓰기</a>

<style>
.selected {
	color: red;
}
</style>



<!-- 첫 페이지로 이동버튼이 노출될 필요가 있는지 여부 -->
<c:set var="goFirstBtnNeedToShow" value="${page > pageMenuArmSize + 1}" />

<!-- 마지막 페이지로 이동버튼이 노출될 필요가 있는지 여부 -->
<c:set var="goLastBtnNeedToShow" value="true" />

<c:if test="${0 == totalPage}">
	<c:set var="goLastBtnNeedToShow" value="false" />
	<c:set var="goFirstBtnNeedToShow" value="false" />
</c:if>

<nav aria-label="Page navigation">
  <ul class="pagination justify-content-center">
  <c:if test="${goFirstBtnNeedToShow}">
    <li class="page-item">
      <a class="page-link" href="?page=1&searchKeyword=${param.searchKeyword}">&laquo;</a>
    </li>
    </c:if>
    <c:forEach var="i" begin="${pageMenuStart}" end="${pageMenuEnd}">
    <c:set var="className" value="${i==page ? 'selected' : ''}" />
    <li class="page-item"><a class="${className} page-link"
		href="?page=${i}&searchKeyword=${param.searchKeyword}">${i}</a></li>
		<!-- 방금 노출된 페이지 번호가 마지막 페이지의 번호였다면, 마지막으로 이동하는 버튼이 노출될 필요가 없다고 설정 -->
<c:if test="${i == totalPage}">
		<c:set var="goLastBtnNeedToShow" value="false" />
	</c:if>
    </c:forEach>
    <!-- 마지막 페이지로 이동버튼이 노출될 필요가 있다면 노출 -->
    <c:if test="${goLastBtnNeedToShow}">
    <li class="page-item">
      <a class="page-link" href="?page=${totalPage}&searchKeyword=${param.searchKeyword}">&raquo;</a>
    </li>
    </c:if>
  </ul>
</nav>


<%@ include file="../part/foot.jspf"%>
