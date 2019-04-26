<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>join</title>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
	integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr"
	crossorigin="anonymous">
<link rel="stylesheet" href="ucss/ucss_join.css">
<link rel="stylesheet" href="css/bootstrap.css">
<script src="js/jquery-3.3.1.min.js"></script>
<script src="ujs/ujs_join.js"></script>

</head>
<body>
	<div class="wrapper">
		<div id="loader">
			<div class="loader">
				<img class="loaderImg" src="img/loading_leaf_circle.gif"><br>
				Loading...
			</div>
			<div class="loaderBack"></div>
		</div>
		<div class="topSpace">
			<div class="topSpace_side"></div>
			<div class="topSpace_center">
				<div class="contentItem" style="margin-top: 20px">
					<a href="#" id="home"><img class="webLogo"
						src="img/sprout_logo.png"></a>
				</div>
			</div>
			<div class="topSpace_side"></div>
		</div>
		<div class="mainContent">
			<div class="sideSpace"></div>
														<!-- enctype="multipart/form-data" -->
			<form id="joinForm" action="join" method="POST" enctype="multipart/form-data">
				<div class="mainSpace sbd2 rounded shadow bgwhite">
					<div class="contentItem_text">
		<b>アイディ</b>
					</div>
					<div class="contentItem_input">
						<input class="form-control" type="text" id="member_id" name="member_id">
					</div>
					<span id="idCheckMessage"></span>
					<div class="contentItem_text">
		<b>パスワード</b>
					</div>
					<div class="contentItem_input">
						<input class="form-control" type="password" id="member_password" name="member_password">
					</div>
					<div class="contentItem_text">
		<b>パスワード確認</b>
					</div>
					<div class="contentItem_input">
						<input class="form-control" type="password" id="member_password_re">
					</div>
					<div class="contentItem_text">
		<b>名前</b>
					</div>
					<div class="contentItem_input">
						<input class="form-control" type="text" id="member_name" name="member_name">
					</div>
					<div class="contentItem_text">
		<b>携帯電話</b>
					</div>
					<div class="contentItem_input">
						<input class="form-control" type="text" id="member_phone" name="member_phone" placeholder="数字だけを入力してください。">
					</div>
					<div class="contentItem_text">
		<b>住所</b>
					</div>
					<div class="contentItem_input">
						<input class="form-control" type="text" id="member_address" name="member_address">
					</div>
					<div class="contentItem_text">
		<b>プロフィールイメージ</b>
					</div>
					<div class="contentItem_input">
						<div class="topSpace">
							<div class="topSpace_center">
								<label class="btn btn-dark">
									ファイル添付
									<input type="file" class="form-control-file border" id="upload" name="upload" style="display:none">
								</label>
							</div>
							<div class="topSpace_side">
								<input class="form-control" type="text" id="fileName" readonly="readonly">
							</div>
						</div>
						<input class="text" type="hidden" id="memberImage_saveAddress" name="memberImage_saveAddress">
						<div class="contentItem_input" style="margin-top: 50px;">
		<!-- 버튼 -->	<input type="button" class="btn btn-dark w-100" style="height: 45px" id="joinBtn" value="登録する">
						</div>
					</div>
				</div>
			</form>
			<div class="sideSpace"></div>
		</div>
	</div>
	<script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>