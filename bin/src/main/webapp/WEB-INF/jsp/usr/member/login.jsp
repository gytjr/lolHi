<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="title" value="로그인" />
<%@ include file="../part/head.jspf"%>


<form action="doLogin" method="post">
	<div>
		ID : <input type="text" name="loginId">
		<br>
		비밀번호 : <input type="password" name="loginPw">
		<br>
	</div>
	<input type="submit" value="로그인">
	<br>
</form>

<%@ include file="../part/foot.jspf"%>