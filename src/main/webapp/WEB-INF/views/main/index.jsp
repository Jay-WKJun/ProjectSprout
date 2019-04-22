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
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css">
<link rel="stylesheet" href="ucss/ucss_index.css">
<link rel="stylesheet" href="css/bootstrap.css">
<!-- 구글 api -->
<script src="https://apis.google.com/js/platform.js?onload=renderButton" async defer></script>
<script src="js/jquery-3.3.1.min.js"></script>
<script src="ujs/ujs_index.js"></script>
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
		<div class="leftBar">
			<div class="card h-100 border-0" style="padding:5px;background-color:#282e38;">
				<div class="contentItem">
					<div class="webNameSpace" style="margin-bottom:20px">
						<a onclick="home();" href="#"><img class="webLogo" src="img/sprout_logo_2.png"></a>
					</div>
				</div>
				<div class="contentItem">
					<button class="btn btn-light w-100" style="height: 50px;margin-bottom:10px" id="wantedBoardBtn">
						<i class="far fa-clipboard fa-lg fontSize20"></i><span
							class="fontSize20" style="margin-left: 8px">프로젝트 공고</span>
					</button>
				</div>
				<div class="contentItem" style="margin-bottom: 15px">
					<button class="btn btn-light w-100" style="height: 50px"
						id="newProjectBtn">
						<i class="fas fa-folder-plus fa-lg fontSize20"></i><span
							class="fontSize20" style="margin-left: 8px">새로 시작하기</span>
					</button>
				</div>
				
				<hr class="sbd1" style="margin: 10px;">

				<div class="list-group">

					<c:forEach var="MainProject" items="${projectList}">
						<a class="projectSelectBtn list-group-item list-group-item-action" style="margin-bottom:8px;border:1px solid #6079a0;"
							data-pno="${MainProject.mainproject_projectnum}">${MainProject.mainproject_title}</a>
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
											<img class="rounded-circle border" src="download?loginId=${loginId}" onerror="src='img/empty_profile.png'"
												style="width: 50px; height: 50px" id="userProfileIcon">
										</c:if>
										<c:if test="${empty sessionScope.mime}"> 
											<img class="rounded-circle border" style="width: 50px; height: 50px" onerror="src='img/empty_profile.png'"
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
						</div>
					</div>
			<div class="contentSpace">
				<div class="contentSpace_left"></div>
				<div class="contentSpace_center" style="padding:50px;text-align:center">
					<div>
							<h4 style="color:#569cc4">New Experience</h4>
							<p style="color:#569cc4;margin:0px">With</p>
							<h1 style="color:#5680c4;margin:0px"><b>Sprout</b></h1>
					</div>
				</div>
				<div class="contentSpace_right"></div>
			</div>
			<div id="webPresentation" style="display: block">
				
			</div>
			<div id="newProject" style="display: none">
				<div class="contentSpace contentSpace_topMargin">
					<div class="contentSpace_left"></div>
					<div class="contentSpace_center">

						<form action="mainProjectRegist" id="mainProjectRegist"
							method="post">

							<div class="contentItem" style="text-align: center;">
									<div class="card-body sbd2 rounded shadow bgwhite">
										<h4 class="card-title">프로젝트 이름</h4>
										<hr class="sbd1">
										<div style="text-align-last: left">
											<textarea class="form-control"
												style="height: 70px; width: 400px; border:1px solid #6079a0" id="mainproject_title"
												name="mainproject_title"></textarea>
										</div>
									</div>
									<div class="card-body sbd2 rounded shadow bgwhite" style="margin-top: 40px">
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
									<button class="btn btn-info w-50" style="height: 50px"
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
							<button class="btn btn-info w-25"
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
						<div class="rounded sbd2 bgwhite shadow" style="padding:40px;width:1200px">
							<ul class="nav nav-tabs">
								<li class="nav-item bgwhite">
									<a class="nav-link active" data-toggle="tab" id="internalWantedSpace">내부</a>
								</li>
								<li class="nav-item bgwhite">
									<a class="nav-link" data-toggle="tab" id="externalWantedSpace">외부</a>
								</li>
							</ul>
							<div class="rounded-bottom w-100 sbd2 shadow bgwhite">
								<div id="internalWantedBoardLoad"></div>
								<div id="wantedBoardLoad"></div>
							</div>
						</div>
					</div>
					<div class="contentSpace_right"></div>
				</div>
			</div>
			
			<!-- 내부 공고글 작성 모달 -->
							
			<div id="internalWantedWriteModal">
				<div class="modalBlack"></div>
				<div class="writeWantedModalContent rounded sbd2">
					<div id="writeBoardLoad"></div>
					<div style="padding:10px">
						<input type="button" class="btn btn-dark" value="글등록" style="margin-right:5px" id="writeBoardBtn">
						<input type="button" class="btn btn-danger" value="닫기" id="closeWriteInternalBtn">
					</div>
				</div>
			</div>
			
			<div id="boardDetailModal">
				<div class="modalBlack"></div>
				<div class="writeWantedModalContent rounded sbd2">
					<div id="boardDetailLoad"></div>
					<!-- <div style="padding:10px">
						<input type="button" class="btn btn-danger" value="닫기" id="closeDetailInternalBtn">
					</div> -->
				</div>
			</div>
			</div>
			
			<div class="contentSpace">
				<div class="contentSpace_left"></div>
				<div class="contentSpace_center" style="padding:20px">
					<div id="webPresentSpace" style="display:none">
						<div class="rounded bgwhite sbd2 shadow" style="text-align:center">
							
							<div id="demo" class="carousel slide" data-ride="carousel">
							
							<!-- 웹 소개 이미지 -->
							
							<!-- Indicators -->
							  <ul class="carousel-indicators">
							    <li data-target="#demo" data-slide-to="0" class="active"></li>
							    <li data-target="#demo" data-slide-to="1"></li>
							    <li data-target="#demo" data-slide-to="2"></li>
							  </ul>
							
							  <!-- The slideshow -->
							  <div class="carousel-inner" style="width:1000px;height:600px">
							    <div class="carousel-item active">
							      <img src="img/bird2.jpg" style="width:100%;height:auto">
							    </div>
							    <div class="carousel-item">
							      <img src="img/chita.jpg" style="width:100%;height:auto">
							    </div>
							    <div class="carousel-item" >
							      <img src="img/chita2.jpg" style="width:100%;height:auto">
							    </div>
							  </div>
							
							  <!-- Left and right controls -->
							  <a class="carousel-control-prev" href="#demo" data-slide="prev">
							    <span class="carousel-control-prev-icon"></span>
							  </a>
							  <a class="carousel-control-next" href="#demo" data-slide="next">
							    <span class="carousel-control-next-icon"></span>
							  </a>
							
							</div>
						</div>
					</div>
				</div>
				<div class="contentSpace_left"></div>
			</div>
			
		</div>
	</div>
	<script src="js/bootstrap.bundle.min.js"></script>
</body>

</html>
