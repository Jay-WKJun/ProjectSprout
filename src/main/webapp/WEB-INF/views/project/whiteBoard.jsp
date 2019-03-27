<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>whiteBoard</title>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.7.2/css/all.css"
	integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr"
	crossorigin="anonymous">
<link rel="stylesheet" href="ucss/ucss_whiteBoard.css">
<link rel="stylesheet" href="css/bootstrap.css">
<!-- <script src="js/jquery-1.8.2.js"></script> -->
<script src="js/jquery-3.3.1.min.js"></script>
<script src="js/jquery-ui.min.js"></script>
<script>
//jquery 최신버전에서는 js/jquery-ui.min.js의 일부 메서드가 작동하지 않는 것을 해결
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
	$(".draggable").draggable({
		// cursor옵션은 드래그 하는 동안 마우스 포인터의 모양을 변화시킴
		cursor : "move",
		// stack옵션은 드래그 대상이 되는 요소들을 자동으로 깊이 설정을 해줌
		// 드래그 되는 요소가 가장 위에 올라온다
		stack : ".draggable",
		// 드래그 하는 동안 불투명도는 0.7
		opacity : 0.7
	});

	// dragtest이벤트는 드래그가 시작하면 발생
	$(".draggable").bind("dragstart", function(event, ui) {
		// invert 클래스가 적용되어 배경 이미지가 변경됨
		$(this).addClass("invert");
	});

	// dragstop이벤트는 드래그가 멈추면 발생
	$(".draggable").bind("dragstop", function(event, ui) {
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
	

});
</script>
</head>

<body>
	<div>
		<button class="btn btn-dark" style="width: 80px">
			<span class="fa-stack fa-lg"><i
				class="far fa-sticky-note fa-stack-2x"></i><i
				class="fas fa-plus fa-stack-1x"></i></span>
		</button>
	</div>
	<div class="draggable" id="div1" style="position: absolute; left: 20px; top: 250px;">

	</div>

	<script src="js/bootstrap.bundle.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>