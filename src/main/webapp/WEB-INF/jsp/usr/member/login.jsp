<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>

	<form action="doLogin" method = "post">
		<div>ID : <input type="text" name ="loginId"><br>
			비밀번호 : <input type="password" name = "loginPw"><br>
		</div>
		<input type="submit" value = "로그인"><br>
	</form>

</body>
</html>