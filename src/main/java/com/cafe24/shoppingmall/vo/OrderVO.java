package com.cafe24.shoppingmall.vo;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class OrderVO {
	
	private Integer no;
	private Integer member_no;
	@NotBlank
	private String order_code;
	@NotBlank
	private String order_name;
	@NotBlank
	private String order_addr;
	private String order_tel_home;
	@NotBlank
	@Pattern(regexp = "^01(?:0|1|0)-\\d{4}-\\d{4}$")
	private String order_tel_phone;
	@Email
	private String order_email;
	@NotBlank
	private String receiver_name;
	private String receiver_tel_home;
	@NotBlank
	@Pattern(regexp = "^01(?:0|1|0)-\\d{4}-\\d{4}$")
	private String receiver_tel_phone;
	@NotBlank
	private String deliver_addr;
	private String deliver_msg;
	private Integer deliver_cost = 0;
	private Integer point = 0;
	@NotNull
	private Integer payment;
	@NotBlank
	private String pay_method;
	private String pay_token;
	@NotBlank
	private String status;
	private String approved_at;
	@NotBlank
	private String pw;
	
	@Valid
	@NotNull
	private List<OrderDetailVO> orderDetail;

	@Override
	public String toString() {
		return "OrderVO [no=" + no + ", member_no=" + member_no + ", order_code=" + order_code + ", order_name="
				+ order_name + ", order_addr=" + order_addr + ", order_tel_home=" + order_tel_home
				+ ", order_tel_phone=" + order_tel_phone + ", order_email=" + order_email + ", receiver_name="
				+ receiver_name + ", receiver_tel_home=" + receiver_tel_home + ", receiver_tel_phone="
				+ receiver_tel_phone + ", deliver_addr=" + deliver_addr + ", deliver_msg=" + deliver_msg
				+ ", deliver_cost=" + deliver_cost + ", point=" + point + ", payment=" + payment + ", pay_method="
				+ pay_method + ", pay_token=" + pay_token + ", status=" + status + ", approved_at=" + approved_at
				+ ", pw=" + pw + ", orderDetail=" + orderDetail + "]";
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

	public String getOrder_code() {
		return order_code;
	}

	public void setOrder_code(String order_code) {
		this.order_code = order_code;
	}

	public String getOrder_name() {
		return order_name;
	}

	public void setOrder_name(String order_name) {
		this.order_name = order_name;
	}

	public String getOrder_addr() {
		return order_addr;
	}

	public void setOrder_addr(String order_addr) {
		this.order_addr = order_addr;
	}

	public String getOrder_tel_home() {
		return order_tel_home;
	}

	public void setOrder_tel_home(String order_tel_home) {
		this.order_tel_home = order_tel_home;
	}

	public String getOrder_tel_phone() {
		return order_tel_phone;
	}

	public void setOrder_tel_phone(String order_tel_phone) {
		this.order_tel_phone = order_tel_phone;
	}

	public String getOrder_email() {
		return order_email;
	}

	public void setOrder_email(String order_email) {
		this.order_email = order_email;
	}

	public String getReceiver_name() {
		return receiver_name;
	}

	public void setReceiver_name(String receiver_name) {
		this.receiver_name = receiver_name;
	}

	public String getReceiver_tel_home() {
		return receiver_tel_home;
	}

	public void setReceiver_tel_home(String receiver_tel_home) {
		this.receiver_tel_home = receiver_tel_home;
	}

	public String getReceiver_tel_phone() {
		return receiver_tel_phone;
	}

	public void setReceiver_tel_phone(String receiver_tel_phone) {
		this.receiver_tel_phone = receiver_tel_phone;
	}

	public String getDeliver_addr() {
		return deliver_addr;
	}

	public void setDeliver_addr(String deliver_addr) {
		this.deliver_addr = deliver_addr;
	}

	public String getDeliver_msg() {
		return deliver_msg;
	}

	public void setDeliver_msg(String deliver_msg) {
		this.deliver_msg = deliver_msg;
	}

	public Integer getDeliver_cost() {
		return deliver_cost;
	}

	public void setDeliver_cost(Integer deliver_cost) {
		this.deliver_cost = deliver_cost;
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

	public String getPay_method() {
		return pay_method;
	}

	public void setPay_method(String pay_method) {
		this.pay_method = pay_method;
	}

	public String getPay_token() {
		return pay_token;
	}

	public void setPay_token(String pay_token) {
		this.pay_token = pay_token;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getApproved_at() {
		return approved_at;
	}

	public void setApproved_at(String approved_at) {
		this.approved_at = approved_at;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public List<OrderDetailVO> getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(List<OrderDetailVO> orderDetail) {
		this.orderDetail = orderDetail;
	}



	
	
}
