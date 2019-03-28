//jquery 최신버전에서는 js/jquery-ui.min.js의 일부 메서드가 작동하지 않는 것을 해결
//andSelf메서드를 addBack로 변경 후 아래 코드 작성 
jQuery.browser = {};
(function () {
    jQuery.browser.msie = false;
    jQuery.browser.version = 0;
    if (navigator.userAgent.match(/MSIE ([0-9]+)\./)) {
        jQuery.browser.msie = true;
        jQuery.browser.version = RegExp.$1;
    }
})();

//드래그 구현
$(function() {
	// 클래스가 draggable인 요소에 드래그 기본 함수를 적용한다.
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

		var obj = $("#div1").offset();

		// #div의 현재 위치
		console.log("left: " + obj.left + "px, top: " + obj.top + "px");

		/*
		 * // #div의 현재 위치에서 특정치(50px)만큼 이동 $("#div1").css("left", obj.left +
		 * 50);
		 *  // #div 좌표 새로 설정 $("#div1").css({ "position": "absolute", "top":
		 * "100px", "left": "200px" });
		 */
	});
	
	//포스트잇 추가 버튼
	$('#addPostit').on('click',function(){
		$('#boardSpace').append('<div class="postit" style="position: absolute;"></div>');
	})
	
});