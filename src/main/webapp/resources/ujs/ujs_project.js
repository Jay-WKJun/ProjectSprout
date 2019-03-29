$(function() {

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

	$('#modalBtn').on('click',function(){
		$('#whiteBoardModal').modal('show');
		var postitNumFromProjectNum=$('#postitNumFromProjectNum').val();
		$('#headers').load('whiteBoard?postitNumFromProjectNum='+postitNumFromProjectNum);
	})
	
	$('#modalCloseBtn').on('click', function() {
		$('#whiteBoardModal').modal('hide');
	})
	
})