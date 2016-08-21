package com.t003.framework.system.user.service;

import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.t003.framework.base.dao.DaoSupport;
import com.t003.framework.base.data.PageResult;
import com.t003.framework.system.user.entity.User;

@Service
public class UserService {

	@Autowired
	DaoSupport dao;

	public User getUserByNameAndPwd(Map<String, String> params) throws Exception {
		String username = params.get("username");
		String password = params.get("password");
		params.put("password", new SimpleHash("SHA-1", username, password).toString());
		return (User) dao.findForObject("UserMapper.getUserInfo", params);
	}

	/*
	 * 通过id获取数据
	 */
	public User getUserAndRoleById(String USER_ID) throws Exception {
		return (User) dao.findForObject("UserMapper.getUserAndRoleById", USER_ID);
	}

	public PageResult getUsersForPage(Map<String, String> params) throws Exception {
		int pageIndex = Integer.valueOf(params.get("page"));
		int pageSize = Integer.valueOf(params.get("rows"));
		return dao.findForPage("UserMapper.userListPage", null, new RowBounds(pageIndex, pageSize));
	}

	public void addUser(Map<String, String> params) throws Exception {
		dao.save("UserXMapper.saveUser", params);
	}

}
