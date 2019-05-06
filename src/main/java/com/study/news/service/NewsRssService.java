package com.study.news.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.news.dao.NewsRssDao;
import com.study.news.domain.NewsRssVo;

@Service
public class NewsRssService {
	
	@Autowired
	private NewsRssDao newsRssDao;
	
	/**
	 * 뉴스 RSS URL 목록 조회
	 * @return
	 */
	public int insertNewsRss(NewsRssVo newsRssVo) {
		
		return newsRssDao.insertNewsRss(newsRssVo);
	}

	/**
	 * 뉴스 RSS URL 목록 데이터 건수 조회
	 * @return
	 */
	public int selectNewsRssPageCount(NewsRssVo newsRssVo) {
		
		return newsRssDao.selectNewsRssPageCount(newsRssVo);
	}
	
	/**
	 * 뉴스 RSS URL 목록 조회
	 * @return
	 */
	public List<NewsRssVo> selectNewsRssPageList(NewsRssVo newsRssVo) {
		
		return newsRssDao.selectNewsRssPageList(newsRssVo);
	}

	/**
	 * 뉴스 RSS URL 목록 조회
	 * @return
	 */
	public List<NewsRssVo> selectNewsRssList(NewsRssVo newsRssVo) {
		
		return newsRssDao.selectNewsRssList(newsRssVo);
	}

}
