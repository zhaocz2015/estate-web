<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户弹窗</title>
</head>
<body>

<form id="userForm" method="post">
	<table class="list">
		<tr>
			<td colspan="4" style="background-color:#EFF5FF;font-weight:bold;">基本信息</td>
		<tr>
		<tr>
			<th>选择职位：</th>
			<td colspan="3">
				<input name="ROLE_ID" class="easyui-combobox" url="/" required="true" missingMessage="请选择职位" prompt="请选择职位" panelHeight="auto" style="width:280px;"/>
			</td>
		</tr>
		<tr>
			<th>编号</th>
			<td>
				<input name="NUMBER" class="easyui-textbox" readonly="true" style="width:160px;"/>
			</td>
			<th>用户名</th>
			<td>
				<input name="USER_NAME" class="easyui-textbox" required="true" missingMessage="请输入用户名" prompt="请输入用户名" style="width:160px;"/>
			</td>
		</tr>
		<tr>
			<th>输入密码</th>
			<td>
				<input name="PASSWORD" class="easyui-passwordbox" required="true" missingMessage="请输入密码" prompt="请输入密码" style="width:160px;"/>
			</td>
			<th>确认密码</th>
			<td>
				<input class="easyui-passwordbox" required="true" missingMessage="请确认密码" prompt="请确认密码" style="width:160px;"/>
			</td>
		</tr>
		<tr>
			<td colspan="4" style="background-color:#EFF5FF;font-weight:bold;">个人信息</td>
		<tr>
		<tr>
			<th>姓名</th>
			<td>
				<input name="NAME" class="easyui-textbox" required="true" missingMessage="请输入姓名" prompt="请输入姓名" style="width:160px;"/>
			</td>
			<th>电话</th>
			<td>
				<input name="PHONE" class="easyui-numberbox" required="true" missingMessage="请输入电话" prompt="请输入电话" style="width:160px;"/>
			</td>
		</tr>
		<tr>
			<th>邮箱</th>
			<td colspan="3">
				<input name="EMAIL" class="easyui-textbox" required="true" missingMessage="请输入邮箱" validType="email" prompt="请输入邮箱" invalidMessage="请输入正确格式的邮箱" style="width:280px;"/>
			</td>
		</tr>
		<tr>
			<th>备注</th>
			<td colspan="3">
				<input name="BZ" class="easyui-textbox" multiline="true" style="width:560px;height:80px;"/>
			</td>
		</tr>
	</table>
</form>

</body>
</html>