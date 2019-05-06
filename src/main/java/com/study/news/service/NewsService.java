package com.study.news.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.news.dao.NewsDao;
import com.study.news.domain.NewsVo;

@Service
public class NewsService {
	
	@Autowired
	private NewsDao newsDao;
	
	/**
	 * 뉴스 등록
	 * @param newsVo
	 * @return
	 */
	public void insertNews(NewsVo newsVo) {
		
		//뉴스 조회
		NewsVo existNewsVo = new NewsVo();
		existNewsVo.setCompCd(newsVo.getCompCd());         //언론사 코드
		existNewsVo.setNewsUrl(existNewsVo.getNewsUrl());  //뉴스 URL
		existNewsVo = newsDao.selectNews(newsVo);
		
		//뉴스 등록
		if (existNewsVo == null) {
			//insert
		}
		//뉴스 수정
		else if (0 < new Date(newsVo.getPubDt()).compareTo(new Date(existNewsVo.getPubDt()))) {
			//update
		}
	}
	
	/**
	 * 뉴스 조회
	 * @return newsVo
	 */
	public NewsVo selectNews(NewsVo newsVo) {
		
		return newsDao.selectNews(newsVo);
	}

}
