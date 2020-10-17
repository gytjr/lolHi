<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>

	<form action="doJoin">
		<div>ID : <input type="text" name ="loginId"><br>
			비밀번호 : <input type="password" name = "loginPw"><br>
			이름 : <input type="text" name = "name">
		</div>
		<input type="submit" value = "회원가입"><br>
	</form>

</body>
</html>