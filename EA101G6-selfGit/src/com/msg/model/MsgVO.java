package com.msg.model;
import java.sql.Date;

public class MsgVO implements java.io.Serializable{
	private String msgno;
	private String mbrno;
	private String detail;
	private String artno;
	private Integer status;
	
	public String getMsgno() {
		return msgno;
	}
	public void setMsgno(String msgno) {
		this.msgno = msgno;
	}
	public String getMbrno() {
		return mbrno;
	}
	public void setMbrno(String mbrno) {
		this.mbrno = mbrno;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getArtno() {
		return artno;
	}
	public void setArtno(String artno) {
		this.artno = artno;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	
	
	
	
}	