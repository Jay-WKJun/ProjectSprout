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
    
    $('#projectCreateBtn').on('click', function(){
    	$('#mainProjectRegist').submit();
    })
    
    $('#project').on('click', function(){
    	location.href="project";
    })

});