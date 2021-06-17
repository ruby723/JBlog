package com.douzone.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;

@Repository
public class BlogRepository {

	@Autowired
	private SqlSession sqlSession;
	
	public BlogVo findById(String id) {
		
		return sqlSession.selectOne("blog.findById",id);
	}

	public List<CategoryVo> categoryList(String id) {
		List<CategoryVo> list = sqlSession.selectList("blog.categoryList",id);
		return list;
	}

	public List<PostVo> postList(int category) {
		List<PostVo> list = sqlSession.selectList("blog.postList",category);
		return list;
	}
}
