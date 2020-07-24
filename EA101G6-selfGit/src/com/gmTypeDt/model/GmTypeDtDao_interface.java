package com.gmTypeDt.model;

import java.util.List;
import java.util.Set;

public interface GmTypeDtDao_interface {
	
	public void add(GmTypeDtVO gmTypeDt);
	public void delete(String typeNo,String commNo);
	public void deleteByTypeNo(String typeNo);
	public void deleteByCommNo(String commNo);
	public Set<GmTypeDtVO> getAll();
	public Set<GmTypeDtVO> findByTypeNo(String typeNo);
	public Set<GmTypeDtVO> findByCommNo(String commNo);
	//public GmTypeDtVO getOne(GmTypeDtVO gmTypeDt);
	
}
