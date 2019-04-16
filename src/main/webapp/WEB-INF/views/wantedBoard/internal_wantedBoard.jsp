<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h2>[ (내부) 게시판 글 목록 by_환 ]</h2>
<div id="wrapper">

<!-- 게시글 목록 시작 -->
<table border='1'>
	<tr>
		<th>중요 표시</th>
		<th class="title">글제목</th>
		<th>프로젝트 제안자</th>
		<th>지원 기간</th>
	</tr>
	
	<!-- 게시글 출력 반복 -->
	<c:forEach var="board" items="${list_from_DB}" varStatus="stat">
		<tr>
			<td>${stat.count + navi.startRecord}</td>
			<td><a href="detail_info?wantedBoard_num=${board.wantedBoard_num}">${board.wantedBoard_title}</a> </td> <!-- ========================== 여기 하는 중 -->
			<td>${board.wantedBoard_from}</td>
			<td>${board.wantedBoard_date }</td>
		</tr>
	</c:forEach>
</table>
<!--주목!!!! '!= null' = 'not empty' -->

<div class="write"><br><a class="btn" href="boardRegist">글쓰기</a></div>

<!-- Paging 출력 부분 -->
<div id="navigator">

<a href="boardList?currentPage=1${navi.currentPage-navi.pagePerGroup}&searchItem=${searchItem}&searchWord=${searchWord}">
◁◁
</a>
<a href="boardList?currentPage=1${navi.currentPage-1}&searchItem=${searchItem}&searchWord=${searchWord} ">
◀
</a>
&nbsp;&nbsp;
<c:forEach var="page" begin="${navi.startPageGroup}" end="${navi.endPageGroup}">
	<c:if test="${navi.currentPage == page}">
		<span style="color : red; font-size : 2em;">${page}</span>
	</c:if>
	<c:if test="${navi.currentPage != page}">
		<a href="boardList?currentPage=${page}&searchItem=${searchItem}&searchWord=${searchWord}&boardnum=${board.boardnum}">${page}</a> &nbsp;
	</c:if>
</c:forEach>
&nbsp;&nbsp;

<a href="boardList?currentPage=1${navi.currentPage+1}&searchItem=${searchItem}&searchWord=${searchWord}">
▶
 </a> 
<a href="boardList?currentPage=1${navi.currentPage+navi.pagePerGroup}&searchItem=${searchItem}&searchWord=${searchWord}">
▷▷
</a>
</div>
</div>
</body>
</html>