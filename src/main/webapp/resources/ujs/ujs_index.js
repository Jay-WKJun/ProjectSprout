
$(function() {
	
	$('#loader').hide();
	
	$('#loginBtn').on('click', function() {
		$('#loader').show();
		location.href = "login";
	});
	$('#logoutBtn').on('click', function() {
		$('#loader').show();
		location.href = "logout";
	});
	
	//외부공고글 보기
	$('#externalWantedSpace').on('click', function(){
		$('#wantedBoardLoad').show();
		$('#internalWantedBoardLoad').hide();
	})
	
	//내부공고글 보기
	$('#internalWantedSpace').on('click',function(){
		$('#wantedBoardLoad').hide();
		$('#internalWantedBoardLoad').show();
	})
	
	//내부 공고글 작성 모달
	$('#writeInternalWantedBoard').on('click',function(){
		$('#internalWantedWriteModal').show();
		$('#writeBoardLoad').load('boardRegist');
	})
	
	//내부 공고글 작성 모달 닫기
	$('#closeWriteInternalBtn').on('click',function(){
		$('#internalWantedWriteModal').hide();
		$('#writeBoardLoad').empty();
	})
	
	//내부 공고글 자세히 보기
	$('.wantedBoardDetail').on('click',function(){
		var boardName=$(this).attr('data-bNum');
		$('#boardDetailModal').show();
		$('#boardDetailLoad').load('detail_info?wantedBoard_num='+boardName);
	})
	
	//내부 공고 지원글
	$('#applyBoardBtn').on('click',function(){
		$('#boardDetailLoad').load('apply_wanted');
	})
	
/*	//내부 공고글 자세히 보기 닫기
	$('#closeDetailInternalBtn').on('click',function(){
		$('#boardDetailModal').hide();
		$('#boardDetailLoad').empty();
	})
*/	
	//프로젝트 생성 버튼
	$('#newProjectBtn').on('click', function() {
		$.ajax({
			method : 'get',
			url : 'signinCheck',
			success : function(result){
				if(result=="success"){
					$('#webPresentSpace').hide();
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
		$('#loader').show();
		$('#wantedBoardSpace').attr('style', 'display:block');
		$('#webPresentation').attr('style', 'display:none');
		$('#newProject').attr('style', 'display:none');
		$('#ProjectStartSpace').attr('style', 'display:none');
		$('#wantedBoardLoad').load('wantedBoard');
		$('#internalWantedBoardLoad').load('internal');
		$('#webPresentSpace').hide();
	})
	
	$('#projectCreateBtn').on('click', function() {
		$('#loader').show();
		$('#mainProjectRegist').submit();
	})

	$('#project').on('click', function() {
		$('#loader').show();
		location.href = "project";
	})

	//프로젝트 시작하기 버튼을 
	$('#openproject').on('click', function() {
		$('#loader').show();
	    $('#project_go').submit();
	})
	
	//왼쪽 프로젝트타이틀 리스트 누르면, 컨트롤러 정보 보냄. 
	$('body').delegate('.projectSelectBtn', 'click', function() {
		$('#webPresentSpace').hide();
		$('#wantedBoardSpace').attr('style', 'display:none');
		$('#webPresentation').attr('style', 'display:none');
		$('#wantedBoardSpace').attr('style', 'display:none');
		$('#newProject').attr('style', 'display:none');
		$('#ProjectStartSpace').attr('style', 'display:block');
		var mainproject_projectnum = $(this).attr('data-pno');
		$('#mainProjectNum').val(mainproject_projectnum);
		
		//alert($('#mainProjectNum').val());

		$.ajax({
			method : 'get',
			url : 'startproject_go', // MainProjectController.java
			data : "mainproject_projectnum=" + mainproject_projectnum,
			success : function(mainproject) {

			
				$('#goproject_title').html(mainproject.goproject_title)
				$('#goproject_content').html(mainproject.goproject_content)
				var output = '';
				$.each(mainproject.memberList,function(index, item){
					
					output += '<div >';
					output += '<img class="rounded-circle border memberImg" src="download?loginId='+item.member_id+'"'; 
					output += 'style="width: 35px; height: 35px; margin-right:10px" id="memberIcon">'; 
					output += item.member_name;
					output += '<hr style="background-color:#6079a0">';
					output += '<div>';
				})
				$('#goprojet_membername').html(output)
				$('.memberImg').attr('onerror','src="img/empty_profile.png"');
			}
			,beforeSend:function(){

		        $('#loader').show();

		    }
		    ,complete:function(){

		        $('#loader').hide();

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
		$('#loader').show();
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

//홈이동
function home(){
	$('#loader').show();
	location.href="/sprout";
}