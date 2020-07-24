package com.artrp.model;

public class ArtrpVO implements java.io.Serializable{
	
	private String artrpno;
	private String artno;
	private String detail;
	private String mbrno;
	private Integer status;
	public String getArtrpno() {
		return artrpno;
	}
	public void setArtrpno(String artrpno) {
		this.artrpno = artrpno;
	}
	public String getArtno() {
		return artno;
	}
	public void setArtno(String artno) {
		this.artno = artno;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getMbrno() {
		return mbrno;
	}
	public void setMbrno(String mbrno) {
		this.mbrno = mbrno;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
