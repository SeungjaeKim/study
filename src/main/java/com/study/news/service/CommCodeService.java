package com.study.news.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.news.dao.CommCodeDao;
import com.study.news.domain.CommCodeVo;

/**
 * 공통코드 서비스
 */
@Service
public class CommCodeService {
	
	@Autowired
	private CommCodeDao CommCodeDao;
	
	/**
	 * 공통코드 목록 조회
	 * @return
	 */
	public List<CommCodeVo> selectCommCodeList(CommCodeVo commCodeVo) {
		
		return CommCodeDao.selectCommCodeList(commCodeVo);
	}

}
