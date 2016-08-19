<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<title>物业管理系统</title>
<%@ include file="/static/jsp/top.jsp"%>
<script type="text/javascript" src="jsp/system/user/userList.js"></script>
</head>
<body class="easyui-layout">
	<div region="center" title="系统用户列表">
		<div id="tb">
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
		</div>
		
		<table id="dg"></table>
	</div>
</body>
</html>