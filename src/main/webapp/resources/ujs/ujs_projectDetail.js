$(function(){
	//파일 리스트 출력
	getFileList();
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
	
	$(document).on('change','select',function(){
		var str = $( "select option:selected" ).val();
		alert(str);
		
		if (str == "default") {
			
		} else {
			$.ajax({
				type : 'POST',
				url : 'loadBoard',
				data : 'board_num='+str,
				success : function(result){
					var tag = '<div>';
					tag += result.boardTitle;
					$.each(result.files, function(index, item){
						tag += item.document_file_location;
					})
					tag += result.boardContent;
					tag += '</div>';
					$('.contentSpace').html(tag)
				}
			});
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
		
//폴더와 파일 리스트 가져오기
function getFileList(){
	//ajax로 가자
	
	$.ajax({
		type : 'GET',
		url : 'loadList',
		success : function(result){
			
			var tag = '<div>';
			$.each(result, function(index, item){
				//map을 하나식 꺼낸다.
				tag += '<p>Folder <h2>'+item.folderName+'</h2></p>';
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
