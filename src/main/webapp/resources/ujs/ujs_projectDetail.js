$(function(){
	//파일 리스트 출력
	//getFileList();
	folderForm();
	
	// 파일 업로드 버튼
	$('#fileUpLoadBtn').on('click', function(){
		if($('#upLoadFile').val()==""){
			alert('파일을 선택해주세요.');
		}else{
			fileUpLoad();
		}
	})
	
	// 폴더 생성 버튼
	$('#createFolderInputBtn').on('click',changeFolderForm);
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
				$('#document_board_title').val('');
				$('#document_board_content').val('');
				$('#file_name').val('');
				//getFileList();
				folderForm();
			} else {
				alert(result);
				console.log("파일 업로드 실패");
			}
		}
	})
}

//폴더 폼 생성
function changeFolderForm(){
	var temp = '<input type="text" name="folder_name" id="folder_name" placehoder="file-name">';
	$('#folderDiv').html(temp);
}

//폴더 되돌리기 (list출력필요)
function folderForm(){
	var temp = '<select name="folder_name">';
	temp += '<option value="testFolder">testFolder</option>'
	temp += '</select>'
	$('#folderDiv').html(temp);
}
		
//파일 리스트 받아옴
function getFileList() {

	$.ajax({
		method : 'GET',
		url : 'getFileList',
		success : printProjectFile
	})
}

//파일 츌룍이지만 참고용이니 빨리 지우자
function printProjectFile(fileList) {
	var tag = "<thead>";
	tag += "<tr>";
	tag += "<th></th>";
	tag += "<th>파일명</th>";
	tag += "<th>로그</th>";
	tag += "<th>업로드 날짜</th>";
	tag += "<th></th>";
	tag += "</tr>";
	tag += "</thead>";
	tag += "<tbody>";
	if($('#folderLocation').html()!="home"){
		tag += "<tr class='topFolder'>";
		tag += "<td>";
		tag += "<i class='fas fa-folder-open fa-2x'></i>";
		tag += "</td>";
		tag += "<td>";
		tag += "..";
		tag += "</td>";
		tag += "<td colspan='3'>";
		tag += "</td>";
		tag += "</tr>";
	}
	$.each(fileList, function(index, item) {
		tag += "<tr class='";
		if(item.projectFile_type==1){
			tag += "folder'";
		}else{
			tag += "file'"; 
		}
		tag +=" data-fName='"+item.projectFile_originalname+"' data-fno='" + item.projectFile_num+"'";
		tag+= "data-fEx='"+item.projectFile_extension+"'>";
		tag += "<td>";
		if(item.projectFile_type==1){
			tag += "<i class='fas fa-folder-open fa-2x'></i>";
		}else{
			tag += "<i class='fas fa-file-alt fa-2x'></i>"; 
		}
		tag += "</td>";
		tag += "<td>";
		tag += item.projectFile_originalname;
		tag += "</td>";
		tag += "<td>";
		tag += item.projectFile_member;
		tag += "</td>";
		tag += "<td>";
		tag += item.projectFile_date;
		tag += "</td>";
		tag += "<td>";
			
		tag += '<div class="dropdown dropright float-right">';
		tag += '<span class="btn btn-light" data-toggle="dropdown">';
		tag += '<label>';
		tag += '<i class="fas fa-ellipsis-v fa-lg"></i>';
		tag += '</label>';
		tag += '</span>';
		tag += '<div class="dropdown-menu">';
		tag += '<a class="dropdown-item fileDownload">다운로드</a>';
		tag += '<a class="dropdown-item fileDelete" >삭제</a>';
		tag += '</div>';
		tag += '</div>';
			
		tag += "</td>";
		tag += "</tr>";
	});
	tag += "</tbody>";
	$("#fileManagerTable").html(tag);
	$(".folder").on('dblclick', folderDoubleClick);
	$(".topFolder").on('dblclick', topFolderDoubleClick);
	$(".fileDownload").on('click',fileDownload);
	$(".fileDelete").on('click',fileDelete);
	$('.file').on('mouseover',fileImageUp);
	$('.file').on('mouseout', fileImageDown);
}


/*function getFileList(){
	var tag = ''
	var finalResult = ${finalResult};
	$.each(finalResult, function(index, item){
		//map을 하나식 꺼낸다.
		item.folderName
		$.each(item.boardList, function(index, item){
			//여기부턴 DocumentBoard가 들어있다
		});
	});
	
}*/



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
