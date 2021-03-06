package com.cafe24.shoppingmall.vo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class OrderDetailVO {
	@NotNull
	private Integer product_detail_no;
	private Integer order_no;
	@NotNull
	private Integer price;
	@NotNull
	@Min(1)
	private Integer quantity;

	private Integer cart_no;

	private ProductDetailCartVO orderInfo;

	@Override
	public String toString() {
		return "OrderDetailVO [product_detail_no=" + product_detail_no + ", order_no=" + order_no + ", price=" + price
				+ ", quantity=" + quantity + ", cart_no=" + cart_no + ", orderInfo=" + orderInfo + "]";
	}

	public Integer getProduct_detail_no() {
		return product_detail_no;
	}

	public void setProduct_detail_no(Integer product_detail_no) {
		this.product_detail_no = product_detail_no;
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

	public Integer getCart_no() {
		return cart_no;
	}

	public void setCart_no(Integer cart_no) {
		this.cart_no = cart_no;
	}

	public ProductDetailCartVO getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(ProductDetailCartVO orderInfo) {
		this.orderInfo = orderInfo;
	}

}
