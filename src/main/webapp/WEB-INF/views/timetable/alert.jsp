<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Alert Page</title>
<script src="js/jquery-3.3.1.min.js"></script>
<script>

$(function() {
	$('#loader').hide();
})

function addDefault(){
	location.href = '/sprout/timetableMakeFirst';
}

</script>
</head>
<body>

<img src="img/alertImage.png" width="100%" height="auto" align="middle"/>
<button class="btn btn-dark" id="regist" value="" onclick="addDefault()">
	<i class="far fa-calendar-check fa-lg"></i>
</button>

</body>
</html>