package com.cafe24.shoppingmall.dto;

public class PageInfo {
	
	private Integer totalCount;
	private Integer start;
	
	private Integer totalPageCount;
	private Integer page;
	private Integer nextPage;
	private Integer prevPage;
	
	private Integer display;
	private Integer pageNum;
	
	private String kwd;
	
	public PageInfo() {
		this.display = 10;
		this.pageNum = 5;
		this.start = 0;
		setPage(1);
		this.kwd = "";
	}
	
	public Integer getTotalCount() {
		return totalCount;
	}
	
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
		this.totalPageCount = (totalCount-1) / display + 1;
	}
	
	public Integer getTotalPageCount() {
		return totalPageCount;
	}
	
	public Integer getPage() {
		return page;
	}
	
	public void setPage(Integer page) {
		this.page = page < 1 ? 1 : page;
		this.start = (this.page - 1) * this.display;
		
		this.prevPage = ( this.page / (this.pageNum + 1) ) * this.pageNum;
		this.nextPage = this.prevPage + this.pageNum + 1;
	}
	
	public Integer getNextPage() {
		return nextPage;
	}
	
	public Integer getPrevPage() {
		return prevPage;
	}
	
	public Integer getDisplay() {
		return display;
	}

	public Integer getStart() {
		return start;
	}

	public String getKwd() {
		return kwd;
	}

	public void setDisplay(Integer display) {
		this.display = display;
		setPage(this.page);
	}

	public void setKwd(String kwd) {
		this.kwd = kwd;
	}

	@Override
	public String toString() {
		return "PageInfo [totalCount=" + totalCount + ", start=" + start + ", totalPageCount=" + totalPageCount
				+ ", page=" + page + ", nextPage=" + nextPage + ", prevPage=" + prevPage + ", display=" + display
				+ ", pageNum=" + pageNum + ", kwd=" + kwd + "]";
	}

	
}
