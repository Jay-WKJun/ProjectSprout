<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>login</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
    <link rel="stylesheet" href="ucss/ucss_login.css">
    <link rel="stylesheet" href="css/bootstrap.css">
    <script src="js/jquery-3.3.1.min.js"></script>
    <script src="ujs/ujs_login.js"></script>
</head>

<body>
    <div class="wrapper">
        <div class="topSpace">
            <div class="topSpace_side"></div>
            <div class="topSpace_center">
                <div class="contentItem" style="padding:20px;">
                    <a href="/sprout"><img class="webLogo" src="img/sprout_logo.png"></a>
                </div>
            </div>
            <div class="topSpace_side"></div>
        </div>
        <div class="content_space">
            <div class="content_space_left"></div>
            <div class="content_space_center">
                <div class="contentItem" style="text-align-last: center">
                    <div class="loginBox rounded">
                        <div class="loginBox_inner_top">
                            <h4>로그인</h4>
                        </div>
                        <div class="loginBox_inner_center">
                        <div class="contentItem">
                        	<p id="message" style="color:#e24848">${message}</p>
                        </div>
                            <form id="login" action="login" method="POST">
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" style="min-width:120px;"><i class="far fa-id-badge fa-lg"></i><span style="margin-left:8px">아이디</span></span>
                                    </div>
                                    <input type="text" class="form-control" id="id" name="id" value="${cookie['idChecked'].value}">
                                </div>
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" style="min-width:120px"><i class="fas fa-lock fa-lg"></i><span style="margin-left:8px">비밀번호</span></span>
                                    </div>
                                    <input type="password" class="form-control" id="password" name="password">
                                </div>
                                <div class="checkBox_space">
                                	<div class="checkBox_space_side"></div>
                                	<div class="checkBox_space_main">
                                		<label><span style="margin-right:10px">아이디 저장</span><input type="checkbox" name="idChecked" ${checked}/></label>
                                	</div>
                                </div>
                                <div class="contentItem">
                                    <button class="btn btn-dark w-100" id="loginBtn"><i class="fas fa-sign-in-alt fa-lg"></i><span style="margin-left:8px">로그인</span></button>
                                </div>
                                <div style="margin-top: 10px">
                                    <a href="#" style="color: black; margin-right: 10px">비밀번호 찾기</a>
                                    <a href="join" style="color: black; margin-left: 10px">회원가입</a>
                                </div>
                            </form>
                        </div>
                        <div class="loginBox_inner_right"></div>
                    </div>
                </div>
            </div>
            <div class="content_space_right"></div>
        </div>
    </div>
    <script src="js/bootstrap.bundle.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</body>
</html>