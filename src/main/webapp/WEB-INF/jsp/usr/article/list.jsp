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
	제목 : <a href="detail?id=${article.id}">${article.title}</a><br>
	내용 : ${article.body}<br>
	<a onclick="if(confirm('삭제하시겠습니까?') == false) return false;" href="doDelete?id=${article.id}">삭제</a>
	<a href="modify?id=${article.id}">수정</a>
	<hr>
</div>
</c:forEach>

<a href="write">등록</a>
</body>
</html>