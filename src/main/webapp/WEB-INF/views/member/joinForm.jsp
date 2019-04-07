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
		<div class="topSpace">
			<div class="topSpace_side"></div>
			<div class="topSpace_center">
				<div class="contentItem" style="margin-top: 20px">
					<a href="/sprout"><img class="webLogo"
						src="img/sprout_logo.png"></a>
				</div>
			</div>
			<div class="topSpace_side"></div>
		</div>
		<div class="mainContent">
			<div class="sideSpace"></div>
														<!-- enctype="multipart/form-data" -->
			<form id="joinForm" action="join" method="POST" enctype="multipart/form-data">
				<div class="mainSpace">
					<div class="contentItem_text">
		<b>아이디</b>
					</div>
					<div class="contentItem_input">
						<input class="form-control" type="text" id="member_id" name="member_id">
					</div>
					<span id="idCheckMessage"></span>
					<div class="contentItem_text">
		<b>비밀번호</b>
					</div>
					<div class="contentItem_input">
						<input class="form-control" type="password" id="member_password" name="member_password">
					</div>
					<div class="contentItem_text">
		<b>비밀번호 확인</b>
					</div>
					<div class="contentItem_input">
						<input class="form-control" type="password" id="member_password_re">
					</div>
					<div class="contentItem_text">
		<b>이름</b>
					</div>
					<div class="contentItem_input">
						<input class="form-control" type="text" id="member_name" name="member_name">
					</div>
					<div class="contentItem_text">
		<b>휴대전화</b>
					</div>
					<div class="contentItem_input">
						<input class="form-control" type="text" id="member_phone" name="member_phone" placeholder="숫자만 입력해주세요.">
					</div>
					<div class="contentItem_text">
		<b>주소</b>
					</div>
					<div class="contentItem_input">
						<input class="form-control" type="text" id="member_address" name="member_address">
					</div>
					<div class="contentItem_text">
		<b>프로필 사진</b>
					</div>
					<div class="contentItem_input">
						<input type="file" class="form-control-file border" id="upload" name="upload" value="파일첨부">
						<input class="text" type="hidden" id="memberImage_saveAddress" name="memberImage_saveAddress">
						<div class="contentItem_input" style="margin-top: 50px;">
		<!-- 버튼 -->			<input type="submit" class="btn btn-dark w-100" style="height: 45px" id="joinBtn" value="가입하기" onclick="check();">
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