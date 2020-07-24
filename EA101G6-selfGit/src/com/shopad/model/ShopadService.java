package com.shopad.model;

import java.util.List;

public class ShopadService {
	
	private ShopadDAO_interface dao;
	
	public ShopadService() {
		dao = new ShopadDAO();
	}
	
	public ShopadVO addShopad(String shopno, String shopadtt, java.sql.Date startt, java.sql.Date stopt, Integer status) {
		ShopadVO shopadVO = new ShopadVO();
		
		shopadVO.setShopno(shopno);
		shopadVO.setShopadtt(shopadtt);
		shopadVO.setStartt(startt);
		shopadVO.setStopt(stopt);
		shopadVO.setStatus(status);
		dao.insert(shopadVO);
		
		return shopadVO;
	}
	
	public ShopadVO updateStatus(String shopadno, Integer status) {
		ShopadVO shopadVO = new ShopadVO();
		
		shopadVO.setShopadno(shopadno);
		shopadVO.setStatus(status);
		dao.update(shopadVO);
		
		return shopadVO;
	}
	
	public void deleteShopad(String shopadno) {
		dao.delete(shopadno);
	}
	
	public ShopadVO getOneShopad(String shopadno) {
		return dao.findByPrimaryKey(shopadno);
	}
	
	public List<ShopadVO> getAll(){
		return dao.getAll();
	}
	public List<ShopadVO> getAllStatus(Integer status){
		return dao.getAllStatus(status);
	}

}
