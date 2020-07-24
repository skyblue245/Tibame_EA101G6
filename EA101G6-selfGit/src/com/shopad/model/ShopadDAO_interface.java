package com.shopad.model;

import java.util.*;

public interface ShopadDAO_interface {
	public void insert(ShopadVO shopadVO);
	public void update(ShopadVO shopadVO);
	public void delete(String shopadno);
	public ShopadVO findByPrimaryKey(String shopadno);
	public List<ShopadVO> getAll();
	public List<ShopadVO> getAllStatus(Integer status);
}