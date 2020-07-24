package com.news.model;

import java.sql.Date;

public class NewsVO implements java.io.Serializable{
	private String newsno;
	private String newstt;
	private String detail;
	private Date pdate;
	
	public Date getPdate() {
		return pdate;
	}
	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}
	public String getNewsno() {
		return newsno;
	}
	public void setNewsno(String newsno) {
		this.newsno = newsno;
	}
	public String getNewstt() {
		return newstt;
	}
	public void setNewstt(String newstt) {
		this.newstt = newstt;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	
}