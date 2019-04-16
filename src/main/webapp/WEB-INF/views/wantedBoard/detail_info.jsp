<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>detail_info.jsp</title>
<style>/*----------------------------------------------- style*/
	div#wrapper			{ width : 800px; margin : 0 auto; }
	div#wrapper>h2 		{ text-align : center; }
	div#wrapper table 	{ margin:0 auto; width : 800px; }
	th 					{ width: 100px; }
	pre 				{ width : 600px; height : 200px; overflow: auto; }
	table.reply 		{ width : 800px; }
	input[name='text']	{ width : 600px; }
	span 				{ display: inline-block; margin : 5px; } /*wtf is this*/
	td.replycontent 	{ width : auto; }
	td.replytext 		{ width : 600px; text-align : left; }
	td.replysub 		{ width : 80px; text-align : right; }
	td.replybtn			{ width : 100px; text-align : right; }
</style>
</head>
<body>
	<div id="wrapper">
	<h2>[ 공지글 보기 ]</h2>
	<hr>
	<table border="1">
		<tr>
			<th>Title</th>
			<td>${one_wanted_from_DB.wantedBoard_title}</td>
		</tr>
		<tr>
			<th>공지된 날짜</th>
			<td>${one_wanted_from_DB.wantedBoard_date }</td>
		</tr>	
		<tr>
			<th>작성자</th>
			<td>${one_wanted_from_DB.wantedBoard_from}</td>
		</tr>
		<tr>
			<th>Content</th>
			<td>
				<pre>${one_wanted_from_DB.wantedBoard_content}</pre>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				
				<c:if test="${not empty sessionScope.same}">
					<a href="apply_wanted" >지원하기</a>
				</c:if>
				
				<c:if test="${empty sessionScope.same}">
					<a href="delete_wanted?wantedBoard_num=${one_wanted_from_DB.wantedBoard_num}" >삭제하기</a>
				</c:if>
				
			</td>
		</tr>
	</table>
	<a href="boardList" >return to list</a> <!-- action name  -->
	</div>
</body>
</html>