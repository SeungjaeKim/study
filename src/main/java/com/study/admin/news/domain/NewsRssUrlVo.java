package com.study.admin.news.domain;

import com.study.admin.comm.domain.CommPagingVo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsRssUrlVo extends CommPagingVo {

	/** RSS 일련번호 */
	private Long rssSeq;

	/** 언론사 코드 - G1 */
	private String compCd;

	/** 뉴스 분야 코드 - G2 */
	private String clCd;

	/** RSS URL */
    private String rssUrl;

    /** 사용여부 YN */
    private String useYn;

    /** 마지막 작성 일시 */
    private String lastBuildDate;

    /** 등록일시 */
    private String regDt;

    /** 등록자 ID */
    private String regUserId;

    /** 수정일시 */
    private String updDt;

    /** 수정자 ID */
    private String updUserId;

}
