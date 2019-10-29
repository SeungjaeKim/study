package com.study.admin.user.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.study.news.domain.NewsVo;

@Repository
public class UserDao {
	
    @Autowired
	private SqlSession sqlSession;
	  
	/**
	 * 뉴스 조회
	 * @param newsVo
	 */
	public NewsVo selectNews(NewsVo newsVo) {
		
		return this.sqlSession.selectOne("News.selectNews", newsVo);
	}
	
}
