<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 상세보기</title>
</head>
<body>
<h1>게시물 상세보기</h1>

번호 : ${article.id }<br>
작성날짜 : ${article.regDate}<br>
수정날짜 : ${article.updateDate}<br>
제목 : ${article.title }<br>
내용 : ${article. body }<br>

</body>
</html>