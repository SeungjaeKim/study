package com.study.news.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.study.news.domain.NewsRssVo;

@Repository
public class NewsRssDao {
	
    @Autowired
	private SqlSession sqlSession;
	  
    /**
     * 뉴스 RSS URL 등록
     * @return
     */
    public int insertNewsRssList(NewsRssVo newsRssVo) {
    	
    	return this.sqlSession.insert("NewsRss.insertNewsRss", newsRssVo);
    }

    /**
	 * 뉴스 RSS URL 목록
	 * @return
	 */
	public List<NewsRssVo> getNewsRssList() {
		
		return this.sqlSession.selectList("NewsRss.selectNewsRssList");
	}
	
}
