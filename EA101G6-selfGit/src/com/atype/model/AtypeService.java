package com.atype.model;

import java.util.List;
import java.util.Set;
//import com.art.model.ArtVO;

import com.art.model.ArtVO;

public class AtypeService {
	
	private AtypeDAO_interface dao;
	
	public AtypeService() {
		dao = new AtypeDAO();
	}
	
	public List<AtypeVO> getAll() {
		return dao.getAll();
	}
	
	public AtypeVO getOneAtype(String atno) {
		return dao.findByPrimaryKey(atno);
	}
	
	public Set<ArtVO> getArtsByAtno(String atno){
		return dao.getArtsByAtno(atno);
	}
	
	public void deleteAtype(String atno) {
		dao.delete(atno);
	}

}
