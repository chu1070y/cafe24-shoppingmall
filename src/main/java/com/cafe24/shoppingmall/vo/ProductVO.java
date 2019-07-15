package com.cafe24.shoppingmall.vo;

import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class ProductVO {
	private Integer no;
	private String code;
	
	@NotEmpty
	@Length(min = 2)
	private String name;
	private String description;
	private Integer price;
	private Integer salePrice;
	private String del;
	private Integer categoryNo;
	
	private Integer productDetailNo;
	private String optionCode;
	private Integer addPrice;
	private Integer stockNum;
	private Integer stockAvail;
	private String stockUse;
	private String display;
	private String detailDel;
	
	List<ProductImgVO> productImgList;

	@Override
	public String toString() {
		return "ProductVO [no=" + no + ", code=" + code + ", name=" + name + ", description=" + description + ", price="
				+ price + ", salePrice=" + salePrice + ", del=" + del + ", categoryNo=" + categoryNo
				+ ", productDetailNo=" + productDetailNo + ", optionCode=" + optionCode + ", addPrice=" + addPrice
				+ ", stockNum=" + stockNum + ", stockAvail=" + stockAvail + ", stockUse=" + stockUse + ", display="
				+ display + ", detailDel=" + detailDel + ", productImgList=" + productImgList + "]";
	}

	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

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

	public String getDel() {
		return del;
	}

	public void setDel(String del) {
		this.del = del;
	}

	public Integer getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(Integer categoryNo) {
		this.categoryNo = categoryNo;
	}

	public Integer getProductDetailNo() {
		return productDetailNo;
	}

	public void setProductDetailNo(Integer productDetailNo) {
		this.productDetailNo = productDetailNo;
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

	public String getDetailDel() {
		return detailDel;
	}

	public void setDetailDel(String detailDel) {
		this.detailDel = detailDel;
	}

	public List<ProductImgVO> getProductImgList() {
		return productImgList;
	}

	public void setProductImgList(List<ProductImgVO> productImgList) {
		this.productImgList = productImgList;
	}
	
}
