package com.authority.model;

import java.util.List;

public interface AuthorityDAO_Interface {
	public void insert(AuthorityVO authorityVO);
    public void delete(String empno, String ftno);
    public void deleteEmpAuth(String empno);
    public List<AuthorityVO> findByEmpno(String empno);//查某員工有哪些權限
    public List<AuthorityVO> findByFtno(String ftno);//查擁有某權限的有哪些員工
    public List<AuthorityVO> getAll();
}
