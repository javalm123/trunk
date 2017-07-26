package com.trunk.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
	
	@Autowired
	private UserService<User> userServiceImpl;
	
}
