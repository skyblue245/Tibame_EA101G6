package com.msgrp.model;
import java.sql.Date;

public class MsgrpVO implements java.io.Serializable{
	private String msgrpno;
	private String msgno;
	private String detail;
	private String mbrno;
	private Integer status;
	
	public String getMsgrpno() {
		return msgrpno;
	}
	public void setMsgrpno(String msgrpno) {
		this.msgrpno = msgrpno;
	}
	public String getMsgno() {
		return msgno;
	}
	public void setMsgno(String msgno) {
		this.msgno = msgno;
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