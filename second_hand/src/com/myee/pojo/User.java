package com.myee.pojo;


import javax.validation.constraints.NotEmpty;

public class User {
	private String ID;//�û�id
	@NotEmpty(message="{error.userName.NoEmpty}")
	private String username;//�û���
	@NotEmpty(message="{error.password.NoNull}")
	private String password;//����
	private String post;//ְλ
	private String email;//����
	private String phone;//�ֻ�
	private String registertime;//ע��ʱ��
	private String credit;//���ö�
	private String turnover;//�ɽ���
	private String complaint;//Ͷ����
	private String address;//�ջ���ַ
	private String paypassword;//֧������
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
