package com.study.news.domain;

import lombok.Getter;
import lombok.Setter;

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

	public Long getRssSeq() {
		return rssSeq;
	}

	public void setRssSeq(Long rssSeq) {
		this.rssSeq = rssSeq;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
    
}
