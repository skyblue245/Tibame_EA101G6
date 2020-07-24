package com.mallad.model;

import java.util.*;

public interface MalladDAO_interface {
	public void insert(MalladVO malladVO);
	
	public void delete(String malladno);
	public MalladVO findByPrimaryKey(String malladno);
	public List<MalladVO> getAll();
}
