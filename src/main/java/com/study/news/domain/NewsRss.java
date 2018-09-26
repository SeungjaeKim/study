package com.study.news.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsRss {

	/**
	 * RSS 일련번호
	 */
	private Long rssSeq;

	/**
	 * RSS URL
	 */
    private String url;

    /**
     * 사용여부 YN
     */
    private String useYn;

}
