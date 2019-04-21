<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>wantedBoard_directly.jsp</title>
<script>
	$(function(){
		$('#writeBoardBtn').on('click',function(){
			$('#write_wanted').submit();
		})
	})
</script>
</head>
<body>
	<form action="write_wanted" id="write_wanted">
	<div class="container" style="text-align:left">
		<div class="row">
			<div class="col">
			<span style="margin-right:20px">작성자</span>
			<span>${loginId}</span>
			<hr>
			</div>
		</div>
		<div class="row">
			<div class="col">제목</div>
		</div>
		<div class="row" style="margin-bottom:30px;">
			<div class="col">
				<input type="text" class="form-control" name="wantedBoard_title" placeholder="제목을 입력하세요."/>
			</div>
		</div>
		<div class="row">
			<div class="col">내용</div>
		</div>
		<div class="row">
			<div class="col">
				<textarea class="form-control" name="wantedBoard_content" style="height:500px"></textarea>
			</div>
		</div>
	</div>
	
	</form>
	
	
</body>
</html>