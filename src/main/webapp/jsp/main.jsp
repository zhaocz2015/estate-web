<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
<%@ include file="/static/jsp/top.jsp"%>
</head>
<body class="easyui-layout">
	
	<div region="north" style="height: 80px;">
		<h1>Welcome to access index.jsp page!</h1>
		<div class="easyui-linkmenu"></div>
	</div>
	
	<div region="west" title="导航菜单" style="width: 280px;">
		
		<div class="easyui-accordion" style="height: 400px;">
			<c:forEach items="${menus}" var="menu">
				<div title="${menu.MENU_NAME}" iconCls="icon-add" style="height: 300px;"></div>
			</c:forEach>
		</div>
		
	</div>
	
	<div region="center">
	
	</div>
	
</body>
</html>