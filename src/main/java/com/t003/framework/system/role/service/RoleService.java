package com.t003.framework.system.role.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.t003.framework.base.dao.DaoSupport;
import com.t003.framework.base.data.TreeNode;
import com.t003.framework.system.role.entity.Role;

@Service
public class RoleService {

	@Autowired
	DaoSupport dao;

	public List<TreeNode> roleTree() throws Exception {
		List<Role> roles = (List<Role>) dao.findForList("RoleMapper.listAllERRoles", null);

		List<TreeNode> nodes = new ArrayList<TreeNode>();
		for (Role role : roles) {
			TreeNode node = new TreeNode();
			node.setId(role.getROLE_ID());
			node.setText(role.getROLE_NAME());
			node.setIconCls("icon-label");

			nodes.add(node);
		}

		return nodes;
	}

	/**
	 * 通过id查找
	 */
	public Role findRoleByUId(String roleId) throws Exception {
		return (Role) dao.findForObject("RoleMapper.findRoleByUId", roleId);
	}

	// 通过当前登录用的角色id获取管理权限数据
	public Map findGLbyrid(String roleId) throws Exception {
		return (Map) dao.findForObject("RoleMapper.findGLbyrid", roleId);
	}

	// 通过当前登录用的角色id获取用户权限数据
	public Map findYHbyrid(String roleId) throws Exception {
		return (Map) dao.findForObject("RoleMapper.findYHbyrid", roleId);
	}

}
