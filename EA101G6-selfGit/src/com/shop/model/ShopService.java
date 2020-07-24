package com.shop.model;

import java.util.List;
import java.util.stream.Collectors;


public class ShopService {
	
	  ShopDAO_interface dao;
	
	public ShopService() {
		dao = new ShopDAO();
	}
	public ShopVO addShop(String shopact, String shoppw, String shopname, String shoploc, String shopcy, Integer shopphone, byte[] shopimg,Integer status) {
		
		ShopVO shopVO = new ShopVO();
		
		shopVO.setShopact(shopact);
		shopVO.setShoppw(shoppw);
		shopVO.setShopname(shopname);
		shopVO.setShoploc(shoploc);
		shopVO.setShopcy(shopcy);
		shopVO.setShopphone(shopphone);
		shopVO.setShopimg(shopimg);
		shopVO.setStatus(status);
		dao.insert(shopVO);
		
		return shopVO;
		
	}
	
	public ShopVO updateShop(String shopno, String shopact, String shoppw, String shopname, String shoploc, String shopcy, Integer shopphone, byte[] shopimg, Integer status) {

		ShopVO shopVO = new ShopVO();

		shopVO.setShopno(shopno);
		shopVO.setShopact(shopact);
		shopVO.setShoppw(shoppw);
		shopVO.setShopname(shopname);
		shopVO.setShoploc(shoploc);
		shopVO.setShopcy(shopcy);
		shopVO.setShopphone(shopphone);
		shopVO.setShopimg(shopimg);
		shopVO.setStatus(status);
		dao.update(shopVO);
		
		return shopVO;
	}
	public ShopVO updateShop_back(Integer status, String shopno) {
		ShopVO shopVO = new ShopVO();
		
		shopVO.setShopno(shopno);
		shopVO.setStatus(status);
		dao.update_back(shopVO);
		
		return shopVO;
	}
	
	public ShopVO getOneShop(String Shopno) {
		return dao.findByPrimaryKey(Shopno);
	}
	
	public List<ShopVO> getAllowedShop() {
		return dao.getAllowedShop();
	}
	
	public List<ShopVO> getAll() {
		return dao.getAll();
	}
	
	public ShopVO compare(String shopact, String shoppw) {
		return dao.login(shopact, shoppw);
	}
	
	public List<ShopVO> getByStatus(){
		List list = getAll().stream()
		        .filter(ee -> ee.getStatus() == 0)
		        .collect(Collectors.toList());		
		return list;
	}
	
	public List<ShopVO> getByStatus2(Integer status){
		List list = getAll().stream()
		        .filter(ee -> ee.getStatus() == status)
		        .collect(Collectors.toList());		
		return list;
	}
}
