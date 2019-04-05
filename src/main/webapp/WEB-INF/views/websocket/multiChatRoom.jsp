<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script src="<c:url value="/resources/js/jquery.min.js" />"></script>
	<script src="<c:url value="/resources/js/sockjs.js" />"></script>
	<script src="<c:url value="/resources/js/stomp.js" />"></script>
	<script type="text/javascript">
		$(function(){
			startT();
			connect();
		
			$("#send").on("click",function(){
				var chatbox = $("#chatbox").val();
				var chatRoom_num=${chatRoom_num};
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
	
		//시작하자마자 채팅방에 있는 내용 전부 가져오기
		function startT() {
			var chatRoom_num=${chatRoom_num};
			 var content;
			$.ajax({
				type : 'post',
				url : 'printMessage',
				data : {
					"chatRoom_num" : chatRoom_num
				} ,
				success : function(text) {
					$.each(text, function(index, item){
						  content += '<p>'+item.chat_content+'</p>';
					});
					$('#chatRoom').html(content);
				}
			});
		}
		function enterkey() {
			if (window.event.keyCode == 13) {
				// 엔터키가 눌렸을 때 실행할 내용
				var chatbox = $("#chatbox").val();
				var chatRoom_num=${chatRoom_num};
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
					$("#chatRoom").append(data.username+" 님 -> "+data.message+"<br />");
				});	
				
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
			/* '<a href="download?boardnum='+${UpDown.updown_num }+'">'+${UpDown.originalfile}+'</a>' */
			  var content ='<a href="download?updown_num='+updowns.updown_num+'">'+updowns.originalfile+'</a>';
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
		#chatRoom{
			width: 300px;
			height: 300px;
			border: 1px solid;
			overflow: scroll;
		}
	</style>
<title>일반 채팅 방</title>
</head>
<body>
	<input type="text" id="chatbox" onkeydown="enterkey();">
	<input type="button" id="send" value="전송"><br>
	<br>
	<div id="chatRoom">
	</div>
	
	<form id="FILE_FORM"  method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<td>파일첨부</td>
				<td>
					<!-- 새로 첨부할 파일 선택 --> 
					<input type="file" name="upload" id="FILE_TAG"size="30">
					<a id ="uploads" class="ui-shadow ui-btn ui-corner-all" href="javascript:uploadFile();">전송</a>
				</td>
			</tr>
		</table>
	</form>
	
</body>
</html>