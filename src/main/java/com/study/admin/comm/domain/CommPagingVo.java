package com.study.admin.comm.domain;

import lombok.Getter;
import lombok.Setter;

public class CommPagingVo {
	
	public CommPagingVo() {
		this.setCurrentPageNo(1);        //현재 페이지 번호
		this.setRecordCountPerPage(50);  //한 페이지에 게시되는 데이터 건수
		this.setPageSize(10);            //페이징 리스트의 사이즈
	}

	public CommPagingVo(int currentPageNo, int totalRecordCount) {
		this.setCurrentPageNo(currentPageNo);        //현재 페이지 번호
		this.setTotalRecordCount(totalRecordCount);  // 전체 데이터 건수
	}
	
	/** 
	 * 현재 페이지 번호 
	 */
	@Getter 
	@Setter
	private int currentPageNo;
	
	/**
	 * 한 페이지에 게시되는 데이터 건수
	 */
	@Getter
	@Setter
	private int recordCountPerPage;
	
	/**
	 * 페이징 리스트의 사이즈
	 */
	@Getter
	@Setter
	private int pageSize;
	
	/**
	 * 전데 데이터 건수
	 */
	@Getter
	@Setter
	private int totalRecordCount;

	private int totalPageCount;
	
	private int firstPageNoOnPageList;
	
	private int lastPageNoOnPageList;
	
	private int firstRecordIndex;
	
	private int lastRecordIndex;

	public int getTotalPageCount() {
		totalPageCount = ((getTotalRecordCount() - 1) / getRecordCountPerPage()) + 1;
		return totalPageCount;
	}

	public int getFirstPageNo() {
		return 1;
	}

	public int getLastPageNo() {
		return getTotalPageCount();
	}

	public int getFirstPageNoOnPageList() {
		firstPageNoOnPageList = ((getCurrentPageNo() - 1) / getPageSize()) * getPageSize() + 1;
		return firstPageNoOnPageList;
	}

	public int getLastPageNoOnPageList() {
		lastPageNoOnPageList = getFirstPageNoOnPageList() + getPageSize() - 1;
		if (lastPageNoOnPageList > getTotalPageCount()) {
			lastPageNoOnPageList = getTotalPageCount();
		}
		return lastPageNoOnPageList;
	}

	public int getFirstRecordIndex() {
		firstRecordIndex = (getCurrentPageNo() - 1) * getRecordCountPerPage();
		return firstRecordIndex;
	}

	public int getLastRecordIndex() {
		lastRecordIndex = getCurrentPageNo() * getRecordCountPerPage();
		return lastRecordIndex;
	}
}
