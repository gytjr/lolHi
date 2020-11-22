<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="title" value="회원정보 수정" />
<%@ include file="../part/head.jspf"%>


<form action="doModify" method="post">

	<div>
		<input type="hidden" name="updateDate" value="now()">
		<input type="hidden" name="checkLoginPwAuthCode" value="${param.checkLoginPwAuthCode}">
		 번호 :	${loginedMember.id} <br>
		 가입날짜 : ${loginedMember.regDate} <br>
		 로그인 아이디 : ${loginedMember.loginId} <br>
		 이름 : <input type="text" maxlength="30" value="${loginedMember.name}" name="name"> <br> 
		  <input type="submit" value="수정"> <br> 
		  <a onclick="history.back()">뒤로가기</a>
	</div>
</form>

<%@ include file="../part/foot.jspf"%>
