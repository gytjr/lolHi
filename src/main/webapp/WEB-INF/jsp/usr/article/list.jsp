<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 목록</title>
</head>
<body>
<h1>게시물 목록</h1>
<c:forEach items= "${articles}" var = "article">
<div>
	번호 : ${article.id}<br>
	제목 : ${article.title}<br>
	내용 : ${article.body}<hr>
</div>
</c:forEach>

</body>
</html>