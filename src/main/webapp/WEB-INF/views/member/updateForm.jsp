<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정</title>
<script>
function validate() {	
	 var member_num = document.getElementById("member_num");
	
	 var member_id  = document.getElementById("member_id");
	 
	 if(member_id.value.trim().length == 0 ) {
			alert("아이디을 입력하세요");
			member_id.focus();
			return false;
		}
	 
	 if(member_id.value.trim().length < 3 || member_id.value.trim().length >= 12) {
	 		alert("아이디는 3~12자리로");
	 		member_id.select();
	 		return false;
	 	}
	 
	var member_password = document.getElementById("member_password");
	
 	if(member_password.value.trim().length < 3 || member_password.value.trim().length >= 10) {
 		alert("비밀번호는 3~10자리로");
 		member_password.select();
 		return false;
 	}
	
	var member_name= document.getElementById("member_name");
	
	if(member_name.value.trim().length == 0 ) {
		alert("이름을 입력하세요");
		member_name.focus();
		return false;
	}
	
	var member_phone= document.getElementById("member_phone"); 

	if(isNaN(member_phone.value) || member_phone.value.trim().length != 8 ) {
		alert("전화번호는 8자리 숫자만 입력하세요");
		member_phone.select(); 
		return false;
	}

	var member_address= document.getElementById("member_address");

	if(member_address.value.trim().length == 0) {
		alert("주소를 입력하세요");
		member_address.focus();
		return false;
	}

	updateForm.submit();
}
</script>
</head>
<body>
<h2>[ 회원 정보 수정 ]</h2>
<form id="updateForm" name="updaateForm" action="update" method="POST">
		<input type="hidden" id="member_num" name="member_num" value="${num}">
		<input type="text" id="member_id" name="member_id" value="${id}">
		<input type="password" id="member_password" name="member_password" value="${password}"> <br>
		<input type="text" id="member_name" name="member_name" value="${name}"><br>
		<input type="text" id="member_phone" name="member_phone" value="${phone}"><br>
		<input type="text" id="member_address" name="member_address" value="${address}">
		<input type="text" id="member_memo" name="member_memo" value="${memo}">
		<input type="button" id="loginButton" name="loginButton" value="수정 완료" onclick="validate()">
</form>
</body>
</html>