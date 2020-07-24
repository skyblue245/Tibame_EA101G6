package com.gmlist.model;
import java.util.List;

import com.gmlist.model.GmlistVO;
public interface GmlistDAO_interface {
	public void insert2(GmlistVO gmlistVO, java.sql.Connection con);
	public void insert(GmlistVO gmlistVO);
	public void delete(String gmno, String shopno);
	public GmlistVO findByPrimaryKey(String gmno, String shopno);
	public List<GmlistVO> findByGame(String gmno);
	public List<GmlistVO> findByShop(String shopno);
	public List<GmlistVO> getAll();
}
