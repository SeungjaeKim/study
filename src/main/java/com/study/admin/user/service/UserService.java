package com.study.admin.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.news.dao.NewsDao;
import com.study.news.domain.NewsVo;

@Service
public class UserService {
	
	@Autowired
	private NewsDao newsDao;
	
	/**
	 * 뉴스 조회
	 * @return newsVo
	 */
	public NewsVo selectNews(NewsVo newsVo) {
		
		return newsDao.selectNews(newsVo);
	}

}
