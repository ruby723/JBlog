package com.douzone.jblog.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.jblog.service.UserService;
import com.douzone.jblog.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;
	
	@RequestMapping("/join")
	public String join(@ModelAttribute UserVo vo) {
		
		return "user/join";
	}
	
	@RequestMapping(value="/join",method=RequestMethod.POST)
	public String joinsuccess(@ModelAttribute @Valid UserVo vo, BindingResult result,Model model) {
		if(result.hasErrors()) {

			model.addAllAttributes(result.getModel());
			return "user/join";
		}
		userService.join(vo);
		return "user/joinsuccess";
	}
	
	@RequestMapping("/login")
	public String login() {
		
		return "user/login";
	}
	
	@RequestMapping("/loginsuccess")
	public String loginsuccess() {
		return "redirect:/";
	}
	
	@RequestMapping("/logout")
	public String logout() {
		
		return "redirect:/";
	}
}
