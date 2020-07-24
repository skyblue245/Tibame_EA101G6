package com.artrp.model;

import java.util.*;

public interface ArtrpDAO_interface {
	public void insert(ArtrpVO artrpVO);
	public void update(ArtrpVO artrpVO);
	public void delete(String artrpno);
	public ArtrpVO findByPrimaryKey(String artrpno);
	public List<ArtrpVO> getAll();
	public List<ArtrpVO> getAllByArtno(String artno);
	public List<ArtrpVO> getAllByStatus(Integer status);
}
