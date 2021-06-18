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

	public PostVo findPost(int i) {
		
		return sqlSession.selectOne("blog.findPost",i);
	}

	public int categoryMin(String id) {
		
		return sqlSession.selectOne("blog.categoryMin",id);
	}

	public int postMin(int categoryNo) {
		
		return sqlSession.selectOne("blog.postMin",categoryNo);
	}

	public void update(BlogVo blogvo) {
		
		sqlSession.update("blog.update",blogvo);
		
	}

	public void categoryInsert(CategoryVo vo) {
		
		sqlSession.insert("blog.categoryInsert",vo);
		
	}

	public void categoryDelete(int no) {
		
		sqlSession.delete("blog.categoryDelete",no);
	}

	public void write(PostVo vo) {
		sqlSession.insert("blog.write",vo);
		
	}
}
