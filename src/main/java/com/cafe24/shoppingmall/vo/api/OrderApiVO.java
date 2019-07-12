package com.cafe24.shoppingmall.vo.api;

import java.util.List;
import java.util.Map;

public class OrderApiVO {
	
	private Integer memberNo;
	private String orderName;
	private String orderAddr;
	private String orderTelHome;
	private String orderTelPhone;
	private String orderEmail;
	private String receiverName;
	private String receiverTelHome;
	private String receiverTelPhone;
	private String deliverAddr;
	private String deliverMsg;
	private Integer deliverCost;
	private Integer point;
	private Integer payment;
	private String payMethod;
	private String pw;
	
	private List<Map<String, Object>> ordersDetail;

	@Override
	public String toString() {
		return "OrderApiVO [memberNo=" + memberNo + ", orderName=" + orderName + ", orderAddr=" + orderAddr
				+ ", orderTelHome=" + orderTelHome + ", orderTelPhone=" + orderTelPhone + ", orderEmail=" + orderEmail
				+ ", receiverName=" + receiverName + ", receiverTelHome=" + receiverTelHome + ", receiverTelPhone="
				+ receiverTelPhone + ", deliverAddr=" + deliverAddr + ", deliverMsg=" + deliverMsg + ", deliverCost="
				+ deliverCost + ", point=" + point + ", payment=" + payment + ", payMethod=" + payMethod + ", pw=" + pw
				+ ", ordersDetail=" + ordersDetail + "]";
	}

	public Integer getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(Integer memberNo) {
		this.memberNo = memberNo;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getOrderAddr() {
		return orderAddr;
	}

	public void setOrderAddr(String orderAddr) {
		this.orderAddr = orderAddr;
	}

	public String getOrderTelHome() {
		return orderTelHome;
	}

	public void setOrderTelHome(String orderTelHome) {
		this.orderTelHome = orderTelHome;
	}

	public String getOrderTelPhone() {
		return orderTelPhone;
	}

	public void setOrderTelPhone(String orderTelPhone) {
		this.orderTelPhone = orderTelPhone;
	}

	public String getOrderEmail() {
		return orderEmail;
	}

	public void setOrderEmail(String orderEmail) {
		this.orderEmail = orderEmail;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverTelHome() {
		return receiverTelHome;
	}

	public void setReceiverTelHome(String receiverTelHome) {
		this.receiverTelHome = receiverTelHome;
	}

	public String getReceiverTelPhone() {
		return receiverTelPhone;
	}

	public void setReceiverTelPhone(String receiverTelPhone) {
		this.receiverTelPhone = receiverTelPhone;
	}

	public String getDeliverAddr() {
		return deliverAddr;
	}

	public void setDeliverAddr(String deliverAddr) {
		this.deliverAddr = deliverAddr;
	}

	public String getDeliverMsg() {
		return deliverMsg;
	}

	public void setDeliverMsg(String deliverMsg) {
		this.deliverMsg = deliverMsg;
	}

	public Integer getDeliverCost() {
		return deliverCost;
	}

	public void setDeliverCost(Integer deliverCost) {
		this.deliverCost = deliverCost;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public Integer getPayment() {
		return payment;
	}

	public void setPayment(Integer payment) {
		this.payment = payment;
	}

	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public List<Map<String, Object>> getOrdersDetail() {
		return ordersDetail;
	}

	public void setOrdersDetail(List<Map<String, Object>> ordersDetail) {
		this.ordersDetail = ordersDetail;
	}
	
	

}
