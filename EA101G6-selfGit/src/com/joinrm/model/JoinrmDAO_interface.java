package com.joinrm.model;

import java.util.*;

public interface JoinrmDAO_interface {
	public void insert(JoinrmVO joinrmVO);
	public List<JoinrmVO> findByPK(String rmno,String mbrno);
	public void delete(String rmno,String mbrno);
	public List<JoinrmVO> getAll();
	public void update(JoinrmVO joinrmVO);
}
