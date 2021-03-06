package com.cafe24.shoppingmall.vo;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class ProductVO {
	private Integer no;
	private String code;
	
	@NotBlank(message="상품명을 입력해주세요.")
	@Length(min = 2, message="상품명은 최소 2글자 이상이어야 합니다.")
	private String name;
	private String description;
	
	@NotNull(message="가격을 입력해주세요.")
	private Integer price;
	private Integer sale_price;
	private String show_product;
	private String del;
	
	List<ProductDetailVO> productDetailList;
	
	List<ProductImgVO> productImgList;
	
	List<CategoryVO> categoryList;
	
	List<OptionVO> optionList;

	@Override
	public String toString() {
		return "ProductVO [no=" + no + ", code=" + code + ", name=" + name + ", description=" + description + ", price="
				+ price + ", sale_price=" + sale_price + ", show_product=" + show_product + ", del=" + del
				+ ", productDetailList=" + productDetailList + ", productImgList=" + productImgList + ", categoryList="
				+ categoryList + ", optionList=" + optionList + "]";
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

	public Integer getSale_price() {
		return sale_price;
	}

	public void setSale_price(Integer sale_price) {
		this.sale_price = sale_price;
	}

	public String getShow_product() {
		return show_product;
	}

	public void setShow_product(String show_product) {
		this.show_product = show_product;
	}

	public String getDel() {
		return del;
	}

	public void setDel(String del) {
		this.del = del;
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

	public List<CategoryVO> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<CategoryVO> categoryList) {
		this.categoryList = categoryList;
	}

	public List<OptionVO> getOptionList() {
		return optionList;
	}

	public void setOptionList(List<OptionVO> optionList) {
		this.optionList = optionList;
	}


}
