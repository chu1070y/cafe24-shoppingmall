package com.cafe24.shoppingmall.vo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class CategoryVO {
	@NotNull
	private Integer category_no;
	@NotBlank
	private String category_name;
	private Integer parent;
	
	private Integer ord = 1;
	
	private Integer product_no;

	@Override
	public String toString() {
		return "CategoryVO [category_no=" + category_no + ", category_name=" + category_name + ", parent=" + parent
				+ ", ord=" + ord + ", product_no=" + product_no + "]";
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

	public Integer getParent() {
		return parent;
	}

	public void setParent(Integer parent) {
		this.parent = parent;
	}

	public Integer getOrd() {
		return ord;
	}

	public void setOrd(Integer ord) {
		this.ord = ord;
	}

	public Integer getProduct_no() {
		return product_no;
	}

	public void setProduct_no(Integer product_no) {
		this.product_no = product_no;
	}


	
	
} 
