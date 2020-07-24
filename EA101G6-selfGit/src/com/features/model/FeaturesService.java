package com.features.model;

import java.util.List;

public class FeaturesService {
	
	private FeaturesDAO_Interface dao;
	
	public FeaturesService() {
		dao = new FeaturesDAO();
	}
	
	public  FeaturesVO addFeatures(String ftname) {
		
		FeaturesVO featuresVO = new FeaturesVO();
		
		featuresVO.setFtname(ftname);
		
		dao.insert(featuresVO);
		
		return featuresVO;
	}
	
	public FeaturesVO updateFeatures(String ftname, String ftno) {
		FeaturesVO featuresVO = new FeaturesVO();
		
		featuresVO.setFtname(ftname);
		featuresVO.setFtno(ftno);
		
		dao.update(featuresVO);
		
		return featuresVO;
	}
	
	public void deleteFeatures(String ftno) {
		dao.delete(ftno);
	}
	
	public FeaturesVO getOneFeatures(String ftno) {
		return dao.findByPrimaryKey(ftno);
	}
	
	public List<FeaturesVO> getAll(){ 
		return dao.getAll();
	}

}
