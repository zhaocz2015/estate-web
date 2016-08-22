/**
 * 系统用户
 */

$(function() {
	initVars();
	initCmts();
	initDataGrid();
});

var urls = {
	form : "user/userForm",
	list : "user/userList",
	add: "user/addUser",
	edit: "user/editUser",
	rmv: "user/rmvUser",
};

var modes = {
	add: "add",
	edit: "edit",
	rmv: "rmv"
};

var dg, tb;
function initVars() {
	tb = $("#tb");
	dg = $("#dg");
}

function initCmts() {

}

function initDataGrid() {
	dg.datagrid({
		fit : true,
		striped : true,
		singleSelect : true,
		rownumbers : true,
		toolbar : tb,
		url : urls.list,
		loadMsg : "正在加载，请稍候",
		pagination : true,
		columns : [ [ {
			field : "USER_ID",
			checkbox : true
		}, {
			title : "编号",
			field : "NUMBER",
			width : 100,
		}, {
			title : "用户名",
			field : "USERNAME",
			width : 140
		}, {
			title : "姓名",
			field : "NAME",
			width : 160
		}, {
			title : "职位",
			field : "ROLE_NAME",
			width : 160
		}, {
			title : "邮箱",
			field : "EMAIL",
			width : 180
		}, {
			title : "最近登录",
			field : "LAST_LOGIN",
			width : 160
		}, {
			title : "上次登录IP",
			field : "IP",
			width : 160
		} ] ]
	});
}

function viewWin() {
	var row = dg.datagrid("getSelected");
	if (!row) {
		showAlert("请选择一条记录");
		return;
	}

	showWin({
		title : "详情窗口",
		width: 800,
		height: 400,
		href : urls.form,
		buttons : [ {
			text : "关闭",
			iconCls : "icon-cancel",
			handler : function() {
				$win.dialog("close");
			}
		} ],
		onLoad : function() {
			var userForm = $win.find("#userForm");
			userForm.form("load", row);
			userForm.find("input[]")
		}
	});
}

function addWin() {
	showWin({
		title : "新增窗口",
		width: 800,
		height: 400,
		href : urls.form,
		buttons : [ {
			text : "确定",
			iconCls : "icon-ok",
			handler : function() {
				submitForm(modes.add);
			}
		}, {
			text : "取消",
			iconCls : "icon-cancel",
			handler : function() {
				$win.dialog("close");
			}
		} ],
		onLoad : function() {
			// 生成用户编号
			$win.find("input[textboxname='NUMBER']").textbox("setValue", new Date().Format("yyyyMMddhhmmss"));
		}
	});
}

function editWin() {

	var row = dg.datagrid("getSelected");
	if (!row) {
		showAlert("请选择一条记录");
		return;
	}

	showWin({
		title : "编辑窗口",
		width: 800,
		height: 400,
		href: urls.form,
		buttons : [ {
			text : "确定",
			iconCls : "icon-ok",
			handler : function() {
				submitForm(modes.edit)
			}
		}, {
			text : "取消",
			iconCls : "icon-cancel",
			handler : function() {
				$win.dialog("close");
			}
		} ],
		onLoad : function() {
			$win.find("input[textboxname='PASSWORD']").passwordbox("clear");
			$win.find("#userForm").form("load", row);
		}
	});
}

function rmvRecord() {
	var row = dg.datagrid("getSelected");
	if (!row) {
		showAlert("请选择一条记录");
		return;
	}
}

function submitForm(mode){
	showProgress();
	
	form.form("submit", {
		url: urls[mode],
		onSubmit: function(){
			var isValid = $(this).form("validate");
			if(!isValid){
				closeProgress();
			}
			return isValid;
		},
		success: function(data){
			closeProgress();
			
			var rsMsg = JSON.parse(data);
			if(rsMsg.success){
				showInfo(rsMsg.info);
				dg.datagrid("reload");
			}else{
				showAlert(rsMsg.info);
			}
		}
	});
}

function doQuery(){
	dg.datagrid("reload");
}

function doClear(){
	dg.datagrid("load", {});
}
