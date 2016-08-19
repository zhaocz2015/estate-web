<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<title>登录页面</title>
<link rel="stylesheet" href="static/css/login.css" />
<script type="text/javascript" src="static/jquery-easyui-1.5/jquery.min.js"></script>
</head>
<body>
	
	<div class="account-container">
        <h2>用户登录</h2>
        <form>
            <div class="account-input">
            	<input autofocus="" placeholder="用户名/手机/邮箱" name="account">
            </div>
           	<div class="account-input">
           		<input type="password" placeholder="密码" name="password">
           	</div>
            <div class="account-input captcha">
            	<input placeholder="验证码">
            	<img src="https://coding.net/api/getCaptcha?code=0.44605678467826126">
            </div>
        </form>
        <button onclick="login()">登录</button>
    </div>
	
<script>
$(function(){
	// 进行初始化的操作
});

function login(){
	window.location.href="main/index";
}
</script>
</body>
</html>