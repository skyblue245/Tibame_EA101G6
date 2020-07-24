package com.shoprp.model;

import java.sql.*;

public class ShoprpVO implements java.io.Serializable{
	private String mbrno;
	private String rmno;
	private String detail;
	private Timestamp rpdate;
	private Integer attend;
	
	public String getMbrno() {
		return mbrno;
	}
	public void setMbrno(String mbrno) {
		this.mbrno = mbrno;
	}
	public String getRmno() {
		return rmno;
	}
	public void setRmno(String rmno) {
		this.rmno = rmno;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public Timestamp getRpdate() {
		return rpdate;
	}
	public void setRpdate(Timestamp rpdate) {
		this.rpdate = rpdate;
	}
	public Integer getAttend() {
		return attend;
	}
	public void setAttend(Integer attend) {
		this.attend = attend;
	}
	
	
	

}
