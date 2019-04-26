$(function(){
	//파일 리스트 출력
	getFileList();
	
	// 파일 업로드 버튼
	$('#fileUpLoadBtn').on('click', function(){
		if($('#upLoadFile').val()==""){
			alert('ファイルを選んでください。');
		}else{
			fileUpLoad();
		}
	})
	
	// 폴더 생성 버튼
	$('#createFolderInputBtn').on('click',createFolderNaming);
	
	//파일명
	$("#fileInput").on('change', function(){  // 값이 변경되면
		if(window.FileReader){  // modern browser
			var filename = $(this)[0].files[0].name;
		} else {  // old IE
			var filename = $(this).val().split('/').pop().split('\\').pop();  // 파일명만 추출
		}
		// 추출한 파일명 삽입
		$("#upLoadFile").val(filename);
	});
	
})

// 파일 업로드
function fileUpLoad() {
	var fileUpLoadForm = $('#fileUpLoadForm')[0];
	var formData = new FormData(fileUpLoadForm);
	
	console.log(formData);
	
	$.ajax({
		method : 'post',
		url : 'projectFileUpLoad',
		data : formData,
		processData : false,
		contentType : false,
		success : function(result) {
			if (result == "success") {
				$('#upLoadFile').val('');
				getFileList();
			} else {
				console.log("アップロード失敗。");
			}
		}
	})
}

// 파일 리스트 받아옴
function getFileList() {
	var projectFile_location = $("#projectFile_location").val();

	$.ajax({
		method : 'post',
		url : 'getFileList',
		data : "projectFile_location=" + projectFile_location,
		success : printProjectFile
	})
}

// 파일 출력
function printProjectFile(fileList) {
	var tag = "<thead>";
	tag += "<tr>";
	tag += "<th></th>";
	tag += "<th>ファイル名</th>";
	tag += "<th>アップローダー</th>";
	tag += "<th>アップロード日付</th>";
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
			if(item.projectFile_extension=='jpg'||item.projectFile_extension=='JPG'||
					item.projectFile_extension=='png'||item.projectFile_extension=='PNG'){
				tag += "<i class='fas fa-images fa-2x'></i>"; 
			}else if(item.projectFile_extension=='zip'||item.projectFile_extension=='rar'){
				tag += "<i class='fas fa-file-archive fa-2x'></i>"; 
			}else{
				tag += "<i class='fas fa-file-alt fa-2x'></i>"; 
			}
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
		tag += '<a class="dropdown-item fileDownload">ダウンロード</a>';
		tag += '<a class="dropdown-item fileDelete" >削除</a>';
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

//파일 이미지 지우기
function fileImageDown(){
	$('#fileImageBox').remove();
}

//파일 이미지 업
function fileImageUp(e){
	var fileNum=$(this).attr('data-fno');
	var extension=$(this).attr('data-fEx');
	var projectFile_location=$('#projectFile_location').val();
	if(extension=="jpg"||extension=="png"||extension=="PNG"||extension=="JPG"){
		if($('#fileImageBox').attr('data-fno')!=fileNum){
			var tag="";
			tag+="<div id='fileImageBox' data-fno='"+fileNum+"'>";
			tag+="<img id='fileImage' src='/fileImage/"+projectFile_location+"/"+fileNum+"."+extension+"'/>";
			tag+="</div>";
			$('.wrapper').append(tag);
			var left=e.pageX-$('#fileImage').width();
			var top=e.pageY-$('#fileImage').height();
			$('#fileImageBox').attr('style',"left:"+left+"px;top:"+top+"px");
		}
	}
}

//상위폴더로 이동
function topFolderDoubleClick(){
	var folderLocation=$('#folderLocation').html();
	var projectFile_location=$('#projectFile_location').val();
	var lastIndex=folderLocation.lastIndexOf('/');
	var lastIndex2=projectFile_location.lastIndexOf('/');
	if(lastIndex>-1&&lastIndex2>-1){
		var topfolderLocation=folderLocation.substring(0,lastIndex);
		var topprojectFile_location=projectFile_location.substring(0,lastIndex2);
		$('#projectFile_location').val(topprojectFile_location);
		$('#folderLocation').html(topfolderLocation);
		getFileList();
	}
}

// 폴더 들어가기
function folderDoubleClick(){
	//유저에게 보여지는 경로는 폴더이름으로 표기
	//로직을 위한 경로는 파일 넘버를 표기
	var projectFile_location=$('#projectFile_location').val();
	var folderLocation=$('#folderLocation').html();
	var fileName2=$(this).attr('data-fName');
	var fileName=$(this).attr('data-fName');
	projectFile_location+="/"+fileName2;
	folderLocation+="/"+fileName;
	$('#projectFile_location').val(projectFile_location);
	$('#folderLocation').html(folderLocation);
	console.log(projectFile_location);
	console.log(folderLocation);
	getFileList();
}

//파일 다운로드
function fileDownload(){
	var fileNum=$(this).parent().parent().parent().parent().attr('data-fno');
	var fileType=$(this).parent().parent().parent().parent().attr('class');
	
	if(fileType=="file"){
		location.href="fileDownload?projectFile_num="+fileNum;
	}
}

//파일 삭제
function fileDelete(){
	var fileNum=$(this).parent().parent().parent().parent().attr('data-fno');
	var fileType=$(this).parent().parent().parent().parent().attr('class');
	
	if(fileType=="file"){
		$.ajax({
			method : 'post',
			url : 'fileDelete',
			data : "projectFile_num=" + fileNum,
			success : function(result){
				if(result=="success"){
					getFileList();
				}else{
					console.log("ファイル削除失敗。");
				}
			}
		})
	}else{
		$.ajax({
			method : 'post',
			url : 'folderDelete',
			data : "projectFile_num=" + fileNum,
			success : function(result){
				if(result=="success"){
					getFileList();
				}else{
					console.log("ファイル削除失敗。");
				}
			}
		})
	}
}

//폴더 생성 네이밍
function createFolderNaming(){
	tag="<input type='text' id='folderName'>";
	tag+="<button class='btn btn-dark' id='createFolderBtn'>フォルダーを作る。</button>";
	$('#createFolderSpace').html(tag);
	$('#createFolderBtn').on('click',createFolder);
}

// 폴더 생성
function createFolder(){
	var projectFile_originalname = $('#folderName').val();
	var MainProject_ProjectNum=$('#MainProject_ProjectNum').val();
	var projectFile_location=$('#projectFile_location').val();
	
	var projectFile_originalname=$('#folderName').val();
	if(projectFile_originalname.indexOf('/')>-1||projectFile_originalname.indexOf('*')>-1||
			projectFile_originalname.indexOf(':')>-1||projectFile_originalname.indexOf('"')>-1||
			projectFile_originalname.indexOf('<')>-1||projectFile_originalname.indexOf('>')>-1||
			projectFile_originalname.indexOf('|')>-1){
		var msg="ファイル名には次の記号を入れられません。　’／、＊、：、”、＜、＞、｜’";
		$('#closeFolderMessage').html(msg);
		var tag='<button class="btn btn-dark" id="createFolderInputBtn"><i class="fas fa-folder-open fa-lg"></i></button>';
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
				var tag='<button class="btn btn-dark" id="createFolderInputBtn"><i class="fas fa-folder-open fa-lg"></i></button>';
				$('#createFolderSpace').html(tag);
				$('#createFolderInputBtn').on('click',createFolderNaming);
				$('#closeFolderMessage').empty();
			} else {
				console.log("フォルダー作り失敗。");
			}
		}
	})
}