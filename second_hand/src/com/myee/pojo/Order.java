package com.myee.pojo;

public class Order {
private String ID;//����id
private String productid;//��Ʒid(���ظ�)
private String username;//�µ��û���(�û������ظ�)
private String createtime;//��������ʱ��
private String address;//�ջ���ַ
public String getID() {
	return ID;
}
public void setID(String iD) {
	ID = iD;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getProductid() {
	return productid;
}
public void setProductid(String productid) {
	this.productid = productid;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getCreatetime() {
	return createtime;
}
public void setCreatetime(String createtime) {
	this.createtime = createtime;
}

}
