<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>wantedBoard_directly.jsp</title>

</head>
<body>
	
	<form action="write_wanted">
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
				<textarea class="form-control" name="wantedBoard_content" style="height:500px">
					내용 입력
				</textarea>
			</div>
		</div>
		<div style="text-align-last:right;">
			<input type="submit" class="btn btn-dark" value="글등록">
			<input type="button" class="btn btn-danger" value="닫기" id="closeWriteInternalBtn">
		</div>
	</div>
	
	
	<%-- <table>
	<tr>
		<th>작성자</th>
		<th>제목</th>
		<th>내용</th>
	</tr>
	<tr>
		<td>
			${loginId}
		</td>
		<td>
			<input type="text" name="wantedBoard_title" id="" placeholder="제목"/> <br> <!-- title -->
		</td>
		<td>
			<input type="text" name="wantedBoard_content" id="" placeholder="내용"/> <br> <!-- content -->
		</td>
	</tr>	
	
	</table>
		<input type="submit" value="글등록"> --%>
	</form>
	
	
</body>
</html>