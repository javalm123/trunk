package com.trunk.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trunk.common.service.BaseServiceImpl;



@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService<User> {
	
	
	private UserDao<User> userDaoImpl;
	
	@Autowired
	public void setUserDaoImpl(UserDao<User> userDaoImpl){
		super.setBaseDao(userDaoImpl);
		this.userDaoImpl=userDaoImpl;
		
	}
}
