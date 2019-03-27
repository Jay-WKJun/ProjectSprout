<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 구글 로그인 접속 정보  -->
<meta name="google-signin-client_id" content="302280011098-j31rpdam1nmlron2808kv4g4gb6p21a4.apps.googleusercontent.com">
<title>index</title>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
	integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr"
	crossorigin="anonymous">
<link rel="stylesheet" href="ucss/ucss_index.css">
<link rel="stylesheet" href="css/bootstrap.css">
<!-- 구글 api -->
<script src="https://apis.google.com/js/platform.js?onload=renderButton" async defer></script>
<script src="js/jquery-3.3.1.min.js"></script>
<script src="ujs/ujs_index.js"></script>
</head>

<body>
	<div class="wrapper">
		<div class="leftBar">
			<div class="card h-100">
				<div class="webNameSpace">
					<a href="/sprout"><img class="webLogo"
						src="img/sprout_logo.png"></a>
				</div>
				<div class="contentItem">
					<button class="btn btn-dark w-100" style="height: 50px">
						<i class="far fa-clipboard fa-lg fontSize20"></i><span
							class="fontSize20" style="margin-left: 8px">프로젝트 공고</span>
					</button>
				</div>
				<hr style="margin: 10px">
				<div class="contentItem" style="margin-bottom: 15px">
					<button class="btn btn-dark w-100" style="height: 50px" id="newProjectBtn">
						<i class="fas fa-folder-plus fa-lg fontSize20"></i><span
							class="fontSize20" style="margin-left: 8px">새로 시작하기</span>
					</button>
				</div>

				<div class="list-group" style="margin: 5px;">

					<c:forEach var="MainProject" items="${projectList}">
						<a class="projectSelectBtn list-group-item list-group-item-action" data-pno="${MainProject.mainproject_projectnum}">${MainProject.mainproject_title}</a>

					</c:forEach>

				</div>
			</div>
		</div>
		<div class="mainSpace">
			<div class="topBar">
				<div class="topBar_top"></div>
				<div class="userInfo">
					<div class="userInfo_left"></div>
					<div class="userInfo_right">
						<c:if test="${sessionScope.loginId == null }">
							<button class="btn btn-primary" id="project">프로젝트</button>
							<button class="btn btn-primary" id="whiteBoardBtn">화이트보드</button>
							
							<button class="btn btn-dark" id="loginBtn">
								<span style="margin-right: 8px">로그인</span><i
									class="fas fa-sign-in-alt fa-lg"></i>
							</button>
						</c:if>
						<c:if test="${sessionScope.loginId != null }">
							<c:if test="${sessionScope.google == null}">
								<p>${loginName}님,
									<button class="btn btn-dark" id="logoutBtn">
										<span style="margin-right: 8px">로그아웃</span> <i
											class="fas fa-sign-out-alt"></i>
									</button>
								</p>
							</c:if>
							<c:if test="${sessionScope.google != null}">
							<button class="btn btn-primary" id="project">프로젝트</button>
							<button class="btn btn-primary" id="whiteBoardBtn">화이트보드</button>
								<p>${loginName}님,
									<button class="btn btn-dark" id="googleLogout"
										onclick="signOut();">
										<span style="margin-right: 8px">로그아웃</span> <i
											class="fas fa-sign-out-alt"></i>
									</button>
								</p>
							</c:if>
							<button class="btn btn-dark" id="updateBtn">
								<i class="fas fa-user fa-lg"></i>회원정보 수정
							</button>
						</c:if>
					</div>
				</div>
				<div class="topBar_bottom"></div>
			</div>


			<div id="webPresentation" style="display: block">
				<div class="contentSpace">
					<div class="contentSpace_left"></div>
					<div class="contentSpace_center">
						<div class="contentItem">
							<h1>[ 웹 소개 내용 ]</h1>
						</div>
					</div>
					<div class="contentSpace_right"></div>

				</div>
			</div>

			<div id="newProject" style="display: none">
				<div class="contentSpace">
					<div class="contentSpace_left"></div>
					<div class="contentSpace_center">

						<form action="mainProjectRegist" id="mainProjectRegist"
							method="post">

							<div class="contentItem" style="text-align: center">
								<div class="card">
									<div class="card-body">
										<h4 class="card-title">프로젝트 이름</h4>
										<hr>
										<div style="text-align-last: left">
											<textarea class="form-control"
												style="height: 70px; width: 400px" id="mainproject_title"
												name="mainproject_title"></textarea>
										</div>
									</div>
								</div>
								<div class="card" style="margin-top: 40px">
									<div class="card-body">
										<h4 class="card-title">프로젝트 내용</h4>
										<hr>
										<div style="text-align-last: left">
											<textarea class="form-control"
												style="height: 300px; width: 400px" id="mainproject_memo"
												name="mainproject_memo"></textarea>
										</div>
									</div>
								</div>
								<div class="contentItem"
									style="margin-top: 30px; text-align: right">
									<button class="btn btn-dark w-50" style="height: 50px"
										id="projectCreateBtn">생성하기</button>
								</div>
							</div>
						</form>
					</div>
					<div class="contentSpace_right"></div>

				</div>
			</div>
			<div id="ProjectStartSpace" style="display: none">
				<div class="contentSpace">
					<div class="contentSpace_left"></div>
					<div class="contentSpace_center">

						<form action="project_go" id="project_go"
							method="post">

							<div class="contentItem" style="text-align: center">
								<div class="card">
									<div class="card-body">
										<h4 class="card-title">프로젝트 이름</h4>
										<hr>
										<div style="text-align-last: left">
											<span id="goproject_title">
											
											</span>
										</div>
									</div>
								</div>
								<div class="card" style="margin-top: 40px">
									<div class="card-body">
										<h4 class="card-title">프로젝트 내용</h4>
										<hr>
										<div style="text-align-last: left">
											<span id="goproject_content"></span>
										</div>
									</div>
								</div>
								<div class="contentItem"
									style="margin-top: 30px; text-align: right">
									<button class="btn btn-dark w-50" style="height: 50px"
										id="projectCreateBtn">시작하기</button>
								</div>
							</div>
						</form>
					</div>
					<div class="contentSpace_right">
						<div class="memberList rounded border">
							<h4 class="card-title">참여 멤버</h4>
							<hr>
							<span id="goprojet_membername"></span>							
						</div>
					</div>
					</div>

				</div>
		</div>
	</div>

	<script src="js/bootstrap.bundle.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>

</html>