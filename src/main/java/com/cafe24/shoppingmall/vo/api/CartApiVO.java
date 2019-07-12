package com.cafe24.shoppingmall.vo.api;

public class CartApiVO {
	private Integer memberNo;
	private Integer nomemberNo;
	private Integer productDetailNo;
	private Integer quantity = 1;
	
	public Integer getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(Integer memberNo) {
		this.memberNo = memberNo;
	}
	public Integer getNomemberNo() {
		return nomemberNo;
	}
	public void setNomemberNo(Integer nomemberNo) {
		this.nomemberNo = nomemberNo;
	}
	public Integer getProductDetailNo() {
		return productDetailNo;
	}
	public void setProductDetailNo(Integer productDetailNo) {
		this.productDetailNo = productDetailNo;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "CartApiVO [memberNo=" + memberNo + ", nomemberNo=" + nomemberNo + ", productDetailNo=" + productDetailNo
				+ ", quantity=" + quantity + "]";
	}
	
	
}
