package com.authority.model;

import java.io.Serializable;

public class AuthorityVO implements Serializable{
	
	private String empno;
	private String ftno;
	
	public AuthorityVO() {
		
	}
	
	public AuthorityVO(String empno, String ftno) {
		this.empno = empno;
		this.ftno = ftno;
	}
	
	public String getEmpno() {
		return empno;
	}
	public void setEmpno(String empno) {
		this.empno = empno;
	}
	public String getFtno() {
		return ftno;
	}
	public void setFtno(String ftno) {
		this.ftno = ftno;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ftno == null) ? 0 : ftno.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AuthorityVO other = (AuthorityVO) obj;
		if (ftno == null) {
			if (other.ftno != null)
				return false;
		} else if (!ftno.equals(other.ftno))
			return false;
		return true;
	}

}
