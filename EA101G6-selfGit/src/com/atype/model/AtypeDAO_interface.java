package com.atype.model;

import java.util.*;
//import com.art.model.ArtVO;

import com.art.model.ArtVO;

public interface AtypeDAO_interface {
	public void insert(AtypeVO atypeVO);
	public void update(AtypeVO atypeVO);
	public void delete(String atno);
	public AtypeVO findByPrimaryKey(String atno);
	public List<AtypeVO> getAll();
	public Set<ArtVO> getArtsByAtno(String atno);
}
