package com.cafe24.shoppingmall.vo;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

public class AdminVO {
	private Integer no;
	@NotBlank
	@Pattern(regexp = "^[a-zA-Z0-9]{4,18}$")
	private String id;
	@NotBlank
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$")
	private String pw;
	private String shop_name;
	private String regdate;
	private String updatedate;

	@Override
	public String toString() {
		return "AdminVO [no=" + no + ", id=" + id + ", pw=" + pw + ", shop_name=" + shop_name + ", regdate=" + regdate
				+ ", updatedate=" + updatedate + "]";
	}

	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getShop_name() {
		return shop_name;
	}

	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}

}
