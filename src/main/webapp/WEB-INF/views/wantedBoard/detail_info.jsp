<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>detail_info.jsp</title>
<script>
	$(function(){
		//공고글 삭제
		$('#applyBoardDeleteBtn').on('click',deleteWantedBoard);
		
		//내부 공고글 자세히 보기 닫기
		$('#closeDetailInternalBtn').on('click',function(){
			$('#boardDetailModal').hide();
			$('#boardDetailLoad').empty();
		})
	})
	
	function deleteWantedBoard(){
		var wantedBoardNum=$('#wantedBoardNum').val();
		location.href="delete_wanted?wantedBoard_num="+wantedBoardNum;
	}
</script>
</head>
<body>
	<div class="container" style="text-align:left">
		<div class="row">
			<div class="col fontSize20">
				<span>${one_wanted_from_DB.wantedBoard_title}</span>
			</div>
		</div>
			<hr class="sbd1">
		<div class="row">
			<div class="col">
				<span>${one_wanted_from_DB.wantedBoard_from}</span>
			</div>
			<div class="col" style="text-align:right">
				<span>${one_wanted_from_DB.wantedBoard_date }</span>
			</div>
		</div>
		<div class="row rounded sbd1">
			<div class="col" style="height:700px">
				<span>${one_wanted_from_DB.wantedBoard_content}</span>
			</div>
		</div>
	</div>
	<div style="padding:10px">
	<c:if test="${not empty sessionScope.same}">
		<button class="btn btn-dark" id="applyBoardBtn">지원하기</button>
	</c:if>
				
	<c:if test="${empty sessionScope.same}">
		<input type="hidden" value="${one_wanted_from_DB.wantedBoard_num}" id="wantedBoardNum">
		<button class="btn btn-dark" id="applyBoardDeleteBtn">삭제하기</button>
	</c:if>
		<input type="button" class="btn btn-danger" value="닫기" id="closeDetailInternalBtn">
	</div>
	
</body>
</html>