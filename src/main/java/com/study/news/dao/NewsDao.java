package com.study.news.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class NewsDao {
	
    @Autowired
	private SqlSession sqlSession;
	  
	/**
	 * 뉴스 등록
	 * @return
	 */
	public void getNewsList() {
		
		this.sqlSession.insert("News.insertNews");
	}
	
}
