$(function(){
	//파일 리스트 출력
	//getFileList();
	
	// 파일 업로드 버튼
	$('#fileUpLoadBtn').on('click', function(){
		if($('#upLoadFile').val()==""){
			alert('파일을 선택해주세요.');
		}else{
			fileUpLoad();
		}
	})
	
	// 폴더 생성 버튼
	//$('#createFolderInputBtn').on('click',createFolderNaming);
})

// 파일 업로드
function fileUpLoad() {
	var fileUpLoadForm = $('#documentFileUpLoadForm')[0];
	var formData = new FormData(fileUpLoadForm);
	
	console.log(formData);
	
	$.ajax({
		method : 'post',
		url : 'documentFileUpLoad',
		data : formData,
		processData : false,
		contentType : false,
		success : function(result) {
			if (result == "success") {
				alert("success");
				$('#upLoadFile').val('');
				//getFileList();
			} else {
				console.log("파일 업로드 실패");
			}
		}
	})
}

/*//폴더 생성
function createFolder(){
	var projectFile_originalname = $('#folderName').val();
	var MainProject_ProjectNum=$('#MainProject_ProjectNum').val();
	var projectFile_location=$('#projectFile_location').val();
	
	var projectFile_originalname=$('#folderName').val();
	if(projectFile_originalname.indexOf('/')>-1||projectFile_originalname.indexOf('*')>-1||
			projectFile_originalname.indexOf(':')>-1||projectFile_originalname.indexOf('"')>-1||
			projectFile_originalname.indexOf('<')>-1||projectFile_originalname.indexOf('>')>-1||
			projectFile_originalname.indexOf('|')>-1){
		var msg='폴더 이름에는 다음 기호가 들어갈 수 없습니다.  /, *, :, ", <, >, | ';
		$('#closeFolderMessage').html(msg);
		var tag='<button class="btn btn-dark" id="createFolderInputBtn">폴더 생성</button>';
		$('#createFolderSpace').html(tag);
		$('#createFolderInputBtn').on('click',createFolderNaming);
		return;
	}
	
	var projectFile={
			"projectFile_originalname":projectFile_originalname,
			"MainProject_ProjectNum":MainProject_ProjectNum,
			"projectFile_location":projectFile_location
	}
	
	$.ajax({
		method : 'post',
		url : 'projectFolderCreate',
		data : projectFile,
		success : function(result) {
			if (result == "success") {
				getFileList();
				var tag='<button class="btn btn-dark" id="createFolderInputBtn">폴더 생성</button>';
				$('#createFolderSpace').html(tag);
				$('#createFolderInputBtn').on('click',createFolderNaming);
			} else {
				console.log("폴더 생성 실패");
			}
		}
	})
}*/
