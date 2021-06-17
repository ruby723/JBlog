package com.douzone.jblog.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.jblog.security.AuthUser;
import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.UserVo;

@Controller
@RequestMapping("/blog/{id:(?!assets).*}")
public class BlogController {
	
	@Autowired
	BlogService blogService;
	
	@RequestMapping("")
	public String index(String id,Model model) {
		
		BlogVo vo = blogService.findById(id);
		
		model.addAttribute("id",id);
		model.addAttribute("blogvo",vo);
		return "/blog/main";
	}
	

	@RequestMapping({"/{pathNo1}","/{pathNo1}/{pathNo2}"})
	public String index(
			@PathVariable("id") String id,
			@PathVariable("pathNo1") Optional<Long> pathNo1,
			@PathVariable("pathNo2") Optional<Long> pathNo2) {
		
		Long categoryNo = 0L;
		Long postNo = 0L;
		
		if(pathNo2.isPresent()) {
			categoryNo = pathNo1.get();
			postNo = pathNo2.get();
		}else if(pathNo1.isPresent()) {
			categoryNo = pathNo1.get();
		}
		
		System.out.println("id:" +id);
		System.out.println("category:"+categoryNo);
		System.out.println("post:"+postNo);
		
		return "/blog/main";
	}
	
	@ResponseBody
	@RequestMapping("/admin/basic")
	public String adminBasic(@PathVariable("id") String id) {
		System.out.println(id);
		return "BlogController.adminBasic";
		
	}
}
