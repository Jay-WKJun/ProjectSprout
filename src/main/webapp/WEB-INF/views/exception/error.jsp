<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="refresh" 
	  content="5;url=${pageContext.request.contextPath}/">
<title>에러발생페이지처리</title>
<style>
	#wrapper {
		width : 700px;
		height : auto;
		margin : 0 auto;
		text-align : center;
	}
</style>
</head>
<body>
<div id="wrapper">
	<h2>[ Error ]</h2>
	<div>에러가 발생되었습니다. 5초 후 첫 화면으로 이동합니다.<br />
		 잠시 후 다시 이용해 주세요.
	</div>
	<div style="color:red">
		${errMsg }
	</div>
</div>
</body>
</html>