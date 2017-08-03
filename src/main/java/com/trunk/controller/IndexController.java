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


@RequestMapping("/index")
@Controller
public class IndexController {
	
	@RequestMapping("/nsfwHome")
	public String nsfwHome(){
		
		return "nsfw/home";
	}
}
