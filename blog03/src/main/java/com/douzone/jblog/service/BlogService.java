package com.douzone.jblog.service;

import java.util.List;
import java.util.Optional;

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

	public PostVo findPost(int categoryNo,int postNo) {
		
		PostVo vo = blogRepository.findPost(postNo);

		return vo;
	}

	public void update(BlogVo blogvo) {
		
		blogRepository.update(blogvo);
	}

	public void categoryInsert(CategoryVo vo) {
		
		blogRepository.categoryInsert(vo);
		
	}

	public void categoryDelete(int no) {
		
		blogRepository.categoryDelete(no);
	}

	public void write(PostVo vo) {
		
		blogRepository.write(vo);
		
	}

}
