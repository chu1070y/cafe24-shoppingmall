package com.cafe24.shoppingmall.vo;

public class CartVO {
	
	private Integer no;
	private Integer member_no;
	private Integer product_detail_no;
	private Integer nomember_no;
	private Integer quantity;
	
	private ProductDetailCartVO productDetailInfo;

	@Override
	public String toString() {
		return "CartVO [no=" + no + ", member_no=" + member_no + ", product_detail_no=" + product_detail_no
				+ ", nomember_no=" + nomember_no + ", quantity=" + quantity + ", productDetailInfo=" + productDetailInfo
				+ "]";
	}

	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	public Integer getMember_no() {
		return member_no;
	}

	public void setMember_no(Integer member_no) {
		this.member_no = member_no;
	}

	public Integer getProduct_detail_no() {
		return product_detail_no;
	}

	public void setProduct_detail_no(Integer product_detail_no) {
		this.product_detail_no = product_detail_no;
	}

	public Integer getNomember_no() {
		return nomember_no;
	}

	public void setNomember_no(Integer nomember_no) {
		this.nomember_no = nomember_no;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public ProductDetailCartVO getProductDetailInfo() {
		return productDetailInfo;
	}

	public void setProductDetailInfo(ProductDetailCartVO productDetailInfo) {
		this.productDetailInfo = productDetailInfo;
	}




}
