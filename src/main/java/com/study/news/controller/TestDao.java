package com.study.news.controller;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class TestDao {
	
	  @Autowired
	  private SqlSession sqlSession;
	  
	  public String getNow() {
		    return this.sqlSession.selectOne("com.study.news.controller.TestRepository.getNow");
	  }
}
