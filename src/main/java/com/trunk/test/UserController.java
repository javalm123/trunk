package com.trunk.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class UserController {
	
	@Autowired
	private UserService<User> userServiceImpl;
	
	@RequestMapping("/list")
	public ModelAndView list(){
		List<User> findAll = userServiceImpl.findAll();
		ModelAndView m=new ModelAndView();
		m.addObject("list", findAll);
		m.setViewName("test");
		return m;
	}
}
