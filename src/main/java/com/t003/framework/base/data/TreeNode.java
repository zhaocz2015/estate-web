package com.t003.framework.base.data;

import java.util.List;

public class TreeNode {

	private String id;
	private String text;
	private String iconCls;
	private boolean checked;
	private TREE_NODE_STATE state = TREE_NODE_STATE.open;

	private String pid;
	private List<TreeNode> children;

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public TREE_NODE_STATE getState() {
		return state;
	}

	public void setState(TREE_NODE_STATE state) {
		this.state = state;
	}

}

enum TREE_NODE_STATE {
	open, closed
}
