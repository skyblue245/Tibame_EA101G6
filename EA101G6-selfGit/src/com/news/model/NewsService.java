package com.news.model;

import java.util.List;

public class NewsService {
	
	private NewsDAO_interface dao;
	
	public NewsService() {
		dao = new NewsDAO();
	}
	
	public NewsVO addNews(String newstt, String detail) {
		NewsVO newsVO = new NewsVO();
		
		newsVO.setNewstt(newstt);
		newsVO.setDetail(detail);
		dao.insert(newsVO);
		
		return newsVO;
	}
	
	public NewsVO updateNews(String newsno, String newstt, String detail) {
		NewsVO newsVO = new NewsVO();
		
		newsVO.setNewsno(newsno);
		newsVO.setNewstt(newstt);
		newsVO.setDetail(detail);
	
		dao.update(newsVO);
		
		return newsVO;
	}
	
	public void deleteNews(String newsno) {
		dao.delete(newsno);
	}
	
	public NewsVO getOneNews(String newsno) {
		return dao.findByPrimaryKey(newsno);
	}
	
	public List<NewsVO> getAll(){
		return dao.getAll();
	}

}
