/**
 * 加载页面初始化jQuery脚本
 */
$(function() {
	// 绑定上下文菜单
	bindContextMenuEvnets();
});

function openMenuTab(mID, mTitle, mUrl){
	if($("#tabs").tabs("exists", mTitle)){
		$("#tabs").tabs("select", mTitle);
	}else{
		var menuFrame = createMenuFrame(mID, mUrl);
		$("#tabs").tabs("add", {
			id: mID,
			title: mTitle,
			content: menuFrame,
			closable: true
		});
	}
	
	bindTabMenuEvents();
}

function createMenuFrame(mID, mUrl){
	if(mUrl.indexOf("menuid") == -1){
		if(mUrl.indexOf("?") == -1){
			mUrl += "?menuid=" + mID;
		}else{
			mUrl += "&menuid=" + mID;
		}
	}
		
	return "<iframe src='" + mUrl + "' srcolling='auto' frameborder='0' style='width:100%;height:100%;'></iframe>";
}

function bindTabMenuEvents() {
	/* 双击关闭TAB选项卡 */
	$(".tabs-inner").dblclick(function() {
		var subtitle = $(this).children(".tabs-closable").text();
		$('#tabs').tabs('close', subtitle);
	});
	/* 为选项卡绑定右键 */
	$(".tabs-inner").bind('contextmenu', function(e) {
		$('#mm').menu('show', {
			left : e.pageX,
			top : e.pageY
		});

		var subtitle = $(this).children(".tabs-closable").text();

		$('#mm').data("currtab", subtitle);
		$('#tabs').tabs('select', subtitle);
		return false;
	});
}


/**
 * 绑定右键菜单事件
 */
function bindContextMenuEvnets() {
	
	// 刷新
	$('#mm-tabupdate').click(function() {
		var currTab = $('#tabs').tabs('getSelected');
		var url = $(currTab.panel('options').content).attr('src');
		var menuid = $(currTab.panel('options').content).attr('id');
		if (url != undefined && currTab.panel('options').title != 'Home') {
			$('#tabs').tabs('update', {
				tab : currTab,
				options : {
					content : createFrame(url, menuid)
				}
			});
		}
	});
	// 关闭当前
	$('#mm-tabclose').click(function() {
		var currtab_title = $('#mm').data("currtab");
		$('#tabs').tabs('close', currtab_title);
	});
	// 全部关闭
	$('#mm-tabcloseall').click(function() {
		$('.tabs-inner span').each(function(i, n) {
			var t = $(n).text();
			if (t != '首页提醒') {
				$('#tabs').tabs('close', t);
			}
		});
	});
	// 关闭除当前之外的TAB
	$('#mm-tabcloseother').click(function() {
		var prevall = $('.tabs-selected').prevAll();
		var nextall = $('.tabs-selected').nextAll();
		if (prevall.length > 0) {
			prevall.each(function(i, n) {
				var t = $('a:eq(0) span', $(n)).text();
				if (t != '首页提醒') {
					$('#tabs').tabs('close', t);
				}
			});
		}
		if (nextall.length > 0) {
			nextall.each(function(i, n) {
				var t = $('a:eq(0) span', $(n)).text();
				if (t != '首页提醒') {
					$('#tabs').tabs('close', t);
				}
			});
		}
		return false;
	});
	// 关闭当前右侧的TAB
	$('#mm-tabcloseright').click(function() {
		var nextall = $('.tabs-selected').nextAll();
		if (nextall.length == 0) {
			// msgShow('系统提示','后边没有啦~~','error');
			alert('后边没有啦~~');
			return false;
		}
		nextall.each(function(i, n) {
			var t = $('a:eq(0) span', $(n)).text();
			$('#tabs').tabs('close', t);
		});
		return false;
	});
	// 关闭当前左侧的TAB
	$('#mm-tabcloseleft').click(function() {
		var prevall = $('.tabs-selected').prevAll();
		if (prevall.length == 0) {
			alert('到头了，前边没有啦~~');
			return false;
		}
		prevall.each(function(i, n) {
			var t = $('a:eq(0) span', $(n)).text();
			$('#tabs').tabs('close', t);
		});
		return false;
	});

	// 退出
	$("#mm-exit").click(function() {
		$('#mm').menu('hide');
	});
}

