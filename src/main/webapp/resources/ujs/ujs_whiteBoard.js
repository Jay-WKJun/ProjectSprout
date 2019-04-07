//jquery 최신버전에서는 js/jquery-ui.min.js의 일부 메서드가 작동하지 않는 것을 해결
//jquery-ui.min.js의 andSelf메서드를 addBack로 변경 후 아래 코드 작성 
jQuery.browser = {};
(function() {
	jQuery.browser.msie = false;
	jQuery.browser.version = 0;
	if (navigator.userAgent.match(/MSIE ([0-9]+)\./)) {
		jQuery.browser.msie = true;
		jQuery.browser.version = RegExp.$1;
	}
})();

$(function() {
	setTimeout(function() {
		getPostitList();
		// 포스트잇 추가 버튼
		$('#addPostit').on('click', addPostit);
    }, 500);
});

// 포스트잇 생성
function addPostit() {
	var projectNum = $("#projectNum").val();
	var postit_color = "#fffa75";
	var postit_shape = "normal";

	var postit = {
		"MainProject_ProjectNum" : projectNum,
		"postit_top" : 65,
		"postit_left" : 9,
		"postit_color" : postit_color,
		"postit_shape" : postit_shape
	};
	setTimeout(function() {
		$.ajax({
			method : 'post',
			url : 'addPostit',
			data : postit,
			success : function(result) {
				if (result == "fail") {
					alert('포스트잇 생성 실패');
				} else {
					getPostitList();
				}
			}
		})
	}, 500);
}

// 포스트잇 리스트 가져오기
function getPostitList() {
	var projectNum = $("#projectNum").val();
	
	$.ajax({
		method : 'post',
		url : 'getPostitList',
		data : "MainProject_ProjectNum=" + projectNum,
		success : postitPrint
	})
}

// 포스트잇 출력
function postitPrint(postitList) {
	var tag = '';
	$.each(postitList, function(index, item) {
		tag += '<div class="postit" data-psq="' + item.postit_num;
		tag += '" style="top:' + item.postit_top + 'px;left:'+ item.postit_left;
		tag += 'px;background-color:' + item.postit_color + '">';
		tag += '<div class="postit_top">';
		tag += '<a class="close postitClose">';
		tag += '&times;';
		tag += '</a>';
		tag += '</div>';
		tag += '<div class="postit_bottom">';
		tag += '<textarea type="text" class="postitInput">';
		if(item.postit_content!=null){
			tag += item.postit_content;
		}
		tag += '</textarea>';
		tag += '</div>';
		tag += '</div>';
		tag += '</div>';
	});
	$('#boardSpace').html(tag);
	postitDrag();
	deletePostit();
	postitContentSave();
}

// 드래그 메서드
function postitDrag() {

	$(".postit").draggable({
		// cursor옵션은 드래그 하는 동안 마우스 포인터의 모양을 변화시킴
		cursor : "move",
		// stack옵션은 드래그 대상이 되는 요소들을 자동으로 깊이 설정을 해줌
		// 드래그 되는 요소가 가장 위에 올라온다
		stack : ".postit",
		// 드래그 하는 동안 불투명도는 0.7
		opacity : 0.7
	});

	// dragtest이벤트는 드래그가 시작하면 발생
	$(".postit").bind("dragstart", function(event, ui) {
		// invert 클래스가 적용되어 배경 이미지가 변경됨
		$(this).addClass("invert");
	});

	// dragstop이벤트는 드래그가 멈추면 발생
	$(".postit").bind("dragstop", function(event, ui) {
		// invert 클래스가 제거되어 원래 배경이미지로 돌아감
		$(this).removeClass("invert");

		// #div의 현재 위치에서 특정치(50px)만큼 이동 $("#div1").css("left", obj.left
		// +50);
		// #div 좌표 새로 설정 $("#div1").css({ "position": "absolute",
		// "top":"100px", "left": "200px" });
		
		//포스트잇 위치 저장
		var postitOffset = $(this).offset();
		var postitSeq = $(this).attr("data-psq");

		var postit = {
			"postit_num" : postitSeq,
			"postit_top" : postitOffset.top,
			"postit_left" : postitOffset.left
		}
		
		console.log(postit.postit_top+" "+postitOffset.left);

		$.ajax({
			method : 'post',
			url : 'postitMove',
			data : postit,
			success : function(result) {
				if (result == 'fail') {
					console.log('포스트잇 삭제 에러');
				}
			}
		})
	
	});

}

//포스트잇 삭제
function deletePostit(){
	$(".postitClose").on('click',function(){
		
		var postitSeq = $(this).parent().parent().attr("data-psq");
		$(this).parent().parent().remove();
		
		$.ajax({
			method : 'post',
			url : 'deletePostit',
			data : 'postit_num='+postitSeq,
			success : function(result) {
				if (result == 'fail') {
					console.log('포스트잇 이동 에러');
				}
			}
		})
	})
}

//포스트잇 내용 저장
function postitContentSave(){
	$('.postitInput').blur(function(){
		var postitSeq = $(this).parent().parent().attr("data-psq");
		var postit_content=$(this).val();
		
		var postit={
				"postit_num":postitSeq,
				"postit_content":postit_content
		}
		
		$.ajax({
			method : 'post',
			url : 'postitContentSave',
			data : postit,
			success : function(result) {
				if (result == 'fail') {
					console.log('포스트잇 내용 에러');
				}
			}
		})
	})
}