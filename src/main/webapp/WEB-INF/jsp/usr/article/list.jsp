<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="title" value="게시물 목록"/>
<%@ include file="../part/head.jspf" %>

	
	<c:forEach items="${articles}" var="article">
		<div>
			번호 : ${article.id}<br> 
			제목 : <a href="detail?id=${article.id}">${article.title}</a><br>
			내용 : ${article.body}<br> 
			작성자 : ${article.memberId}<br>
			<a onclick="if(confirm('삭제하시겠습니까?') == false) return false;" href="doDelete?id=${article.id}">삭제</a> <a href="modify?id=${article.id}">수정</a>
			<hr>
		</div>
	</c:forEach>
	<a href="write">등록</a>
	
	<style>
		.selected {
			color :red;
		}
	</style>
	
	<h2>페이지</h2>
		<!-- 첫 페이지로 이동버튼이 노출될 필요가 있는지 여부 -->
		<c:set var="goFirstBtnNeedToShow"
			value="${page > pageMenuArmSize + 1}" />

		<!-- 마지막 페이지로 이동버튼이 노출될 필요가 있는지 여부 -->
		<c:set var="goLastBtnNeedToShow" value="true" />

		<!-- 첫 페이지로 이동버튼이 노출될 필요가 있다면 노출 -->
		<c:if test="${goFirstBtnNeedToShow}">
			<a href="?page=1">◀</a>
		</c:if>
		<c:forEach var="i" begin="${pageMenuStart}" end="${pageMenuEnd}">
		<c:set var="className" value="${i==page ? 'selected' : ''}"/>
			<a class = "${className}" href="?page=${i}">${i}</a>
			
			<!-- 방금 노출된 페이지 번호가 마지막 페이지의 번호였다면, 마지막으로 이동하는 버튼이 노출될 필요가 없다고 설정 -->
			<c:if test="${i == totalPage}">
				<c:set var="goLastBtnNeedToShow" value="false" />
			</c:if>
		</c:forEach>
		
		<!-- 마지막 페이지로 이동버튼이 노출될 필요가 있다면 노출 -->
		<c:if test="${goLastBtnNeedToShow}">
			<a href="?page=${totalPage}">▶</a>
		</c:if>
<%@ include file="../part/foot.jspf" %>
