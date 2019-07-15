package com.cafe24.shoppingmall.vo;

public class OrderDetailVO {
	private Integer no;
	private Integer order_no;
	private Integer price;
	private Integer quantity;
	
	@Override
	public String toString() {
		return "OrderDetailVO [no=" + no + ", order_no=" + order_no + ", price=" + price + ", quantity=" + quantity
				+ "]";
	}
	public Integer getNo() {
		return no;
	}
	public void setNo(Integer no) {
		this.no = no;
	}
	public Integer getOrder_no() {
		return order_no;
	}
	public void setOrder_no(Integer order_no) {
		this.order_no = order_no;
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
	
	
}
