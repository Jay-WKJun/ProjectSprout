<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css">
	<link rel="stylesheet" href="ucss/ucss_project.css">
	<link rel="stylesheet" href="css/bootstrap.css">
	<script src="<c:url value="/resources/js/jquery.min.js" />"></script>
	<script src="<c:url value="/resources/js/sockjs.js" />"></script>
	<script src="<c:url value="/resources/js/stomp.js" />"></script>
	<script src="js/bootstrap.bundle.min.js"></script>
	<script type="text/javascript">
		$(function(){
			startT();
			connect();

			//파일 선택시
			$("#FILE_TAG").change(function(e){
				var fileName=$('#FILE_TAG')[0].files[0].name;
				$('#fileValue').val(fileName);
				 //  $('input[type=file]')[0].files[0].name;
				 //  $("#imgUpload")[0].files[0].type;
				 //  $("#imgUpload")[0].files[0].size;
			 });

			//파일 추가 모달 열기
			$('#addFileModalBtn').on('click',function(){
				$('#addFileModal').show();
			})
			
			//파일 전송 버튼
			$('#uploads').on('click',function(){
				uploadFile();
				$('#fileValue').val('');
				$('#addFileModal').hide();
			})
			
			//파일 추가 모달 닫기
			$('#closeFileModal').on('click',function(){
				$('#fileValue').val('');
				$('#addFileModal').hide();
			})
			
			//멤버 추가 모달 열기
			$('#addMemberModalBtn').on('click',function(){
				$('#addMemberModal').show();
			})
			
			//멤버 추가 모달 닫기
			$('#closeMemberModalBtn').on('click', function(){
				$('#addMemberModal').hide();
			})
			
			//멤버 초대
			$("#BtnInvitation").on("click", function(){
				// 맴버이름으로 맴버 번호가져오기
				var memberinvitation = $('#memberinvitation').val();
				
				var asdfg = {
					"member_name" : memberinvitation
				};
				
				
				$.ajax({
					type : 'post',
					url : 'selectMemberNum',
					data : asdfg ,
					
					success : function(member_nameOne) {
						 invitations(member_nameOne); 
					}
				});
			})
			
			
			//클릭 센드
			$("#send").on("click",function(){
				var chatbox = $("#chatbox").val();
				 var chatRoom_num= ${sessionScope.chatRoom_num};  
			 	var member_num = ${sessionScope.member_num};
				var text = {
						"chat_content" : chatbox
					 	, "chatRoom_num"  : chatRoom_num 
						, "member_num" : member_num
					};
				// 테이블에 채팅내역 저장
				$.ajax({
					type : 'post',
					url : 'tinsert',
					data : text ,
					
					success : function() {
						sendMessage();
					}
				});
				$("#chatbox").focus();
			})
		
			
			document.onkeydown = function ( event ) {
			    if ( event.keyCode == 116  // F5
			        || event.ctrlKey == true && (event.keyCode == 82) // ctrl + r
			    ) {
			        //접속 강제 종료
			        disconnect();
			        // keyevent
			        event.cancelBubble = true; 
			        event.returnValue = false; 
			        setTimeout(function() {
			            window.location.reload();
			        }, 100);
			        return false;
			    }
			}
			
		})
	
		//채팅방 초대하기
		function invitations(member_nameOne){
			var member_num = member_nameOne;
			var chatRoom_num = $('#chatRoom_nums').val(); /* ${sessionScope.chatRoom_num}; */
			var member_name = $('#memberinvitation').val();
			var chatRoom_name = $('#chatRoom_namess').val();
			var qp = {
					"member_num" : member_num,
					"chatRoom_num" : chatRoom_num,
					"member_name" : member_name,
					"chatRoom_name" : chatRoom_name
			};
			$.ajax({
				type : 'post',
				url : 'invitationss',
				data : qp ,
				
				success : function(success) {
					alert("초대완료");
					}
			});
			
		}
		 
		//시작하자마자 채팅방에 있는 내용 전부 가져오기
		
		function startT() {
			
			var chatRoom_num= ${sessionScope.chatRoom_num}; 
			var member_num = ${sessionScope.member_num};
			 var content="";
			$.ajax({
				type : 'post',
				url : 'printMessage',
				data : {
					"chatRoom_num" : chatRoom_num
				} ,
				success : function(text) {
					$.each(text, function(index, item){
						if (content == undefined) {
							
						}else {
							if (item.member_num == member_num) {
						  content += '<p align="right">'+item.chat_content+'</p>';
								
							} else {
								 content += '<p>'+item.member_name+' : '+item.chat_content+'</p>';
							}
						}
					});
					$('#chatRoom').html(content);
					
				scroll();
				}
			});
		}
		function enterkey() {
			if (window.event.keyCode == 13) {
				// 엔터키가 눌렸을 때 실행할 내용
				var chatbox = $("#chatbox").val();
				 var chatRoom_num= ${sessionScope.chatRoom_num}; 
			 	var member_num=${sessionScope.member_num}; 
				var text = {
						"chat_content" : chatbox
						 , "chatRoom_num"  : chatRoom_num 
						, "member_num" : member_num
						
					};
				// 테이블에 채팅내역 저장
				$.ajax({
					type : 'post',
					url : 'tinsert',
					data : text ,
					
					success : function() {

				sendMessage();
					}
				});
			}
		}
		
		var stompClient = null;
		
		//채팅방 연결
		function connect() {
			// WebsocketMessageBrokerConfigurer의 registerStompEndpoints() 메소드에서 설정한 endpoint("/endpoint")를 파라미터로 전달
			var socket = new SockJS('/sprout/endpoint');
			stompClient = Stomp.over(socket);
			stompClient.connect({}, function(frame) {
				// 메세지 구독
				// WebsocketMessageBrokerConfigurer의 configureMessageBroker() 메소드에서 설정한 subscribe prefix("/subscribe")를 사용해야 함
				// 멀티 채팅방
				stompClient.subscribe('/subscribe/chat/${chatRoom_num}', function(message){
					
					setTimeout(function() {
						scroll();
						}, 100);
					
					var data = JSON.parse(message.body);
					$("#chatRoom").append('<p  align="right">'+data.message+"<br /></p>");
				});	
				//+data.username+" : "
			});
		}
		
		//채팅 메세지 전달
		function sendMessage() {
			
			var str = $("#chatbox").val();
			str = str.replace(/ /gi, '&nbsp;')
			str = str.replace(/(?:\r\n|\r|\n)/g, '<br />');
			
			if(str.length > 0){
				// WebsocketMessageBrokerConfigurer의 configureMessageBroker() 메소드에서 설정한 send prefix("/")를 사용해야 함
				// 멀티 채팅방
				
				stompClient.send("/chat/${chatRoom_num}", {}, JSON.stringify({
					message : str
				}));
			}
			$("#chatbox").val("");
			
		} 
		
		//파일전송
		function sendfile(updowns) {
			
			var str = $("#chatbox").val();
			str = str.replace(/ /gi, '&nbsp;')
			str = str.replace(/(?:\r\n|\r|\n)/g, '<br />');
			
			var upl = $("#FILE_TAG").val();
			  var content ='<a href="downloads?updown_num='+updowns.updown_num+'">'+updowns.originalfile+'</a>';
			 if (updowns != "") {
				stompClient.send("/chat/${chatRoom_num}", {}, JSON.stringify({
					message : content
				}));
				$("#chatbox").val("");
			} 
			
			
		} 
			 
			
		
		// 채팅방 연결 끊기
		function disconnect() {
			stompClient.disconnect();
		}
		
		//파일 업로드
		function uploadFile(){
	         var form = $('#FILE_FORM')[0];
	         var formData = new FormData(form);
	         formData.append("fileObj", $("#FILE_TAG")[0].files[0]);
	         $.ajax({
	            		 url: 'write', 
	                     processData: false,
	                     contentType: false,
	                     data: formData,
	                     type: 'POST',
	                     success: function(updowns){
	                    	 sendfile(updowns);
	                     }
	             });
	     }
		
		function scroll() {
			$("#chatRoom").scrollTop($("#chatRoom")[0].scrollHeight);
		}
	</script>
	<style type="text/css">
		html{
			height:100%;
			background-color: #efefef;
			overflow-y:hidden;
		}
		
		body{
			padding:5px;
			background-color: #efefef;
		}
		
	 	#chatRoom{
			width: 100%;
			height: 450px; 
			overflow: scroll;
			overflow-x:hidden;
		} 
		::-webkit-scrollbar-track{
		 -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.3);
		 border-radius: 10px;
		 background-color: #F5F5F5;
		}
		
		::-webkit-scrollbar{
		 width: 12px;
		 background-color: #F5F5F5;
		}
		
		::-webkit-scrollbar-thumb{
		 border-radius: 10px;
		 -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,.3);
		 background-color: #555;
		 }
		 
		 /* =======input file======== */
		 

	</style>
<title>일반 채팅 방</title>
</head>
<body>
	<div class="useFont bgwhite">
		<div class="border-bottom-0 sbd2 w-100">
			<div class="mainSpace">
				<div class="mainSpace_bottom"></div>
				<div class="mainSpace_top fontSize20" style="padding:10px;">
					<div class="mainSpace_top_side"></div>
					<div class="mainSpace_top_center">
						${chatRoom_namess }
					</div>
					<div class="mainSpace_top_side">
						<div class="mainSpace_top">
							<div class="mainSpace_top_side"></div>
							<div class="mainSpace_top_center">
								<button class="btn btn-light" id="addMemberModalBtn">
									<i class="fas fa-user-plus fa-lg"></i>
								</button>
								<div id="addMemberModal" style="display:none">
									<div class="modalBlack"></div>
									<div class="noticeModalContent">
										<div style="padding:5px">
											<input class="form-control" type="text" id="memberinvitation">
										</div>
										<div style="padding:5px">
											<input type="button" class="btn btn-dark" id="BtnInvitation" value="초대">
											<button class="btn btn-danger" id="closeMemberModalBtn">닫기</button>
										</div>
									</div>
									</div>
							</div>
						</div>
					</div>
				</div>
				<div class="mainSpace_bottom"></div>
			</div>
		</div>
		<div class="border-bottom-0 border-right-0 border-left-0 sbd1"></div>
		<div id="chatRoom" class="border-top-0 border-bottom-0 sbd2">
		</div>
		<div class="border-bottom-0 border-right-0 border-left-0 sbd1"></div>
		<div class="border-top-0 sbd2">
			<div class="mainSpace_top">
				<div class="mainSpace_top_center">
					<button class="btn btn-dark" id="addFileModalBtn">
						<i class="fas fa-file-medical fa-lg"></i>
					</button>
					<div id="addFileModal" style="display:none">
						<div class="modalBlack"></div>
						<div class="noticeModalContent">
							<form id="FILE_FORM"  method="post" enctype="multipart/form-data">
								<!-- 새로 첨부할 파일 선택 --> 
								<div class="mainSpace_top" style="padding:5px">
									<div class="mainSpace_top_center">
										<label class="btn btn-dark">
										<i class="fas fa-plus"></i>
										<input type="file" class="form-control" name="upload" id="FILE_TAG" size="30" style="display:none">
										</label>
									</div>
									<div class="mainSpace_top_side">
										<input class="form-control" type="text" id="fileValue" readonly="readonly">
										<input type="hidden" value="${chatRoom_namess }" id="chatRoom_namess">
										<input type="hidden" value="${sessionScope.chatRoom_num }" id="chatRoom_nums">
									</div>
								</div>
								<div style="padding:5px">
									<input type="button" id ="uploads" class="btn btn-dark" value="전송">
									<input type="button" id ="closeFileModal" class="btn btn-danger" value="닫기">
								</div>
							</form>
						</div>
					</div>					
				</div>
				<div class="mainSpace_top_side">
					<input class="form-control w-100" type="text" id="chatbox" onkeydown="enterkey();">
				</div>
				<div class="mainSpace_top_center">
					<input class="btn btn-dark" type="button" id="send" value="보내기">
				</div>
			</div>
			
		</div>
	</div>
	<br>
</body>
</html>