package com.trunk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trunk.common.service.BaseServiceImpl;
import com.trunk.dao.RoleDao;
import com.trunk.entity.Role;
import com.trunk.service.RoleService;
import com.trunk.service.UserService;



@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService<Role> {
	
	private RoleDao roleDaoImpl;
	
	@Autowired
	public void setBaseDao(RoleDao roleDaoImpl){
		super.setBaseDao(roleDaoImpl);
		this.roleDaoImpl=roleDaoImpl;
	}
	
	
}
