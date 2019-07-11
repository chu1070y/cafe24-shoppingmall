package com.cafe24.shoppingmall.vo.api;

import java.util.List;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class ProductApiVO {
	@Length(min = 2)
	private String name;
	private String description;
	@NotEmpty
	private Integer price;
	private Integer salePrice;
	
	private String optionCode;
	private Integer addPrice;
	private Integer stockNum;
	private Integer stockAvail;
	
	@Pattern(regexp = "^[0-1]$")
	private String stockUse;
	@Pattern(regexp = "^[0-1]$")
	private String display;
	
	List<ProductImgApiVO> productImgList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Integer salePrice) {
		this.salePrice = salePrice;
	}

	public String getOptionCode() {
		return optionCode;
	}

	public void setOptionCode(String optionCode) {
		this.optionCode = optionCode;
	}

	public Integer getAddPrice() {
		return addPrice;
	}

	public void setAddPrice(Integer addPrice) {
		this.addPrice = addPrice;
	}

	public Integer getStockNum() {
		return stockNum;
	}

	public void setStockNum(Integer stockNum) {
		this.stockNum = stockNum;
	}

	public Integer getStockAvail() {
		return stockAvail;
	}

	public void setStockAvail(Integer stockAvail) {
		this.stockAvail = stockAvail;
	}

	public String getStockUse() {
		return stockUse;
	}

	public void setStockUse(String stockUse) {
		this.stockUse = stockUse;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public List<ProductImgApiVO> getProductImgList() {
		return productImgList;
	}

	public void setProductImgList(List<ProductImgApiVO> productImgList) {
		this.productImgList = productImgList;
	}

	@Override
	public String toString() {
		return "ProductApiVO [name=" + name + ", description=" + description + ", price=" + price + ", salePrice="
				+ salePrice + ", optionCode=" + optionCode + ", addPrice=" + addPrice + ", stockNum=" + stockNum
				+ ", stockAvail=" + stockAvail + ", stockUse=" + stockUse + ", display=" + display + ", productImgList="
				+ productImgList + "]";
	}

	

	
}
