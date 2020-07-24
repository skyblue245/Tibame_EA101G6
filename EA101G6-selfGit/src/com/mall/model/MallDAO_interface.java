package com.mall.model;


import java.util.List;
import java.util.Set;

import com.gmType.model.GmTypeVO;

public interface MallDAO_interface {
	//新增
	public String add(MallVO mall);
	//刪除
	public void delete(String commno);
	//修改
	public void update(MallVO mall);
	//用商品名稱查詢，因為我查的可能不只一項
	public Set<MallVO> findByName(String name);
	//拿全部
	public Set<MallVO> getAll();
	//拿全部上架的
	public Set<MallVO> getAllUp();
	//用商品名稱查詢，因為我查的可能不只一項回傳list 上架版
	public Set<MallVO> findByNameUp(String name);
	//取得最新商品前五比
	public Set<MallVO> getNew();
	//找一筆
	public MallVO findOneByNo(String commno);
	//拿出遊戲類型
	public Set<GmTypeVO> getType(String commno);
	//拿出遊戲類型相同的上架商品
	public Set<MallVO> findByType(String typeno);
	
}
