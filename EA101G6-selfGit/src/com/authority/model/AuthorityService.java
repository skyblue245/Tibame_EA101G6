package com.authority.model;

import java.util.List;

public class AuthorityService {
	
	private AuthorityDAO_Interface dao;
	
	public AuthorityService() {
		dao = new AuthorityDAO();
	}
	
	public AuthorityVO addAuthority(String empno, String[] ftno) {
		AuthorityVO authorityVO = new AuthorityVO();
		for(String empftno : ftno) {
			authorityVO.setEmpno(empno);
			authorityVO.setFtno(empftno);
			dao.insert(authorityVO);
		}
		return authorityVO;
	}
	
	public AuthorityVO deleteAuthority(String empno, String ftno) {
		AuthorityVO authorityVO = new AuthorityVO();
		authorityVO.setEmpno(empno);
		authorityVO.setFtno(ftno);
		dao.delete(empno, ftno);
		return authorityVO;
	}
	
	public void deleteEmpAuth(String empno) {
		dao.deleteEmpAuth(empno);
	}
	
	public List<AuthorityVO> getEmpAuthority(String empno){
		return dao.findByEmpno(empno);
	}
	
	public List<AuthorityVO> getAuthorityEmp(String ftno){
		return dao.findByFtno(ftno);
	}
	
	public List<AuthorityVO> getAll(){
		return dao.getAll();
	}
}
