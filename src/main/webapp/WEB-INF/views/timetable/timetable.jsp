<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jQuery.Gantt</title>

<meta http-equiv="X-UA-Compatible" content="IE=Edge;chrome=1">
<!-- 타임테이블을 꾸며주는 css임으로 반드시 불러줘야합니다. -->
<link rel="stylesheet" href="gantt/css/style.css" />
<!-- 아래 스타일은 전부 popup을 위한 style -->
<style>
.pop-layer .pop-container {
  padding:20px;
  height:auto;
}

.pop-layer p.ctxt {
  color: #666;
  height:100%;
  width:100%;
}

.pop-layer .btn-r {
  width: 100%;
  height: 10%;
  padding-top: 10px;
  border-top: 1px solid #DDD;
  text-align: right;
}

.pop-layer {
  display: none;
  position: absolute;
  top: 50%;
  left: 50%;
  width: 35%;
  height: 55%;
  background-color: #fff;
  border: 5px solid #3571B5;
  z-index: 10;
}

.dim-layer {
  display: none;
  position: fixed;
  _position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: 100;
}

.dim-layer .dimBg {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: #000;
  opacity: .5;
  filter: alpha(opacity=50);
}

.dim-layer .pop-layer {
  display: block;
}

.btn-layerClose {
  display: inline-block;
  height: 25px;
  padding: 0 14px 0;
  border: 1px solid #304a8a;
  background-color: #3f5a9d;
  font-size: 13px;
  color: #fff;
  line-height: 25px;
}

.pop-conts{
	width:100%;
	height:100%;
}

.btn-layerClose:hover {
  border: 1px solid #091940;
  background-color: #1f326a;
  color: #fff;
}

</style>
</head>
<body>

	<!-- 아래 script를 통해 이 class이름안에 모든 타임테이블이 그려집니다. -->
	<div class="gantt"></div>

	<hr>

	<input type="button" id="regist" value="regist" onclick="layer_popup('#layer2', null)"/>

	<!-- 여기서부터  popup설정 -->
	<a href="#layer2" class="btn-example"></a>
	<div class="dim-layer">
		<div class="dimBg"></div>
		<div id="layer2" class="pop-layer">
			<div class="pop-container">
				<div class="pop-conts">
					<!--content //-->
					<p class="ctxt mb20">
						이곳에 디테일 들어옵니다.
					</p>

					<div class="btn-r">
						<a href="#" class="btn-layerClose" id="update">수정</a>
						<a href="#" class="btn-layerClose" id="delete">삭제</a>
						<a href="#" class="btn-layerClose" id="close">닫기</a>
					</div>
					<!--// content-->
				</div>
			</div>
		</div>
	</div>
	<!-- 여기까지 popup설정 -->




<!-- 이 두개는 반드시 불러와야하고 반드시 한곳에 있어야합니다. -->

<script src="js/jquery-3.3.1.min.js"></script>
<script src="gantt/script/jquery.fn.gantt.js"></script>
<!-- script는 반드시 그대로 써주세요 -->
<script>
    
		//등록할 form을 먼저 만들고
		//form을 submit할때,
		//동시에 source에 JSON데이터를 새로 쓴다.(LIST나 Map으로 보내고 반복해서 source에 쓰는식으로 해야한다.)
		//새로 쓰고 db에 쓰면서 redirect요청으로 새로고침을 한다.
		$(function() {
			
			/* $('#regist').click(function(){
				layer_popup('#layer2');
			}); */
			
			
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
				onItemClick: function(pcNum) {
					alert("Item clicked - show some details");
					alert(pcNum);
					findPC(pcNum);
				},
				onAddClick: function(dt, rowId) {
					
				},
				onRender: function() {
					if (window.console && typeof console.log === "function") {
						console.log("chart rendered");
					}
				}
			});

			 /* $(".gantt").popover({
				selector: ".bar",
				title: function _getItemText() {
                    return this.textContent;
                },
                container: '.gantt',
				content: "And I'm the content of said popover.",
				trigger: "hover",
				placement: "auto right"
			}); */
			
			/* $('.btn-example').click(function(){
		        var href = $(this).attr('href');
		        layer_popup('#layer2');
		    }); */

		});
		
		function findPC(pcNum) {
			
			$.ajax({
				type : 'GET',
				url : 'checkPC',
				data : 'pcNum='+pcNum,
				success : function(pc){
					alert('success when findPC!' + JSON.stringify(pc));
					layer_popup('#layer2', pc);
					
				},
				error : function() {
					alert('fail when findPC!');
				}
			})
		}
		
		
	    function layer_popup(el, pc){

	        var $el = $(el);        //레이어의 id를 $el 변수에 저장
	        alert(JSON.stringify($el));
	        //var isDim = $el.prev().hasClass('dimBg');   //dimmed 레이어를 감지하기 위한 boolean 변수

	        //isDim ? $('.dim-layer').fadeIn() : $el.fadeIn();
	        $('.dim-layer').fadeIn();

	        var $elWidth = ~~($el.outerWidth()),
	            $elHeight = ~~($el.outerHeight()),
	            docWidth = $(document).width(),
	            docHeight = $(document).height();

	        // 화면의 중앙에 레이어를 띄운다.
	        if ($elHeight < docHeight || $elWidth < docWidth) {
	            $el.css({
	                marginTop: -$elHeight /2,
	                marginLeft: -$elWidth/2
	            })
	        } else {
	            $el.css({top: 0, left: 0});
	        }
	        
	        if (pc == null) {
	        	alert('pc는 null');
	        	
	        	$.ajax({
					type : 'GET',
					url : 'timetableMake',
					success : function(results){
						alert('success when load the createForm!' + JSON.stringify(results));
						var options = '';
						$.each(results, function(key, value){
	        				options += '<option value="'+value.member_num+'">'+value.member_name+'</option>';
	        				
	        			});
						
						$('.ctxt').html('<form method="POST" action="timetableMake" id="createForm" name="createForm" accept-charset="utf-8">'
			        			+'<input type="text" id="projectContent_title" name="projectContent_title" placeholder="title"><br>'
			        			+'<input type="text" id="projectContent_content" name="projectContent_content" placeholder="content"><br>'
			        			+'<input type="date" id="projectContent_startDate" name="projectContent_startDate"><br>'
			        			+'<input type="date" id="projectContent_endDate" name="projectContent_endDate"><br>'
			        			+'<input type="text" id="projectContent_color" name="projectContent_color"><br>'
			        			+'<input type="hidden" id="mainproject_projectNum" name="mainproject_projectNum"><br>'
			        			+'<select id="member_num" name="member_num">'
			        			+ options
			        			+'</select>' 
			        			+'</form>'
			        	);
						
						//완료 버튼 만들기
						$('div.btn-r').html('<a href="#" class="btn-layerClose" id="close">닫기</a>'
								+'<input type="button" class="btn-layerClose" id="createContent" onclick="doCreateSubmit()" value="완료" />'
						);
			        	
			        	//닫기 메소드
				        $el.find('a#close').click(function(){
				            //isDim ? $('.dim-layer').fadeOut() : $el.fadeOut(); // 닫기 버튼을 클릭하면 레이어가 닫힌다.
				            $('.dim-layer').fadeOut();
				            return false;
				        });
						
					},
					error : function() {
						alert('fail when load the createForm!');
					}
				})
	        	
	        	//pc가 없다면 만들기로 불러온 것이다.
	        	
			} else{
				alert('pc는 null 아님');
				//내용 쓰기 메소드
		        $('.ctxt').html('projectContent_title : '+pc.projectContent_title+'<br>'
		        	+'projectContent_content : '+pc.projectContent_content+'<br>'
		        	+'projectContent_startDate : '+pc.projectContent_startDate+'<br>'
		        	+'projectContent_endDate : '+pc.projectContent_endDate+'<br>'
		        	+'projectContent_color : '+pc.projectContent_color+'<br>'
		        	+'mainproject_projectNum : '+pc.mainproject_projectNum+'<br>'
		        	+'member_num : '+pc.member_num
		        	);
		        
		        //지우기 메소드
		        $el.find('a#delete').click(function(){
		        	alert('delete clicked');
		        	$('.ctxt').html('<form method="GET" action="deleteContent" id="deleteForm" name="deleteForm" accept-charset="utf-8">'
		        			+'<input type="hidden" id="projectContent_num" name="projectContent_num" value="'+pc.projectContent_num+'">'
		        			+'</form>'
		        	);
		        	$('#deleteForm').submit();
		        });
		        
				//닫기 메소드
		        $el.find('a#close').click(function(){
		            //isDim ? $('.dim-layer').fadeOut() : $el.fadeOut(); // 닫기 버튼을 클릭하면 레이어가 닫힌다.
		            $('.dim-layer').fadeOut();
		            return false;
		        });

		        //update form으로 바꿔주는 메소드, string타입의 날짜는 date타입으로 바꿔줘야한다.
		        $el.find('a#update').click(function(){
		        	var startDateCh = new Date(pc.projectContent_startDate);
		        	var endDateCh = new Date(pc.projectContent_endDate);
		        	
		        	startDateCh = getFormatDate(startDateCh);
		        	endDateCh = getFormatDate(endDateCh);
		        	
		        	alert(startDateCh + typeof(startDateCh));
		        	alert(endDateCh + typeof(endDateCh));
		        	
		        	$('.ctxt').html('<form method="post" action="updateContent" id="updateForm" name="updateForm" accept-charset="utf-8">'
		        			+'<input type="hidden" id="projectContent_num" name="projectContent_num" value="'+pc.projectContent_num+'">'
		        			+'<input type="text" id="projectContent_title" name="projectContent_title" value="'+pc.projectContent_title+'"><br>'
		        			+'<input type="text" id="projectContent_content" name="projectContent_content" value="'+pc.projectContent_content+'"><br>'
		    	        	+'<input type="date" id="projectContent_startDate" name="projectContent_startDate" value="'+startDateCh+'"><br>'
		    	        	+'<input type="date" id="projectContent_endDate" name="projectContent_endDate" value="'+endDateCh+'"><br>'
		    	        	+'<input type="text" id="projectContent_color" name="projectContent_color" value="'+pc.projectContent_color+'"><br>'
		    	        	+'<input type="text" id="mainproject_projectNum" name="mainproject_projectNum" value="'+pc.mainproject_projectNum+'"><br>'
		    	        	+'<input type="text" id="member_num" name="member_num" value="'+pc.member_num+'"><br>'
		    	        	+'</form>'
		    	        	);
		        	//완료버튼 생성
		        	$('div.btn-r').html('<a href="#" class="btn-layerClose" id="close">닫기</a>'
		        			+'<input type="button" class="btn-layerClose" id="updateContent" onclick="doUpdateSubmit()" value="완료" />');
		            return false;
				});
			}
	        
		}//layer
	        
	  	//업데이트 완료버튼 동작
	    function doUpdateSubmit() {
	    	//컨트롤러로 직행!
	    	$('#updateForm').submit();
	    }
		
	  //생성 완료버튼 동작
	    function doCreateSubmit() {
	    	//컨트롤러로 직행!
	    	$('#createForm').submit();
	    }
	    
	    //날짜변환 메소드
	    function getFormatDate(date){
	    	var year = date.getFullYear();                                 //yyyy
	    	var month = (1 + date.getMonth());                     //M
	    	month = month >= 10 ? month : '0' + month;     // month 두자리로 저장
	    	var day = date.getDate();                                        //d
	    	day = day >= 10 ? day : '0' + day;                            //day 두자리로 저장
	    	return  year + '-' + month + '-' + day;
	    }

	    
    </script>
    </body>
</html>