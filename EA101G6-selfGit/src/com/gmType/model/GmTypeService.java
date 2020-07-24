package com.gmType.model;

import java.util.List;
import java.util.Set;

public class GmTypeService {
	private GmTypeDao_interface dao ;
	
	public GmTypeService() {
		dao = new GmTypeDao();
	}
	
	public GmTypeVO add(String typeName){
		GmTypeVO gmTypeVo = new GmTypeVO();
		gmTypeVo.setTypeName(typeName);
		gmTypeVo.setTypeNo(dao.add(gmTypeVo));
		return gmTypeVo;
	};
	public void delete(String typeNo){
		dao.delete(typeNo);
	};
	public GmTypeVO update(String typeNo, String typeName) {
		GmTypeVO gmTypeVo = new GmTypeVO();
		gmTypeVo.setTypeNo(typeNo);
		gmTypeVo.setTypeName(typeName);
		dao.update(gmTypeVo);
		return gmTypeVo;
	};
	public Set<GmTypeVO> getAll(){
		return dao.getAll();
	};
	public GmTypeVO findOneByTypeNo(String typeNo){
		return dao.findOneByTypeNo(typeNo);
	};
	/**
	public static void main(String[] args) {
		GmTypeService ser =new GmTypeService();
		ser.add("哈哈類");
		ser.update("ZT00008","西西");
		ser.delete("ZT00009");
		List<GmTypeVO> list = ser.getAll();
		for(GmTypeVO vo : list)
			System.out.println(vo.getTypeName());
		System.out.println(ser.getOneByNo("ZT00009").getTypeName());
		
	}
	**/
}
