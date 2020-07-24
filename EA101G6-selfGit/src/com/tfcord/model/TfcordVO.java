package com.tfcord.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class TfcordVO implements Serializable{
	private String tfno;
	private String mbrno;
	private String tftype;
	private Integer price;
	private Timestamp tftime;
	private Integer tfstatus;
	
	public TfcordVO() {
		
	}
	
	public TfcordVO(String tfno, String mbrno, String tftype, Integer price, Timestamp tftime, Integer tfstatus) {
		this.tfno = tfno;
		this.mbrno = mbrno;
		this.tftype = tftype;
		this.price = price;
		this.tftime = tftime;
		this.tfstatus = tfstatus;	
	}
	
	public String getTfno() {
		return tfno;
	}
	public void setTfno(String tfno) {
		this.tfno = tfno;
	}
	public String getMbrno() {
		return mbrno;
	}
	public void setMbrno(String mbrno) {
		this.mbrno = mbrno;
	}
	public String getTftype() {
		return tftype;
	}
	public void setTftype(String tftype) {
		this.tftype = tftype;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Timestamp getTftime() {
		return tftime;
	}
	public void setTftime(Timestamp tftime) {
		this.tftime = tftime;
	}
	public Integer getTfstatus() {
		return tfstatus;
	}
	public void setTfstatus(Integer tfstatus) {
		this.tfstatus = tfstatus;
	}
}
