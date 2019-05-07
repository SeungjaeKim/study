package com.study.news.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.study.news.domain.NewsVo;

@Repository
public class NewsDao {
	
    @Autowired
	private SqlSession sqlSession;
	  
	/**
	 * 뉴스 등록
	 * @return
	 */
	public int insertNews(NewsVo newsVo) {
		
		return this.sqlSession.insert("News.insertNews");
	}

	/**
	 * 뉴스 수정
	 * @return
	 */
	public int updateNews(NewsVo newsVo) {
		
		return this.sqlSession.update("News.updateNews");
	}

	/**
	 * 뉴스 조회
	 * @param newsVo
	 */
	public NewsVo selectNews(NewsVo newsVo) {
		
		return this.sqlSession.selectOne("News.selectNews", newsVo);
	}
	
}
