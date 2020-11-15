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
		findLoginIdFormSubmitDone = true;
	}
</script>

<form action="doFindLoginId" method="post"
	onsubmit="findLoginIdFormSubmit(this); return false;">
	<div>
		이름 : <input type="text" maxlength="30" name="name">
		<br>
		이메일 : <input type="email" maxlength="50" name="email">
	</div>
	<input type="submit" value="찾기">
	<br>
</form>

<%@ include file="../part/foot.jspf"%>
