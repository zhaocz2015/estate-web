package com.t003.framework.system.menu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.t003.framework.base.dao.DaoSupport;
import com.t003.framework.system.menu.entity.Menu;

@Service
public class MenuService {

	@Autowired
	DaoSupport dao;

	public List<Menu> listAllParentMenu() throws Exception {
		return (List<Menu>) dao.findForList("MenuMapper.listAllParentMenu", null);
	}

}
