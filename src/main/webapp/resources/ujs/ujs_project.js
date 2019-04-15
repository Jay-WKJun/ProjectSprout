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
	
	//공지사항 모달 열기
	$('#noticeModalBtn').on('click', function(){
		$('#noticeModal').show();
		createNotice();
	});
	
	//공지사항 모달 닫기
	$('#cancelNoticeBtn').on('click', function(){
		$('#noticeModal').hide();
		$('#noticeMsg').empty();
	})
	
	//공지사항 디테일 버튼
	$('#cancelNoticeDetailBtn').on('click',function(){
		$('#noticeDetailModal').hide();
	})
	
	//공지사항 생성 버튼 
	$('#noticeCheckBtn').on('click',function(){
		$('#noticeSpace').attr('style','display:block');
		$('#memberSpace_display').attr('style','display:none');
		nolist();
	})
	
	//멤버리스트로 돌아가는 버튼
	$('#memberChangeBtn').on('click',function(){
		$('#noticeSpace').attr('style','display:none');
		$('#memberSpace_display').attr('style','display:block');
	})
	
	//강퇴버튼 
	$('.kickMember').on('click',kickMember);
	
	//detail로 넘어가는 버튼
	$('#projectDetail').on('click',detail);

	//타임 테이블
	$('#timeTable').load('timetable');
	
})

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
					$('#addmemberMessage').html('추가가능한 아이디입니다. 추가하기를 누르세요');
					$('#addmemberMessage').attr('style','color:#f23a3a');
					$('#addmem').removeAttr('disabled');
					
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
}
