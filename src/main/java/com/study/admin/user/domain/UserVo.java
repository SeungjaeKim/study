package com.study.admin.user.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserVo {

	private String id;

	private String email;

	private String name;

	/** 부모 사이트 종류 */
	private String parentSiteTy;
}
