<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>여기서 task를 만듭니다.</title>
</head>
<body>

<form action="timetableMake" method="POST">
	<input type="text" id="projectContent_title" name="projectContent_title" placeholder="title">
	<input type="text" id="projectContent_content" name="projectContent_content" placeholder="content">
	<input type="date" id="projectContent_startDate" name="projectContent_startDate">
	<input type="date" id="projectContent_endDate" name="projectContent_endDate">
	<input type="text" id="mainproject_projectNum" name="mainproject_projectNum" value="${projectNumTest}">
	<input type="text" id="member_num" name="member_num" value="${memberNumTest}">
	<input type="submit" id="projectContent_submit" name="projectContent_submit">
	

</form>

</body>
</html>