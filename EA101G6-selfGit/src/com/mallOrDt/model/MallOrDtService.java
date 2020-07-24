package com.mallOrDt.model;

import java.sql.Connection;
import java.util.List;
import java.util.Set;

public class MallOrDtService {
	
	MallOrDtDao_interface dao=null;
	
	public MallOrDtService(){
		dao=new MallOrDtDao();
	}
	
	//ok
	public void add(String mallOrNo, String commNo, Integer quantity, Integer price){
		MallOrDtVO mallOrDtVo =new MallOrDtVO();
		mallOrDtVo.setMallOrNo(mallOrNo);
		mallOrDtVo.setCommNo(commNo);
		mallOrDtVo.setQuantity(quantity);
		mallOrDtVo.setPrice(price);
		dao.add(mallOrDtVo);
	}
	//ok
	public void delete(String mallOrNo,String commNo){
		dao.delete(mallOrNo, commNo);
	}
	//ok
	public MallOrDtVO update(String mallOrNo, String commNo, Integer quantity, Integer price){
		MallOrDtVO mallOrDtVo =new MallOrDtVO();
		mallOrDtVo.setMallOrNo(mallOrNo);
		mallOrDtVo.setCommNo(commNo);
		mallOrDtVo.setQuantity(quantity);
		mallOrDtVo.setPrice(price);
		dao.update(mallOrDtVo);
		return mallOrDtVo;
	}
	//ok
	public Set<MallOrDtVO> getAll(){
		return dao.getAll();
	}
	//ok
	public Set<MallOrDtVO> getByOrNo(String mallOrNo){
		return dao.getByOrNo(mallOrNo);
	}
	//ok
	public MallOrDtVO findOneByFk(String mallOrNo,String commNo){
		return dao.findOneByFk(mallOrNo, commNo);
	}
	
	
	/**
	public static void main(String[] args) {
		MallOrDtService ser =new MallOrDtService();
		ser.add("20200603-0000001","ZM00005",1,199);
		ser.update("20200603-0000001","ZM00005",2,199);
		ser.delete("20200603-0000001","ZM00005");
		
		System.out.println(ser.getOneByFk("20200603-0000001","ZM00004").getPrice());
		List<MallOrDtVO>list=ser.getAll();
		for(MallOrDtVO vo:list)
		System.out.println(vo.getCommNo());
		
		List<MallOrDtVO>list1=ser.getByOrNo("20200603-0000001");
		for(MallOrDtVO vo:list1)
		System.out.println(vo.getCommNo());
		System.out.println(ser.getTotal("20200603-0000001"));
	}
	**/
}
