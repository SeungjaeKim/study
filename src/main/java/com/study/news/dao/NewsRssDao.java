package com.study.news.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.study.news.domain.NewsRss;

@Repository
public class NewsRssDao {
	
    @Autowired
	private SqlSession sqlSession;
	  
	/**
	 * 뉴스 RSS URL 목록
	 * @return
	 */
	public List<NewsRss> getNewsRssList() {
		
		return this.sqlSession.selectList("NewsRss.selectNewsRssList");
	}
	
}
