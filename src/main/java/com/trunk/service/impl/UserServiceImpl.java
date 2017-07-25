package com.trunk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trunk.dao.UserDao;
import com.trunk.entity.User;
import com.trunk.service.UserService;



@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao<User> userDao;

	@Override
	public User save(User user) {
		// TODO Auto-generated method stub
		return userDao.save(user);
	}

}
