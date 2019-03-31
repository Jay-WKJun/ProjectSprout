<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- google로그인 api 접속정보 -->
<meta name="google-signin-client_id"
	content="302280011098-j31rpdam1nmlron2808kv4g4gb6p21a4.apps.googleusercontent.com">
<title>login</title>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
	integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr"
	crossorigin="anonymous">
<link rel="stylesheet" href="ucss/ucss_login.css">
<link rel="stylesheet" href="css/bootstrap.css">
<script src="js/jquery-3.3.1.min.js"></script>
<script src="ujs/ujs_login.js"></script>
</head>

<body>
	<div class="wrapper">
		<div class="topSpace">
			<div class="topSpace_side"></div>
			<div class="topSpace_center">
				<div class="contentItem" style="padding: 20px;">
					<a href="/sprout"><img class="webLogo"
						src="img/sprout_logo.png"></a>
				</div>
			</div>
			<div class="topSpace_side"></div>
		</div>
		<div class="content_space">
			<div class="content_space_left"></div>
			<div class="content_space_center">
				<div class="contentItem" style="text-align-last: center">
					<div class="loginBox rounded">
						<div class="loginBox_inner_top">
							<h4 class="fontSize60 fontBold_1">로그인</h4>
						</div>
						<div class="loginBox_inner_center">
							<div class="contentItem">
								<p id="message" style="color: #e24848">${message}</p>
							</div>
							<form id="login" action="login" method="POST">
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text" style="min-width: 120px;"><i
											class="far fa-id-badge fa-lg"></i><span
											style="margin-left: 8px">아이디</span></span>
									</div>
									<input type="text" class="form-control" id="id" name="id"
										value="${cookie['idChecked'].value}">
								</div>
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text" style="min-width: 120px"><i
											class="fas fa-lock fa-lg"></i><span style="margin-left: 8px">비밀번호</span></span>
									</div>
									<input type="password" class="form-control  fontDefault" id="password"
										name="password">
								</div>
								<div class="checkBox_space">
									<div class="checkBox_space_side"></div>
									<div class="checkBox_space_main">
										<label><span style="margin-right: 10px">아이디 저장</span><input
											type="checkbox" name="idChecked" ${checked} /></label>
									</div>
								</div>
								<div class="contentItem">
									<button class="btn btn-dark w-100" id="loginBtn">
										<i class="fas fa-sign-in-alt fa-lg"></i><span
											style="margin-left: 8px">로그인</span>
									</button>
								</div>
								<div style="margin-top: 10px">
									<a href="#" style="color: black; margin-right: 10px">비밀번호
										찾기</a> <a href="join" style="color: black; margin-left: 10px">회원가입</a>
								</div>
							</form>
						</div>
						<div class="contentItem" style="margin:0 35px 0 35px">
							<!-- 얘가 구글 버튼 -->
							<div id="my-signin2"></div>
						</div>
						<div class="loginBox_inner_right"></div>
					</div>
				</div>
			</div>
			<div class="content_space_right"></div>
		</div>
	</div>
	<script src="js/bootstrap.bundle.min.js"></script>
	<script src="js/bootstrap.min.js"></script>


	<script
		src="https://apis.google.com/js/platform.js?onload=renderButton" async
		defer></script>

	<script>
		//Google로그인 여기서부터
		function onSuccess(googleUser) {
			console.log('Logged in as: '
					+ googleUser.getBasicProfile().getName());

			signInCallback(googleUser);
		}
		function onFailure(error) {
			console.log(error);
		}
		function renderButton() {
			gapi.signin2.render('my-signin2', {
				'scope' : 'profile email',
				'width' : 350,
				'height' : 43,
				'longtitle' : true,
				'theme' : 'dark',
				'onsuccess' : onSuccess,
				'onfailure' : onFailure
			});
			
			$('#not_signed_infk232ad3h9nh').html('아아ㅏ');
		}
		function signOut() {
			var auth2 = gapi.auth2.getAuthInstance();

			auth2.signOut().then(function() {
				console.log('User signed out.');
				document.getElementById("result").innerHTML = "";
			});
		}
		function signInCallback(googleUser) {
			console.log(JSON.stringify(googleUser));
			var id_token = googleUser.getAuthResponse().id_token;
			var id_email = googleUser.getBasicProfile().getEmail();
			var id_name = googleUser.getBasicProfile().getName();
			alert(typeof (id_token));

			var send = {
				"member_password" : id_token,
				"member_id" : id_email,
				"member_name" : id_name
			};

			console.log(JSON.stringify(send));
			// Send the code to the server
			$
					.ajax({
						type : 'POST',
						url : 'googleSignInCallback',
						data : send,
						success : function(result) {
							alert("success")
							alert(result);
							//얘를 로그인을 눌럿을때 가는 컨트롤러로 이어준다.
							signOut();
							window.location.href = 'http://localhost:2848/sprout/googleChecked?memberId='
									+ result;
						},
						error : function(result) {
							alert("fail")
						}
					});
		}
		//Google login 여기까지
	</script>
</body>
</html>