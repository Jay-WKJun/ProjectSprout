
// 프로필 사진 삭제하기.
$(function(){
	
	$('#update_with_old_info').on('click', function(){
		validate();
	})
	
	//회원 탈퇴
	$('#delete_infoBtn').on('click',function(){
		location.href="delete_info";
	})
	
	//홈으로
	$('#cancleBtn').on('click',function(){
		location.href="/sprout";
	})
	
	$('#profileDelete').on('click',function(){
		alert('프로필 사진을 삭제합니다 ');
		$.ajax({
			url : 'del_pro', // 프로필 사진을 삭제하기
			type : 'get',
			success : function(result){
				$("#picture").attr("src", "img/empty_profile.png");
				$("#picture").attr("class", "rounded-circle border profileImg");
			}
		});
	})
})

// 프로필 정보가 빈칸?으로 넘어가면   update_with_old_info

function validate() {	
	 
	var member_password = $('#member_password').val();
	
 	if(member_password.trim().length < 3 || member_password.trim().length > 10) {
 		alert("비밀번호는 3~10자리로 입력하세요.");
 		member_password.select();
 		return false;
 	}
	
	var member_name= $("#member_name").val();
	
	if(member_name.length == 0 ) {
		alert("이름을 입력하세요");
		member_name.focus();
		return false;
	}
	
	var phone=$('#member_phone').val();
	
	if(isNaN(phone)){
		alert("휴대전화 번호는 숫자만 입력해주세요.");
		return false;
	}
	
	if(phone.length == 0 ) {
		alert("전화번호를 입력하세요");
		member_phone.focus();
		return false;
	}
	
	var member_address = $("#member_address").val();
	
	if(member_address.length == 0 ) {
		alert("주소를 입력하세요");
		member_address.focus();
		return false;
	}
	$('#updateForm').submit();
}

