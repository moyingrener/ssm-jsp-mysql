package com.myee.pojo;


import javax.validation.constraints.NotEmpty;

public class User {
	private String ID;//用户id
	@NotEmpty(message="{error.userName.NoEmpty}")
	private String username;//用户名
	@NotEmpty(message="{error.password.NoNull}")
	private String password;//密码
	private String post;//职位
	private String email;//邮箱
	private String phone;//手机
	private String registertime;//注册时间
	private String credit;//信用度
	private String turnover;//成交量
	private String complaint;//投诉量
	private String address;//收货地址
	private String paypassword;//支付密码
	public String getPaypassword() {
		return paypassword;
	}
	public void setPaypassword(String paypassword) {
		this.paypassword = paypassword;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRegistertime() {
		return registertime;
	}
	public void setRegistertime(String registertime) {
		this.registertime = registertime;
	}
	public String getCredit() {
		return credit;
	}
	public void setCredit(String credit) {
		this.credit = credit;
	}
	public String getTurnover() {
		return turnover;
	}
	public void setTurnover(String turnover) {
		this.turnover = turnover;
	}
	public String getComplaint() {
		return complaint;
	}
	public void setComplaint(String complaint) {
		this.complaint = complaint;
	}
}
