package com.tfcord.model;

import java.util.List;

import com.mbrpf.model.MbrpfVO;

public interface TfcordDAO_Interface {
	public void insert(TfcordVO tfcordVO);
	public void update(TfcordVO tfcordVO);
	public void delete(String tfno);
	public void changeStatusBytfno(String tfno);//點數轉換的審核
	public TfcordVO findByPrimaryKey(String tfno);//查詢某筆轉換紀錄
	public List<TfcordVO> findWhoAll(String mbrno);//查某會員有哪些紀錄，也可用於帳戶管理
	public List<TfcordVO> getNotYetAll();//查所有還沒處理的點數紀錄
	public List<TfcordVO> getAll();//查所有點數紀錄
	public String insert2(TfcordVO tfcordVO, MbrpfVO mbrpfVO);//新增點數轉換紀錄的同時增加減少會員點數
	
	//還沒：查詢某段時間的紀錄  換點的  換錢的
	

}
