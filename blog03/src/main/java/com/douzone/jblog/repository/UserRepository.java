package com.douzone.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.douzone.jblog.vo.UserVo;

public class UserRepository {

	@Autowired
	private SqlSession sqlSession;
	
	public void join(UserVo vo) {
		
		int count= sqlSession.insert("board.insert",vo);
		return count==1;
	}
}
