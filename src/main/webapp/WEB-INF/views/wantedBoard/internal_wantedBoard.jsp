<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="ujs/ujs_index.js"></script>
</head>
<body>
	<div style="padding: 10px;">
		<table class="table table-hover table-light">
			<tr>
				<th>글 번호</th>
				<th class="title">글제목</th>
				<th>프로젝트 제안자</th>
				<th>지원 기간</th>
			</tr>

			<!-- 게시글 출력 반복 -->
			<c:forEach var="board" items="${list_from_DB}" varStatus="stat">
				<tr>
					<td>${stat.count + navi.startRecord}</td>
					<td><a
						href="detail_info?wantedBoard_num=${board.wantedBoard_num}">${board.wantedBoard_title}</a>
					</td>
					<!-- ========================== 여기 하는 중 -->
					<td>${board.wantedBoard_from}</td>
					<td>${board.wantedBoard_date }</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div style="padding: 20px">
		<div class="contentSpace">
			<div class="contentSpace_left"></div>
			<div class="contentSpace_center">
				<div style="margin-top:20px">
				<a href="#" style="margin:5px"><i class="fas fa-angle-double-left fa-lg"></i></a>
				<a href="#" style="margin:5px 15px 5px 5px"><i class="fas fa-angle-left fa-lg"></i></a>
				<a href="#" style="color:red;margin:5px">1</a>
				<a href="#" style="color:#6079a0;margin:5px">2</a>
				<a href="#" style="color:#6079a0;margin:5px">3</a>
				<a href="#" style="color:#6079a0;margin:5px">4</a>
				<a href="#" style="color:#6079a0;margin:5px 15px 5px 5px">5</a>
				<a href="#" style="margin:5px"><i class="fas fa-angle-right fa-lg"></i></a>
				<a href="#" style="margin:5px"><i class="fas fa-angle-double-right fa-lg"></i></a>
				</div>
			</div>
			<div class="contentSpace_right">
				<div class="contentSpace_left"></div>
				<div class="contentSpace_center">
					<button class="btn btn-dark" id="writeInternalWantedBoard">글작성</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>