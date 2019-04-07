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

</head>
<body>
	<div class="wrapper">
		<div class="sideSpace">
			<div class="sideSpace_top">
				<a href="/sprout"><img class="webLogo" src="img/sprout_logo.png"></a>
			</div>
			<div class="sideSpace_bottom">
				<ul class="nav nav-tabs">
					<li class="nav-item"><a class="nav-link active"
						data-toggle="tab" href="#home">멤버</a></li>
					<li class="nav-item"><a class="nav-link" data-toggle="tab"
						href="#menu1">메세지</a></li>
					<li class="nav-item" id="noticeCheckBtn"><a class="nav-link" data-toggle="tab"
						href="#menu2">공지사항</a></li>
				</ul>
				<!-- 같은 프로젝트일때 참여인원  출력 -->
				<div
					class="communicationBar rounded-bottom border border-top-0 w-100"
					style="padding: 5px">
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
					<div id="noticeSpace">
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
					
						<!-- 아래 script를 통해 이 class이름안에 모든 타임테이블이 그려집니다. -->
						<div class="gantt"></div>
					
						<hr>
					
						<input type="button" id="regist" value="regist" onclick="layer_popup('#layer2', null)"/>
					
						<!-- 여기서부터  popup설정 -->
						<a href="#layer2" class="btn-example"></a>
						<div class="dim-layer">
							<div class="dimBg"></div>
							<div id="layer2" class="pop-layer">
								<div class="pop-container">
									<div class="pop-conts">
										<!--content //-->
										<p class="ctxt mb20">
											이곳에 디테일 들어옵니다.
										</p>
					
										<div class="btn-r">
											<a href="#" class="btn-layerClose" id="update">수정</a>
											<a href="#" class="btn-layerClose" id="delete">삭제</a>
											<a href="#" class="btn-layerClose" id="close">닫기</a>
										</div>
										<!--// content-->
									</div>
								</div>
							</div>
						</div>
						<!-- 여기까지 popup설정 -->
					
					</div>

					<div class="fileManager rounded border" id="fileManager">
						<!-- <div id="fileManagerHeaders"></div> -->
					</div>
					
				</div>
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
	<script src="gantt/script/jquery.fn.gantt.js"></script>
	<script>
    
		//등록할 form을 먼저 만들고
		//form을 submit할때,
		//동시에 source에 JSON데이터를 새로 쓴다.(LIST나 Map으로 보내고 반복해서 source에 쓰는식으로 해야한다.)
		//새로 쓰고 db에 쓰면서 redirect요청으로 새로고침을 한다.
		$(function() {
			
			/* $('#regist').click(function(){
				layer_popup('#layer2');
			}); */
			
			
			//밑에 source에 들어가는 데이터 형은 array name, desc, value(from, to label, customClass)
			//gantt 매개변수는 {source : 객체}
			"use strict";
			$(".gantt").gantt({
				source: ${jsonTest},
				navigate: "scroll",
				scale: "weeks",
				maxScale: "months",
				minScale: "days",
				itemsPerPage: 10,
				onItemClick: function(pcNum) {
					alert("Item clicked - show some details");
					alert(pcNum);
					findPC(pcNum);
				},
				onAddClick: function(dt, rowId) {
					
				},
				onRender: function() {
					if (window.console && typeof console.log === "function") {
						console.log("chart rendered");
					}
				}
			});

			 /* $(".gantt").popover({
				selector: ".bar",
				title: function _getItemText() {
                    return this.textContent;
                },
                container: '.gantt',
				content: "And I'm the content of said popover.",
				trigger: "hover",
				placement: "auto right"
			}); */
			
			/* $('.btn-example').click(function(){
		        var href = $(this).attr('href');
		        layer_popup('#layer2');
		    }); */

		});
		
		function findPC(pcNum) {
			
			$.ajax({
				type : 'GET',
				url : 'checkPC',
				data : 'pcNum='+pcNum,
				success : function(pc){
					alert('success when findPC!' + JSON.stringify(pc));
					layer_popup('#layer2', pc);
					
				},
				error : function() {
					alert('fail when findPC!');
				}
			})
		}
		
		
	    function layer_popup(el, pc){

	        var $el = $(el);        //레이어의 id를 $el 변수에 저장
	        alert(JSON.stringify($el));
	        //var isDim = $el.prev().hasClass('dimBg');   //dimmed 레이어를 감지하기 위한 boolean 변수

	        //isDim ? $('.dim-layer').fadeIn() : $el.fadeIn();
	        $('.dim-layer').fadeIn();

	        var $elWidth = ~~($el.outerWidth()),
	            $elHeight = ~~($el.outerHeight()),
	            docWidth = $(document).width(),
	            docHeight = $(document).height();

	        // 화면의 중앙에 레이어를 띄운다.
	        if ($elHeight < docHeight || $elWidth < docWidth) {
	            $el.css({
	                marginTop: -$elHeight /2,
	                marginLeft: -$elWidth/2
	            })
	        } else {
	            $el.css({top: 0, left: 0});
	        }
	        
	        if (pc == null) {
	        	alert('pc는 null');
	        	
	        	$.ajax({
					type : 'GET',
					url : 'timetableMake',
					success : function(results){
						alert('success when load the createForm!' + JSON.stringify(results));
						var options = '';
						$.each(results, function(key, value){
	        				options += '<option value="'+value.member_num+'">'+value.member_name+'</option>';
	        				
	        			});
						
						$('.ctxt').html('<form method="POST" action="timetableMake" id="createForm" name="createForm" accept-charset="utf-8">'
			        			+'<input type="text" id="projectContent_title" name="projectContent_title" placeholder="title"><br>'
			        			+'<input type="text" id="projectContent_content" name="projectContent_content" placeholder="content"><br>'
			        			+'<input type="date" id="projectContent_startDate" name="projectContent_startDate"><br>'
			        			+'<input type="date" id="projectContent_endDate" name="projectContent_endDate"><br>'
			        			+'<input type="text" id="projectContent_color" name="projectContent_color"><br>'
			        			+'<input type="hidden" id="mainproject_projectNum" name="mainproject_projectNum"><br>'
			        			+'<select id="member_num" name="member_num">'
			        			+ options
			        			+'</select>' 
			        			+'</form>'
			        	);
						
						//완료 버튼 만들기
						$('div.btn-r').html('<a href="#" class="btn-layerClose" id="close">닫기</a>'
								+'<input type="button" class="btn-layerClose" id="createContent" onclick="doCreateSubmit()" value="완료" />'
						);
			        	
			        	//닫기 메소드
				        $el.find('a#close').click(function(){
				            //isDim ? $('.dim-layer').fadeOut() : $el.fadeOut(); // 닫기 버튼을 클릭하면 레이어가 닫힌다.
				            $('.dim-layer').fadeOut();
				            return false;
				        });
						
					},
					error : function() {
						alert('fail when load the createForm!');
					}
				})
	        	
	        	//pc가 없다면 만들기로 불러온 것이다.
	        	
			} else{
				alert('pc는 null 아님');
				//내용 쓰기 메소드
		        $('.ctxt').html('projectContent_title : '+pc.projectContent_title+'<br>'
		        	+'projectContent_content : '+pc.projectContent_content+'<br>'
		        	+'projectContent_startDate : '+pc.projectContent_startDate+'<br>'
		        	+'projectContent_endDate : '+pc.projectContent_endDate+'<br>'
		        	+'projectContent_color : '+pc.projectContent_color+'<br>'
		        	+'mainproject_projectNum : '+pc.mainproject_projectNum+'<br>'
		        	+'member_num : '+pc.member_num
		        	);
		        
		        //지우기 메소드
		        $el.find('a#delete').click(function(){
		        	alert('delete clicked');
		        	$('.ctxt').html('<form method="GET" action="deleteContent" id="deleteForm" name="deleteForm" accept-charset="utf-8">'
		        			+'<input type="hidden" id="projectContent_num" name="projectContent_num" value="'+pc.projectContent_num+'">'
		        			+'</form>'
		        	);
		        	$('#deleteForm').submit();
		        });
		        
				//닫기 메소드
		        $el.find('a#close').click(function(){
		            //isDim ? $('.dim-layer').fadeOut() : $el.fadeOut(); // 닫기 버튼을 클릭하면 레이어가 닫힌다.
		            $('.dim-layer').fadeOut();
		            return false;
		        });

		        //update form으로 바꿔주는 메소드, string타입의 날짜는 date타입으로 바꿔줘야한다.
		        $el.find('a#update').click(function(){
		        	var startDateCh = new Date(pc.projectContent_startDate);
		        	var endDateCh = new Date(pc.projectContent_endDate);
		        	
		        	startDateCh = getFormatDate(startDateCh);
		        	endDateCh = getFormatDate(endDateCh);
		        	
		        	alert(startDateCh + typeof(startDateCh));
		        	alert(endDateCh + typeof(endDateCh));
		        	
		        	$('.ctxt').html('<form method="post" action="updateContent" id="updateForm" name="updateForm" accept-charset="utf-8">'
		        			+'<input type="hidden" id="projectContent_num" name="projectContent_num" value="'+pc.projectContent_num+'">'
		        			+'<input type="text" id="projectContent_title" name="projectContent_title" value="'+pc.projectContent_title+'"><br>'
		        			+'<input type="text" id="projectContent_content" name="projectContent_content" value="'+pc.projectContent_content+'"><br>'
		    	        	+'<input type="date" id="projectContent_startDate" name="projectContent_startDate" value="'+startDateCh+'"><br>'
		    	        	+'<input type="date" id="projectContent_endDate" name="projectContent_endDate" value="'+endDateCh+'"><br>'
		    	        	+'<input type="text" id="projectContent_color" name="projectContent_color" value="'+pc.projectContent_color+'"><br>'
		    	        	+'<input type="text" id="mainproject_projectNum" name="mainproject_projectNum" value="'+pc.mainproject_projectNum+'"><br>'
		    	        	+'<input type="text" id="member_num" name="member_num" value="'+pc.member_num+'"><br>'
		    	        	+'</form>'
		    	        	);
		        	//완료버튼 생성
		        	$('div.btn-r').html('<a href="#" class="btn-layerClose" id="close">닫기</a>'
		        			+'<input type="button" class="btn-layerClose" id="updateContent" onclick="doUpdateSubmit()" value="완료" />');
		            return false;
				});
			}
	        
		}//layer
	        
	  	//업데이트 완료버튼 동작
	    function doUpdateSubmit() {
	    	//컨트롤러로 직행!
	    	$('#updateForm').submit();
	    }
		
	  //생성 완료버튼 동작
	    function doCreateSubmit() {
	    	//컨트롤러로 직행!
	    	$('#createForm').submit();
	    }
	    
	    //날짜변환 메소드
	    function getFormatDate(date){
	    	var year = date.getFullYear();                                 //yyyy
	    	var month = (1 + date.getMonth());                     //M
	    	month = month >= 10 ? month : '0' + month;     // month 두자리로 저장
	    	var day = date.getDate();                                        //d
	    	day = day >= 10 ? day : '0' + day;                            //day 두자리로 저장
	    	return  year + '-' + month + '-' + day;
	    }

	    
    </script>
</body>

</html>
