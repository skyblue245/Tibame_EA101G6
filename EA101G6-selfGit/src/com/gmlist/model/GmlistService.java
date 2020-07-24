package com.gmlist.model;

import java.util.List;

public class GmlistService {

	GmlistDAO_interface dao;
	
	public GmlistService() {
		dao = new GmlistDAO();
	}
	public GmlistVO addGmlist(String gmno, String shopno) {
		
		GmlistVO gmlistVO = new GmlistVO();
		
		gmlistVO.setGmno(gmno);
		gmlistVO.setShopno(shopno);
		dao.insert(gmlistVO);
		
		return gmlistVO;
	}
	public void deleteGmlist(String gmno, String shopno) {
		dao.delete(gmno,shopno);
	}
	public GmlistVO getOneGmlist(String gmno, String shopno) {
		return dao.findByPrimaryKey(gmno, shopno);	
	}

	public List<GmlistVO> getSomeGmlistByGame(String gmno) {
		return dao.findByGame(gmno);	
	}
	public List<GmlistVO> getSomeGmlistByShop(String shopno) {
		return dao.findByShop(shopno);	
	}
	public List<GmlistVO> getAll() {
		return dao.getAll();	
	}
}
