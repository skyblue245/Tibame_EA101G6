package com.joinrm.model;

import java.util.List;

public class JoinrmService {
	
	private JoinrmDAO_interface dao;
	
	public JoinrmService() {
		dao = new JoinrmDAO();
	}
	
	
	public JoinrmVO insertMbr(String rmno,String mbrno) {
		JoinrmVO joinrmVO = new JoinrmVO();
		
		joinrmVO.setRmno(rmno);
		joinrmVO.setMbrno(mbrno);
		dao.insert(joinrmVO);
		return joinrmVO;
		
	}

	public List<JoinrmVO> findByPK(String rmno,String mbrno){
		return dao.findByPK(rmno,mbrno);
	}

	public void deleteMbr(String rmno,String mbrno) {
		dao.delete(rmno, mbrno);
	}
	
	public List<JoinrmVO> getAll(){
		return dao.getAll();
	}
	
	public JoinrmVO update(Integer ratereport, Integer shopreport, String rmno,String mbrno) {
		JoinrmVO joinrmVO = new JoinrmVO();
		joinrmVO.setRatereport(ratereport);
		joinrmVO.setShopreport(shopreport);
		joinrmVO.setRmno(rmno);
		joinrmVO.setMbrno(mbrno);
		dao.update(joinrmVO);
		return joinrmVO;
	}
}
