package com.mallOrDt.model;

import java.io.Serializable;

public class MallOrDtVO implements Serializable {
	
	private String mallOrNo;
	private String commNo;
	private Integer quantity;
	private Integer price;
	
	public MallOrDtVO(){
		
	}

	
	public MallOrDtVO(String mallOrNo, String commNo, Integer quantity, Integer price) {
		super();
		this.mallOrNo = mallOrNo;
		this.commNo = commNo;
		this.quantity = quantity;
		this.price = price;
	}

	public String getMallOrNo() {
		return mallOrNo;
	}

	public void setMallOrNo(String mallOrNo) {
		this.mallOrNo = mallOrNo;
	}

	public String getCommNo() {
		return commNo;
	}

	public void setCommNo(String commNo) {
		this.commNo = commNo;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((commNo == null) ? 0 : commNo.hashCode());
		result = prime * result + ((mallOrNo == null) ? 0 : mallOrNo.hashCode());
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
		MallOrDtVO other = (MallOrDtVO) obj;
		if (commNo == null) {
			if (other.commNo != null)
				return false;
		} else if (!commNo.equals(other.commNo))
			return false;
		if (mallOrNo == null) {
			if (other.mallOrNo != null)
				return false;
		} else if (!mallOrNo.equals(other.mallOrNo))
			return false;
		return true;
	}
	
	

	
	

	
	
	
	
	
}
