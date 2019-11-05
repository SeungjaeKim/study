package com.study.admin.news.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.admin.news.dao.NewsRssUrlAdmDao;
import com.study.admin.news.domain.NewsRssUrlVo;

@Service
public class NewsRssUrlAdmService {

	@Autowired
	private NewsRssUrlAdmDao newsRssDao;

	/**
	 * 뉴스 RSS URL 목록 조회
	 * @return
	 */
	public int insertNewsRss(NewsRssUrlVo newsRssVo) {

		return newsRssDao.insertNewsRss(newsRssVo);
	}

	/**
	 * 뉴스 RSS 마지막 생성 일시 수정
	 * @return
	 */
	public int updateLastBuildDateOfNewsRss(NewsRssUrlVo newsRssVo) {

		return this.newsRssDao.updateLastBuildDateOfNewsRss(newsRssVo);
	}

	/**
	 * 뉴스 RSS URL 목록 데이터 건수 조회
	 * @return
	 */
	public int selectNewsRssPageCount(NewsRssUrlVo newsRssVo) {

		return newsRssDao.selectNewsRssPageCount(newsRssVo);
	}

	/**
	 * 뉴스 RSS URL 목록 조회
	 * @return
	 */
	public List<NewsRssUrlVo> selectNewsRssPageList(NewsRssUrlVo newsRssVo) {

		return newsRssDao.selectNewsRssPageList(newsRssVo);
	}

	/**
	 * 뉴스 RSS URL 목록 조회
	 * @return
	 */
	public List<NewsRssUrlVo> selectNewsRssList(NewsRssUrlVo newsRssVo) {

		return newsRssDao.selectNewsRssList(newsRssVo);
	}

}
