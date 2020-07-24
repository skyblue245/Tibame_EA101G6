package com.mallad.model;

import java.sql.Date;

public class MalladVO implements java.io.Serializable{
	private String malladno;
	private String commno;
	private String gmadtt;
	private byte[] detail;
	private Date startt;
	private Date stopt;
	
	public String getMalladno() {
		return malladno;
	}
	public void setMalladno(String malladno) {
		this.malladno = malladno;
	}
	public String getCommno() {
		return commno;
	}
	public void setCommno(String commno) {
		this.commno = commno;
	}
	public String getGmadtt() {
		return gmadtt;
	}
	public void setGmadtt(String gmadtt) {
		this.gmadtt = gmadtt;
	}
	public byte[] getDetail() {
		return detail;
	}
	public void setDetail(byte[] detail) {
		this.detail = detail;
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
	
	
}
