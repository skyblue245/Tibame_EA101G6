package com.art.model;
import java.sql.Date;

public class ArtVO implements java.io.Serializable{
	private String artno;
	private String mbrno;
	private String detail;
	private String arttt;
	private Date pdate;
	private Integer status;
	private String atno;
	
	private byte[] apic;
	
	public byte[] getApic() {
		return apic;
	}
	public void setApic(byte[] apic) {
		this.apic = apic;
	}
	public String getArtno() {
		return artno;
	}
	public void setArtno(String artno) {
		this.artno = artno;
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
	public String getArttt() {
		return arttt;
	}
	public void setArttt(String arttt) {
		this.arttt = arttt;
	}
	public Date getPdate() {
		return pdate;
	}
	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getAtno() {
		return atno;
	}
	public void setAtno(String atno) {
		this.atno = atno;
	}
	
	
	
}
