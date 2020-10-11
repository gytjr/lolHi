<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 작성</title>
</head>
<body>
<h1>게시물 작성</h1>

<form action="doWrite"  method= "post">

<div>
	제목 : <input type="text" maxlength="30" placeholder="제목" name = "title"> <br>
	내용 : <input type="text" maxlength="30" placeholder="내용" name = "body"><br>
	<input type="submit" value = "작성">
</div>
</form>




</body>
</html>