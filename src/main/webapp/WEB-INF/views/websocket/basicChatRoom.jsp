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
	$(function() {
		connect();

		$("#send").on("click", function() {

			var chatbox = $("#chatbox").val();

			$.ajax({
				type : 'post',
				url : 'tinsert',
				data : {
					text : chatbox
				},
				
				success : function() {
					sendMessage();
				}
			});
			$("#chatbox").focus();
		})

		 document.onkeydown = function(event) {

			if (event.keyCode == 116 // F5
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

	 function enterkey() {
		if (window.event.keyCode == 13) {
			// 엔터키가 눌렸을 때 실행할 내용
			var chatbox = $("#chatbox").val();
			$.ajax({
				type : 'post',
				url : 'tinsert',
				data : {
					text : chatbox
				},
				success : function() {

				}
			});
			sendMessage();
		}
	} 

	var stompClient = null;

	//채팅방 연결
	function connect() {
		//WebsocketMessageBrokerConfigurer의 registerStompEndpoints() 메소드에서 설정한 endpoint("/endpoint")를 파라미터로 전달
		var socket = new SockJS('/kr/endpoint');
		stompClient = Stomp.over(socket);
		stompClient.connect({}, function(frame) {
			
			// 메세지 구독
			// WebsocketMessageBrokerConfigurer의 configureMessageBroker() 메소드에서 설정한 subscribe prefix("/subscribe")를 사용해야 함
			stompClient.subscribe('/subscribe/basicChatRoom',
					function(message) {
					setTimeout(function() {
					scroll();
					}, 100);
						var data = JSON.parse(message.body);
						$("#chatroom").append(
								data.username + " さま ->  " + data.message
										+ "<br />");
					});
			
		});
	}

	//채팅 메세지 전달
	function sendMessage() {
		var str = $("#chatbox").val();
		str = str.replace(/ /gi, '&nbsp;')
		str = str.replace(/(?:\r\n|\r|\n)/g, '<br />');
		if (str.length > 0) {
			// WebsocketMessageBrokerConfigurer의 configureMessageBroker() 메소드에서 설정한 send prefix("/")를 사용해야 함
			stompClient.send("/basicChatRoom", {}, JSON.stringify({
				message : str
			}));
		

		}
		
		$("#chatbox").val("");
	}

	// 채팅방 연결 끊기
	function disconnect() {
		stompClient.disconnect();
	}

	function scroll() {
		$("#chatroom").scrollTop($("#chatroom")[0].scrollHeight);
	}
	
	
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
                     success: function(result){
                    	console.log("せいこう!");
                     }
             });
     }


	
	
</script>
<style type="text/css">
#chatroom {
	width: 300px;
	height: 300px;
	border: 1px solid;
	overflow: scroll;
}
</style>
<title>일반 채팅 방</title>
</head>
<body>
 
	
		<input type="text" id="chatbox"  onkeydown="return enterkey();">
		<input type="button" id="send" value="전송"><br>
		<br>
	
	<div id="chatroom"></div>
	<form id="FILE_FORM"  method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<td>ファイル添付</td>
				<td>
					<!-- 새로 첨부할 파일 선택 --> 
					<input type="file" name="upload" id="FILE_TAG"size="30">
					<a class="ui-shadow ui-btn ui-corner-all" href="javascript:uploadFile();">送る</a>
			
				</td>
			</tr>
		</table>
	</form>
</body>
</html>