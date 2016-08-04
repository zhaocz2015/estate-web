package com.t003.framework.system.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.t003.framework.base.controller.BaseController;

@Controller
public class LoginController extends BaseController {

	@RequestMapping("/login_toLogin")
	public ModelAndView toLogin() {
		return new ModelAndView("system/login/login");
	}

	@RequestMapping("login_login")
	public Object login(){
		
		
		
		return null;
	}
	
	@RequestMapping("/main/index")
	public ModelAndView index() {
		return new ModelAndView("main");
	}
	
}
