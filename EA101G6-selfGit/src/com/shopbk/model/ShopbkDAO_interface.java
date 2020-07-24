package com.shopbk.model;

import java.sql.Timestamp;
import java.util.List;

import com.shopbk.model.ShopbkVO;

public interface ShopbkDAO_interface {
	public void insert(ShopbkVO shopbkVO);
	public void update(ShopbkVO shopbkVO);
	public void delete(String shopbkno);
	public ShopbkVO findByPrimaryKey(String shopbkno);
	public List<ShopbkVO> findByShoppd(Timestamp shoppds);
	public List<ShopbkVO> findByShop(String shopno);
	public List<ShopbkVO> getAll();
	public List<ShopbkVO> getAllAfterNow();
}
