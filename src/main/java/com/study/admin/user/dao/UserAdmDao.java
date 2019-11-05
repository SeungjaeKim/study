package com.study.admin.user.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.study.admin.user.domain.UserVo;

@Repository
public class UserAdmDao {

    @Autowired
	private SqlSession sqlSession;

	/**
	 * 사용자 등록
	 * @param userVo
	 * @return
	 */
	public int insertUser(UserVo userVo) {

		return this.sqlSession.insert("UserAdm.insertUser", userVo);
	}

	/**
	 * 사용자 조회
	 * @param userVo
	 */
	public UserVo selectUser(UserVo userVo) {

		return this.sqlSession.selectOne("UserAdm.selectUser", userVo);
	}

}
