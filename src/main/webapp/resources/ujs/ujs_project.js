var test;
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
	
	//개인정보 수정 페이지
	$('#memberInfoBtn').on('click',function(){
		$('#loader').show();
		location.href="memberInfo";
	})
	
	//파일 매니저
	var postitNumFromProjectNum=$('#postitNumFromProjectNum').val();
	$('#fileManager').load('fileManager?postitNumFromProjectNum='+postitNumFromProjectNum);
	
	//화이트 보드
	$('#whiteBoardModalBtn').on('click',openWhiteBoard);
	//$('#modalCloseBtn').on('click', closeWhiteBoard);
	
	//멤버 모달 버튼
	$('#memberPlusModalBtn').on('click',function(){
		$('#memberPlusSpace').show();
	})
	
	//멤버 모달 닫기
	$('#cancelmemberPlusBtn').on('click',function(){
		$('#memberPlusSpace').hide();
		$('#addMember').val('');
		$('#addmemberMessage').empty();
	})
	
	//아이디 추가 전에 확인
	$('#addMember').keyup(addMemberCheck);
	
	//아이디 추가하기
	$('#addmem').on('click',function(){
		$('#addProjectMember').submit();
		$('#addmem').attr('disabled','disabled');
	})
	
	/////////////////
	/////////////////
	/////////////////
	//클릭으로 메시지 초대보내기
	$('.messagesend').on('click', function() {
	/*var member_name = $('#member_namesss').val();*/
	var member_name=$(this).attr('data-name');
	ClickChatRoomList(member_name);
	})
			
	//클릭으로 채팅생성시 text에 채팅이름저장
	function ClickChatRoomList(member_name){
		var content;
		var member_id = $('#member_ids').val();//세션아이디
		var member_num =$('#member_nums').val();//로그인넘버
		var chatRoom_name = $('#member_ids').val(); // 임시로 아이디로 만들어지도록 함. 추후에 초대장 아이디랑 초대받은 사람 아이디로 업로드 예정(이라쓰고 안하기)
		
		var test3 = chatRoom_name.concat(', '+member_name);
		
		var sad;
		var member_name = member_name;
	
		var name_membernum = {
			/*	"chatRoom_name" : chatRoom_name,*/
				"chatRoom_name" : test3,
				"member_num" :  member_num
		};
	$.ajax({
			
			method : 'post',
			url : 'roomname',
			data : name_membernum,
			success : function(chatroomname){
				
				ClickSendInvitation(member_name);//상대편 아이디 추가하로가기.
			}
			
		})
		
	}

	//상대편 아이디 넘버가져오기 
	function ClickSendInvitation(member_name){
		var asdfgs = {
				"member_name" : member_name
			};
			
//		alert(member_name);
//			$.ajax({
//				type : 'post',
//				url : 'ClickselectMemberNum',
//				data : asdfgs ,
//				
//				success : function(member_nameOne) {
//					
//					 invitationss(member_nameOne); 
//				}
//			});
		
		alert(member_name);
		
		$.ajax({
			type : 'post',
			url : 'ClickselectMemberNums',
			data : asdfgs ,
			
			success : function(member_nameOne) {
				invitationss(member_nameOne);
			}
		});
	}
	
	function invitationss(member_nameOne){
		var member_num = member_nameOne["member_num"];
		var chatRoom_num = $('#chatRoom_nums').val();
		var member_name = member_nameOne["member_name"];
		var chatRoom_name = $('#member_ids').val(); // 일단 아이디로 대신하자.. ..
		var test3 = chatRoom_name.concat(', '+member_name);
		
		alert("왜:"+member_name);
		var qp = {
				"member_num" : member_num,
				"chatRoom_num" : chatRoom_num,
				"member_name" : member_name,
			/*	"chatRoom_name" : chatRoom_name*/
				"chatRoom_name" : test3
		};

		$.ajax({
			type : 'post',
			url : 'invitationss',
			data : qp ,
			
			success : function(success) {
				messapgespace();
				}
		});
		
	}
	
	
	
	
	//////////////////
	//공지사항 모달 열기 
	$('#noticeModalBtn').on('click', function(){
		$('#noticeModal').show();
		createNotice();
	});
	//채팅 생성
	$('#Invitatio').on('click',function(){
		var chatRoom_name =$('#addMembers').val();
		$('#ChatRoomInvitation').hide();
		/*chatRoomCreat(addMembers);*/
		ChatRoomList(chatRoom_name);
	})
	
	//공지사항 추가 버튼
	$('#noticeBtn').on('click', createNotice);
	
	//공지사항 디테일 버튼
	$('#cancelNoticeDetailBtn').on('click',function(){
		$('#noticeDetailModal').hide();
	})
	
	//공지사항 보기 버튼 
	$('#noticeCheckBtn').on('click',function(){
		$('#noticeSpace').attr('style','display:block');
		$('#memberSpace_display').attr('style','display:none');
		$('#messageSpace').attr('style','display:none');
		nolist();
	})
	
	//공지사항 생성창 닫기 버튼
	$('#cancelNoticeBtn').on('click',function(){
		$('#noticeModal').hide();
	})
	
	//채팅방 생성 모달 버튼
	$('#addChatMemberBtn').on('click', function(){
		$('#ChatRoomInvitation').show();
	})
	
	//채팅창 생성 모달 닫기 버튼
	$('#chatRoomModalCloseBtn').on('click', function(){
		$('#ChatRoomInvitation').hide();
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

	//타임 테이블
	$('#timeTable').load('timetable');
	
	
	

})//$function()

//채팅방 생성
function chatRoomCreat(chatRoom_name){
	var content;
	var chatRoom_name =chatRoom_name;
	var member_id = $('#member_ids').val();//세션아이디
	
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
			$('').hide()
		}
		
	})
	
}




//채팅리스트 출력 부분
function ChatRoomList2(memberList){
	var content='';
	var member_id = $('#member_ids').val();//세션아이디
	var member_num =$('#member_nums').val();//로그인넘버
	var sad;
	
	content +='<div class="list-group">';
	$.each(memberList, function(index, item){
		
		
		if (item.chatRoom_name != null) {
			content += '<a class="list-group-item list-group-item-action noticeDetail"';
			content += 'style="margin-bottom: 2px; border: 1px solid #6079a0;"';
			content += 'href="multiChatRoom?chatRoom_num='+item.chatRoom_num;
			content += '&chatRoom_name='+item.chatRoom_name+'" onclick="window.open(this.href, \'_blank\', \'top=200,left=500,width=400,height=580,toolbars=no,scrollbars=no\'); return false;">';
			content += item.chatRoom_name+'방</a>';
			
		}	
	});
	content +='</div>';
	
		$('#messageChatRoomList').html(content);
}




//화이트보드 띄우기
function openWhiteBoard(){
	$('#whiteBoardModal').attr('style','display:block');
	var postitNumFromProjectNum=$('#postitNumFromProjectNum').val();
	
	var tag='';

	tag+='<div id="whiteBoardLoad"></div>';
	
	$('#headers').html(tag);
	
	$('#whiteBoardLoad').load('whiteBoard?postitNumFromProjectNum='+postitNumFromProjectNum);
}

//화이트보드 종료
function closeWhiteBoard(){
	/*$('#whiteBoardModal').empty();*/
	$('#whiteBoardModal').attr('style','display:none');
	$('#headers').empty();
}

function detail(){
	$('#loader').show();
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
			 output +='<div class="list-group">';
			 $.each(result,function(index, item){
				output +='<div class="list-group-item list-group-item-action noticeDetail"';
				output +='style="margin-bottom: 2px; border: 1px solid #6079a0;"';
				output +='data-nCon="'+item.notice_content+'">';
				output +='<div>'+item.notice_Date+'</div>';
				output +='</div>';
			 })
			 output +='</div>';
			$('#noticeSpace').html(output);
			$('.noticeDetail').on('click',noticeDetail);
		 }
	})
	
}

//공지사항 읽기
function noticeDetail(){
	$('#noticeDetailModal').show();
	var noticeContent=$(this).attr('data-nCon');
	$('#noticeDetail').val(noticeContent);
}

//공지사항 생성
function createNotice(){
		$('#createNoticeBtn').on('click',function(){
			/*$('#noticeContent').submit();*/
			var notice_content = $('#noticeContent').val();
			if(notice_content==''){
				$('#noticeMsg').html('공지사항을 입력해주세요.');
			}else{
				$.ajax({
					method : 'get'
						,url : 'registNotice'
							,data: "notice_content="+notice_content
							,success: function(result){
								if(result=="success"){
									$('#noticeContent').val('');
									$('#noticeModal').hide();
									$('#noticeMsg').empty();
									nolist();
								}else{
									console.log("공지사항 추가 실패");
								}
								
							}
				})
			}
		})
}

//추가가능한지 아닌지
function addMemberCheck(){
	$('#addMember').keyup(function(){
		var addMember =$('#addMember').val();
		
		$.ajax({
			 method : 'post'
			 ,url : 'checkForAddMember'
			 ,data: "addMember="+addMember
			,success: function(result){
				if (result.resp == 1 ) {
					$('#addmemberMessageSpace').html('추가가능한 아이디입니다. 추가하기를 누르세요');
					$('#addmemberMessageSpace').attr('style','color:#f23a3a');
					$('#addmem').removeAttr('disabled');
					
				}
				else if (result.resp ==2) {
					$('#addmemberMessageSpace').html('이미 추가한 아이디 입니다');
					$('#addmemberMessageSpace').attr('style','color:#f23a3a');
					
				} else {

					$('#addmemberMessageSpace').html('존재하지 않는 아이디 입니다. 다시 입력하세요');
					$('#addmemberMessageSpace').attr('style','color:#304dd1');
				}
			}
		})
	})
}

function home(){
	location.href="/sprout";
}