<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="js/jquery-3.3.1.min.js"></script>
<script>
$(function(){
	alert("인증하지 않으셨습니다.인증 후 다시 시도해주세요.");
	location.href('/');
});
</script>
</head>
<body>
<div id="loader">
			<div class="loader">
				<img class="loaderImg" src="img/loading_leaf_circle.gif"><br>
				Loading...
			</div>
			<div class="loaderBack"></div>
		</div>
</body>
</html>