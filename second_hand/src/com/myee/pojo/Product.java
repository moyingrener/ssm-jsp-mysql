package com.myee.pojo;


public class Product {
	private String ID;//��Ʒid(���ظ�)
	private String userid;//�û�id(���ظ�)
	private String goods;//��Ʒ��
	private String goodsdescription;//��Ʒ���
	private float price;//�۸�
	private String goodspic;//��ƷͼƬ��
	private String acondition;//��ɫ
	private String priceunit;//�۸�λ
	private String location;//������
	private String shelftime;//�ϼ�ʱ��
	private String goodstype;//��Ʒ����
	private String state;//��Ʒ�۳�״̬
	private Integer from;//��ʼ��ҳ��
	private Integer pagesize;//ÿ�η�ҳ������
	public Integer getFrom() {
		return from;
	}
	public void setFrom(Integer from) {
		this.from = from;
	}
	public Integer getPagesize() {
		return pagesize;
	}
	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
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
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPriceunit() {
		return priceunit;
	}
	public void setPriceunit(String priceunit) {
		this.priceunit = priceunit;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getGoodstype() {
		return goodstype;
	}
	public void setGoodstype(String goodstype) {
		this.goodstype = goodstype;
	}
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
}
