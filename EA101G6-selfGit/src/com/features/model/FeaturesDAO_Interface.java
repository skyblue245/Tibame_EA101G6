package com.features.model;

import java.util.List;

public interface FeaturesDAO_Interface {
	public void insert(FeaturesVO featuresVO);
	public void update(FeaturesVO featuresVO);
	public void delete(String ftno);
	public FeaturesVO findByPrimaryKey(String ftno);
	public List<FeaturesVO> getAll();

}
