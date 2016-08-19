package com.t003.framework.system.user.service;

import java.util.Map;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
