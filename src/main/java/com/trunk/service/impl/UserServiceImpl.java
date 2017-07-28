package com.trunk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trunk.common.service.BaseServiceImpl;
import com.trunk.dao.impl.UserDao;
import com.trunk.entity.User;



@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService<User> {
	
	private UserDao userDaoImpl;
	
	@Autowired
	public void setBaseDao(UserDao userDaoImpl){
		super.setBaseDao(userDaoImpl);
		this.userDaoImpl=userDaoImpl;
	}
	
	
}
