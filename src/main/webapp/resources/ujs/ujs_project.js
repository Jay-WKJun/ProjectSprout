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
		$('#headers').load('whiteBoard');
	})
	
	$('#modalCloseBtn').on('click', function() {
		$('#headers').html('');
		$('#whiteBoardModal').modal('hide');
	})
	
})