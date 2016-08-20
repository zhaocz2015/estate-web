package com.t003.framework.system.user.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.t003.framework.base.controller.BaseController;
import com.t003.framework.base.data.PageResult;
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

}
