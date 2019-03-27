$(function(){
    $('#loginBtn').on('click',function(){
        location.href="login";
    });
    
    $('#logoutBtn').on('click', function(){
    	location.href="logout";
    });
    
    $('#updateBtn').on('click', function(){
    	location.href="update";
    });
    
    $('#newProjectBtn').on('click',function(){
    	$('#webPresentation').attr('style','display:none');
    	$('#newProject').attr('style','display:block');
    })
    
    $('#projectCreateBtn').on('click', function(){
    	$('#mainProjectRegist').submit();
    })
    

    $('#project').on('click', function(){
    	location.href="project";
    })

    $('#whiteBoardBtn').on('click',function(){
    	location.href="whiteBoard";
    })
});

//구글 로그아웃 기능
function signOut() {
	var auth2 = gapi.auth2.getAuthInstance();


	auth2.signOut().then(function() {
		console.log('User signed out.');
		window.location.href = 'logout';
	});
}