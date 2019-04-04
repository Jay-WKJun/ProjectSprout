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
	
	//공지사항 버튼
	$('#noticeBtn').on('click', createNotice);
	
})

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