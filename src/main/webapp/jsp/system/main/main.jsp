<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
<%@ include file="/static/jsp/top.jsp"%>
<link rel="stylesheet" type="text/css" href="jsp/system/main/main.css">
<script type="text/javascript" src="jsp/system/main/main.js"></script>
</head>
<body class="easyui-layout">
	
	<div region="north" style="height:44px;">
		<div style='background: url("https://dn-coding-net-production-static.qbox.me/d58141c9-9a0c-40b0-a408-5935fd70670f.jpg") 100 40 / cover no-repeat fixed;'>
			
		</div>
		<div class="easyui-panel" style="padding:5px;">
			<c:forEach items="${menuList}" var="menu">
				<c:if test="${menu.hasMenu}">
		        	<a href="#" class="easyui-menubutton" menu='#mm${menu.MENU_ID}' style="font-weight:bold;font-size:24px;">${menu.MENU_NAME}</a>
				</c:if>
			</c:forEach>
	    </div>
	    
	    <c:forEach items="${menuList}" var="menu">
			<c:if test="${menu.hasMenu}">
				<div id="mm${menu.MENU_ID}" style="width:100px;">
					<c:forEach items="${menu.subMenu}" var="sub">
						<c:if test="${sub.hasMenu}">
							<c:choose>
									<c:when test="${not empty sub.MENU_URL}">
										<div style="margin:5px;" onclick="openMenuTab('z${sub.MENU_ID }', '${sub.MENU_NAME}','${sub.MENU_URL }')">${sub.MENU_NAME }</div>
									</c:when>
									<c:otherwise>
										<div>${sub.MENU_NAME}</div>
									</c:otherwise>
								</c:choose>
						</c:if>
					</c:forEach>
			    </div>
			</c:if>
		</c:forEach>
				    
	</div>
	
	<div region="west" title="导航菜单" style="width: 280px;">
		
		<div class="easyui-accordion">
			<c:forEach items="${menuList}" var="menu">
				<c:if test="${menu.hasMenu}">
					<div title="${menu.MENU_NAME}" >
						<ul class="subMenu">
							<c:forEach items="${menu.subMenu}" var="sub">
								<c:if test="${sub.hasMenu}">
								<c:choose>
									<c:when test="${not empty sub.MENU_URL}">
										<li id="z${sub.MENU_ID }">
											<a target="mainFrame"  onclick="openMenuTab('z${sub.MENU_ID }', '${sub.MENU_NAME}','${sub.MENU_URL }')">${sub.MENU_NAME }</a>
										</li>
									</c:when>
									<c:otherwise>
										<li><a href="javascript:void(0);">${sub.MENU_NAME}</a></li>
									</c:otherwise>
								</c:choose>
								</c:if>
							</c:forEach>
						</ul>
					</div>
				</c:if>
			</c:forEach>
		</div>
		
	</div>
	
	<!-- 右侧Tab页签 -->
	<div id="mainPanle" region="center" border="true" border="false">
		 <div id="tabs" class="easyui-tabs"  fit="true" border="false" >
            <div id="home" title="首页提醒">
				<div class="cs-home-remark">
					<h1>首页提醒</h1>
					
				</div>
			</div>
        </div>
	</div>
	
	<!-- Tab页签上的右键菜单 -->
	<div id="mm" class="easyui-menu cs-tab-menu">
		<div id="mm-tabupdate">刷新</div>
		<div class="menu-sep"></div>
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseother">关闭其他</div>
		<div id="mm-tabcloseall">关闭全部</div> 
	</div>
	
</body>
</html>