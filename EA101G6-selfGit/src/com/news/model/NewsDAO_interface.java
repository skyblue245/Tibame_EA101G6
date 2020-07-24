package com.news.model;

import java.util.List;

public interface NewsDAO_interface {
	
	public void insert(NewsVO newsVO);
	public void update(NewsVO newsVO);
	public void delete(String newsno);
	public NewsVO findByPrimaryKey(String newsno);
	public List<NewsVO> getAll();

}
