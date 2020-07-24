package com.shop.model;


public class ShopVO implements java.io.Serializable{
	private String shopno;
	private String shopact;
	private String shoppw;
	private String shopname;
	private String shoploc;
	private String shopcy;
	private Integer shopphone;
	private byte[] shopimg;
	private Integer status;
	
	public byte[] getShopimg() {
		return shopimg;
	}
	public void setShopimg(byte[] shopimg) {
		this.shopimg = shopimg;
	}
	public String getShopno() {
		return shopno;
	}
	public void setShopno(String shopno) {
		this.shopno = shopno;
	}
	public String getShopact() {
		return shopact;
	}
	public void setShopact(String shopact) {
		this.shopact = shopact;
	}
	public String getShoppw() {
		return shoppw;
	}
	public void setShoppw(String shoppw) {
		this.shoppw = shoppw;
	}
	public String getShopname() {
		return shopname;
	}
	public void setShopname(String shopname) {
		this.shopname = shopname;
	}
	
	public String getShoploc() {
		return shoploc;
	}
	public void setShoploc(String shoploc) {
		this.shoploc = shoploc;
	}
	public String getShopcy() {
		return shopcy;
	}
	public void setShopcy(String shopcy) {
		this.shopcy = shopcy;
	}
	public Integer getShopphone() {
		return shopphone;
	}
	public void setShopphone(Integer shopphone) {
		this.shopphone = shopphone;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}