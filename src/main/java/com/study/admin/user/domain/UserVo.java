package com.study.admin.user.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserVo {

	/** 사용자 일련번호 */
	private Integer userSeq;

	/** 부모 사이트 종류 - G3 */
	private String parentSiteTy;

	/** id */
	private String id;

	/** 이메일 */
	private String email;

	/** 이름 */
	private String name;

	/** 프로필 이미지 URL */
	private String imageUrl;

	/** 사용여부YN */
	private String useYn;

	/** 로그인 실패 횟수 */
	private Integer loginFailCnt;

	/** 등록일시 */
	private String regDt;

	/** 등록자 일련번호 */
	private String regUserSeq;

	/** 수정일시 */
	private String updDt;

	/** 수정자 일련번호 */
	private String updUserSeq;

}
