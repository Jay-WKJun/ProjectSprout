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

	$('body').delegate('.projectSelectBtn', 'click', function() {
		$('#webPresentation').attr('style', 'display:none');
		$('#newProject').attr('style', 'display:none');
		$('#ProjectStartSpace').attr('style', 'display:block');
		var mainproject_projectnum = $(this).attr('data-pno');

		$.ajax({
			method : 'get',
			url : 'startproject_go',
			data : "mainproject_projectnum=" + mainproject_projectnum,
			success : function(mainproject) {
				/* alert(JSON.stringify(mainproject.mainproject_title)); */
				$('#goproject_title').html(mainproject.goproject_title)
				$('#goproject_content').html(mainproject.goproject_content)
				$('#goprojet_membername').html(mainproject.goprojet_membername)

			}

		})

	})

});

//로그아웃 해결법 아직 미완
function onLoad() {
	gapi.load('auth2', function() {
		gapi.auth2.init();
	});
}

// 구글 로그아웃 기능
function signOut() {
	var auth2 = gapi.auth2.getAuthInstance();

	auth2.signOut().then(function() {
		console.log('User signed out.');
		window.location.href = 'logout';
	});
}