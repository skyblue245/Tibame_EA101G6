package com.shoprpdt.model;

import java.util.List;

import com.shoprpdt.model.ShoprpdtVO;

public interface ShoprpdtDAO_interface {
	public void insert(ShoprpdtVO shoprpdtVO);
	public void update(ShoprpdtVO shoprpdtVO);
	public List<ShoprpdtVO> findByPrimaryKey(String shoprpdtno, Integer status);
	public List<ShoprpdtVO> getAll();
}
