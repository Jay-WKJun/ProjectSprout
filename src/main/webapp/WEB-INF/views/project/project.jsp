<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>project</title>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css">
<link rel="stylesheet" href="ucss/ucss_project.css">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="gantt/css/style.css" />
<script src="js/jquery-3.3.1.min.js"></script>
<script src="ujs/ujs_project.js"></script>
<script src="js/bootstrap.bundle.min.js"></script>
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
		<div class="sideSpace">
			<div class="sideSpace_top">
				<a onclick="home();" href="#"><img class="webLogo" src="img/sprout_logo.png" id="logo"></a>
			</div>
			<div class="sideSpace_bottom">
				<ul class="nav nav-tabs">
					<li class="nav-item bgwhite"><a class="nav-link active"
						id="memberChangeBtn" data-toggle="tab">멤버</a></li>
					<li class="nav-item bgwhite"><a class="nav-link" id="messageSpaceBtn"
						data-toggle="tab">채팅</a></li>
					<li class="nav-item bgwhite" id="noticeCheckBtn"><a
						class="nav-link" data-toggle="tab">공지사항</a></li>

				</ul>
				
				<!-- 같은 프로젝트일때 참여인원  출력 -->
				<div
					class="communicationBar rounded-bottom border-top-0 w-100 sbd2 shadow bgwhite"
					style="padding: 5px">
					<div id="memberSpace_display" style="display: block">
					
						<div class="list-group">

							<c:forEach var="list" items="${projectMembersList}">
								<div class="dropdown dropright float-right list-group-item list-group-item-action"
									style="margin-bottom: 2px; border: 1px solid #6079a0;">
									<div data-toggle="dropdown"> 
										<img class="rounded-circle border" src="download?loginId=${list.member_id}" onerror="src='img/empty_profile.png'"
												style="width: 35px; height: 35px;margin-right:10px" id="memberIcon"> 
										${list.member_name}
									</div>
									<div class="dropdown-menu shadow" style="border:1px solid #6079a0">
										<h5 class="dropdown-header">${list.member_name}</h5>
										<div style="margin-top: 20px" data-pno="${list.member_num}"
											id="forkick">
											<a href="#" style="border:1px solid #6079a0" data-name="${list.member_name }"
												class="list-group-item list-group-item-action border-left-0 border-right-0 messagesend">메세지
												보내기</a>
											
											<input type="hidden" value="${sessionScope.chatRoom_num }" id="chatRoom_nums">
											<input type="hidden" value="${list.member_name }" id="ClickMember_name">
											<c:if test="${member_rank eq 5}">
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
					
					<div id="noticeDetailModal">
						<div class="modalBlack"></div>
						<div class="noticeModalContent">
							<textarea class="form-control" style="height:300px" id="noticeDetail"></textarea>
							<button class="btn btn-danger" id="cancelNoticeDetailBtn" style="margin-top:10px">닫기</button>
						</div>
					</div>
					
					<!-- 메신저 영역 -->
					<div id="messageSpace" style="display: none">
					
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
								<c:if test="${not empty sessionScope.mime}"> 
									<img class="rounded-circle border" src="download?loginId=${loginId}" onerror="src='img/empty_profile.png'"
										style="width: 50px; height: 50px" id="userProfileIcon">
								</c:if>
								<c:if test="${empty sessionScope.mime}"> 
									<img class="rounded-circle border" style="width: 50px; height: 50px" 
										src="img/empty_profile.png" id="userProfileIcon">
								</c:if>
								<!-- <img class="rounded-circle border"
									style="width: 50px; height: 50px" src="img/empty_profile.png"
									id="userProfileIcon"> -->
							</div>
							<div class="dropdown-menu shadow" style="border:1px solid #6079a0">
								<h5 class="dropdown-header">${loginName}</h5>
								<div style="margin-top: 20px">
									<a href="#" style="border:1px solid #6079a0" id="memberInfoBtn"
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
							
							<!-- 채팅방 생성 모달 -->
							<button type="button" class="btn btn-dark"  style="width:60px" id="addChatMemberBtn">
								<i class="fas fa-comment-medical fa-2x"></i>
							</button>
					
							<!-- The Modal -->
							<div id="ChatRoomInvitation">
								<div class="modalBlack"></div>
								<div class="noticeModalContent rounded sbd2">
									<div class="container">
										<div class="row">
											<div class="col">
												<input type="text" class="form-control" id="addMembers" name="addMember" placeholder="생성할 채팅방 이름">
												<input type="hidden" id="member_ids" value="${sessionScope.loginId }"/>
												<input type="hidden" id="member_nums" value="${sessionScope.loginNum }"/>
												
											</div>
										</div>
										<div class="row">
											<div class="col">
												<button class="btn btn-dark" id="Invitatio" style="margin:10px 5px 0 0">채팅방 생성</button>
												<button class="btn btn-danger" id="chatRoomModalCloseBtn" style="margin-top:10px">닫기</button>
											</div>
										</div>
									</div>
								</div>
							</div>
							
							
							<!-- 공기사항 모달 -->
							<c:if test="${member_rank eq 5}">
								<button class="btn btn-dark" id="noticeModalBtn" style="width:60px">
									<i class="fas fa-bullhorn fa-2x"></i>
								</button>
							</c:if>
							<div id="noticeModal">
								<div class="modalBlack"></div>
								<div class="noticeModalContent rounded sbd2">
									<textarea class="form-control" id="noticeContent" style="height:300px"></textarea>
									<button class="btn btn-dark" id="createNoticeBtn" style="margin:10px 5px 0 0">공지사항 추가</button>
									<button class="btn btn-danger" id="cancelNoticeBtn" style="margin-top:10px">닫기</button>
									<span id="noticeMsg"></span>
								</div>
							</div>
							
							<!-- 멤버 추가 모달 -->
							<c:if test="${member_rank eq 5}">
								<button type="button" id="memberPlusModalBtn" class="btn btn-dark" style="width:60px">
									<i class="fas fa-user-plus fa-2x"></i>
								</button>
							</c:if>
							
							<div id="memberPlusSpace">
								<div class="modalBlack"></div>
								<form action="addProjectMember" id="addProjectMember"
								method="GET">
								<div class="noticeModalContent rounded sbd2">
										<input type="text" class="form-control" id="addMember"
											name="addMember" placeholder="추가하실 아이디를 입력하세요.">
									<div class="container">
									<div class="row">
									<div class="col-8" style="text-align:left;padding:10px">
										<span id="addmemberMessageSpace"></span>
									</div>
									<div class="col">
										<button class="btn btn-dark" id="addmem" style="margin-top:10px"
											disabled="disabled">멤버 추가</button>
										<input type="button" class="btn btn-danger" id="cancelmemberPlusBtn" style="margin-top:10px" value="닫기">
									</div>
									</div>
									</div>
								</div>
								</form>
							</div>
							
							<button class="btn btn-dark" id="projectDetail" style="margin-right:20px;width:60px">
								<i class="fas fa-paste fa-2x"></i>
							</button>
				</div>
			</div>
			<div class="mainSpace_bottom">
				<div class="contentSpace_side"></div>
				<div class="contentSpace">
					<div class="timeTable rounded bgwhite sbd2 shadow" id="timeTable">
						
					</div>

					<div class="fileManager rounded bgwhite sbd2 shadow" id="fileManager">
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
						<div id="whiteBoardModal" style="display:none;">
						<div class="modalBlack"></div>
							<div id="headers">
								<div id="whiteBoardLoad"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<input type="hidden" id="memberName_click"	value="${Click_member_name }">
	
</body>

</html>