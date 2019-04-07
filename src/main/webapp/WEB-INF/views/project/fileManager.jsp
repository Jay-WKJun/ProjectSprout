<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>fileManager</title>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
	integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr"
	crossorigin="anonymous">
<link rel="stylesheet" href="ucss/ucss_fileManager.css">
<link rel="stylesheet" href="css/bootstrap.css">
<script src="js/jquery-3.3.1.min.js"></script>
<script src="js/jquery-ui.min.js"></script>
<script src="ujs/ujs_fileManager.js"></script>
</head>
<body>
	<div class="container">
		<input type="hidden" id="projectNum" value="${projectNum }">
		
		<div class="row">
			<span id="folderLocation">home</span>
		</div>
		<div class="row">
		<form action="projectFileUpLoad" id="fileUpLoadForm" method="post"
			enctype="multipart/form-data">
			<input type="file" value="파일 찾기" id="upLoadFile" name="upLoadFile">
			<input type="hidden" value="${mainproject_projectnum }" id="MainProject_ProjectNum" name="MainProject_ProjectNum"> 
			<input type="hidden" value="${mainproject_projectnum }" id="projectFile_location" name="projectFile_location">
		</form>
		<input type="button" id="fileUpLoadBtn" value="파일 올리기">
		</div>
		<div class="row">
			<div id="createFolderSpace">
				<button class="btn btn-dark" id="createFolderInputBtn">폴더 생성</button>
				<span id="closeFolderMessage"></span>
			</div>
			</div>
			<div class="row">
		<div class="fileManagerTable">
			<table class="table table-light table-hover" id="fileManagerTable">
				<thead>
				</thead>
			</table>
		</div>
		</div>
	</div>

	<script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>