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
	$('#whiteBoardModalBtn').on('click',openWhiteBoard);
	$('#modalCloseBtn').on('click', closeWhiteBoard);
	
	//아이디 추가하기
	$('#addmem').on('click',function(){
		$('#addProjectMember').submit();
		$('#addmem').attr('disabled','disabled');
	})
	
	//공지사항 추가 버튼
	$('#noticeBtn').on('click', createNotice);
	
	//공지사항 버튼 
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

	//나중에 삭제
	/*$('#timeTable').on('click', function(){
		location.href = "timetable";
	});*/
	
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
		tag+='<button class="btn btn-dark" id="createNoticeBtn">확인</button>';
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
