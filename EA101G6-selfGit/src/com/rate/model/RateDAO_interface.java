package com.rate.model;

import java.util.List;

public interface RateDAO_interface {
	public void insert(RateVO rateVO);
	public void delete(String rateno);
	public List<RateVO> getAll();
	public List<RateVO> findByRatedmbrno(String ratedmbrno);
}
