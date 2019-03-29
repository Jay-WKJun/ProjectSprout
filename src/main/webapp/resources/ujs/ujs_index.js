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
	
	//구글 로그아웃
	$('#googleLogout').on('click', function() {
		location.href = "login";
	});
	
	//나중에 삭제
	$('#timeTable').on('click', function(){
		location.href = "timetable";
	})

	$('#newProjectBtn').on('click', function() {

		$('#webPresentation').attr('style', 'display:none');
		$('#newProject').attr('style', 'display:block');
		$('#ProjectStartSpace').attr('style', 'display:none');
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
				$('#goprojet_membername').html(mainproject.goprojet_membername)
			}

		})

	})

});

// 구글 로그아웃 기능
function signOut() {
	gapi.load('auth2', function() {
		gapi.auth2.init();
	});
	
	var auth2 = gapi.auth2.getAuthInstance();

	auth2.signOut().then(function() {
		console.log('User signed out.');
		window.location.href = 'logout';
	});
}