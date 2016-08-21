/**
 * util.js
 */
function showWin(wOpts){
	var _win = parent.$("<div></div>");
	if(typeof($win) == "undefined"){
		$win = {};
		if(wOpts.winID){
			$win[wOpts.winID] = _win;
		}else{
			$win = _win;
		}
	}else{
		if(wOpts.winID){
			$win[wOpts.winID] = _win;
		}else{
			showAlert("请配置winID属性");
			return;
		}
	}
	
	_win.dialog($.extend({
		width: 500,
		height: 400,
		modal: true,
		onClose: function(){
			_win.dialog("destroy");
			if(wOpts.winID){
				delete $win[wOpts.winID];
				
				if(JSONLength($win) == 0){
					$win = undefined;
				}
			}else{
				$win = undefined;
			}
		}
	}, wOpts));
}

function showAlert(warn, fn){
	parent.$.messager.alert("警告", warn, "warning", fn);
}

function showInfo(info, fn){
	parent.$.messager.alert("提示", info, "info", fn);
}

function showProgress(opts){
	parent.$.messager.progress(opts);
}

function closePrgress(){
	parent.$.messager.progress("close");
}

function JSONLength(jsonObj){
	var size = 0;
	for(var obj in jsonObj){
		if(jsonObj.hasOwnProperty(obj)){
			size++;
		}
	}
	return size;
}

Date.prototype.Format = function (fmt) { 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}