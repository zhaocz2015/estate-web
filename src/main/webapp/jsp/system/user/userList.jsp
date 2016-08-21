<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<title>物业管理系统</title>
<%@ include file="/static/jsp/common.jsp"%>
<script type="text/javascript" src="jsp/system/user/userList.js"></script>
</head>
<body class="easyui-layout">
	<div region="center" title="系统用户列表">
		<div id="tb" class="tb-css">
			<div class="tb-css-btn">
				<a class="easyui-linkbutton" iconCls="icon-expand" plain="true" onclick="toggleTb(this)"></a>
				<span class="xian"></span>
				<a class="easyui-linkbutton" iconCls="icon-ok" plain="true" onclick="viewWin()">详情</a>
				<span class="xian"></span>
				<a class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addWin()">新增</a>
				<span class="xian"></span>
				<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editWin()">修改</a>
				<span class="xian"></span>
				<a class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="rmvRecord()">刪除</a>
			</div>
			<div class="tb-css-qry">
				&nbsp;
				用户编码：<input class="easyui-textbox" id="usercode" />
				用户名称：<input class="easyui-textbox" id="username" />
				<a class="easyui-linkbutton" iconCls="icon-search" plain="false" onClick="doQuery()">查询</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="false" onClick="clearUsers()">清空</a>
			</div>
		</div>
		<table id="dg"></table>
	</div>
</body>
</html>