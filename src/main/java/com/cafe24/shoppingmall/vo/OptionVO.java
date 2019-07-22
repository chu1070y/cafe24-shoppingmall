package com.cafe24.shoppingmall.vo;

import java.util.List;

public class OptionVO {

	private Integer option_no;
	private Integer product_no;
	private String name;
	private String necessary;
	
	List<OptionDetailVO> optionDetailList;

	@Override
	public String toString() {
		return "OptionVO [option_no=" + option_no + ", product_no=" + product_no + ", name=" + name + ", necessary="
				+ necessary + ", optionDetailList=" + optionDetailList + "]";
	}

	public Integer getOption_no() {
		return option_no;
	}

	public void setOption_no(Integer option_no) {
		this.option_no = option_no;
	}

	public Integer getProduct_no() {
		return product_no;
	}

	public void setProduct_no(Integer product_no) {
		this.product_no = product_no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNecessary() {
		return necessary;
	}

	public void setNecessary(String necessary) {
		this.necessary = necessary;
	}

	public List<OptionDetailVO> getOptionDetailList() {
		return optionDetailList;
	}

	public void setOptionDetailList(List<OptionDetailVO> optionDetailList) {
		this.optionDetailList = optionDetailList;
	}
	
	
	
}
