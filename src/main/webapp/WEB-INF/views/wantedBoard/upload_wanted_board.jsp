<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>wantedBoard_directly.jsp</title>
</head>
<body>
	
	<h1>wantedBoard_directly.jsp</h1>
	<hr>
	
	<form action="write_wanted" id="">
	
	<table>
	<tr>
		<th>작성자</th>
		<th>제목</th>
		<th>내용</th>
	</tr>
	<tr>
		<td>
			${loginId}
		</td>
		<td>
			<input type="text" name="wantedBoard_title" id="" placeholder="제목"/> <br> <!-- title -->
		</td>
		<td>
			<input type="text" name="wantedBoard_content" id="" placeholder="내용"/> <br> <!-- content -->
		</td>
	</tr>	
	
	</table>
		<input type="submit" value="글등록">
	</form>
	
	
</body>
</html>