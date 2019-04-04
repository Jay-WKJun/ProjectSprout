<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>memberinfo</title>
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
			<form action="modify" method="post" enctype="multipart/form-data">			<!-- Form -->
				<div class="mainSpace">
					<div class="tableSpace border">
						<table>
							<tr>
		<!-- 프로필 사진 -->			<td class="border-right">프로필 사진</td>
								<td class="border-right" style="text-align: center">
									<c:if test="${not empty sessionScope.mime}">
										<img src="download?loginId=${loginId}" style="width: 50px; height: 50px" /> <br>
									</c:if> 
									<c:if test="${empty sessionScope.mime}">
										<img class="rounded-circle border profileImg" src="img/empty_profile.png" /> <br>
									</c:if> 
									<label class="btn btn-light btn-file border" style="margin-top: 10px"> 
										이미지 변경 <input type="file" style="display: none;">
									</label>
									</td>
		<!-- 아이디 -->			<td class="border-right">아이디</td>
								<td>
									<input type="text" value="${member.member_id}" name="member_id">
								</td>
							</tr>
							<tr>
		<!-- 이름 -->				<td class="border-right">이름</td>
								<td>
									<input type="text" value="${member.member_name}" name="member_name">
								</td>
		<!-- 비밀번호 -->			<td class="border-right">비밀번호</td>
								<td>
									<input type="text" value="${member.member_password}" name="member_password">
								</td>
							</tr>
							<tr>
		<!-- 휴대전화 -->			<td class="border-right">휴대전화</td>
								<td>
									<input type="text" value="${member.member_phone}" name="member_phone">
								</td>
		<!-- 주소 -->				<td class="border-right">주소</td>
								<td>
									<input type="text" value="${member.member_address}" name="member_address">
								</td>
							</tr>
						</table>
					</div>
					<div class="contentItem w-100" style="text-align: right; margin-top: 10px">
		<!-- 수정-->	<input type="submit" class="btn btn-dark" style="width: 100px; height: 50px" value="수정">
		<!-- 탈퇴 -->	<button class="btn btn-danger" style="width: 100px; height: 50px">탈퇴</button>
					</div>
				</div>
			</form>
			<div class="sideSpace"></div>
		</div>
	</div>
	<script src="js/bootstrap.bundle.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>