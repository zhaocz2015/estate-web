package com.t003.framework.system.login.controller;

import java.util.ArrayList;
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

import com.t003.framework.base.controller.BaseController;
import com.t003.framework.base.data.ResultMsg;
import com.t003.framework.base.util.AppUtil;
import com.t003.framework.base.util.Const;
import com.t003.framework.base.util.RightsHelper;
import com.t003.framework.base.util.Tools;
import com.t003.framework.system.menu.entity.Menu;
import com.t003.framework.system.menu.service.MenuService;
import com.t003.framework.system.role.entity.Role;
import com.t003.framework.system.user.entity.User;
import com.t003.framework.system.user.service.UserService;

@Controller
public class LoginController extends BaseController {

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
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView();

		try {
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();

			User user = (User) session.getAttribute(Const.SESSION_USER);
			if (user == null) {
				mav.setViewName("system/login/login");
			} else {

				User userr = (User) session.getAttribute(Const.SESSION_USERROL);
				if (null == userr) {
					user = userService.getUserAndRoleById(user.getUSER_ID());

					session.setAttribute(Const.SESSION_USERROL, user);
				} else {
					user = userr;
				}
				Role role = user.getRole();
				String roleRights = role != null ? role.getRIGHTS() : "";
				// 避免每次拦截用户操作时查询数据库，以下将用户所属角色权限、用户权限限都存入session
				session.setAttribute(Const.SESSION_ROLE_RIGHTS, roleRights); // 将角色权限存入session
				session.setAttribute(Const.SESSION_USERNAME, user.getUSERNAME()); // 放入用户名

				List<Menu> allmenuList = new ArrayList<Menu>();

				if (null == session.getAttribute(Const.SESSION_allmenuList)) {
					allmenuList = menuService.listAllMenu();
					if (Tools.notEmpty(roleRights)) {
						for (Menu menu : allmenuList) {
							menu.setHasMenu(RightsHelper.testRights(roleRights, menu.getMENU_ID()));
							if (menu.isHasMenu()) {
								List<Menu> subMenuList = menu.getSubMenu();
								for (Menu sub : subMenuList) {
									sub.setHasMenu(RightsHelper.testRights(roleRights, sub.getMENU_ID()));
								}
							}
						}
					}
					session.setAttribute(Const.SESSION_allmenuList, allmenuList); // 菜单权限放入session中
				} else {
					allmenuList = (List<Menu>) session.getAttribute(Const.SESSION_allmenuList);
				}

				// if (null == session.getAttribute(Const.SESSION_QX)) {
				// session.setAttribute(Const.SESSION_QX, this.getUQX(session));
				// // 按钮权限放到session中
				// }

				mav.setViewName("system/main/main");
				mav.addObject("user", user);
				mav.addObject("menuList", allmenuList);
			}
		} catch (Exception e) {
			mav.setViewName("system/login/login");
			logger.error(e.getMessage(), e);
		}

		return mav;
	}

}
