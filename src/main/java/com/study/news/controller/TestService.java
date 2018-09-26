package com.study.news.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {
	
	@Autowired
	private TestDao testDao;
	
	public String getNow() {
		return testDao.getNow();
	}

}
