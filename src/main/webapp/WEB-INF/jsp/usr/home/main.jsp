<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="title" value="구현기능 리스트"/>
<%@ include file="../part/head.jspf" %>
<ul class="list-group">
  <li class="list-group-item">로그인</li>
  <li class="list-group-item">회원가입</li>
  <li class="list-group-item">회원가입후 이메일 인증</li>
  <li class="list-group-item">회원정보수정</li>
  <li class="list-group-item">ID찾기</li>
  <li class="list-group-item">비번찾기(임시비번, 메일 발송)
</li>
<li class="list-group-item">멀티게시판</li>
<li class="list-group-item">게시물 쓰기</li>
  <li class="list-group-item">게시물 수정/삭제</li>
  <li class="list-group-item">게시물 리스트</li>
  <li class="list-group-item">게시물 검색</li>
  <li class="list-group-item">게시물 수정/삭제시 권한체크</li>
  <li class="list-group-item">댓글 리스트</li>
  <li class="list-group-item">댓글 수정/삭제</li>
    <li class="list-group-item">댓글 수정/삭제시 권한체크</li>
</ul>
<%@ include file="../part/foot.jspf" %>
