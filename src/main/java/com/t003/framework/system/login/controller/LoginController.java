package com.t003.framework.system.login.controller;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.t003.framework.base.util.DateUtil;
import com.t003.framework.base.util.RightsHelper;
import com.t003.framework.base.util.Tools;
import com.t003.framework.system.menu.entity.Menu;
import com.t003.framework.system.menu.service.MenuService;
import com.t003.framework.system.role.entity.Role;
import com.t003.framework.system.role.service.RoleService;
import com.t003.framework.system.user.entity.User;
import com.t003.framework.system.user.service.UserService;

@Controller
public class LoginController extends BaseController {

	@Autowired
	UserService userService;

	@Autowired
	RoleService roleService;

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
			// 更新登录时间
			Map nparams = new HashMap();
			nparams.put("USER_ID", loginUser.getUSER_ID());
			nparams.put("LAST_LOGIN", DateUtil.getTime().toString());
			userService.updateLastLogin(nparams);

			loginUser.setLAST_LOGIN(DateUtil.getTime().toString());
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

				if (null == session.getAttribute(Const.SESSION_QX)) {
					session.setAttribute(Const.SESSION_QX, this.getUQX(session)); // 按钮权限放到session中
				}

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

	/**
	 * 获取用户权限
	 */
	public Map<String, String> getUQX(Session session) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			String USERNAME = session.getAttribute(Const.SESSION_USERNAME).toString();
			map.put(Const.SESSION_USERNAME, USERNAME);

			String ROLE_ID = userService.findByUserName(USERNAME).getROLE_ID();

			// 管理权限
			Map glqx = roleService.findGLbyrid(ROLE_ID);
			map.put("FX_QX", glqx.get("FX_QX").toString());
			map.put("FW_QX", glqx.get("FW_QX").toString());
			map.put("QX1", glqx.get("QX1").toString());
			map.put("QX2", glqx.get("QX2").toString());
			map.put("QX3", glqx.get("QX3").toString());
			map.put("QX4", glqx.get("QX4").toString());

			// 用户权限
			Map yhqx = roleService.findYHbyrid(ROLE_ID);
			map.put("C1", yhqx.get("C1").toString());
			map.put("C2", yhqx.get("C2").toString());
			map.put("C3", yhqx.get("C3").toString());
			map.put("C4", yhqx.get("C4").toString());
			map.put("Q1", yhqx.get("Q1").toString());
			map.put("Q2", yhqx.get("Q2").toString());
			map.put("Q3", yhqx.get("Q3").toString());
			map.put("Q4", yhqx.get("Q4").toString());

			// 操作权限
			Role role = roleService.findRoleByUId(ROLE_ID);
			map.put("adds", role.getADD_QX());
			map.put("dels", role.getDEL_QX());
			map.put("edits", role.getEDIT_QX());
			map.put("chas", role.getEDIT_QX());

			this.getRemortIP(USERNAME);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return map;
	}

	/**
	 * 获取登录用户的IP
	 * 
	 * @throws Exception
	 */
	public void getRemortIP(String USERNAME) throws Exception {
		HttpServletRequest request = this.getRequest();
		String ip = "";
		if (request.getHeader("x-forwarded-for") == null) {
			ip = request.getRemoteAddr();
		} else {
			ip = request.getHeader("x-forwarded-for");
		}

		Map params = new HashMap();
		params.put("USERNAME", USERNAME);
		params.put("IP", ip);
		userService.saveIP(params);
	}

}
