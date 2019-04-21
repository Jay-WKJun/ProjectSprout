<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>apply_wanted.jsp</title>
<script>
	$(function(){
		//내부 공고글 작성 모달 닫기
		$('#closeWriteInternalBtn2').on('click',function(){
			$('#boardDetailModal').hide();
			$('#writeBoardLoad').empty();
		})
	}) 
</script>
</head>
<body>
	<form action="apply_this_job" method="POST" enctype="multipart/form-data" > <!-- WantedBoardController -->
	
	<div class="container" style="text-align:left">
		<div class="row">
			<div class="col">
				${loginId}
				<input type="hidden" id="wantedboard_from" name="wantedBoard_from" value="${loginId}">
			</div>
		</div>
		<hr class="sbd1">
		<div class="row" style="height:650px;margin-bottom:10px">
			<div class="col">
				<textarea class="form-control h-100" name="wantedBoard_content"></textarea>
			</div>
		</div>
		<div class="row">
			<div class="col">
				<input class="form-control" type="file" id="upload" name="upload" value="파일첨부" />
			</div>
		</div>
		<div class="row">
			<div class="col" style="padding:10px; text-align-last:right">
				<input class="btn btn-dark" type="submit" value="완료" style="margin-right:5px">
				<input class="btn btn-danger" type="button" id="closeWriteInternalBtn2" value="닫기">
			</div>
		</div>
	</div>
	
		<%-- <table border="1">
			<tr>
				<th>지원자</th>
				<td> ${loginId} 
					<input type="hidden" id="wantedboard_from" name="wantedBoard_from" value="${loginId}">
				</td>
			</tr>
			<tr>
				<th>content</th>
				<td> <input type="text" name="wantedBoard_content"/> </td>
			</tr>
			<tr>
				<th>file</th>
				<td> <input type="file" id="upload" name="upload" value="파일첨부" /> </td>
			</tr>
		</table>
		<p>file은 압축해서 올리쇼</p>
		
		<input type="submit" value="upload"> --%>
	</form>
	
</body>
</html>