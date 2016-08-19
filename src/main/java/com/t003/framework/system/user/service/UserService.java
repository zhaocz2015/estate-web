package com.t003.framework.system.user.service;

import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.t003.framework.base.dao.DaoSupport;
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

	public Page<User> getUsersByPage() throws Exception {
		return (Page<User>) dao.findForList("UserMapper.userListPage", null, new RowBounds(1, 10));
	}

}
