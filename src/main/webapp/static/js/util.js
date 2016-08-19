/**
 * util.js
 */
function showWin(wOpts){
	$win = parent.$("<div></div>");
	$win.dialog($.extend({
		width: 500,
		height: 400,
		modal: true,
		onClose: function(){
			$win.dialog("destroy");
		}
	}, wOpts));
}

function showAlert(warn, fn){
	parent.$.messager.alert("警告", warn, "warning", fn);
}

function showInfo(info, fn){
	parent.$.messager.alert("提示", info, "info", fn);
}