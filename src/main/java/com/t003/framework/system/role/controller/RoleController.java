package com.t003.framework.system.role.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.t003.framework.base.controller.BaseController;
import com.t003.framework.base.data.TreeNode;
import com.t003.framework.system.role.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

	@Autowired
	RoleService roleService;

	@RequestMapping("/roleTree")
	@ResponseBody
	public List<TreeNode> roleTree() throws Exception {
		return roleService.roleTree();
	}

}
