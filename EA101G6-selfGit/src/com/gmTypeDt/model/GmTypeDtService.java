package com.gmTypeDt.model;

import java.util.List;
import java.util.Set;

public class GmTypeDtService {
	
	private GmTypeDtDao_interface dao;
	
	public GmTypeDtService(){
		dao=new GmTypeDtDao();
	}
	//ok
	public void add(String typeNo, String commNo){
		
		GmTypeDtVO gmTypeDtVo = new GmTypeDtVO();
		gmTypeDtVo.setTypeNo(typeNo);
		gmTypeDtVo.setCommNo(commNo);
		dao.add(gmTypeDtVo);
	}
	
	//ok
	public void delete(String typeNo,String commNo){
		dao.delete(typeNo,commNo);
	}
	
	//ok
	public void deleteByCommNo(String commNo){
		dao.deleteByCommNo(commNo);
	}
	
	//ok
	public void deleteByTypeNo(String typeNo){
		dao.deleteByTypeNo(typeNo);
	}
	
	//ok
	public Set<GmTypeDtVO> getAll(){
		return dao.getAll();
	}
	//ok
	public Set<GmTypeDtVO> findByTypeNo(String typeNo){
		return dao.findByTypeNo(typeNo);
	}
	//ok
	public Set<GmTypeDtVO> findByCommNo(String commNo){
		return dao.findByCommNo(commNo);
	}
	/**
	public static void main(String[] args) {
		
		GmTypeDtService ser = new GmTypeDtService();
		ser.add("ZT00007", "ZM00010");
		ser.delete("ZT00007", "ZM00010");
		List<GmTypeDtVO>list=ser.getByCommNo("ZM00010");
		for(GmTypeDtVO vo:list)
		System.out.println(vo.getTypeNo());
		System.out.println(ser.getByCommNo(1001));
		System.out.println(ser.getByTypeNo(4));
	}**/
	
}
