package com.cafe24.shoppingmall.vo;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class UserVO {
	
	private Integer no;
	
	@NotEmpty
	@Length(min=4, max=18)
	@Pattern(regexp = "^[a-zA-Z0-9]*$")
	private String id;
	
	@Length(min=6, max=14)
	@NotEmpty
	private String pw;
	
	@NotEmpty
	@Length(min=2, max=8)
	private String name;
	private String addr = "";
	
	@Email
	@NotEmpty
	private String email;
	
	@Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$")
	private String tel_home;
	
	@NotEmpty
	@Pattern(regexp = "^01(?:0|1|0)-\\d{4}-\\d{4}$")
	private String tel_phone;
	
	private Integer point;
	private String gender;
	private String birthdate;
	private String updatedate;
	private String regdate;
	private String status;
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel_home() {
		return tel_home;
	}
	public void setTel_home(String tel_home) {
		this.tel_home = tel_home;
	}
	public String getTel_phone() {
		return tel_phone;
	}
	public void setTel_phone(String tel_phone) {
		this.tel_phone = tel_phone;
	}
	public Integer getPoint() {
		return point;
	}
	public void setPoint(Integer point) {
		this.point = point;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public String getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "UserVO [no=" + no + ", id=" + id + ", pw=" + pw + ", name=" + name + ", addr=" + addr + ", email="
				+ email + ", tel_home=" + tel_home + ", tel_phone=" + tel_phone + ", point=" + point + ", gender="
				+ gender + ", birthdate=" + birthdate + ", updatedate=" + updatedate + ", regdate=" + regdate
				+ ", status=" + status + "]";
	}
	
}