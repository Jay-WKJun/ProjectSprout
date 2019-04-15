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
					<li class="nav-item"><a class="nav-link active" id="memberChangeBtn"
						data-toggle="tab" href="#home">멤버</a></li>
					<li class="nav-item"><a class="nav-link" data-toggle="tab"
						href="#menu1" id="messageSpaceBtn">메세지</a></li>
					<li class="nav-item" id="noticeCheckBtn"><a class="nav-link" data-toggle="tab"
						href="#menu2">공지사항</a></li>
				</ul>
				<!-- 같은 프로젝트일때 참여인원  출력 -->
				<div
					class="communicationBar rounded-bottom border border-top-0 w-100"
					style="padding: 5px">
					<div id="memberSpace_display" style="display:block">
					<div class="list-group">
					
					<form action="addProjectMember" id="addProjectMember" method="GET"> 
					
					<button type="button" class="btn btn-dark w-100" data-toggle="modal" data-target="#myModal">
						멤버 초대하기
					</button>
					
					<!-- The Modal -->
					<div class="modal" id="myModal">
						<div class="modal-dialog">
							<div class="modal-content">
								<!-- Modal body -->
								<div class="modal-body">
									<div class="input-group mb-3">
									<input type="text" class="form-control" id="addMember" name="addMember" placeholder="추가하실 아이디를 입력하세요.">
										<div class="input-group-append">
											<button class="btn btn-dark w-100" id="addmem" disabled="disabled">멤버 추가</button> 
										</div>
									</div>
									<span id = "addmemberMessage"></span>
								</div>
							</div>
						</div>
					</div>
				</form> 
				
				
										
						<c:forEach var="list" items="${projectMembersList}">
							<div
								class="dropdown dropright float-right list-group-item list-group-item-action">
								<div data-toggle="dropdown">${list.member_name}</div>
								<div class="dropdown-menu">
									<h5 class="dropdown-header">${list.member_name}</h5>
									<div style="margin-top: 20px" data-pno="${list.member_num}" id="forkick">
										<a href="#"
											class="list-group-item list-group-item-action border-left-0 border-right-0">플래너</a>
										<a href="#"
											class="list-group-item list-group-item-action border-left-0 border-right-0">메세지
											보내기</a>
										<c:if test="${member_rank == 5}"> 
											 <a href="#"
											class="kickMember list-group-item list-group-item-action border-left-0 border-right-0">내보내기</a>
										</c:if> 
									</div>
								</div>
							</div>
						</c:forEach>

					</div>
					</div>
					
					<!-- 메신저 영역 -->
					<div id="messageSpace" style="display: none">
					
					<button type="button" class="btn btn-dark w-100" data-toggle="modal" data-target="#ChatRoomInvitation">
						채팅방 생성
					</button>
					
					<!-- The Modal -->
					<div class="modal" id="ChatRoomInvitation">
						<div class="modal-dialog">
							<div class="modal-content">
								<!-- Modal body -->
								<div class="modal-body">
									<div class="input-group mb-3">
									<input type="text" class="form-control" id="addMembers" name="addMember" placeholder="생성할 채팅방 이름">
										<div class="input-group-append">
											<button class="btn btn-dark w-100" id="Invitatio">채팅방 생성</button> 
											<input type="hidden" id="member_ids" value="${sessionScope.loginId }"/>
											<input type="hidden" id="member_nums" value="${sessionScope.loginNum }"/>
										</div>
									</div>
									<span id = "addmemberMessage"></span>
								</div>
							</div>
						</div>
					</div>
					<!-- code here -->
						<div id="messageChatRoomList">
			
						</div>
					</div>
					
					<div id="noticeSpace" style="display: none">
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
			<div class="mainSpace_top">
				<div class="mainSpace_top_side"></div>
				<div class="mainSpace_top_center"></div>
				<div class="mainSpace_top_side" style="text-align: right">
					<div class="contentItem" style="margin-right: 20px">
					<span id="noticeBtnSpace">
				
					</span>
						<button class="btn btn-dark" id="noticeBtn" data-toggle="1">
							<i class="fas fa-bullhorn fa-lg"></i>
						</button>
						<button class="btn btn-dark" id="projectDetail">
							<i class="far fa-calendar-check fa-lg"></i>
						</button>
					</div>
				</div>
			</div>
			<div class="mainSpace_bottom">
				<div class="contentSpace_side"></div>
				<div class="contentSpace">
					<div class="timeTable rounded border" id="timeTable">
					</div>

					<div class="fileManager rounded border" id="fileManager">
						<!-- <div id="fileManagerHeaders"></div> -->
					</div>
					
				</div>
				<div class="contentSpace_side"></div>
				<div class="contentSpace_right">
					<div class="contentItem" style="margin: 20px; height: 100%;">
					
					<button type="button" class="btn btn-dark h-100" data-toggle="modal" data-target="#whiteBoardModal" id="modalBtn">
						<i class="fas fa-chevron-left fa-lg"></i>
					</button>
					
						<!-- 부트스트랩 modal-backdrop 클래스 지움 -->
						<input type="hidden" id="postitNumFromProjectNum" value="${mainproject_projectnum }">
						<div class="modal w-100 h-100" id="whiteBoardModal">
							<div class="mainSpace" id="whiteBoardMainSpace">
										<!-- <div class="modal-header rounded postitWindow">
											<button class="btn btn-dark" id="addPostit"
												style="width: 80px">
												<span class="fa-stack fa-lg"> <i
													class="far fa-sticky-note fa-stack-2x"></i> <i
													class="fas fa-plus fa-stack-1x"></i>
												</span>
											</button>
											<button type="button" class="btn btn-danger modalCloseBtn" 
												data-dismiss="modal">Close</button>
										</div> -->
								<div id="headers">
									<div id="whiteBoardLoad"></div>
								</div>
								<div class="w-100 modalCloseBtn" style="height:700px;" data-dismiss="modal"></div>
							</div>
						</div>
						
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

</html>
