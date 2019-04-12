$(function() {
	$('#loginBtn').on('click', function() {
		location.href = "login";
	});

	$('#logoutBtn').on('click', function() {
		location.href = "logout";
	});

	$('#updateBtn').on('click', function() {
		location.href = "update";
	});
	
	//프로젝트 생성 버튼
	$('#newProjectBtn').on('click', function() {
		$.ajax({
			method : 'get',
			url : 'signinCheck',
			success : function(result){
				if(result=="success"){
					$('#webPresentation').attr('style', 'display:none');
					$('#newProject').attr('style', 'display:block');
					$('#ProjectStartSpace').attr('style', 'display:none');
					$('#wantedBoardSpace').attr('style', 'display:none');
				}else{
					location.href="login";
				}
			}
		})
	})
	
	//공고글 보기
	$('#wantedBoardBtn').on('click', function(){
		$('#wantedBoardSpace').attr('style', 'display:block');
		$('#webPresentation').attr('style', 'display:none');
		$('#newProject').attr('style', 'display:none');
		$('#ProjectStartSpace').attr('style', 'display:none');
		$('#wantedBoardLoad').load('wantedBoard');
	})
	
	$('#projectCreateBtn').on('click', function() {
		$('#mainProjectRegist').submit();
	})

	$('#project').on('click', function() {
		location.href = "project";
	})

	$('#whiteBoardBtn').on('click', function() {
		location.href = "whiteBoard";
	})
	//프로젝트 시작하기 버튼을 
	$('#openproject').on('click', function() {
	    $('#project_go').submit();
		
	})
	
	//왼쪽 프로젝트타이틀 리스트 누르면, 컨트롤러 정보 보냄. 
	$('body').delegate('.projectSelectBtn', 'click', function() {
		$('#webPresentation').attr('style', 'display:none');
		$('#wantedBoardSpace').attr('style', 'display:none');
		$('#newProject').attr('style', 'display:none');
		$('#ProjectStartSpace').attr('style', 'display:block');
		var mainproject_projectnum = $(this).attr('data-pno');
		$('#mainProjectNum').val(mainproject_projectnum);
		
		//alert($('#mainProjectNum').val());

		$.ajax({
			method : 'get',
			url : 'startproject_go',
			data : "mainproject_projectnum=" + mainproject_projectnum,
			success : function(mainproject) {

			
				$('#goproject_title').html(mainproject.goproject_title)
				$('#goproject_content').html(mainproject.goproject_content)
				var output = '';
				$.each(mainproject.memberList,function(index, item){
					output += item.member_name+'<br>';
				})
				$('#goprojet_membername').html(output)
				
			}

		})

	})
	
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
	
	//회원정보로 이동
	$('#memberInfoBtn').on('click',function(){
		location.href="memberInfo";
	})
	
	//프로젝트 제거
	$('#outOfProject').on('click',function(){
		$('#whiteBoardModal').modal('show');
	})
	
	//모달 닫기
	$('#modalCloseBtn').on('click', function() {
		$('#whiteBoardModal').modal('hide');
	})
	

});