$(function () {
    
    $("#headers").load("whiteBoard");  // 페이지 인클루딩 파일 경로

    $('#userProfileIcon').on('mouseover', function () {
        $('#userProfileIcon').attr('class', 'rounded-circle');
    })
    $('#userProfileIcon').on('click', function () {
        $('#userProfileIcon').attr('class', 'rounded-circle');
    })
    $('#userProfileIcon').on('mouseup', function () {
        $('#userProfileIcon').attr('class', 'rounded-circle border');
    })
    $('#userProfileIcon').on('mouseout', function () {
        $('#userProfileIcon').attr('class', 'rounded-circle border');
    })
})
