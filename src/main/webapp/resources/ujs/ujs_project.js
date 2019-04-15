$(function() {
	
	$('#userProfileIcon').on('mouseover', function() {
		$('#userProfileIcon').attr('class', 'rounded-circle');
	})
	$('#userProfileIcon').on('click', function() {
		$('#userProfileIcon').attr('class', 'rounded-circle');
	})
	$('#userProfileIcon').on('mouseup', function() {
		$('#userProfileIcon').attr('class', 'rounded-circle border');
	})
	$('#userProfileIcon').on('mouseout', function() {
		$('#userProfileIcon').attr('class', 'rounded-circle border');
	})

	//파일 매니저
	var postitNumFromProjectNum=$('#postitNumFromProjectNum').val();
	$('#fileManager').load('fileManager?postitNumFromProjectNum='+postitNumFromProjectNum);
	
	//화이트 보드
	$('#modalBtn').on('click',openWhiteBoard);
	$('.modalCloseBtn').on('click', closeWhiteBoard);
	//$('#whiteBoardMainSpace').on('click', closeWhiteBoard);
	
	//아이디 추가하기
	$('#addmem').on('click',function(){
		$('#addProjectMember').submit();
		$('#addmem').attr('disabled','disabled');
	})
	
	//채팅 생성
	$('#Invitatio').on('click',function(){
		var chatRoom_name =$('#addMembers').val();
		/*chatRoomCreat(addMembers);*/
		ChatRoomList(chatRoom_name);
	})
	
	//공지사항 추가 버튼
	$('#noticeBtn').on('click', createNotice);
	
	//공지사항 버튼 
	$('#noticeCheckBtn').on('click',function(){
		$('#noticeSpace').attr('style','display:block');
		$('#memberSpace_display').attr('style','display:none');
		$('#messageSpace').attr('style','display:none');
		nolist();
	})
	
	//메시지 이동 버튼
	$('#messageSpaceBtn').on('click', function() {
		$('#noticeSpace').attr('style','display:none');
		$('#memberSpace_display').attr('style','display:none');
		$('#messageSpace').attr('style','display:block');
		messapgespace();
	})
	
	//멤버리스트로 돌아가는 버튼
	$('#memberChangeBtn').on('click',function(){
		$('#noticeSpace').attr('style','display:none');
		$('#memberSpace_display').attr('style','display:block');
		$('#messageSpace').attr('style','display:none');
	})
	
	//강퇴버튼 
	$('.kickMember').on('click',kickMember);
	
	//detail로 넘어가는 버튼
	$('#projectDetail').on('click',detail);

	//나중에 삭제
	/*$('#timeTable').on('click', function(){
		location.href = "timetable";
	});*/
	
	//타임 테이블
	$('#timeTable').load('timetable');
	
})
//채팅방 생성
function chatRoomCreat(chatRoom_name){
	var content;
	var chatRoom_name =chatRoom_name;
	var member_id = $('#member_ids').val();//세션아이디
/*	$.each(memberList, function(index, item){*/
			/*content += '<a href="multiChatRoom?chatRoom_num='+item.member_name+'">'+item.member_name+'번방</a></br>'*/
			//Text테이블에 chatROomname추가야해 나중에 리스트로 출력할때 네임들 뽑기가가능
			/*content += '<a href="multiChatRoom?chatRoom_name='+chatRoom_name+'">'+chatRoom_name+'</a></br>'
	*/
/*	});*/
	/*$('#messageChatRoomList').html(content);
*/
	content += '<a href="multiChatRoom?chatRoom_name='+chatRoom_name+'">'+chatRoom_name+'</a></br>'

	$('#messageChatRoomList').html(content);
	
	ChatRoomList(chatRoom_name);
}
//메시지 이동
function messapgespace() {
	var member_id = $('#member_ids').val();//세션아이디
	var content = "";
	
	var member_name = {
			"member_name" : member_id
	};
	$.ajax({
		
		method : 'post',
		url : 'chatRoomM',
		data : member_name,
		success : function(memberList){
			ChatRoomList2(memberList);
		}
		
	})
		
}
//채팅생성시 text에 채팅방이름저장
function ChatRoomList(chatRoom_name){
	var content;
	var member_id = $('#member_ids').val();//세션아이디
	var member_num =$('#member_nums').val();//로그인넘버
	var sad;
	
	/*$.each(memberList, function(index, item){
		////190410 수정해야할 부분
		if (item.chatRoom_num != sad) {
			//190411 채팅룸네임 수정하기 지금은 전부 gg로 들어감
			content += '<a href="multiChatRoom?chatRoom_name=gg">'+item.chatRoom_num+'번방</a></br>'
			sad=item.chatRoom_num;
		}	
			
	});*/
	var name_membernum = {
			"chatRoom_name" : chatRoom_name,
			"member_num" :  member_num
	};
$.ajax({
		
		method : 'post',
		url : 'roomname',
		data : name_membernum,
		success : function(chatroomname){
			messapgespace();//출력문구로 이동
		}
		
	})
	
}




//채팅리스트 출력 예시 2
function ChatRoomList2(memberList){
	var content;
	var member_id = $('#member_ids').val();//세션아이디
	var member_num =$('#member_nums').val();//로그인넘버
	var sad;
	
	$.each(memberList, function(index, item){
		if (item.chatRoom_name != null) {
			
			content += '<a href="multiChatRoom?chatRoom_num='+item.chatRoom_num+'&chatRoom_name='+item.chatRoom_name+'">'+item.chatRoom_name+'방</a></br>'
			
		}	
	
	});
	$('#messageChatRoomList').html(content);
	
	
}



//화이트보드 띄우기
function openWhiteBoard(){
	
	var postitNumFromProjectNum=$('#postitNumFromProjectNum').val();
	
	var tag='';
/*	tag+='<div class="mainSpace">';
	tag+='<div class="mainSpace_top">';
	tag+='<div class="modal-header rounded postitWindow">';
	tag+='<button class="btn btn-dark" id="addPostit" style="width: 80px">';
	tag+='<span class="fa-stack fa-lg"> ';
	tag+='<i class="far fa-sticky-note fa-stack-2x"></i> ';
	tag+='<i class="fas fa-plus fa-stack-1x"></i>';
	tag+='</span>';
	tag+='</button>';
	tag+='<button type="button" class="btn btn-danger" id="modalCloseBtn"';
	tag+='data-dismiss="modal">Close</button>';
	tag+='</div>';
	tag+='<div id="headers"></div>';
	tag+='</div>';
	tag+='<div class="mainSpace_bottom"></div>';
	tag+='</div>';
	*/
	tag+='<div id="whiteBoardLoad"></div>';
	
	$('#headers').html(tag);
	
	$('#whiteBoardLoad').load('whiteBoard?postitNumFromProjectNum='+postitNumFromProjectNum);
}

//화이트보드 종료
function closeWhiteBoard(){
	/*$('#whiteBoardModal').empty();*/
	$('#headers').empty();
}

function detail(){
	location.href ="detailPage";
	
}
function init(){
	location.href ="project_go";
}

function kickMember(){
	
	var member_num =$(this).parent().attr('data-pno');
	alert("member_num"+member_num)
	$.ajax({
		
		method : 'get',
		url : 'kickMember',
		data : "member_num="+member_num,
		success : function(mainproject){
			if (mainproject=="success") {
				alert("강퇴하였습니다..")
				init();
			}else{
				alert("강퇴할 수 없습니다")
				 console.log("강퇴실패 실패");
			}
		}
		
	})
	
	
}

function nolist(){
	
	$.ajax({
		 method : 'get'
		 ,url : 'gonoList'
		/* ,data: "notice_content="+notice_content*/
		 ,success: function(result){
			 
		
			 var output ='';
			 $.each(result,function(index, item){
				 
				 output += item.notice_content+'<br>';
				
			 })
			$('#noticeSpace').html(output)
			 
		 }
	})
	
}

//공지사항 
function createNotice(){
	var dataToggle=$(this).attr('data-toggle');
	if(dataToggle==1){
		var tag='<input type="text" id="noticeContent" name="notice_content">';
		tag+='<button class="btn btn-dark" id="createNoticeBtn">확인</button>'
		$('#noticeBtnSpace').html(tag);
		$(this).attr('data-toggle',2);
		$('#createNoticeBtn').on('click',function(){
			/*$('#noticeContent').submit();*/
		var notice_content = $('#noticeContent').val();
			$.ajax({
				 method : 'get'
				 ,url : 'registNotice'
				 ,data: "notice_content="+notice_content
				 ,success: function(result){
					 if(result=="success"){
						 $('#noticeContent').val('');
						 nolist();
					 }else{
						 console.log("공지사항 추가 실패");
					 }
					
				 }
			})
		})
	}else{
		$('#noticeBtnSpace').html("");
		$(this).attr('data-toggle',1);
	}
}
