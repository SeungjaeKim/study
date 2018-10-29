package com.study.news.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.study.news.domain.CommCodeVo;

@Repository
public class CommCodeDao {
	
    @Autowired
	private SqlSession sqlSession;
	  
	/**
	 * 공통코드 목록 조회
	 * @return
	 */
	public List<CommCodeVo> selectCommCodeList(CommCodeVo commCodeVo) {
		
		return this.sqlSession.selectList("CommCode.selectCommCodeList", commCodeVo);
	}
	
}
