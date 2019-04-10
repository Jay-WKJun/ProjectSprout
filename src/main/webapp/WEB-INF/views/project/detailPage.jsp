<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>project</title>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
	integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr"
	crossorigin="anonymous">
<link rel="stylesheet" href="ucss/ucss_project.css">
<link rel="stylesheet" href="css/bootstrap.css">
<script src="js/jquery-3.3.1.min.js"></script>
<script src="ujs/ujs_project.js"></script>

</head>
<body>
	<div class="wrapper">
		<div class="sideSpace">
			<div class="sideSpace_top">
				<a href="/sprout"><img class="webLogo" src="img/sprout_logo.png"></a>
			</div>
			<div class="sideSpace_bottom">
				
				<div
					class="communicationBar rounded-bottom border border-top-0 w-100"
					style="padding: 5px">
					<div class="list-group">
					<button class="btn btn-dark w-100" disabled="disabled">게시판 추가</button>
					</div>
					<div id="noticeSpace">
					<!-- 맨 처음에 프로젝트의 개요를 띄운다. -->
					<!-- 이미지 띄우고 -->
					<!-- 활동 통계띄우면 끝. -->
					</div>

					
				</div>
			</div>
		</div>
		<div class="mainSpace">
			<div class="mainSpace_top">
				<div class="mainSpace_top_side"></div>
				<div class="mainSpace_top_center">
					<div class="projectName border-bottom">
						<h1 id="projectHome">${MainProject_title}</h1>
					</div>
				</div>
				<div class="mainSpace_top_side" style="text-align: right">
					<div class="myInfo">
						<div class="dropdown dropright float-right h-100">
							<div data-toggle="dropdown">
								<img class="rounded-circle border"
									style="width: 50px; height: 50px" src="img/empty_profile.png"
									id="userProfileIcon">
							</div>
							<div class="dropdown-menu">
								<h5 class="dropdown-header">${loginName}</h5>
								<div style="margin-top: 20px">
									<a href="#"
										class="list-group-item list-group-item-action border-left-0 border-right-0">회원정보</a>
									<a href="logout"
										class="list-group-item list-group-item-action border-left-0 border-right-0">로그아웃</a>
										
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="mainSpace_bottom">
				<div class="contentSpace_side"></div>
	
				<div class="contentSpace_side"></div>
				<div class="contentSpace_right">
					<div class="contentItem" style="margin: 20px; height: 100%;">

						<button type="button" class="btn btn-dark h-100"
							data-target="#whiteBoardModal" id="modalBtn">
							<i class="fas fa-chevron-left fa-lg"></i>
						</button>

						<div class="modal w-100 h-100" id="whiteBoardModal">
							<div class="mainSpace">
								<div class="mainSpace_top">

									<div class="modal-header rounded postitWindow">
										<button class="btn btn-dark" id="addPostit"
											style="width: 80px">
											<span class="fa-stack fa-lg"> <i
												class="far fa-sticky-note fa-stack-2x"></i> <i
												class="fas fa-plus fa-stack-1x"></i>
											</span>
										</button>
										<button type="button" class="btn btn-danger"
											data-dismiss="modal">Close</button>
									</div>
									<input type="hidden" id="postitNumFromProjectNum"
										value="${mainproject_projectnum }">
									<div id="headers"></div>

								</div>
								<div class="mainSpace_bottom"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script src="js/bootstrap.bundle.min.js"></script>
</body>

</html>
