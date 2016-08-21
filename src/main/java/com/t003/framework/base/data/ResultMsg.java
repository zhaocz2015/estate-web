/************************************************************
 * 类名：ResultMsg
 *
 * 类别：vo类
 * 功能：用于在前后台传输业务操作成功标志
 * 
 *   Ver     变更日期               部门            担当者        变更内容
 * ──────────────────────────────────────────────
 *   V1.00  2014-9-11  CFIT-PM   hyf         初版
 *   
 *
 * Copyright (c) 2014 CFIT-Weifang Company All Rights Reserved.
 ************************************************************/
package com.t003.framework.base.data;

import java.util.HashMap;

public class ResultMsg {

	/*
	 * 是否成功
	 */
	boolean success;
	/*
	 * 消息标题
	 */
	String info;
	/*
	 * 消息体
	 */
	HashMap<String, Object> body;

	public ResultMsg() {

	}

	/**
	 * 构造函数
	 * 
	 * @param succ
	 *            是否成功
	 * @param title
	 *            标题
	 */
	public ResultMsg(boolean succ, String info) {
		this.success = succ;
		this.info = info;
	}

	/**
	 * 构造函数
	 * 
	 * @param succ
	 *            是否成功
	 * @param title
	 *            标题
	 * @param body
	 *            可选消息体
	 */
	public ResultMsg(boolean succ, String title, HashMap<String, Object> body) {
		this.success = succ;
		this.info = title;
		this.body = body;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getTitle() {
		return info;
	}

	public void setTitle(String title) {
		this.info = title;
	}

	public HashMap<String, Object> getBody() {
		return body;
	}

	public void setBody(HashMap<String, Object> body) {
		this.body = body;
	}

	/**
	 * 由Exception 自动构建ResultMsg
	 * 
	 * @param exception
	 *            异常
	 * @param defaultErrMsg
	 *            默认异常信息
	 * @return
	 */
	public static ResultMsg build(Exception exception, String defaultErrMsg) {
		exception.printStackTrace();
		if (exception instanceof Exception) {
			return new ResultMsg(false, exception.getMessage());
		} else {
			return new ResultMsg(false, defaultErrMsg);
		}
	}

}
