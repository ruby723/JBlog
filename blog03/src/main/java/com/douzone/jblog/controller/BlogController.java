package com.douzone.jblog.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;

@Controller
@RequestMapping("/blog/{id:(?!assets).*}")
public class BlogController {
	
	@Autowired
	BlogService blogService;
	
	@RequestMapping("")
	public String index(@PathVariable("id")String id,Model model) {
		
		BlogVo vo = blogService.findById(id);
		
		model.addAttribute("id",id);
		model.addAttribute("blogvo",vo);
		
		return "/blog/main";
	}
	

	@RequestMapping({"/{pathNo1}","/{pathNo1}/{pathNo2}"})
	public String index(
			@PathVariable("id") String id,
			@PathVariable("pathNo1") Optional<Integer> pathNo1,
			@PathVariable("pathNo2") Optional<Integer> pathNo2,Model model) {
		
		BlogVo blogvo = blogService.findById(id);
		
		int categoryNo = 0;
		int postNo = 0;
		
		if(pathNo2.isPresent()) {
			categoryNo = pathNo1.get();
			postNo = pathNo2.get();
		}else if(pathNo1.isPresent()) {
			categoryNo = pathNo1.get();
		}
		
		List<CategoryVo> categorylist = new ArrayList<>();
		categorylist = blogService.categoryList(id);
		List<PostVo> postlist = new ArrayList<>();
		postlist = blogService.postList(categoryNo);
		
		model.addAttribute("id",id);
		model.addAttribute("blogvo",blogvo);
		model.addAttribute("category",categorylist);
		model.addAttribute("post",postlist);
		
		return "/blog/main";
	}
	
	@ResponseBody
	@RequestMapping("/admin/basic")
	public String adminBasic(@PathVariable("id") String id) {
		System.out.println(id);
		return "BlogController.adminBasic";
		
	}
}
