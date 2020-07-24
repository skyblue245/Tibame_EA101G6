package com.rate.model;

import java.util.List;


public class RateService {
	private RateDAO_interface dao;
	
	public RateService() {
		dao = new RateDAO();
	}
	public RateVO sendRate(String rmno, String ratingmbrno, String ratedmbrno, String detail, Integer score) {
		RateVO rateVO = new RateVO();
		rateVO.setRmno(rmno);
		rateVO.setRatingmbrno(ratingmbrno);
		rateVO.setRatedmbrno(ratedmbrno);
		rateVO.setDetail(detail);
		rateVO.setScore(score);
		dao.insert(rateVO);
		
		return rateVO;
	}
	
	public void deleteRate(String rateno) {
		dao.delete(rateno);
	}
	
	public List<RateVO> getAll(){
		return dao.getAll();
	}
	
	public List<RateVO> findByRatedmbrno(String ratedmbrno){
		return dao.findByRatedmbrno(ratedmbrno);
	}
}
