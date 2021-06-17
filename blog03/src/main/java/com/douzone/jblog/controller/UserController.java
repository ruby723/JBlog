package com.douzone.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.jblog.service.UserService;
import com.douzone.jblog.vo.UserVo;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	
	@RequestMapping(value="join",method=RequestMethod.POST)
	public String join(UserVo vo) {
		
		userService.join(vo);
		return "user/join";
	}
	
	@RequestMapping("joinsuccess")
	public String joinsuccess() {
		
		return "user/joinsuccess";
	}
	
	@RequestMapping("login")
	public String login() {
		
		return "user/login";
	}
	
	@RequestMapping("loginsuccess")
	public String loginsuccess() {
		
		return "redirect:/";
	}
	
	@RequestMapping("logout")
	public String logout() {
		
		return "redirect:/";
	}
}
