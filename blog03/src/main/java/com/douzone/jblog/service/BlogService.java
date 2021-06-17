package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.BlogRepository;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;

@Service
public class BlogService {

	@Autowired
	BlogRepository blogRepository;
	
	public BlogVo findById(String id) {
		
		return blogRepository.findById(id);
	}
	
	public List<CategoryVo> categoryList(String id){
		
		List<CategoryVo> list =  blogRepository.categoryList(id);
		return list;
	}
	
	public List<PostVo> postList(int category){
		
		List<PostVo> list = blogRepository.postList(category);
		return list;
	}
}
