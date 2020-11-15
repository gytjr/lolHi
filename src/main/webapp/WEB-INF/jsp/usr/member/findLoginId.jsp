<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="title" value="회원 로그인 아이디 찾기" />
<%@ include file="../part/head.jspf"%>
<script>
	var findLoginIdFormSubmitDone = false;
	function findLoginIdFormSubmit(form) {
		if (findLoginIdFormSubmitDone) {
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
			alert('로그인 비밀번호 확인이 일치하지 않습니다.');
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
		if (form.v.value.length == 0) {
			alert('이메일을 입력해주세요.');
			form.email.focus();
			return;
		}
		form.submit();
		joinFormSubmitDone = true;
	}
</script>

<form action="doFindLoginId" method="post"
	onsubmit="findLoginId(this); return false;">
	<div>
		이름 : <input type="text" maxlength="30" name="name">
		<br>
		이메일 : <input type="email" maxlength="50" name="email">
	</div>
	<input type="submit" value="찾기">
	<br>
</form>

<%@ include file="../part/foot.jspf"%>
