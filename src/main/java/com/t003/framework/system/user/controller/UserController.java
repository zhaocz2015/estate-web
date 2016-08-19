package com.t003.framework.system.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.Page;
import com.t003.framework.base.controller.BaseController;
import com.t003.framework.system.user.entity.User;
import com.t003.framework.system.user.service.UserService;

@Controller
public class UserController extends BaseController {

	@Autowired
	UserService userService;

	@RequestMapping("/userEntry")
	public ModelAndView userEntry() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("/system/user/userList");
		return mv;
	}

	@RequestMapping("/userList")
	@ResponseBody
	public Page<User> userList() throws Exception {
		return userService.getUsersByPage();
	}

}
