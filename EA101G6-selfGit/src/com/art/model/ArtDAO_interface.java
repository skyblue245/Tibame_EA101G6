package com.art.model;

import java.util.*;

public interface ArtDAO_interface {
		public void insert(ArtVO artVO);
		public void update(ArtVO artVO);
		public void update_art_status(ArtVO artVO);
		public void delete(String artno);
		public ArtVO findByPrimaryKey(String artno);
		public List<ArtVO> getAll();
		//萬用複合查詢(傳入參數型態Map)(回傳 List)
//		public List<ArtVO> getAll(Map<String, String[]> map);
		public List<ArtVO> getArtsByArttt(String arttt); 
		public List<ArtVO> getArtsByMbrno(String mbrno);
		public List<ArtVO> getArtsByAtno(String atno);
		public List<ArtVO> getArtsByStatus(Integer status);
		
}
