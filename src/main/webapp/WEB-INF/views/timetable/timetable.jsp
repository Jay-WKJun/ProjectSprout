<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
    <head>
    <meta charset="UTF-8">
        <title>jQuery.Gantt</title>
		
		<meta http-equiv="X-UA-Compatible" content="IE=Edge;chrome=1" >
		<!-- 타임테이블을 꾸며주는 css임으로 반드시 불러줘야합니다. -->
        <link rel="stylesheet" href="gantt/css/style.css" />
    </head>
    <body>
				
			<!-- 아래 script를 통해 이 class이름안에 모든 타임테이블이 그려집니다. -->
			<div class="gantt"></div>
			
			<hr>
			
			<a href=timetableMake>Task regist</a>

    </body>
    <!-- 이 두개는 반드시 불러와야하고 반드시 한곳에 있어야합니다. -->>
	<script src="gantt/script/jquery-3.3.1.min.js"></script>
	<script src="gantt/script/jquery.fn.gantt.js"></script>
	<!-- script는 반드시 그대로 써주세요 -->
    <script>
    
		//등록할 form을 먼저 만들고
		//form을 submit할때,
		//동시에 source에 JSON데이터를 새로 쓴다.(LIST나 Map으로 보내고 반복해서 source에 쓰는식으로 해야한다.)
		//새로 쓰고 db에 쓰면서 redirect요청으로 새로고침을 한다.
		$(function() {
			
			//밑에 source에 들어가는 데이터 형은 array name, desc, value(from, to label, customClass)
			//gantt 매개변수는 {source : 객체}
			"use strict";
			$(".gantt").gantt({
				source: ${jsonTest},
				navigate: "scroll",
				scale: "weeks",
				maxScale: "months",
				minScale: "days",
				itemsPerPage: 10,
				onItemClick: function(data) {
					alert("Item clicked - show some details");
					alert(data);
				},
				onAddClick: function(dt, rowId) {
					
				},
				onRender: function() {
					if (window.console && typeof console.log === "function") {
						console.log("chart rendered");
					}
				}
			});

			$(".gantt").popover({
				selector: ".bar",
				title: "I'm a popover",
				content: "And I'm the content of said popover.",
				trigger: "hover"
			});

			prettyPrint();

		});

    </script>
</html>