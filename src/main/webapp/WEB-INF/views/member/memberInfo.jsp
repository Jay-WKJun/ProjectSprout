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
<link rel="stylesheet" href="ucss/ucss_memberInfo.css">
<link rel="stylesheet" href="css/bootstrap.css">
<script src="js/jquery-3.3.1.min.js"></script>
<script src="ujs/ujs_memberInfo.js"></script>

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
			<div class="mainSpace">
				<div class="tableSpace border">
					<table>
						<tr>
							<td class="border-right">프로필 사진</td>
							<td class="border-right" style="text-align:center">
								<img class="rounded-circle border profileImg" src="img/empty_profile.png" /> 
								<br>
								<label class="btn btn-light btn-file border" style="margin-top:10px">
							        이미지 변경 
							        <input type="file" style="display: none;">
							    </label>
							</td>
							<td class="border-right">아이디</td>
							<td>[ 아이디 ]</td>
						</tr>
						<tr>
							<td class="border-right">이름</td>
							<td class="border-right">[ 이름 ]</td>
							<td class="border-right">비밀번호</td>
							<td>[ 비밀번호 ]</td>
						</tr>
						<tr>
							<td class="border-right">휴대전화</td>
							<td class="border-right">[ 휴대전화 ]</td>
							<td class="border-right">주소</td>
							<td>[ 주소 ]</td>
						</tr>
					</table>
				</div>
				<div class="contentItem w-100"
					style="text-align: right;margin-top:10px">
					<button class="btn btn-dark" style="width:100px;height:50px">수정하기</button>
				</div>
			</div>
			<div class="sideSpace"></div>
		</div>
	</div>
	<script src="js/bootstrap.bundle.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>