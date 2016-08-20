package com.t003.framework.base.data;

import java.util.List;

import com.github.pagehelper.Page;

public class PageResult {

	private long total;
	private List rows;

	public PageResult(Page page) {
		rows = page.getResult();
		total = page.getTotal();
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

}
