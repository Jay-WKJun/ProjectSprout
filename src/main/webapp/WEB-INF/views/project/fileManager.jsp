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
<!-- <script src="js/jquery-ui.min.js"></script>
<script src="ujs/ujs_fileManager.js"></script> -->
<script src="ujs/ujs_fileManager.js"></script>
</head>
<body>
	<div class="container">
		<input type="hidden" id="projectNum" value="${projectNum }">

		<div class="row sbd2 border-top-0 border-right-0 border-left-0">
			<div class="col-2" style="background-color: #6079a0; padding: 10px">
				드라이브 : /</div>
			<div class="col-8" style="padding: 10px">
				<span id="folderLocation">home</span>
			</div>
		</div>
		<div class="row" style="padding:20px">

			<form class="" id="fileUpLoadForm" method="post"
				enctype="multipart/form-data">
				
			<div class="form-group">
				<div class="bootstrap-filestyle input-group">
					<span class="group-span-filestyle input-group-btn" tabindex="0">
						<label for="fileInput" class="btn btn-dark ">
							<i class="fas fa-file-medical fa-lg"></i>
						</label>
					</span>
					<input id="fileInput" name="upLoadFile"
						filestyle="" type="file" data-class-button="btn btn-default"
						data-class-input="form-control" data-button-text=""
						data-icon-name="fa fa-upload" class="form-control" tabindex="-1"
						style="position: absolute; clip: rect(0px, 0px, 0px, 0px);">
						<input type="text" id="upLoadFile" class="form-control" disabled=""> 
				</div>
			</div>
				
				<input type="hidden" value="${mainproject_projectnum }"
					id="MainProject_ProjectNum" name="MainProject_ProjectNum">
				<input type="hidden" value="${mainproject_projectnum }"
					id="projectFile_location" name="projectFile_location">
			</form>
			<button class="btn btn-dark h-100" id="fileUpLoadBtn"><i class="fas fa-upload fa-lg"></i></button>
			
			<div id="createFolderSpace" style="margin-left:10px">
				<button class="btn btn-dark" id="createFolderInputBtn">
					<i class="fas fa-folder-open fa-lg"></i>
				</button>
			</div>
		</div>
		<span id="closeFolderMessage"></span>
		<div class="row">
			<div class="fileManagerTable border-bottom-0 border-left-0 border-right-0 sbd2">
				<table class="table table-light table-hover" id="fileManagerTable">
					<thead>
					</thead>
				</table>
			</div>
		</div>
	</div>
</html>