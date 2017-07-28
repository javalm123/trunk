package com.trunk.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.trunk.entity.User;
import com.trunk.service.UserService;


@RequestMapping("/test")
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
	
	@RequestMapping("/delete")
	public ModelAndView delete(Integer id){
		userServiceImpl.delete(id);
		return list();
	}
	
	
	@RequestMapping("/editData")
	public String editData(User user,HttpServletRequest request){
		request.setAttribute("user", user);
		return "testEdit";
	}
	
	@RequestMapping("/edit")
	public ModelAndView edit(User user){
		userServiceImpl.update(user);
		return list();
	}
	
	@RequestMapping("/addjsp")
	public String addJsp(){
		return "testadd";
	}
	
	@RequestMapping("/add")
	public ModelAndView add(User user){
		userServiceImpl.save(user);
		return list();
	}
}
