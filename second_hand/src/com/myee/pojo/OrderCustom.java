package com.myee.pojo;

public class OrderCustom extends Order {
	private String userid;//用户id
	private String goods;//商品名
	private String goodsdescription;//商品简介
	private float price;//价格
	private String goodspic;//商品图片名
	private String acondition;//成色
	private String priceunit;//价格单位
	private String location;//发货地
	private String shelftime;//上架时间
	private String goodstype;//商品类型
	private String state;//商品售出状态
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getGoods() {
		return goods;
	}
	public void setGoods(String goods) {
		this.goods = goods;
	}
	public String getGoodsdescription() {
		return goodsdescription;
	}
	public void setGoodsdescription(String goodsdescription) {
		this.goodsdescription = goodsdescription;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getGoodspic() {
		return goodspic;
	}
	public void setGoodspic(String goodspic) {
		this.goodspic = goodspic;
	}
	public String getAcondition() {
		return acondition;
	}
	public void setAcondition(String acondition) {
		this.acondition = acondition;
	}
	public String getPriceunit() {
		return priceunit;
	}
	public void setPriceunit(String priceunit) {
		this.priceunit = priceunit;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getShelftime() {
		return shelftime;
	}
	public void setShelftime(String shelftime) {
		this.shelftime = shelftime;
	}
	public String getGoodstype() {
		return goodstype;
	}
	public void setGoodstype(String goodstype) {
		this.goodstype = goodstype;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
