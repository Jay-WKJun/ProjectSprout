$(function(){
	$('#loader').hide();
	
	//홈이동
	$('#home').on('click', function(){
		$('#loader').show();
		location.href="/sprout";
	})
	
	$('#join').on('click',function(){
		$('#loader').show();
		location.href="join";
	})
	
	$('#loginBtn').on('click', login);
})

function login() {
	var id  = document.getElementById("id"); 
	var password = document.getElementById("password");

	if(id.value.trim().length == 0) {
		alert("아이디를 입력");
		id.focus();
		return;
	}
	if(password.value.trim().length == 0) {
		alert("비밀번호를 입력");
		password.select();
		return;
	}
	
	var form = document.getElementById("login");
	form.submit();
}