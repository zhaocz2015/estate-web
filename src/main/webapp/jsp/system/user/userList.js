/**
 * 系统用户
 */

$(function(){
	initVars();
	initCmts();
	initDataGrid();
});

var urls = {
	form: "user/userForm",
	list: "user/userList"
};

var dg, tb;
function initVars(){
	tb = $("#tb");
	dg = $("#dg");
}

function initCmts(){
	
}

function initDataGrid(){
	dg.datagrid({
		fit: true,
		striped: true,
		singleSelect: true,
		rownumbers: true,
		toolbar: tb,
		url: urls.list,
		loadMsg: "正在加载，请稍候",
		pagination: true,
		columns:[[{
			field: "user_ID",
			checkbox: true
		},{
			title: "编号",
			field: "number",
			width: 80,
		},{
			title: "用户名",
			field: "username",
			width: 120
		},{
			title: "姓名",
			field: "name",
			width: 120
		},{
			title: "职位",
			field: "role_NAME",
			width: 140
		},{
			title: "邮箱",
			field: "email",
			width: 140
		},{
			title: "最近登录",
			field: "last_LOGIN",
			width: 140
		},{
			title: "上次登录IP",
			field: "ip",
			width: 140
		}]]
	});
}

function viewWin(){
	var row = dg.datagrid("getSelected");
	if(!row){
		showAlert("请选择一条记录");
		return;
	}
	
	showWin({
		title: "新增窗口",
		href: urls.form,
		buttons: [{
			text: "确定",
			iconCls: "icon-ok",
			handler: function(){
				parent.$.messager.alert("信息", "提示信息", "info");
			}
		},{
			text: "取消",
			iconCls: "icon-cancel",
			handler: function(){
				$win.dialog("close");
			}
		}],
		onLoad: function(){

		}
	});
}

function addWin(){
	showWin({
		title: "新增窗口",
		href: urls.form,
		buttons: [{
			text: "确定",
			iconCls: "icon-ok",
			handler: function(){
				parent.$.messager.alert("信息", "提示信息", "info");
			}
		},{
			text: "取消",
			iconCls: "icon-cancel",
			handler: function(){
				$win.dialog("close");
			}
		}],
		onLoad: function(){

		}
	});
}

function editWin(){
	
	var row = dg.datagrid("getSelected");
	if(!row){
		showAlert("请选择一条记录");
		return;
	}
	
	showWin({
		title: "编辑窗口",
//		href: "user/userForm",
		content:"<table class='list'><tr><th>用户名</th><td><input class='easyui-textbox' style='width:120px;'/></td><tr></table>",
		buttons: [{
			text: "确定",
			iconCls: "icon-ok",
			handler: function(){
				showInfo("确定");
			}
		},{
			text: "取消",
			iconCls: "icon-cancel",
			handler: function(){
				$win.dialog("close");
			}
		}],
		onLoad: function(){

		}
	});
}



function rmvRecord(){
	var row = dg.datagrid("getSelected");
	if(!row){
		showAlert("请选择一条记录");
		return;
	}
}