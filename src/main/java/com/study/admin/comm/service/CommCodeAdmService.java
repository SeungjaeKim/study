package com.study.admin.comm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.admin.comm.dao.CommCodeAdmDao;
import com.study.admin.comm.domain.CommCodeVo;

/**
 * 공통코드 서비스
 */
@Service
public class CommCodeAdmService {

	@Autowired
	private CommCodeAdmDao CommCodeAdmDao;

	/**
	 * 공통코드 목록 조회
	 * @return
	 */
	public List<CommCodeVo> selectCommCodeList(String codeGroupId) {

		CommCodeVo commCodeVo = new CommCodeVo();
		commCodeVo.setCodeGroupId(codeGroupId);
		return selectCommCodeList(commCodeVo);
	}

	/**
	 * 공통코드 목록 조회
	 * @return
	 */
	public List<CommCodeVo> selectCommCodeList(CommCodeVo commCodeVo) {

		return CommCodeAdmDao.selectCommCodeList(commCodeVo);
	}

}
