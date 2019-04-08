$(function(){
	$('#joinBtn').on('click',validate);
	
	$('#member_id').keyup(function(){
		var member_id =$('#member_id').val();
		$.ajax({
		 method : 'post'
		 ,url : 'checkId'
		 ,data: "member_id="+member_id
		,success: function(result){
			if (result == 1 ) {
				$('#idCheckMessage').html('이미 존재하는 아이디입니다.');
				$('#idCheckMessage').attr('style','color:#f23a3a');
			}
			else{
				$('#idCheckMessage').html('사용가능한 아이디 입니다');
				$('#idCheckMessage').attr('style','color:#304dd1');
			}
		}
			
		})
				
	});
	
})


function validate() {	
	 var member_id  = document.getElementById("member_id");
	 
	 if(member_id.value.trim().length == 0 ) {
			alert("아이디를 입력하세요");
			member_id.focus();
			return false;
		}
	 
	 if(member_id.value.trim().length < 3 || member_id.value.trim().length >= 12) {
	 		alert("아이디는 3~12자리로 입력하세요.");
	 		member_id.select();
	 		return false;
	 	}
	 
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
}

function check(){
	alert('hello');
}







