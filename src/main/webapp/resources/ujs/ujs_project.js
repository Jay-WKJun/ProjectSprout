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

	$('#modalBtn').on('click',function(){
		$('#whiteBoardModal').modal('show');
		var postitNumFromProjectNum=$('#postitNumFromProjectNum').val();
		$('#headers').load('whiteBoard?postitNumFromProjectNum='+postitNumFromProjectNum);
	})
	
<<<<<<< HEAD
	//화이트 보드
	$('#modalBtn').on('click',openWhiteBoard);
	$('.modalCloseBtn').on('click', closeWhiteBoard);
	//$('#whiteBoardMainSpace').on('click', closeWhiteBoard);
=======
	$('#modalCloseBtn').on('click', function() {
		$('#whiteBoardModal').modal('hide');
	})
>>>>>>> 3fc010cfd705ed1b0929dd67a6293c4c4e48a52c
	
	$('#addmem').on('click',function(){
		$('#addProjectMember').submit();
	})
	
	//공지사항 버튼
	$('#noticeBtn').on('click', createNotice);
	
	//메세지 공간 이동 버튼
	$('#messageChechBtn').on('click', function(){
		var sendmember_num = $(this).attr("data-pno");
		var my_num = $(this).attr("data-mynum");
		$('#messageSpace').attr('style','display:block');
		$('#memberSpace_display').attr('style','display:none');
		printMessageSpace(sendmember_num, my_num);
	})
	//메시지초대보내기
	$('#messagesend').on('click', function(){
		var sendmember_num = $(this).attr("data-pno");
		var my_num = $(this).attr("data-mynum");
		
		$('#messageSpace').attr('style','display:block');
		$('#memberSpace_display').attr('style','display:none');
		printMessageSpace(sendmember_num, my_num);
	})
	
	//맴버로돌아가기
	$('#memberChangeBtn').on('click', function(){
		$('#messageSpace').attr('style','display:none');
		$('#memberSpace_display').attr('style','display:block');
	})
	
	//대화상대 초대 클릭
	$('#invitation').on('click', function(){
		invitation();
	});
	
})

<<<<<<< HEAD
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
=======
//대화상대 초대 누르면 대화상대 정보 가져오기.
function invitation(){
	
	var member_name = $("#invitation1").val();
	 $.ajax({
		 url: 'invitation', 
         data: {
        	 "member_name" : member_name
        	 },
         type: 'POST',
         success: function(member){
        
        	 member_name_insert(member);
         }
 });
};
//대화상대를 가져와서 채팅방 만들기
function member_name_insert(member){
	var member_num = member.member_num;
	var loginNum = $("#loginNum").val();
	alert(loginNum);
	
	/*printMessageSpace(you_member_name, my_member_name);
	*/
	 $.ajax({
		 url: 'ChatRoomStart', 
         data: {
        	 "member_num" : member_num,
        	 "loginNum" : loginNum
        	 },
         type: 'POST',
         success: function(member){
        
        	 member_name_insert(member);
         }
	 });
};
//메시지 초대 초대보내서 방생성하기
function invitation(sendmember_num, my_num) {
	alert(sendmember_num);
	alert(my_num);
	
>>>>>>> 3fc010cfd705ed1b0929dd67a6293c4c4e48a52c
}

//메세지 공간이 보임
function printMessageSpace(you_member_name, my_member_name){
			
/*	 $.ajax({
		 url: 'multiChatRoom', 
         data: {
        	 "member_name" : member_name
        	 },
         type: 'GET',
         success: function(member){
        
         }
	 });
*/	/* ?chatRoom_num='+1+'*/
	var sas= "";
	for (var i = 1; i < 10; i++) {
		
		sas += '<a href="multiChatRoom?chatRoom_num='+i+'">'+i+'번방</a></br>';
	}
	
	$('#messageSpace').html(sas);
}



//공지사항 
function createNotice(){
	var dataToggle=$(this).attr('data-toggle');
	if(dataToggle==1){
		var tag='<input type="text" id="noticeContent">';
		tag+='<button class="btn btn-dark" id="createNoticeBtn">확인</button>'
		$('#noticeBtnSpace').html(tag);
		$(this).attr('data-toggle',2);
	}else{
		$('#noticeBtnSpace').html("");
		$(this).attr('data-toggle',1);
	}
}
<<<<<<< HEAD
=======

// 추가가능한 아이디인지 아닌지 확인하는 것
$(function(){
	
	$('#addMember').keyup(function(){
		var addMember =$('#addMember').val();
		
		
		$.ajax({
			 method : 'post'
			 ,url : 'checkForAddMember'
			 ,data: "addMember="+addMember
			,success: function(result){
				if (result.resp == 1 ) {
					$('#addmemberMessage').html('추가가능한 아이디입니다. 추가하기를 누르세요');
					$('#addmemberMessage').attr('style','color:#f23a3a');
					
				}
				else if (result.resp ==2) {
					$('#addmemberMessage').html('이미 추가한 아이디 입니다');
					$('#addmemberMessage').attr('style','color:#f23a3a');
					
				} else {

					$('#addmemberMessage').html('존재하지 않는 아이디 입니다. 다시 입력하세요');
					$('#addmemberMessage').attr('style','color:#304dd1');
				}
			}
				
			})
	})
	
})
>>>>>>> 3fc010cfd705ed1b0929dd67a6293c4c4e48a52c
