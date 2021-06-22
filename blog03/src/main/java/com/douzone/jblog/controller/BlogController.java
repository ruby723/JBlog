package com.douzone.jblog.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.security.Auth;
import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.FileUploadService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;

@Controller
@RequestMapping("/blog/{id:(?!assets).*}")
public class BlogController {
	
	@Autowired
	BlogService blogService;
	@Autowired
	FileUploadService fileUploadService;
	
	@RequestMapping({"","/{pathNo1}","/{pathNo1}/{pathNo2}"})
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
		
		PostVo postvo = blogService.findPost(categoryNo,postNo);
		
		List<CategoryVo> categorylist = new ArrayList<>();
		categorylist = blogService.categoryList(id);
		List<PostVo> postlist = new ArrayList<>();
		postlist = blogService.postList(categoryNo);
		
		model.addAttribute("id",id);
		model.addAttribute("blogvo",blogvo);
		model.addAttribute("categorylist",categorylist);
		model.addAttribute("postlist",postlist);
		model.addAttribute("postvo",postvo);
		
		return "/blog/main";
	}

	// 블로그 관리
	@Auth
	@RequestMapping(value="/admin/basic", method=RequestMethod.GET)
	public String adminBasic(@PathVariable("id")String id, Model model) {
		
		BlogVo blogvo = blogService.findById(id);
		
		model.addAttribute("id",id);
		model.addAttribute("blogvo",blogvo);
		
		return "/blog/admin/basic";
	}
	
	@Auth
	@RequestMapping(value="/admin/update",method=RequestMethod.POST)
	public String update(String id,BlogVo blogvo,MultipartFile file,Model model) {
		
		BlogVo vo = blogService.findById(id);
		String url = fileUploadService.restore(file);
		vo.setLogo(url);
		vo.setUser_id(id);
		vo.setTitle(blogvo.getTitle());

		blogService.update(vo);
		
		model.addAttribute("id", id);
		model.addAttribute("vo", vo);
			
		return "redirect:/blog/"+id+"/admin/basic";
	}
	
	// 블로그 카테고리 관리
	@Auth
	@RequestMapping(value="/admin/category",method=RequestMethod.GET)
	public String category(@PathVariable("id")String id,Model model) {
		
		BlogVo blogvo = blogService.findById(id);
		
		List<CategoryVo> vo= new ArrayList<>();
		vo = blogService.categoryList(id);
		
		model.addAttribute("id", id);
		model.addAttribute("vo",vo);
		model.addAttribute("blogvo", blogvo);
		
		return "blog/admin/category";
	}
	
	@Auth
	@RequestMapping(value="/admin/category/update",method=RequestMethod.POST)
	public String categoryInsert(String id,CategoryVo vo,Model model) {
		
		vo.setUser_id(id);

		BlogVo blogvo = blogService.findById(id);
		blogService.categoryInsert(vo);
		
		List<CategoryVo> list= new ArrayList<>();
		list = blogService.categoryList(id);
		
		model.addAttribute("id", id);
		model.addAttribute("vo",list);
		model.addAttribute("blogvo", blogvo);
		return "redirect:/blog/"+id+"/admin/category";
	}
	
	@Auth
	@RequestMapping(value="/admin/category/delete/{no}",method= {RequestMethod.GET,RequestMethod.POST})
	public String categoryDelete(String id, @PathVariable("no")int no,Model model) {
		
		blogService.categoryDelete(no);
		BlogVo blogvo = blogService.findById(id);
		
		List<CategoryVo> list= new ArrayList<>();
		list = blogService.categoryList(id);
		
		model.addAttribute("id", id);
		model.addAttribute("vo",list);
		model.addAttribute("blogvo", blogvo);
		return "redirect:/blog/"+id+"/admin/category";
	}
	
	// 블로그 글쓰기 관리
	@Auth
	@RequestMapping("/admin/write")
	public String write(@PathVariable("id")String id,Model model) {
		
		List<CategoryVo> list= new ArrayList<>();
		list = blogService.categoryList(id);
		
		BlogVo blogvo = blogService.findById(id);
		
		model.addAttribute("id", id);
		model.addAttribute("list",list);
		model.addAttribute("blogvo",blogvo);
		
		return "blog/admin/write";
	}
	
	@Auth
	@RequestMapping(value="/admin/write/insert",method=RequestMethod.POST)
	public String writeInsert(String id,PostVo vo,Model model) {
		
		// System.out.println(vo);
		blogService.write(vo);
		
		model.addAttribute("id", id);
		
		return "redirect:/blog/"+id;
	}

}
