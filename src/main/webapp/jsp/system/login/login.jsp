<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<title>物业管理系统</title>
<%@ include file="/static/jsp/top.jsp"%>
</head>
<body>

	<div
		style="width: 400px; height: 300px; position: absolute; top: 50%; margin-top: -150px; left: 50%; margin-left: -200px;">
		<div class="easyui-panel" style="width: 400px; padding: 30px 60px">
			<form id="login-form" method="post">
				<div style="text-align: center;">
					<h1>物业管理系统</h1>
				</div>
				<div style="margin-bottom: 20px">
					<input class="easyui-textbox" id="username" name="username"
						prompt="请输入用户名" required="true" missingMessage="请输入用户名"
						iconCls="icon-man" iconWidth="28"
						style="width: 100%; height: 34px; padding: 10px;">
				</div>
				<div style="margin-bottom: 20px">
					<input class="easyui-passwordbox" id="password" name="password"
						prompt="请输入密码" required="true" missingMessage="请输入密码"
						iconWidth="28" style="width: 100%; height: 34px; padding: 10px">
				</div>
				<div style="margin-bottom: 20px">
					<a href="#" class="easyui-linkbutton" onclick="login()"
						style="width: 100%; height: 36px">登 &nbsp;&nbsp;录</a>
				</div>
			</form>
		</div>
	</div>

	<script>
		$(function() {
			//注册Enter回车事件
			$("#username").textbox("textbox").keydown(function(e) {
				if (e.keyCode == 13) {
					login();
				}
			});

			$('#password').passwordbox(
					{
						inputEvents : $.extend({},
								$.fn.passwordbox.defaults.inputEvents, {
									keydown : function(e) {
										if (e.keyCode == 13) {
											login();
										}
									}
								})
					})

			$(document).keyup(function(event) {
				if (event.keyCode == 13) {
					login();
				}
			});

			$("#login-form").form("disableValidation");
		});

		function login() {
			$("#login-form").form("enableValidation");

			$.messager.progress({
				text : "正在登录"
			});

			$("#login-form").form("submit", {
				url : "login_login",
				onSubmit : function() {
					var isValid = $(this).form("validate");
					var isValid = $(this).form("validate");
					if (!isValid) {
						$.messager.progress("close");
					}
					return isValid;
				},
				success : function(data) {
					$.messager.progress("close");

					var rsMsg = JSON.parse(data);
					if (rsMsg.success) {
						window.location.href = "main";
					} else {
						$.messager.alert("登录提示", rsMsg.title, "warning");
					}
				}
			});
		}
	</script>
</body>
</html>