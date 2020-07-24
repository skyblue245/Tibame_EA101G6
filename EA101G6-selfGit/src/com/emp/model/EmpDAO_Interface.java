package com.emp.model;

import java.util.List;

import com.authority.model.*;

//定義方法，有哪些行為
//1.新增員工 2.修改員工資料(離職、在職...等等) 3.查詢單一員工 4.查看所有員工
public interface EmpDAO_Interface {
	public String insert(EmpVO empVO);
	public void update(EmpVO empVO);
	public void updatePwd(String emppwd, String empno);
	public EmpVO findByPrimaryKey(String empno);//查單一員工
	public EmpVO findByEmpname(String empname);
	public EmpVO findByMail(String mail,String empno);
	public String forgetPwd(String mail, String empno);
	public List<EmpVO> getAll();
	public EmpVO login(String empact);
	public List<String> getAuthorityByEmpno(String empno);
}