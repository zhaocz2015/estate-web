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

function JSONLength(jsonObj){
	var size = 0;
	for(var obj in jsonObj){
		if(jsonObj.hasOwnProperty(obj)){
			size++;
		}
	}
	return size;
}