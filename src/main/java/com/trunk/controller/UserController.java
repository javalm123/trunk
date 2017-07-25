package com.trunk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.trunk.entity.User;
import com.trunk.service.UserService;

@Controller("userController")
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/test")
	public String saveUser(User user) {
		System.out.print(userService);
		//userService.save(user);
		return "success";
	}
}
