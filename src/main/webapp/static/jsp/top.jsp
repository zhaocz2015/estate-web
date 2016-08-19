<%
	String path = request.getContextPath(); 
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="static/easyui/1.5/themes/default/easyui.css" />
<link rel="stylesheet" href="static/easyui/1.5/themes/icon.css" />
<link rel="stylesheet" href="static/easyui/1.5/themes/color.css" />
<link rel="stylesheet" href="static/css/common.css" />
<script type="text/javascript" src="static/easyui/1.5/jquery.min.js"></script>
<script type="text/javascript" src="static/easyui/1.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="static/easyui/1.5/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="static/js/util.js"></script>