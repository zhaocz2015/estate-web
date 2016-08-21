/**
 *  easyui.ext.js
 */
$.extend($.fn.layout.paneldefaults, {
	onCollapse : function() {
		var layout = $(this).parents(".layout");
		var opts = $(this).panel("options");
		var expandKey = "expand" + opts.region.substring(0, 1).toUpperCase()
				+ opts.region.substring(1);
		var expandPanel = layout.data("layout").panels[expandKey];
		if (opts.region == "west" || opts.region == "east") {
			var split = [];
			if (opts.title) {
				for ( var i = 0; i < opts.title.length; i++) {
					split.push(opts.title.substring(i, i + 1));
				}
				expandPanel.panel("body").addClass("panel-title").css(
						"text-align", "center").html(split.join("<br>"));
			}
		} else {
			if (opts.title) {
				expandPanel.panel("setTitle", opts.title);
			}
		}
	}
});