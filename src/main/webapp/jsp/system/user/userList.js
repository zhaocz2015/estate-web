/**
 * 系统用户
 */

$(function(){
	initVars();
	initCmts();
	initDataGrid();
});

var urls = {
	query: "userList"
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
		url: urls.query,
		loadMsg: "正在加载，请稍候",
		pagination: true,
		columns:[[{
			field: "user_ID",
			checkbox: true
		},{
			title: "用户名",
			field: "username",
			width: 120
		},{
			title: "姓名",
			field: "name",
			width: 120
		}]]
	});
}