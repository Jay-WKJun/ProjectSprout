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
	<form action="apply_this_job" enctype="multipart/form-data">

		<table border="1">
			<tr>
				<th>지원자</th>
				<td>111</td>
			</tr>
			<tr>
				<th>content</th>
				<td>222</td>
			</tr>
			<tr>
				<th>file</th>
				<td>
					<input type="file"name="upload" value="파일첨부">
				</td>
			</tr>
		</table>
		<input type="submit" value="upload">
	</form>
</body>
</html>