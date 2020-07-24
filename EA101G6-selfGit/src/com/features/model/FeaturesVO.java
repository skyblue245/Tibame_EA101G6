package com.features.model;

import java.io.Serializable;

public class FeaturesVO implements Serializable{
	private String ftno;
    private String ftname;
    
    public FeaturesVO() {
    	
    }
    
    public FeaturesVO(String ftno, String ftname) {
    	this.ftno = ftno;
    	this.ftname = ftname;
    }
    
    public String getFtno() {
    	return ftno;
    }
    public void setFtno(String ftno) {
    	this.ftno = ftno;
    }
    public String getFtname() {
    	return ftname;
    }
    public void setFtname(String ftname) {
    	this.ftname = ftname;
    }  
}
