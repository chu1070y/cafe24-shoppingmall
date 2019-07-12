package com.cafe24.shoppingmall.vo.api;

public class OrdersDetailApiVO {
	
	private Integer productDetailNo;
	private Integer price;
	private Integer quantity;
	
	public Integer getProductDetailNo() {
		return productDetailNo;
	}
	public void setProductDetailNo(Integer productDetailNo) {
		this.productDetailNo = productDetailNo;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "OrdersDetailApiVO [productDetailNo=" + productDetailNo + ", price=" + price + ", quantity=" + quantity
				+ "]";
	}
	
	
}
