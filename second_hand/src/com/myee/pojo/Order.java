package com.myee.pojo;

public class Order {
private String ID;//订单id
private String productid;//商品id(不重复)
private String username;//下单用户名(用户名不重复)
private String createtime;//订单生成时间
private String address;//收货地址
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
