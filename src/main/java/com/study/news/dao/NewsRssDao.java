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
    public int insertNewsRss(NewsRssVo newsRssVo) {
    	
    	return this.sqlSession.insert("NewsRss.insertNewsRss", newsRssVo);
    }

    /**
     * 뉴스 RSS URL 페이지 전체 건수 조회
     * @return
     */
    public int selectNewsRssPageCount(NewsRssVo newsRssVo) {
    	
    	return this.sqlSession.selectOne("NewsRss.selectNewsRssPageCount", newsRssVo);
    }
    
    /**
	 * 뉴스 RSS URL 페이지 목록 조회
	 * @return
	 */
	public List<NewsRssVo> selectNewsRssPageList(NewsRssVo newsRssVo) {
		
		return this.sqlSession.selectList("NewsRss.selectNewsRssPageList", newsRssVo);
	}

	/**
	 * 뉴스 RSS URL 목록 조회
	 * @return
	 */
	public List<NewsRssVo> selectNewsRssList(NewsRssVo newsRssVo) {
		
		return this.sqlSession.selectList("NewsRss.selectNewsRssList", newsRssVo);
	}
	
}
