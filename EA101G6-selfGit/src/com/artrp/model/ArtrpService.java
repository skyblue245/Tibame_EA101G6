package com.artrp.model;

import java.util.List;

public class ArtrpService {

	private ArtrpDAO_interface dao;
	
	public ArtrpService() {
		dao = new ArtrpDAO();
	}
	
	public ArtrpVO addArtrp(String artno, String detail, String mbrno, Integer status) {
		ArtrpVO artrpVO = new ArtrpVO();
		
		artrpVO.setArtno(artno);
		artrpVO.setDetail(detail);
		artrpVO.setMbrno(mbrno);
		artrpVO.setStatus(status);
		dao.insert(artrpVO);
		
		return artrpVO;
	}
	
	public ArtrpVO updateStatus(String artrpno, Integer status) {
		ArtrpVO artrpVO = new ArtrpVO();
		
		artrpVO.setArtrpno(artrpno);
		artrpVO.setStatus(status);
		
		dao.update(artrpVO);
		return artrpVO;
	}
	
	public void deleteArtrp(String artrpno) {
		dao.delete(artrpno);
	}
	
	public ArtrpVO getOneArtrp(String artrpno) {
		return dao.findByPrimaryKey(artrpno);
	}
	
	public List<ArtrpVO> getAll() {
		return dao.getAll();
	}
	 public List<ArtrpVO> getAllByArtno(String artno) {
		 return dao.getAllByArtno(artno);
	 }
	 public List<ArtrpVO> getAllByStatus(Integer status) {
		 return dao.getAllByStatus(status);
	 }
}
