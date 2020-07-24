package com.rminfo.model;
import java.sql.Timestamp;

public class RminfoVO implements java.io.Serializable{
	private String rmno;
	private String shopno;
	private Timestamp createtime;
	private Timestamp cutoff;
	private Integer status;
	private String naming;
	private Integer uplimit;
	private Integer lowlimit;
	private Timestamp starttime;
	private Timestamp endtime;
	private String mbrno;
	private String game;
	private String remarks;
	private Integer restriction;
	private Integer report;
	
	public String getRmno() {
		return rmno;
	}
	public void setRmno(String rmno) {
		this.rmno = rmno;
	}
	public String getShopno() {
		return shopno;
	}
	public void setShopno(String shopno) {
		this.shopno = shopno;
	}
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	public Timestamp getCutoff() {
		return cutoff;
	}
	public void setCutoff(Timestamp timestamp) {
		this.cutoff = timestamp;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getNaming() {
		return naming;
	}
	public void setNaming(String naming) {
		this.naming = naming;
	}
	public Integer getUplimit() {
		return uplimit;
	}
	public void setUplimit(Integer uplimit) {
		this.uplimit = uplimit;
	}
	public Integer getLowlimit() {
		return lowlimit;
	}
	public void setLowlimit(Integer lowlimit) {
		this.lowlimit = lowlimit;
	}
	public Timestamp getStarttime() {
		return starttime;
	}
	public void setStarttime(Timestamp timestamp) {
		this.starttime = timestamp;
	}
	public Timestamp getEndtime() {
		return endtime;
	}
	public void setEndtime(Timestamp timestamp) {
		this.endtime = timestamp;
	}
	public String getMbrno() {
		return mbrno;
	}
	public void setMbrno(String mbrno) {
		this.mbrno = mbrno;
	}
	public String getGame() {
		return game;
	}
	public void setGame(String game) {
		this.game = game;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Integer getRestriction() {
		return restriction;
	}
	public void setRestriction(Integer restriction) {
		this.restriction = restriction;
	}
	public Integer getReport() {
		return report;
	}
	public void setReport(Integer report) {
		this.report = report;
	}
	
	
	
	

}
