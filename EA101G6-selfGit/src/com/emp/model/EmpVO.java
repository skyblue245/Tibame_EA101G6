package com.emp.model;

import java.io.Serializable;
import java.util.Arrays;

public class EmpVO implements Serializable{
	private byte[] pic;
	private String empno;
	private String empname;
	private String emppwd;
	private String mail;
	private String sex;
	private Integer empstatus;
	
	//JavaBeans
	public EmpVO() {
		
	}
	
	public EmpVO(byte[] pic, String empno, String empname, String emppwd, String mail, String sex, Integer empstatus) {
		this.pic = pic;
		this.empno = empno;
		this.empname = empname;
		this.emppwd = emppwd;
		this.mail = mail;
		this.sex = sex;
		this.empstatus = empstatus;
	}
	
	public byte[] getPic() {
		return pic;
	}
	public void setPic(byte[] pic) {
		this.pic = pic;
	}
	public String getEmpno() {
		return empno;
	}
	public void setEmpno(String empno) {
		this.empno = empno;
	}
	public String getEmpname() {
		return empname;
	}
	public void setEmpname(String empname) {
		this.empname = empname;
	}
	public String getEmppwd() {
		return emppwd;
	}
	public void setEmppwd(String emppwd) {
		this.emppwd = emppwd;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Integer getEmpstatus() {
		return empstatus;
	}
	public void setEmpstatus(Integer empstatus) {
		this.empstatus = empstatus;
	}
}