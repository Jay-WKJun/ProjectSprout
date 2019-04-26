$(function(){
	$('#loader').hide();
	
	$('#joinBtn').on('click',validate);
	
	$('#home').on('click',function(){
		$('#loader').show();
		location.href="/sprout";
	})
	
	$('#member_id').keyup(function(){
		var member_id =$('#member_id').val();
		$.ajax({
		 method : 'post'
		 ,url : 'checkId'
		 ,data: "member_id="+member_id
		,success: function(result){
			if (result == 1 ) {
				$('#idCheckMessage').html('使えないアイディです。');
				$('#idCheckMessage').attr('style','color:#f23a3a');
			}
			else{
				$('#idCheckMessage').html('使えるアイディです。');
				$('#idCheckMessage').attr('style','color:#304dd1');
			}
		}
			
		})
				
	});
	
	//파일 선택시
	$("#upload").change(function(e){
		var fileName=$('#upload')[0].files[0].name;
		$('#fileName').val(fileName);
		 //  $('input[type=file]')[0].files[0].name;
		 //  $("#imgUpload")[0].files[0].type;
		 //  $("#imgUpload")[0].files[0].size;
	 });
	
})


function validate() {
	var member_id =$('#member_id').val();
	var member_password = $('#member_password').val();
	var member_password_re=$('#member_password_re').val();
	
 	if(member_password.trim().length < 3 || member_password.trim().length > 10) {
 		alert("비밀번호는 3~10자리로 입력하세요.");
 		member_password.select();
 		return false;
 	}
 	
 	if(member_password!=member_password_re){
 		alert("비밀번호와 비밀번호 확인이 같아야합니다.");
 		member_password.select();
 	}
	
	var member_name= document.getElementById("member_name");
	
	if(member_name.value.trim().length == 0 ) {
		alert("이름을 입력하세요");
		member_name.focus();
		return false;
	}
	
	var phone=$('#member_phone').val();
	
	if(isNaN(phone)){
		alert("휴대전화 번호는 숫자만 입력해주세요.");
		return false;
	}
	
	$('#joinForm').submit();
	$('#loader').show();
	
}









