package com.study.admin.news.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.admin.news.dao.NewsAdmDao;
import com.study.admin.news.domain.NewsVo;

@Service
public class NewsAdmService {

	@Autowired
	private NewsAdmDao newsAdmDao;

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
		existNewsVo = newsAdmDao.selectNews(newsVo);

		//뉴스 등록
		if (existNewsVo == null) {
			newsAdmDao.insertNews(newsVo);
		}
		//뉴스 수정
		else if (0 < new Date(newsVo.getPubDt()).compareTo(new Date(existNewsVo.getPubDt()))) {
			newsAdmDao.updateNews(newsVo);
		}
	}

	/**
	 * 뉴스 조회
	 * @return newsVo
	 */
	public NewsVo selectNews(NewsVo newsVo) {

		return newsAdmDao.selectNews(newsVo);
	}

}
