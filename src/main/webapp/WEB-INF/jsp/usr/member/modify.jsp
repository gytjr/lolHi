<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="title" value="회원정보 수정" />
<%@ include file="../part/head.jspf"%>


<form action="doModify" method="post">

	<div>
		<input type="hidden" name="updateDate" value="now()"> <input
			type="hidden" name="checkLoginPwAuthCode"
			value="${param.checkLoginPwAuthCode}">

		
		<ul class="list-group list-group-flush">
			<li class="list-group-item">번호 : ${loginedMember.id}</li>
			<li class="list-group-item">가입날짜 : ${loginedMember.regDate}</li>
			<li class="list-group-item">로그인 아이디 : ${loginedMember.loginId}</li>
			<li class="list-group-item">이름 : <input type="text" value="${loginedMember.name}"
			name="name"></li>
		</ul>
		<div class="d-flex justify-content-center">
		<input type="submit" value="수정" class="btn btn-primary mr-3">
		<a onclick="history.back()" button type="button" class="btn btn-dark">뒤로가기</a>
		</div>
		
	</div>
</form>

<%@ include file="../part/foot.jspf"%>
