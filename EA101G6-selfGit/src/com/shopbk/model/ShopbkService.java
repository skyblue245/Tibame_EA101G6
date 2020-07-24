package com.shopbk.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import com.shopbk.model.ShopbkVO;

public class ShopbkService {
	
	ShopbkDAO_interface dao;
	
	public ShopbkService() {
		dao = new ShopbkDAO();
	}
	public ShopbkVO addShopbk(String shopno, Integer ofdtable, Timestamp shoppds, Timestamp shoppde, Integer payinfohr, Integer payinfoday) {
		
		ShopbkVO shopbkVO = new ShopbkVO();
		
		shopbkVO.setShopno(shopno);
		shopbkVO.setOfdtable(ofdtable);
		shopbkVO.setShoppds(shoppds);
		shopbkVO.setShoppde(shoppde);
		shopbkVO.setPayinfohr(payinfohr);
		shopbkVO.setPayinfoday(payinfoday);
		dao.insert(shopbkVO);
		
		return shopbkVO;
		
	}
	public ShopbkVO updateShopbk(String shopbkno, String shopno, Integer ofdtable, Timestamp shoppds, Timestamp shoppde, Integer payinfohr, Integer payinfoday) {
		
		ShopbkVO shopbkVO = new ShopbkVO();
		
		shopbkVO.setShopbkno(shopbkno);
		shopbkVO.setShopno(shopno);
		shopbkVO.setOfdtable(ofdtable);
		shopbkVO.setShoppds(shoppds);
		shopbkVO.setShoppde(shoppde);
		shopbkVO.setPayinfohr(payinfohr);
		shopbkVO.setPayinfoday(payinfoday);
		dao.update(shopbkVO);
		
		return shopbkVO;
		
	}
	public ShopbkVO getOneShopbk(String Shopbkno) {
		return dao.findByPrimaryKey(Shopbkno);
	}
	public List<ShopbkVO> getShopbkByTime(Timestamp shoppd) {
		return dao.findByShoppd(shoppd);
	}
	public List<ShopbkVO> getShopbkByShop(String Shopno) {
		return dao.findByShop(Shopno);
	}
	public List<ShopbkVO> getAll() {
		return dao.getAll();
	}
	public List<ShopbkVO> getAllAfterNow(){
		return dao.getAllAfterNow();
	}
}
