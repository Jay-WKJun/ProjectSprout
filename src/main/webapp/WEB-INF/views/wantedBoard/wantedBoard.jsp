<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>WantedBoard</title>
<!-- 평소엔 한 화살표만 보여주고 다 보기하면 페이징을 표시해준다. -->
<style>
	body {
		font-family : sans-serif;
		font-size : 0.9em;
	}
	input[type=submit] {
		border : none;
	}
	div#wrapper {
		width:900px;
		margin : 0 auto;
		text-align : center;
	}
	table {
		width : 900px;
	}
	.title {
		width : 450px;
	}
	th {
		background : #efefef;
	}
	.write {
		text-align : right;
	}
	.home {
		text-align : left;
	}
		a {
		display : inline-block;
	}
	a, a:link, a:visited, a:active {
		text-decoration : none;
	}
	a:hover {
		font-weight:bolder;
		color : #F00;
	}
	.btn {
		text-align : center;
		border-radius : 0.5em;
		width : 50px;
		margin-top : 10px;
		padding : 5px;
		background : #eee;
		
	}
	input[type=submit]:hover{
		font-weight:bold;
	}
	#search {
		text-align:right;
	}
</style>
<script type="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">



</script>
</head>
<body>

<h1>I've come!!</h1>


<div id="wrapper">

<h2>[ 게시판 글 목록 ]</h2>
<div class="home">
	<a href="${pageContext.request.contextPath}/"><img src="images/home.png" /></a>
	<!-- ./로 해도 좋지만 contextpath가 변하면 문제가 되기 때문에 위처럼 적어주는게 편하다. -->
	<!-- 사이트가 커져 서버가 여러개가 되면 ./가 문제가 된다. -->

	<!-- 특정 글 검색 -->
	<form id="search" action ="boardList" method="GET" >
	<select name="searchItem"> 
		<option value="WANTEDBOARD_TITLE" ${searchItem == 'WANTEDBOARD_TITLE' ? 'selected' : ''}>제목</option>
		<option value="WANTEDBOARD_DATE" ${searchItem == 'WANTEDBOARD_DATE' ? 'selected' : ''}>날짜</option>
		<option value="WANTEDBOARD_CONTENT" ${searchItem == 'WANTEDBOARD_CONTENT' ? 'selected' : ''}>내용</option>
	</select>
	<input type="text" name="searchWord" value="${searchWord}" /> 
	<input class="btn" type="submit" value="검색" />
	</form>
</div>
<!-- 게시글 목록 시작 -->
<table border='1'>
	<tr>
		<th>중요 표시</th>
		<th class="title">글제목</th>
		<th>프로젝트 제안자</th>
		<th>지원 기간</th>
	</tr>
	
	<!-- 게시글 출력 반복 -->
	<c:forEach var="board" items="${boardList}" varStatus="stat">
		<tr>
			<td>${stat.count + navi.startRecord}</td>
			<td>
				<a href="wantedBoardLink?link=${board.wantedBoard_title}">${board.wantedBoard_title}</a>
				<%-- <c:if test="${board.originalfile != null}">
					<img src="images/attachment.png" />
				</c:if> --%>
			</td>
			<td>${board.wantedBoard_from}</td>
			<td>${board.wantedBoard_date }</td>
		</tr>
	</c:forEach>
</table>
<!--주목!!!! '!= null' = 'not empty' -->

<div class="write"><a class="btn" href="boardRegist">글쓰기</a></div>

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