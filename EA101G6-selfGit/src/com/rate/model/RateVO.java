package com.rate.model;

import java.sql.Date;
import java.sql.Timestamp;

public class RateVO implements java.io.Serializable{
	private String rateno;
	private String rmno;
	private String ratingmbrno;
	private String ratedmbrno;
	private String detail;
	private Integer score;
	private Timestamp ratetime;
	
	public String getRateno() {
		return rateno;
	}
	public void setRateno(String rateno) {
		this.rateno = rateno;
	}
	public String getRmno() {
		return rmno;
	}
	public void setRmno(String rmno) {
		this.rmno = rmno;
	}
	public String getRatingmbrno() {
		return ratingmbrno;
	}
	public void setRatingmbrno(String ratingmbrno) {
		this.ratingmbrno = ratingmbrno;
	}
	public String getRatedmbrno() {
		return ratedmbrno;
	}
	public void setRatedmbrno(String ratedmbrno) {
		this.ratedmbrno = ratedmbrno;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Timestamp getRatetime() {
		return ratetime;
	}
	public void setRatetime(Timestamp ratetime) {
		this.ratetime = ratetime;
	}
	
	
}
