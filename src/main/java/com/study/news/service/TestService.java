package com.study.news.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.news.dao.TestDao;

@Service
public class TestService {
	
	@Autowired
	private TestDao testDao;
	
	public String getNow() {
		return testDao.getNow();
	}

}
