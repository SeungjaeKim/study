package com.study.admin.news.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsVo {

	/**
	 * 뉴스 아이디
	 */
	private Long newsId;
	
	/**
	 * 언론사 코드(G1)
	 */
	private String compCd;
	
	/**
	 * 뉴스 분류 코드(G2)
	 */
	private String clCd;
	
	/**
	 * 뉴스 제목
	 */
	private String newsTitle;
	
	/**
	 * 뉴스 내용
	 */
	private String newsContent;
	
	/**
	 * 뉴스 주소
	 */
	private String newsUrl;
	
	/**
	 * 공개일시
	 */
	private String pubDt;
	
	/**
	 * 등록일시
	 */
	private String regDt;
	
	/**
	 * 수정일시
	 */
	private String updDt;
	
}
