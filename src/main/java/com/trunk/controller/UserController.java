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
		user.setName("Trunk");
		System.out.println("刘伟更新了这行代码");
		userService.save(user);
		
		
		System.out.println("提交测试");
		return "success";
	}
}
