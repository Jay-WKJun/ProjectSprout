<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>apply_wanted.jsp</title>
</head>
<body>
	<h2>apply_wanted</h2>
	<hr>
	<p>[ 요구사항 ]</p>
	<p>content size 잡아주십쇼</p>
	<hr>
	
	
	
	<form action="apply_this_job" method="POST" enctype="multipart/form-data" > <!-- WantedBoardController -->
		<table border="1">
			<tr>
				<th>지원자</th>
				<td> ${loginId} 
					<input type="hidden" id="wantedboard_from" name="wantedBoard_from" value="${loginId}">
				</td>
			</tr>
			<tr>
				<th>content</th>
				<td> <input type="text" name="wantedBoard_content"/> </td>
			</tr>
			<tr>
				<th>file</th>
				<td> <input type="file" id="upload" name="upload" value="파일첨부" /> </td>
			</tr>
		</table>
		<p>file은 압축해서 올리쇼</p>
		
		<input type="submit" value="upload">
	</form>
	
	
	
</body>
</html>