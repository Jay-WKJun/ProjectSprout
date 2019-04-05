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
	
	$('#modalCloseBtn').on('click', function() {
		$('#whiteBoardModal').modal('hide');
	})
	$('#addmem').on('click',function(){
		$('#addProjectMember').submit();
	})
	
	//공지사항 추가 버튼
	$('#noticeBtn').on('click', createNotice);
	//공지사항 버튼 
	$('#noticeCheckBtn').on('click',nolist);
	//강퇴버튼 
	$('.kickMember').on('click',kickMember)
	
	//나중에 삭제
	$('#timeTable').on('click', function(){
		location.href = "timetable";
	});
	
})



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






