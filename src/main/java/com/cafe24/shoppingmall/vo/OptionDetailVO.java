package com.cafe24.shoppingmall.vo;

public class OptionDetailVO {
	private Integer option_detail_no;
	private Integer option_no;
	private String detail_name;
	
	@Override
	public String toString() {
		return "OptionDetailVO [option_detail_no=" + option_detail_no + ", option_no=" + option_no + ", detail_name="
				+ detail_name + "]";
	}
	public Integer getOption_detail_no() {
		return option_detail_no;
	}
	public void setOption_detail_no(Integer option_detail_no) {
		this.option_detail_no = option_detail_no;
	}
	public Integer getOption_no() {
		return option_no;
	}
	public void setOption_no(Integer option_no) {
		this.option_no = option_no;
	}
	public String getDetail_name() {
		return detail_name;
	}
	public void setDetail_name(String detail_name) {
		this.detail_name = detail_name;
	}
	
	
}
