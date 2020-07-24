package com.mallad.model;

import java.util.List;

public class MalladService {
	
	private MalladDAO_interface dao;
	
	public MalladService() {
		dao = new MalladDAO();
	}
	
	public MalladVO addMallad(String commno, String gmadtt, byte[] detail, java.sql.Date startt, java.sql.Date stopt) {
		MalladVO malladVO = new MalladVO();
		
		malladVO.setCommno(commno);
		malladVO.setGmadtt(gmadtt);
		malladVO.setDetail(detail);
		malladVO.setStartt(startt);
		malladVO.setStopt(stopt);
		dao.insert(malladVO);
		
		return malladVO;
	}
	
	public void deleteMallad(String malladno) {
		dao.delete(malladno);
	}
	
	public MalladVO getOneMallad(String malladno) {
		return dao.findByPrimaryKey(malladno);
	}
	
	public List<MalladVO> getAll(){
		return dao.getAll();
	}

}
