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
	reset: "user/resetPwd"
};

var modes = {
	add: "add",
	edit: "edit",
	rmv: "rmv"
};

var dg, tb, form;
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
			width : 120,
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
		height: 360,
		href : urls.form,
		buttons : [ {
			text : "关闭",
			iconCls : "icon-cancel",
			handler : function() {
				$win.dialog("close");
			}
		} ],
		onLoad : function() {
			form = $win.find("#userForm");
			form.find("tr.pwd-box").hide(); //隐藏密码
			form.form("load", row);
			form.find("input[class^='easyui']").textbox("readonly");//全部只读
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
			form = $win.find("#userForm");
			form.find("input[textboxname='NUMBER']").textbox("setValue", new Date().Format("yyyyMMddhhmmss"));// 生成用户编号
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
			form = $win.find("#userForm");
			// 隐藏并取消密码框的校验
			form.find("input[textboxname*='PASSWORD']").passwordbox("disableValidation");
			form.find("tr.pwd-box").hide();
			form.form("load", row);
		}
	});
}

function rmvRecord() {
	var row = dg.datagrid("getSelected");
	if (!row) {
		showAlert("请选择一条记录");
		return;
	}
	
	showConfirm("删除提示", "是否删除用户【" + row.USERNAME + "】的数据记录？", function(r){
		if(r){
			showProgress("正在请求");
			$.post(urls.rmv, {userID: row.USER_ID}, function(rsMsg){
				closeProgress();
				
				if(rsMsg.success){
					dg.datagrid("reload");
//					showInfo(rsMsg.info);
				}else{
					showAlert(rsMsg.info);
				}
			}, "JSON");
		}
	});
}

function resetPwd(){
	var row = dg.datagrid("getSelected");
	if(!row){
		showAlert("请选择一条记录");
		return;
	}
	
	showPrompt("重置密码", "请输入用户【" + row.USERNAME + "】的重置密码", function(pwd){
		if(pwd){
			showProgress("正在请求");
			$.post(urls.reset, {USER_ID: row.USER_ID, USERNAME: row.USERNAME, PASSWORD: pwd}, function(rsMsg){
				closeProgress();
				
				if(rsMsg.success){
					dg.datagrid("reload");
					showInfo(rsMsg.info);
				}else{
					showAlert(rsMsg.info);
				}
			}, "JSON");
		}
	});
}

function submitForm(mode){
	showProgress("正在提交");
	form.form("submit", {
		url: urls[mode],
		onSubmit: function(){
			var isValid = form.form("validate");
			if(!isValid){
				closeProgress();
			}
			return isValid;
		},
		success: function(data){
			closeProgress();
			
			var rsMsg = JSON.parse(data);
			if(rsMsg.success){
				$win.dialog("close"); //关闭窗口
				dg.datagrid("reload");//刷新列表
				showInfo(rsMsg.info);
			}else{
				showAlert(rsMsg.info);
			}
		}
	});
}

function doQuery(){
	dg.datagrid("load",{
		USERNAME: $("#USERNAME").textbox("getValue"),
		NAME: $("#NAME").textbox("getValue")
	});
}

function doClear(){
	$("#USERNAME").textbox("clear");
	$("#NAME").textbox("clear");
	dg.datagrid("load", {});
}
