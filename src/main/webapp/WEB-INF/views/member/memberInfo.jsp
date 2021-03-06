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
					<a href="#" id="home"><img class="webLogo" src="img/sprout_logo.png"></a>
				</div>
			</div>
			<div class="topSpace_side"></div>
		</div>
		<div class="mainContent">
			<div class="sideSpace"></div>
			<form id="updateForm" action="modify" method="post" enctype="multipart/form-data">			<!-- Form  -->
				<div class="mainSpace">
					<div class="tableSpace sbd2 shadow bgwhite">
						<table>
							<tr>
								<td class="border-top-0 border-bottom-0 border-left-0 sbd2">
									<c:if test="${not empty sessionScope.mime}">
		<!-- 프로필 사진 -->		<img class="rounded-circle border profileImg" src="download?loginId=${loginId}" id="picture" style="width: 50px; height: 50px" /> <br>
									</c:if> 
									<c:if test="${empty sessionScope.mime}">
										<img class="rounded-circle border profileImg" id="picture" src="img/empty_profile.png" /> <br>
									</c:if> 
								</td>
								<td class="border-top-0 border-bottom-0 border-left-0 sbd2" style="text-align: center">
									<label class="btn btn-light btn-file border" style="margin-top: 10px"> 
										イメージ変更 <input type="file" name="newPicture">
									</label>
		<!-- 프로필 사진 삭제 -->	<input type="button" class="btn btn-dark" id="profileDelete" value="削除"/>
									</td>
		<!-- 아이디 -->			<td class="border-top-0 border-bottom-0 border-left-0 sbd2">アイディ</td>
								<td>
									<p>${sessionScope.member.member_id}</p>
									<input type="hidden" value="${sessionScope.member.member_id}" name="member_id">
								</td>
							</tr>
							<tr>
		<!-- 이름 -->		<td class="border-top-0 border-bottom-0 border-left-0 sbd2">名前</td>
								<td class="border-top-0 border-bottom-0 border-left-0 sbd2">
									<input class="form-control w-50" type="text" id="member_name" value="${sessionScope.member.member_name}" name="member_name">
								</td>
		<!-- 비밀번호 -->		<td class="border-top-0 border-bottom-0 border-left-0 sbd2">パスワード</td>
								<td>
									<input class="form-control passwordFont" type="password" id="member_password" value="${sessionScope.member.member_password}" name="member_password">
								</td>
							</tr>
							<tr>
		<!-- 휴대전화 -->	<td class="border-top-0 border-bottom-0 border-left-0 sbd2">携帯電話</td>
								<td class="border-top-0 border-bottom-0 border-left-0 sbd2">
									<input class="form-control w-50" type="text" id="member_phone" value="${sessionScope.member.member_phone}" name="member_phone">
								</td>
		<!-- 주소 -->				<td class="border-top-0 border-bottom-0 border-left-0 sbd2">住所</td>
								<td>
									<input class="form-control" type="text" id="member_address" value="${sessionScope.member.member_address}" name="member_address">
								</td>
							</tr>
						</table>
					</div>
					<div class="contentItem w-100" style="text-align: right; margin-top: 10px">
		<!-- 수정-->		<input type="submit" id="update_with_old_info" class="btn btn-dark" style="width: 60px; height: 40px" value="修正">
		<!-- 탈퇴 -->		<input type="button" id="delete_infoBtn" class="btn btn-danger" style="width: 60px; height: 40px" value="脱退">
						<input type="button" id="cancleBtn" class="btn btn-danger" style="width: 90px; height: 40px" value="キャンセル">
					</div>
				</div>
			</form>
			<div class="sideSpace"></div>
		</div>
	</div>
	<script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>