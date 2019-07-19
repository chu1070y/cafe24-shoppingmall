package com.cafe24.shoppingmall.vo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class CategoryVO {
	@NotNull
	private Integer category_no;
	@NotBlank
	private String category_name;
	private Integer category_high_no;
	
	@Override
	public String toString() {
		return "CategoryVO [category_no=" + category_no + ", category_name=" + category_name + ", category_high_no="
				+ category_high_no + "]";
	}
	public Integer getCategory_no() {
		return category_no;
	}
	public void setCategory_no(Integer category_no) {
		this.category_no = category_no;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public Integer getCategory_high_no() {
		return category_high_no;
	}
	public void setCategory_high_no(Integer category_high_no) {
		this.category_high_no = category_high_no;
	}
	
	
	
	
} 
