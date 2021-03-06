<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="title" value="패스워드 입력" />
<%@ include file="../part/head.jspf"%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/js-sha256/0.9.0/sha256.min.js"></script>

<script>
	var checkloginPwFormSubmitDone = false;
	function checkloginPwFormSubmit(form) {
		if (checkloginPwFormSubmitDone) {
			alert('처리중입니다.');
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
		checkloginPwFormSubmitDone = true;
	}
</script>

<style>
.form-signin {
    max-width: 330px;
    padding: 15px;
    margin: 0 auto;
}
</style>


<form action="doCheckLoginPw" method="post" onsubmit="checkloginPwFormSubmit(this); return false;" class="form-signin">
	<input type="hidden" name="redirectUrl" value="/usr/member/modify">
	<div>
		비밀번호
		<input type="password" id="inputPassword" class="form-control" placeholder="Password"  name="loginPw">
	</div>
	<div>
	<br>
		<input type="submit" value="확인" class="btn btn-lg btn-primary btn-block">
	</div>

</form>

<%@ include file="../part/foot.jspf"%>