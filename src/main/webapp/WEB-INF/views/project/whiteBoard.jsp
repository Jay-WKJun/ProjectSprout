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
<script src="ujs/ujs_whiteBoard.js"></script>
<script src="js/jquery-ui.min.js"></script>
</head>
<body>
		<input type="hidden" id="projectNum" value="${projectNum }">
		<div class="postitWindow rounded sbd2">
			<div class="mainSpace_top">
			<div class="mainSpace_top_side"></div>
			<div class="mainSpace_top_center">
			<table class="table table-borderless w-100">
				<tr>
					<td>가로</td>
					<td><input type="range" class="form-control" min="100" max="800" id="postitWidth"></td>
					<td rowspan="2">
						색깔
					</td>
					<td rowspan="2">
						<input type="color" class="form-control" id="postitColor" style="width:50px">
					</td>
					<td rowspan="2">
						<button class="btn btn-dark" id="addPostit" style="width: 80px;height:100px">
							<span class="fa-stack fa-lg"> 
								<i class="far fa-sticky-note fa-stack-2x"></i> 
								<i class="fas fa-plus fa-stack-1x"></i>
							</span>
						</button>
					</td>
				</tr>
				<tr>
					<td>세로</td>
					<td><input type="range" class="form-control" min="100" max="800" id="postitHeight"></td>
				</tr>
			</table>
			</div>
			<div class="mainSpace_top_side" style="text-align:right">
				<table class="table table-borderless w-100">
				<tr>
					<td rowspan="2">
						<button type="button" class="btn btn-danger" id="modalCloseBtn" style="width: 80px;height:100px">닫기</button>
					</td>
				</tr>
				</table>
			</div>
			</div>
		</div>
		<div id="boardSpace"></div>
</body>
</html>