<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>whiteBoard</title>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
	integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr"
	crossorigin="anonymous">
<link rel="stylesheet" href="ucss/ucss_whiteBoard.css">
<link rel="stylesheet" href="css/bootstrap.css">
<!-- <script src="js/jquery-1.8.2.js"></script> -->
<script src="js/jquery-3.3.1.min.js"></script>
<script src="js/jquery-ui.min.js"></script>
<script src="ujs/ujs_whiteBoard.js"></script>
</head>
<body>
	<div>
		<button class="btn btn-dark" id="addPostit" style="width: 80px">
			<span class="fa-stack fa-lg">
			<i class="far fa-sticky-note fa-stack-2x"></i><i class="fas fa-plus fa-stack-1x"></i>
			</span>
		</button>
	</div>
	<div id="boardSpace">
		<div class="postit" id="div1" style="position: absolute; left: 20px; top: 250px;"></div>
	</div>

	<script src="js/bootstrap.bundle.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>