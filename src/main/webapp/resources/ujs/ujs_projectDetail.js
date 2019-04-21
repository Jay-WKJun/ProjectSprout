$(function(){
	//파일 리스트 출력
	getFileList();
	folderForm();
	
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
	
	//문서 작성 폼 생성
	$('#createDocument').on('click', function(){
		createForm();
	});
	
	//폴더 select에 변화가 있으면 감지해서 실행해라
	$(document).on('change','select',function(){
		var str = $(this).val();
		
		alert(str);
		
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
						tag += '<img src="'+item.document_file_location+'/'+item.document_file_originalfileName+item.document_file_extension+'">';
					})
					tag += '<div>'+result.boardContent+'</div>';
					tag += '</div>';
					$('.contentSpace').html(tag);
				}
			});
			$(this).val("default").prop("selected", true);
		}
	});
	
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
				getFileList();
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
		
//모든 폴더와 파일 리스트 가져오기
function getFileList(){
	
	$.ajax({
		type : 'GET',
		url : 'loadList',
		success : function(result){
			
			var tag = '<div>';
			$.each(result, function(index, item){
				//map을 하나식 꺼낸다.
				tag += '<h2>'+item.folderName+'</h2>';
				tag += '<select name="document_board_num" id="selectBoard">';
				tag += '<option value="default" selected="selected">'+item.folderName+'</option>'
				//alert(item.folderName);
				$.each(item.boardList, function(index2, item2){
					//여기부턴 DocumentBoard가 들어있다
					//alert(item2.document_board_title);
					tag += '<option value="'+item2.document_board_num+'">'+item2.document_board_title+'</option>';
				});
				tag += '</select>'
			});
			tag +='</div>';
			
			//alert(tag);
			
			$("div#noticeSpace").html(tag);
			
			
			
		}
	});
}



//프로젝트 생성폼 html 써주는 것
function createForm() {
	var formTag = '<form class="" id="documentFileUpLoadForm" method="post" enctype="multipart/form-data">';
	formTag += '<p id="folderDiv">';
	formTag += '<select name="folder_name">';
	formTag += '<option value="testFolder">testFolder</option>';
	formTag += '</select>';
	formTag += '</p>';
	formTag += '<input type="file" value="파일 찾기" id="upLoadFile" name="upLoadFile">';
	formTag += '<input type="text" id="document_board_title" name="document_board_title" placeholder="title">';
	formTag += '<input type="text" id="document_board_content" name="document_board_content" placeholder="content">';
	formTag += '<input type="text" id="file_name" name="file_name" placeholder="파일 이름">';
	formTag += '</form>';
	formTag += '<input type="button" class="btn btn-dark" id="fileUpLoadBtn" value="파일 올리기">';	
	var folderTag  = '<button class="btn btn-dark" id="createFolderInputBtn">폴더 생성</button>';

	$('#contentSpace').html(formTag);
	$('#createFolderSpace').html(folderTag);

	// 파일 업로드 버튼
	$('#fileUpLoadBtn').on('click', function(){
		if($('#upLoadFile').val()==""){
			alert('파일을 선택해주세요.');
		}else{
			fileUpLoad();
		}
	});
	
	// 폴더 생성 버튼
	$('#createFolderInputBtn').on('click',changeFolderForm);
	
	
}
