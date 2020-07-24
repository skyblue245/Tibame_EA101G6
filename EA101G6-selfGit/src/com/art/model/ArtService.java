package com.art.model;

import java.util.List;

public class ArtService {
	
	private ArtDAO_interface dao;
	
	public ArtService() {
		dao = new ArtDAO();
	}
	
	public ArtVO addArt(String mbrno, String detail, String arttt, Integer status, String atno, byte[] apic) {
		ArtVO artVO = new ArtVO();
		
		artVO.setMbrno(mbrno);
		artVO.setDetail(detail);
		artVO.setArttt(arttt);
		artVO.setStatus(status);
		artVO.setAtno(atno);
		artVO.setApic(apic);
		dao.insert(artVO);
		
		return artVO;
	}
	
	public ArtVO updateArt(String artno, String mbrno, String detail, String arttt, String atno, byte[] apic) {
		ArtVO artVO = new ArtVO();
		
		artVO.setArtno(artno);
		artVO.setMbrno(mbrno);
		artVO.setDetail(detail);
		artVO.setArttt(arttt);
		artVO.setAtno(atno);
		artVO.setApic(apic);
		dao.update(artVO);
		
		return artVO;
	}
	
	public ArtVO updateArtStatus(String artno, Integer status) {
		ArtVO artVO = new ArtVO();
		
		artVO.setArtno(artno);
		
		artVO.setStatus(status);
		
		dao.update_art_status(artVO);
		
		return artVO;
	}
	
	public void deleteArt(String artno) {
		dao.delete(artno);
	}
	
	public ArtVO getOneArt(String artno) {
		return dao.findByPrimaryKey(artno);
	}
	
	public List<ArtVO> getAll() {
		return dao.getAll();
	}
	
	public List<ArtVO> getArtsByArttt(String arttt) {
		return dao.getArtsByArttt(arttt);
	}
	
	public List<ArtVO> getArtsByMbrno(String mbrno) {
		return dao.getArtsByMbrno(mbrno);
	}
	
	public List<ArtVO> getArtsByAtno(String atno) {
		return dao.getArtsByAtno(atno);
	}
	public List<ArtVO> getArtsByStatus(Integer status) {
		return dao.getArtsByStatus(status);
	}

}
