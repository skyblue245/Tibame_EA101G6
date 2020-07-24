package com.gmTypeDt.model;

import java.io.Serializable;

public class GmTypeDtVO implements Serializable{

	private String typeNo;
	private String commNo;
	
	public GmTypeDtVO() {
		
	}

	public GmTypeDtVO(String typeNo, String commNo) {
		super();
		this.typeNo = typeNo;
		this.commNo = commNo;
	}
	
	

	public String getTypeNo() {
		return typeNo;
	}

	public void setTypeNo(String typeNo) {
		this.typeNo = typeNo;
	}

	public String getCommNo() {
		return commNo;
	}

	public void setCommNo(String commNo) {
		this.commNo = commNo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((commNo == null) ? 0 : commNo.hashCode());
		result = prime * result + ((typeNo == null) ? 0 : typeNo.hashCode());
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
		GmTypeDtVO other = (GmTypeDtVO) obj;
		if (commNo == null) {
			if (other.commNo != null)
				return false;
		} else if (!commNo.equals(other.commNo))
			return false;
		if (typeNo == null) {
			if (other.typeNo != null)
				return false;
		} else if (!typeNo.equals(other.typeNo))
			return false;
		return true;
	}

	
	

	


	
	
	
}
