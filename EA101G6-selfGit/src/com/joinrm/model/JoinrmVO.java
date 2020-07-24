package com.joinrm.model;

public class JoinrmVO implements java.io.Serializable{
	private String mbrno;
	private String rmno;
	private Integer ratereport;
	private Integer shopreport;
	
	public Integer getRatereport() {
		return ratereport;
	}
	public void setRatereport(Integer ratereport) {
		this.ratereport = ratereport;
	}
	public Integer getShopreport() {
		return shopreport;
	}
	public void setShopreport(Integer shopreport) {
		this.shopreport = shopreport;
	}
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
	

}
