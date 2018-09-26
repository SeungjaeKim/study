package com.study.news.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.news.dao.NewsRssDao;
import com.study.news.domain.NewsRss;

@Service
public class NewsRssService {
	
	@Autowired
	private NewsRssDao newsRssDao;
	
	/**
	 * 뉴스 RSS URL 목록 조회
	 * @return
	 */
	public List<NewsRss> getNewsRssList() {
		
		return newsRssDao.getNewsRssList();
	}

}
