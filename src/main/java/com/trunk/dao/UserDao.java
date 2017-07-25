package com.trunk.dao;

import com.trunk.entity.User;

public interface UserDao<T> {

	public User save(T o);
}
