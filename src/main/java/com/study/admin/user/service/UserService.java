package com.study.admin.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.admin.user.dao.UserDao;
import com.study.admin.user.domain.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	/**
	 * 사용자 조회
	 * @return userVo
	 */
	public UserVo selectUser(UserVo userVo) {

		return userDao.selectUser(userVo);
	}

}
