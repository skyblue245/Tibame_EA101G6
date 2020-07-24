package com.shoprp.model;

import java.util.List;

public class ShoprpService {
	
	private ShoprpDAO_interface dao;

	public ShoprpService() {
		dao = new ShoprpDAO();
	}
	
	public ShoprpVO report(String mbrno,String rmno,String detail,Integer attend) {
		ShoprpVO shoprpVO = new ShoprpVO();
		
		shoprpVO.setMbrno(mbrno);
		shoprpVO.setRmno(rmno);
		shoprpVO.setDetail(detail);
		shoprpVO.setAttend(attend);
		dao.insert(shoprpVO);
		
		return shoprpVO;
		
		
	}
	public List<ShoprpVO> getAll(){
		return dao.getAll();
	}
	
	public void deleteShoprp(String rmno,String mbrno) {
		dao.delete(rmno,mbrno);
	}
}
