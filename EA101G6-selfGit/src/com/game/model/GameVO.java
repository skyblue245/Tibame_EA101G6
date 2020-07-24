package com.game.model;

public class GameVO implements java.io.Serializable{
	private String gmno;
	private String gmname;
	private byte[] gmimg;
	public byte[] getGmimg() {
		return gmimg;
	}
	public void setGmimg(byte[] gmimg) {
		this.gmimg = gmimg;
	}
	public String getGmno() {
		return gmno;
	}
	public void setGmno(String gmno) {
		this.gmno = gmno;
	}
	public String getGmname() {
		return gmname;
	}
	public void setGmname(String gmname) {
		this.gmname = gmname;
	}
}
