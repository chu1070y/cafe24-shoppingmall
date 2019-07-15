package com.cafe24.shoppingmall.vo;

public class AdminVO {
	private Integer no;
	private String id;
	private String pw;
	private String regdate;
	private String updatedate;
	
	@Override
	public String toString() {
		return "AdminVO [no=" + no + ", id=" + id + ", pw=" + pw + ", regdate=" + regdate + ", updatedate=" + updatedate
				+ "]";
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
