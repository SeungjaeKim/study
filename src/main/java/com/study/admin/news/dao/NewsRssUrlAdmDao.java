package com.study.admin.news.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.study.admin.news.domain.NewsRssUrlVo;

@Repository
public class NewsRssUrlAdmDao {

    @Autowired
	private SqlSession sqlSession;

    /**
     * 뉴스 RSS URL 등록
     * @return
     */
    public int insertNewsRss(NewsRssUrlVo newsRssVo) {

    	return this.sqlSession.insert("NewsRss.insertNewsRss", newsRssVo);
    }

	/**
	 * 뉴스 RSS 마지막 생성 일시 수정
	 * @return
	 */
	public int updateLastBuildDateOfNewsRss(NewsRssUrlVo newsRssVo) {

		return this.sqlSession.update("NewsRss.updateLastBuildDateOfNewsRss", newsRssVo);
	}

    /**
     * 뉴스 RSS URL 페이지 전체 건수 조회
     * @return
     */
    public int selectNewsRssPageCount(NewsRssUrlVo newsRssVo) {

    	return this.sqlSession.selectOne("NewsRssAdm.selectNewsRssPageCount", newsRssVo);
    }

    /**
	 * 뉴스 RSS URL 페이지 목록 조회
	 * @return
	 */
	public List<NewsRssUrlVo> selectNewsRssPageList(NewsRssUrlVo newsRssVo) {

		return this.sqlSession.selectList("NewsRssAdm.selectNewsRssPageList", newsRssVo);
	}

	/**
	 * 뉴스 RSS URL 목록 조회
	 * @return
	 */
	public List<NewsRssUrlVo> selectNewsRssList(NewsRssUrlVo newsRssVo) {

		return this.sqlSession.selectList("NewsRssAdm.selectNewsRssList", newsRssVo);
	}

}
