package com.study.common.constant;

import lombok.Getter;
import lombok.Setter;

/**
 * 공통코드
 */
public class CommCode {

	/**
     * YN
	 */
	public enum YN {

		/** Y */
		Y("Y"),

		/** N */
		N("N");

		@Getter @Setter
		private String cd;

		YN(String cd) {
			this.cd = cd;
		}
	}

	/**
	 * 로그인 실패 횟수
	 */
	public enum LoginFailCount {
		/** 초기값 */
		INIT(0, "기본값"),

		/** 최대 로그인 실패 건수 */
		MAX(5, "허용실패횟수");

		private int cd;

		private String nm;

		LoginFailCount(int cd, String nm) {
			this.cd = cd;
			this.nm = nm;
		}
	}

	/**
	 * 사용자의 부모 사이트 종류
	 */
	public enum ParentSiteTy {

		/** 구글 */
		GOOGLE("G3G1", "구글"),

		/** 네이버 */
		NAVER("G3G2", "네이버");

		@Getter @Setter
		private String cd;

		@Getter @Setter
		private String nm;

		ParentSiteTy(String cd, String nm) {
			this.cd = cd;
			this.nm = nm;
		}
	}

}
