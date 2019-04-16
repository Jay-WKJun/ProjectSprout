<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 구글 로그인 접속 정보  -->
<meta name="google-signin-client_id"
	content="302280011098-j31rpdam1nmlron2808kv4g4gb6p21a4.apps.googleusercontent.com">
<title>Sprout</title>
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
	<a href="internal">internal</a>
	<div class="wrapper">
		<div id="loader">
			<div class="loader">
				<img class="loaderImg" src="img/loading_leaf_circle.gif"><br>
				Loading...
			</div>
			<div class="loaderBack"></div>
		</div>
		<div class="leftBar shadow">
			<div class="card h-100" style="padding:5px;border:2px solid #6079a0;">
				<div class="contentItem" style="padding: 80px"></div>
				<div class="contentItem">
					<button class="btn btn-dark w-100" style="height: 50px" id="wantedBoardBtn">
						<i class="far fa-clipboard fa-lg fontSize20"></i><span
							class="fontSize20" style="margin-left: 8px">프로젝트 공고</span>
					</button>
				</div>
				<hr class="sbd1" style="margin: 10px;">
				<div class="contentItem" style="margin-bottom: 15px">
					<button class="btn btn-dark w-100" style="height: 50px"
						id="newProjectBtn">
						<i class="fas fa-folder-plus fa-lg fontSize20"></i><span
							class="fontSize20" style="margin-left: 8px">새로 시작하기</span>
					</button>
				</div>

				<div class="list-group" style="margin: 5px;">

					<c:forEach var="MainProject" items="${projectList}">
						<a class="projectSelectBtn list-group-item list-group-item-action" style="margin-bottom:2px;border:1px solid #6079a0;"
							data-pno="${MainProject.mainproject_projectnum}">${MainProject.mainproject_title}</a>
					</c:forEach>

				</div>
			</div>
		</div>
		<div class="mainSpace">
			<div class="topBar">
				<div class="topBar_top"></div>
				<a href="http://localhost:2848/sprout/emailSend">email Test</a>
				<div class="userInfo">
					<div class="userInfo_left"></div>
					<div class="userInfo_right">
						<c:if test="${sessionScope.loginId == null }">
							
							<div class="myInfo">
									<div class="dropdown dropleft float-right h-100">
										<div data-toggle="dropdown">
											<img class="rounded-circle border"
												style="width: 50px; height: 50px"
												src="img/empty_profile.png" id="userProfileIcon">
										</div>
										<div class="dropdown-menu shadow" style="border:1px solid #6079a0;">
											<h5 class="dropdown-header">로그인해주세요.</h5>
											<div style="margin-top: 20px">
												<a id="loginBtn" style="border:1px solid #6079a0"
													class="list-group-item list-group-item-action border-left-0 border-right-0">로그인</a>
											</div>
										</div>
									</div>
								</div>
								
						</c:if>
						<c:if test="${sessionScope.loginId != null }">
								<div class="myInfo">
									<div class="dropdown dropleft float-right h-100">
										<div data-toggle="dropdown">
										<c:if test="${not empty sessionScope.mime}"> 
											<img class="rounded-circle border" src="download?loginId=${loginId}" 
												style="width: 50px; height: 50px" id="userProfileIcon">
										</c:if>
										<c:if test="${empty sessionScope.mime}"> 
											<img class="rounded-circle border" style="width: 50px; height: 50px" 
												src="img/empty_profile.png" id="userProfileIcon">
										</c:if>
											<!-- <img class="rounded-circle border"
												style="width: 50px; height: 50px"
												src="img/empty_profile.png" id="userProfileIcon"> -->
										</div>
										<div class="dropdown-menu shadow" style="border:1px solid #6079a0;">
											<h5 class="dropdown-header">${loginName}</h5>
											<div style="margin-top: 20px">
												<a id="memberInfoBtn" class="list-group-item list-group-item-action border-left-0 border-right-0"
													 style="border:1px solid #6079a0">회원정보</a>
												<a id="logoutBtn" class="list-group-item list-group-item-action border-left-0 border-right-0" 
													 style="border:1px solid #6079a0;">로그아웃</a>
											</div>
										</div>
									</div>
								</div>
								
							</c:if>
			<div class="contentSpace">
				<div class="contentSpace_left"></div>
				<div class="contentSpace_center">
					<div class="webNameSpace">
						<a href="/sprout"><img class="webLogo"
							src="img/sprout_logo.png"></a>
					</div>
				</div>
				<div class="contentSpace_right"></div>
			</div>
			<div id="webPresentation" style="display: block">
				
				
				<div class="contentSpace contentSpace_topMargin">
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
				<div class="contentSpace contentSpace_topMargin">
					<div class="contentSpace_left"></div>
					<div class="contentSpace_center">

						<form action="mainProjectRegist" id="mainProjectRegist"
							method="post">


							<div class="contentItem" style="text-align: center;">
									<div class="card-body rounded sbd2 shadow bgwhite">
										<h4 class="card-title">프로젝트 이름</h4>
										<hr class="sbd1">
										<div style="text-align-last: left">
											<textarea class="form-control"
												style="height: 70px; width: 400px; border:1px solid #6079a0" id="mainproject_title"
												name="mainproject_title"></textarea>
										</div>
									</div>
									<div class="card-body rounded sbd2 shadow bgwhite" style="margin-top: 40px">
										<h4 class="card-title">프로젝트 내용</h4>
										<hr class="sbd1">
										<div style="text-align-last: left">
											<textarea class="form-control"
												style="height: 300px; width: 400px;border:1px solid #6079a0" id="mainproject_memo"
												name="mainproject_memo"></textarea>
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
				<form action="project_go" id="project_go" method="POST">
					<div class="contentSpace contentSpace_topMargin">
						<div class="contentSpace_left">
						<div class="contentSpace_left"></div>
						<div class="contentSpace_right">
							<div class="contentItem" style="text-align: center; width: 300px">
									<div class="card-body sbd2 shadow bgwhite">
										<h4 class="card-title">프로젝트 이름</h4>
										<hr class="sbd1">
										<div style="text-align-last:left">
											<span id="goproject_title"> </span>
										</div>
									</div>
									<div class="card-body sbd2 shadow bgwhite" style="margin-top: 40px;height:300px;">
										<h4 class="card-title">프로젝트 내용</h4>
										<hr class="sbd1">
										<div style="text-align-last:left">
											<span id="goproject_content"></span>
										</div>
								</div>
							</div>
							</div>
						</div>
						<div class="contentSpace_center"></div>
						<div class="contentSpace_right">
							<div class="memberList rounded sbd2 shadow bgwhite">
								<h4 class="card-title" style="text-align-last:center">참여 멤버</h4>
								<hr class="sbd1">
								<span id="goprojet_membername">
								
								</span>
							</div>
						</div>
					</div>

					<div class="contentSpace_left"></div>
					<div class="contentSpace_center">
						<div class="contentItem contentSpace_topMargin"
							style="width: 100%; text-align: center;">
							<button class="btn btn-dark w-25"
								style="height: 50px; margin-right: 20px" id="openproject">시작</button>

							<input type="hidden" name="mainproject_projectnum"
								id="mainProjectNum">
						</div>
					</div>
					<div class="contentSpace_right"></div>
				</form>
			</div>
			
			<div id="wantedBoardSpace"  style="display: none">
				<div class="contentSpace">
					<div class="contentSpace_left"></div>
					<div class="contentSpace_center">
						<!-- 공고글 로드 -->
						<div id="wantedBoardLoad"></div>
					</div>
					<div class="contentSpace_right"></div>
				</div>
			</div>
				
			</div>
		</div>
		</div>
	</div>
	</div>
	<script src="js/bootstrap.bundle.min.js"></script>
	<!-- ----------------------------------------------------------------------- -->
	<%-- <hr>
	<p>프로필 이미지 test<P><br>
	<c:if test="${not empty sessionScope.mime}"> 
		<img class="rounded-circle border" src="download?loginId=${loginId}" style="width:100px;height:100px">
	</c:if>
	<c:if test="${empty sessionScope.mime}"> 
		<img class="rounded-circle border" style="width: 50px; height: 50px" src="img/empty_profile.png" id="userProfileIcon">
	</c:if> --%>
	<!-- ----------------------------------------------------------------------- -->
	
</body>

</html>
