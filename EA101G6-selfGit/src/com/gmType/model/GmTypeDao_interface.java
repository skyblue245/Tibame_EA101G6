package com.gmType.model;


import java.util.List;
import java.util.Set;

public interface GmTypeDao_interface {

	public String add(GmTypeVO gmType);
	public void delete(String typeNo);
	public void update(GmTypeVO gmType);
	public Set<GmTypeVO> getAll();
	public GmTypeVO findOneByTypeNo(String typeNo);
	
}
