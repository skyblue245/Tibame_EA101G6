package com.gmType.model;

import java.io.Serializable;

public class GmTypeVO implements Serializable {
	
	private String typeNo;
	private String typeName;
	 
	
	
	public GmTypeVO() {
		
	};
	
	 
	public GmTypeVO(String typeNo, String typeName) {
		super();
		this.typeNo = typeNo;
		this.typeName = typeName;
	}
	
	public String getTypeNo() {
		return typeNo;
	}
	public void setTypeNo(String typeNo) {
		this.typeNo = typeNo;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((typeName == null) ? 0 : typeName.hashCode());
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
		GmTypeVO other = (GmTypeVO) obj;
		if (typeName == null) {
			if (other.typeName != null)
				return false;
		} else if (!typeName.equals(other.typeName))
			return false;
		if (typeNo == null) {
			if (other.typeNo != null)
				return false;
		} else if (!typeNo.equals(other.typeNo))
			return false;
		return true;
	}
	
	
	 
}
