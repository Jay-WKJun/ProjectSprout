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
<link rel="stylesheet" href="gantt/css/style.css" />
<script src="js/jquery-3.3.1.min.js"></script>
<script src="ujs/ujs_project.js"></script>
<script src="js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<div class="wrapper">
		<div class="sideSpace">
			<div class="sideSpace_top">
				<a href="/sprout"><img class="webLogo" src="img/sprout_logo.png"></a>
			</div>
			<div class="sideSpace_bottom">
				<ul class="nav nav-tabs">
					<li class="nav-item bgwhite"><a class="nav-link active"
						id="memberChangeBtn" data-toggle="tab" href="#home">멤버</a></li>
					<li class="nav-item bgwhite"><a class="nav-link"
						data-toggle="tab" href="#menu1">메세지</a></li>
					<li class="nav-item bgwhite" id="noticeCheckBtn"><a
						class="nav-link" data-toggle="tab" href="#menu2">공지사항</a></li>
				</ul>
				<!-- 같은 프로젝트일때 참여인원  출력 -->
				<div
					class="communicationBar rounded-bottom border-top-0 w-100 sbd2 shadow bgwhite"
					style="padding: 5px">
					<div id="memberSpace_display" style="display: block">
						<div class="list-group">

							<form action="addProjectMember" id="addProjectMember"
								method="GET"></form>

							<c:forEach var="list" items="${projectMembersList}">
								<div
									class="dropdown dropright float-right list-group-item list-group-item-action"
									style="margin-bottom: 2px; border: 1px solid #6079a0;">
									<div data-toggle="dropdown">${list.member_name}</div>
									<div class="dropdown-menu shadow" style="border:1px solid #6079a0">
										<h5 class="dropdown-header">${list.member_name}</h5>
										<div style="margin-top: 20px" data-pno="${list.member_num}"
											id="forkick">
											<a href="#" style="border:1px solid #6079a0"
												class="list-group-item list-group-item-action border-left-0 border-right-0">메세지
												보내기</a>
											<c:if test="${member_rank == 5}">
												<a href="#" style="border:1px solid #6079a0"
													class="kickMember list-group-item list-group-item-action border-left-0 border-right-0">내보내기</a>
											</c:if>
										</div>
									</div>
								</div>
							</c:forEach>

						</div>
					</div>

					<div id="noticeSpace" style="display: none"></div>

				</div>
			</div>
		</div>
		<div class="mainSpace">
			<div class="mainSpace_top">
				<div class="mainSpace_top_side"></div>
				<div class="mainSpace_top_center">
					<div class="projectName sbd2 border-top-0 border-left-0 border-right-0">
						<div class="mainSpace_top_side"></div>
						<div class="mainSpace_top_center">
							<h1 id="projectHome">${MainProject_title}</h1>
						</div>
						<div class="mainSpace_top_side"></div>
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
							<div class="dropdown-menu shadow" style="border:1px solid #6079a0">
								<h5 class="dropdown-header">${loginName}</h5>
								<div style="margin-top: 20px">
									<a href="#" style="border:1px solid #6079a0"
										class="list-group-item list-group-item-action border-left-0 border-right-0">회원정보</a>
									<a href="logout" style="border:1px solid #6079a0"
										class="list-group-item list-group-item-action border-left-0 border-right-0">로그아웃</a>

								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="mainSpace_top">
				<div class="mainSpace_top_side"></div>
				<div class="mainSpace_top_center">
					
							<span id="noticeBtnSpace"></span>
							<button class="btn btn-dark" id="noticeBtn" data-toggle="1" style="width:60px">
								<i class="fas fa-bullhorn fa-lg"></i>
							</button>
							<button type="button" class="btn btn-dark" data-toggle="modal" style="width:60px"
								data-target="#myModal"><i class="fas fa-user-plus fa-lg"></i></button>

							<div class="modal" id="myModal">
								<div class="modalBlack" data-dismiss="modal"></div>
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-body h-3">
											<div class="input-group mb-3">
												<input type="text" class="form-control" id="addMember"
													name="addMember" placeholder="추가하실 아이디를 입력하세요.">
												<div class="input-group-append">
													<button class="btn btn-dark w-100" id="addmem"
														disabled="disabled">멤버 추가</button>
												</div>
											</div>
											<span id="addmemberMessage"></span>
										</div>
									</div>
								</div>
							</div>
							<button class="btn btn-dark" id="projectDetail" style="margin-right:20px;width:60px">
								<i class="far fa-calendar-check fa-lg"></i>
							</button>
				</div>
			</div>
			<div class="mainSpace_bottom">
				<div class="contentSpace_side"></div>
				<div class="contentSpace">
					<div class="timeTable rounded bgwhite sbd2 shadow" id="timeTable">
					</div>

					<div class="fileManager rounded bgwhite sbd2 shadow" id="fileManager">
						<!-- <div id="fileManagerHeaders"></div> -->
					</div>

				</div>
				<div class="contentSpace_side"></div>
				<div class="contentSpace_right">
					<div class="contentItem" style=" height: 100%;padding:20px">

						<button type="button" class="btn btn-dark h-100" id="whiteBoardModalBtn">
							<i class="fas fa-chevron-left fa-lg"></i>
						</button>
						
						<!-- 부트스트랩 modal-backdrop 클래스 지움 -->
						<input type="hidden" id="postitNumFromProjectNum"
							value="${mainproject_projectnum }">
						<div id="whiteBoardModal" style="display:none">
						<div class="modalBlack"></div>
							<div class="postitWindow rounded">
								<button class="btn btn-dark" id="addPostit" style="width: 80px">
									<span class="fa-stack fa-lg"> <i
										class="far fa-sticky-note fa-stack-2x"></i> <i
										class="fas fa-plus fa-stack-1x"></i>
									</span>
								</button>
								<button type="button" class="btn btn-danger" id="modalCloseBtn">Close</button>
							</div>
							<div id="headers">
								<div id="whiteBoardLoad"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

</html>
