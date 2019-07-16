package com.cafe24.shoppingmall.vo;

import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class ProductVO {
	private Integer no;
	private String code;
	
	@NotEmpty(message="상품명을 입력해주세요.")
	@Length(min = 2, message="상품명은 최소 2글자 이상이어야 합니다.")
	private String name;
	private String description;
	
	@NotEmpty(message="가격을 입력해주세요.")
	private Integer price;
	private Integer salePrice;
	private String del;
	private Integer categoryNo;
	
	List<ProductDetailVO> productDetailList;
	
	List<ProductImgVO> productImgList;

	@Override
	public String toString() {
		return "ProductVO [no=" + no + ", code=" + code + ", name=" + name + ", description=" + description + ", price="
				+ price + ", salePrice=" + salePrice + ", del=" + del + ", categoryNo=" + categoryNo
				+ ", productDetailList=" + productDetailList + ", productImgList=" + productImgList + "]";
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

	public List<ProductDetailVO> getProductDetailList() {
		return productDetailList;
	}

	public void setProductDetailList(List<ProductDetailVO> productDetailList) {
		this.productDetailList = productDetailList;
	}

	public List<ProductImgVO> getProductImgList() {
		return productImgList;
	}

	public void setProductImgList(List<ProductImgVO> productImgList) {
		this.productImgList = productImgList;
	}
	
	
}
