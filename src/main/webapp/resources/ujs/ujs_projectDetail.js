$(function(){
	//파일 리스트 출력
	getFileList();
	/*folderForm();*/
	
	//로그인 버튼 이펙트
	$('#userProfileIcon').on('mouseover', function() {
		$('#userProfileIcon').attr('class', 'rounded-circle');
	})
	$('#userProfileIcon').on('click', function() {
		$('#userProfileIcon').attr('class', 'rounded-circle');
	})
	$('#userProfileIcon').on('mouseup', function() {
		$('#userProfileIcon').attr('class', 'rounded-circle border');
	})
	$('#userProfileIcon').on('mouseout', function() {
		$('#userProfileIcon').attr('class', 'rounded-circle border');
	})
	
	//폴더 모달 버튼
	$('#createFolderModalBtn').on('click',function(){
		$('#noticeDetailModal').show();
	})
	
	//폴더 모달 닫기 버튼
	$('#cancelNoticeDetailBtn').on('click',function(){
		$('#noticeDetailModal').hide();
	})
	
	$('#createFolderBtn').on('click', function(){
		$('#createFolderForm').submit();
	});
	
	//문서 작성 폼 생성
	$('#createDocument').on('click', createForm);
	
})

//문서 선택 및 출력
function selectBoard(){
	$('#loader').show();
	
	var str = $(this).attr("data-bNum");
	
	if (str == "default") {
		
	} else {
		$.ajax({
			type : 'POST',
			url : 'loadBoard',
			data : 'board_num='+str,
			success : function(result){
				$('#createFolderSpace').text('');
				var tag = '<div>'
				tag += '<h1 style="text-align : center">'+result.boardTitle+'</h1><br>';
				$.each(result.files, function(index, item){
					tag += '<img style="width:90%;height:auto" src="'+item.document_file_location+'/'+item.document_file_originalfileName+item.document_file_extension+'">';
				})
				tag += '<div>'+result.boardContent+'</div>';
				tag += '</div>';
				$('#contentSpace').html(tag);
				
				$('#loader').hide();
			}
		});
		$(this).val("default").prop("selected", true);
	}
}

// 파일 업로드
function fileUpLoad() {
	$('#loader').show();
	
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
				getFileList();
			} else {
				alert(result);
				console.log("파일 업로드 실패");
			}
			$('#loader').hide();
		}
	})
}

//모든 폴더와 파일 리스트 가져오기
function getFileList(){
	$('#loader').show();
	
	$.ajax({
		type : 'GET',
		url : 'loadList',
		success : function(result){
			
			var tag ='';
			tag = '<div class="list-group">';
			$.each(result, function(index, item){
				//map을 하나식 꺼낸다.
				tag+='<div class="dropdown dropright float-right list-group-item list-group-item-action"';
				tag+='style="margin-bottom: 2px; border: 1px solid #6079a0;">';
				tag += '<div data-toggle="dropdown">'+item.folderName+'</div>';
				tag += '<div class="dropdown-menu shadow" style="border:1px solid #6079a0">';
				tag += '<h5 class="dropdown-header">'+item.folderName+'</h5>';
				tag += '<button class="btn btn-info w-100 createDocument" data-foderName="'+item.folderName+'">';
				tag += '<i class="fas fa-file-medical fa-lg"></i>';
				tag += '</button>';
				tag += '<div style="margin-top: 5px">';
				$.each(item.boardList, function(index2, item2){
					tag += '<a href="#" style="border:1px solid #6079a0" data-bNum="'+item2.document_board_num+'"';
					tag += 'class="list-group-item list-group-item-action border-left-0 border-right-0 boardSelect">';
					tag += item2.document_board_title;
					tag += '</a>';
				});
				/*tag += '</select>'*/
				tag +='</div>';
				tag +='</div>';
				tag +='</div>';
			});
			tag +='</div>';
			$("div#noticeSpace").html(tag);
			
			//문서 선택
			$(".boardSelect").on('click', selectBoard);
			//문서 작성 폼 생성
			$('.createDocument').on('click', createForm);
			
			$('#loader').hide();
		}
	});
}

//폴더 생성
function createFolder(){
	var folderName=$('createFolderBtn').val();
	
}

//프로젝트 생성폼 html 써주는 것
function createForm() {
	var folderName=$(this).attr('data-foderName');
	var formTag = '';
	
	formTag += '<form id="documentFileUpLoadForm" method="post" enctype="multipart/form-data">';
	formTag += '<input type="text" class="form-control" id="document_board_title" name="document_board_title">';
	
	formTag += '<div class="mainSpace_top" style="margin-top:20px">';
	formTag += '<div class="mainSpace_top_center">';
	formTag += '<label class="btn btn-dark">';
	formTag += '<i class="fas fa-file-medical fa-lg"></i>';
	formTag += '<input type="file" name="upLoadFile" id="upLoadFile" style="display:none">';
	formTag += '</label>';
	formTag += '</div>';
	formTag += '<div class="mainSpace_top_side">';
	formTag += '<input type="text" class="form-control" id="file_name" name="file_name">';
	formTag += '</div>';
	formTag += '</div>';
	
	formTag += '<input type="hidden" name="folder_name" value="'+folderName+'">';
	formTag += '<textarea class="form-control" style="height:600px;margin-top:10px;" id="document_board_content" name="document_board_content"></textarea>';
	/*formTag += '<input type="file" value="파일 찾기" id="upLoadFile" name="upLoadFile">';*/
	formTag += '</form>';
	formTag += '<div class="mainSpace_top" style="margin-top:20px">';
	formTag += '<div class="mainSpace_top_side">';
	formTag += '</div>';
	formTag += '<div class="mainSpace_top_center">';
	formTag += '<input type="button" class="btn btn-dark" id="fileUpLoadBtn" value="文書アップロード">';
	formTag += '</div>';
	formTag += '</div>';

	$('#contentSpace').html(formTag);

	//파일 선택시
	$("#upLoadFile").change(function(e){
		var fileName=$('#upLoadFile')[0].files[0].name;
		
		var fName=fileName.split('.');
		
		$('#file_name').val(fName[0]);
		 //  $('input[type=file]')[0].files[0].name;
		 //  $("#imgUpload")[0].files[0].type;
		 //  $("#imgUpload")[0].files[0].size;
	 });
	
	// 파일 업로드 버튼
	$('#fileUpLoadBtn').on('click', function(){
		if($('#upLoadFile').val()==""){
			alert('파일을 선택해주세요.');
		}else{
			fileUpLoad();
		}
	});
	
}
