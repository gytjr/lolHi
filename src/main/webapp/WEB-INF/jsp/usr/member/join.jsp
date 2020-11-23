<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="title" value="회원가입" />
<%@ include file="../part/head.jspf"%>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/js-sha256/0.9.0/sha256.min.js"></script>

<script>
	var joinFormSubmitDone = false;
	function joinFormSubmit(form) {
		if (joinFormSubmitDone) {
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
		form.loginPwConfirm.value = form.loginPwConfirm.value.trim();
		if (form.loginPw.value != form.loginPwConfirm.value) {
			alert('로그인 비번 확인이 일치하지 않습니다.');
			form.loginPwConfirm.focus();
			return;
		}
		form.name.value = form.name.value.trim();
		if (form.name.value.length == 0) {
			alert('이름을 입력해주세요.');
			form.name.focus();
			return;
		}
		form.email.value = form.email.value.trim();
		if (form.email.value.length == 0) {
			alert('이메일을 입력해주세요.');
			form.email.focus();
			return;
		}
		form.loginPw.value = sha256(form.loginPw.value);
		form.loginPwConfirm.value = '';
		form.submit();
		joinFormSubmitDone = true;
	}
</script>

<style>
.form-signin {
	max-width: 330px;
	padding: 15px;
	margin: 0 auto;
}

input {
	margin: 0 0 15px 0;
}
</style>


<form action="doJoin" method="post"
	onsubmit="joinFormSubmit(this); return false;" class="form-signin">
	<div>
		ID 
		<input type="text" name="loginId" class="form-control" placeholder="아이디를 입력해주세요">
		비밀번호
		<input
			type="password" id="inputPassword" class="form-control"	placeholder="Password" name="loginPw">
		비밀번호 확인
		<input type="password" class="form-control" placeholder="비밀번호를 재입력해주세요" name="loginPwConfirm">
		이름
		<input type="text" class="form-control" name="name" placeholder="이름을 입력해주세요">
		이메일
		<input type="email" class="form-control" name="email" placeholder="이메일을 입력해주세요">
		<br>
		<input type="submit" value="회원가입" class="btn btn-lg btn-primary btn-block">
	</div>
	<br>
</form>

<%@ include file="../part/foot.jspf"%>
