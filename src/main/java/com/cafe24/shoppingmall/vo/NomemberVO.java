package com.cafe24.shoppingmall.vo;

public class NomemberVO {
	private Integer no;
	private String session_id;
	private String regdate;
	
	@Override
	public String toString() {
		return "NomemberVO [no=" + no + ", session_id=" + session_id + ", regdate=" + regdate + "]";
	}
	public Integer getNo() {
		return no;
	}
	public void setNo(Integer no) {
		this.no = no;
	}
	public String getSession_id() {
		return session_id;
	}
	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
	
}
