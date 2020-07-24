package com.shopad.model;

import java.sql.Date;

public class ShopadVO implements java.io.Serializable{
	private String shopadno;
	private String shopno;
	private String shopadtt;
	private Date startt;
	private Date stopt;
	private Integer status;
	
	public String getShopadno() {
		return shopadno;
	}
	public void setShopadno(String shopadno) {
		this.shopadno = shopadno;
	}
	public String getShopno() {
		return shopno;
	}
	public void setShopno(String shopno) {
		this.shopno = shopno;
	}
	public String getShopadtt() {
		return shopadtt;
	}
	public void setShopadtt(String shopadtt) {
		this.shopadtt = shopadtt;
	}
	public Date getStartt() {
		return startt;
	}
	public void setStartt(Date startt) {
		this.startt = startt;
	}
	public Date getStopt() {
		return stopt;
	}
	public void setStopt(Date stopt) {
		this.stopt = stopt;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
