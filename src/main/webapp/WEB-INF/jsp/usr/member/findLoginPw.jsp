<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="title" value="회원 로그인 비밀번호 찾기" />
<%@ include file="../part/head.jspf"%>
<script>
	var findLoginPwFormSubmitDone = false;
	function findLoginPwFormSubmit(form) {
		if (findLoginPwFormSubmitDone) {
			alert('처리중입니다.');
			return;
		}

		form.loginId.value = form.loginId.value.trim();
		if (form.loginId.value.length == 0) {
			alert('로그인 아이디를 입력해주세요.');
			form.loginId.focus();
			return;
		}
		form.name.value = form.name.value.trim();
		if (form.name.value.length == 0) {
			alert('이름을 입력해주세요.');
			form.name.focus();
			return;
		}
		form.email.value = form.email.value.trim();
		if (form.v.value.length == 0) {
			alert('이메일을 입력해주세요.');
			form.email.focus();
			return;
		}
		form.submit();
		findLoginPwFormSubmitDone = true;
	}
</script>

<style>
.form-signin {
    max-width: 330px;
    padding: 15px;
    margin: 0 auto;
}
</style>

<form action="doFindLoginPw" method="post"
	onsubmit="findLoginPwFormSubmit(this); return false;" class="form-signin">
	<div>
		ID 
		<input type="text" name="loginId" class="form-control" placeholder="아이디를 입력해주세요">
		<br>
		이메일
		<input type="email" class="form-control" name="email" placeholder="이메일을 입력해주세요">
	</div>
	<br>
	<input type="submit" value="찾기"  class="btn btn-lg btn-primary btn-block">
</form>

<%@ include file="../part/foot.jspf"%>
