package com.t003.framework.system.login.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.t003.framework.base.data.ResultMsg;
import com.t003.framework.base.util.AppUtil;
import com.t003.framework.base.util.Const;
import com.t003.framework.system.menu.entity.Menu;
import com.t003.framework.system.menu.service.MenuService;
import com.t003.framework.system.user.entity.User;
import com.t003.framework.system.user.service.UserService;

@Controller
public class LoginController {

	@Autowired
	UserService userService;

	@Autowired
	MenuService menuService;

	@RequestMapping("/login_toLogin")
	public ModelAndView toLogin() {
		return new ModelAndView("system/login/login");
	}

	@RequestMapping("login_login")
	@ResponseBody
	public ResultMsg login(HttpServletRequest request) throws Exception {

		ResultMsg rsMsg = null;
		Map<String, String> params = AppUtil.getRequestParams(request);

		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();

		User loginUser = userService.getUserByNameAndPwd(params);
		if (loginUser != null) {
			session.setAttribute(Const.SESSION_USER, loginUser);
			currentUser.login(new UsernamePasswordToken(params.get("username"), params.get("password")));
			rsMsg = new ResultMsg(true, "登录成功");
		} else {
			rsMsg = new ResultMsg(false, "无效的用户名和密码");
		}

		return rsMsg;
	}

	@RequestMapping("/main")
	public ModelAndView index() throws Exception {
		ModelAndView mav = new ModelAndView("main");
		List<Menu> menus = menuService.listAllParentMenu();
		mav.addObject("menus", menus);
		return mav;
	}

}
