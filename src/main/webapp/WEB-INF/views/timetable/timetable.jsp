<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
    <head>
    <meta charset="UTF-8">
        <title>jQuery.Gantt</title>
		
		<meta http-equiv="X-UA-Compatible" content="IE=Edge;chrome=1" >
        <link rel="stylesheet" href="gantt/css/style.css" />
      <!-- 	<link rel="stylesheet" href="http://twitter.github.com/bootstrap/assets/css/bootstrap.css" />
        <link rel="stylesheet" href="http://taitems.github.com/UX-Lab/core/css/prettify.css" />   -->
		<style type="text/css">
			body {
				font-family: Helvetica, Arial, sans-serif;
				font-size: 13px;
				padding: 0 0 50px 0;
			}
			.contain {
				width: 800px;
				margin: 0 auto;
			}
			h1 {
				margin: 40px 0 20px 0;
			}
			h2 {
				font-size: 1.5em;
				padding-bottom: 3px;
				border-bottom: 1px solid #DDD;
				margin-top: 50px;
				margin-bottom: 25px;
			}
			table th:first-child {
				width: 150px;
			}
		</style>
    </head>
    <body>

		<div class="contain">

			<h2>
	    		Example
	    	</h2>
				
			<div class="gantt"></div>
			
			<hr>
			
			<a href=timetableMake>Task regist</a>

    </body>
	<script src="gantt/script/jquery-3.3.1.min.js"></script>
	<script src="gantt/script/jquery.fn.gantt.js"></script>
	<!-- <script src="http://twitter.github.com/bootstrap/assets/js/bootstrap-tooltip.js"></script>
	<script src="http://twitter.github.com/bootstrap/assets/js/bootstrap-popover.js"></script>
	<script src="http://taitems.github.com/UX-Lab/core/js/prettify.js"></script> -->
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