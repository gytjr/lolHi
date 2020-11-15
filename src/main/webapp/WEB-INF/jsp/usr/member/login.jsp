<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="title" value="로그인" />
<%@ include file="../part/head.jspf"%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/js-sha256/0.9.0/sha256.min.js"></script>

<script>
	var loginFormSubmitDone = false;
	function loginFormSubmit(form) {
		if (loginFormSubmitDone) {
			alert('처리중입니다.');
			return;
		}
		form.loginId.value = form.loginId.value.trim();
		if (form.loginId.value.length == 0) {
			alert('로그인 아이디를 입력해주세요.');
			form.loginId.focus();
			return;
		}
		form.loginPw.value = form.loginPw.value.trim();
		if (form.loginPw.value.length == 0) {
			alert('로그인 비번을 입력해주세요.');
			form.loginPw.focus();
			return;
		}
		form.loginPw.value = sha256(form.loginPw.value);
		
		form.submit();
		loginFormSubmitDone = true;
	}
</script>

<form action="doLogin" method="post" onsubmit="loginFormSubmit(this); return false;">
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