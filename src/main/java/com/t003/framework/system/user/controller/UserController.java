package com.t003.framework.system.user.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.t003.framework.base.controller.BaseController;
import com.t003.framework.base.data.PageResult;
import com.t003.framework.base.data.ResultMsg;
import com.t003.framework.base.util.AppUtil;
import com.t003.framework.system.user.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	@Autowired
	UserService userService;

	@RequestMapping("/userEntry")
	public ModelAndView userEntry() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("/system/user/userList");
		return mv;
	}

	@RequestMapping("/userForm")
	public ModelAndView userForm() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("/system/user/userForm");
		return mv;
	}

	@RequestMapping("/userList")
	@ResponseBody
	public PageResult userList(HttpServletRequest request) throws Exception {
		Map<String, String> params = AppUtil.getRequestParams(request);
		return userService.getUsersForPage(params);
	}

	@RequestMapping("/addUser")
	@ResponseBody
	public ResultMsg addUser(HttpServletRequest request) {
		ResultMsg rsMsg = null;
		Map<String, String> params = AppUtil.getRequestParams(request);
		try {
			params.put("USER_ID", get32UUID());
			params.put("RIGHTS", ""); // 权限
			params.put("LAST_LOGIN", ""); // 最后登录时间
			params.put("IP", ""); // IP
			params.put("STATUS", "0"); // 状态
			params.put("SKIN", "default"); // 默认皮肤

			params.put("PASSWORD", new SimpleHash("SHA-1", params.get("USERNAME"), params.get("PASSWORD")).toString());

			userService.addUser(params);
			rsMsg = new ResultMsg(true, "操作成功");
		} catch (Exception e) {
			rsMsg = new ResultMsg(false, "操作失败");
			logger.error(e.getMessage(), e);
		}
		return rsMsg;
	}

}
