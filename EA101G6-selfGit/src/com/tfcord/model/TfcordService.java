package com.tfcord.model;

import java.sql.Timestamp;
import java.util.List;

import com.mbrpf.model.MbrpfVO;

public class TfcordService {
	
	private TfcordDAO_Interface dao;
	
	public TfcordService() {
		dao = new TfcordDAO();
	}
	
	public TfcordVO addTfcord(String mbrno, String tftype, Integer price, Integer tfstatus) {
		TfcordVO tfcordVO = new TfcordVO();
		tfcordVO.setMbrno(mbrno);
		tfcordVO.setTftype(tftype);
		tfcordVO.setPrice(price);
		tfcordVO.setTfstatus(tfstatus);
		dao.insert(tfcordVO);
		return tfcordVO;
	}
	
	public String addTfcordPoint(String mbrno, String tftype, Integer price, Integer tfstatus, MbrpfVO mbrpfVO) {
		TfcordVO tfcordVO = new TfcordVO();
		tfcordVO.setMbrno(mbrno);
		tfcordVO.setTftype(tftype);
		tfcordVO.setPrice(price);
		tfcordVO.setTfstatus(tfstatus);
		String tfno = dao.insert2(tfcordVO, mbrpfVO);
		return tfno;
	}
	
	public TfcordVO updateTfcord(String tfno, String mbrno, String tftype, Integer price, Timestamp tftime, Integer tfstatus) {
		TfcordVO tfcordVO = new TfcordVO();
		tfcordVO.setTfno(tfno);
		tfcordVO.setMbrno(mbrno);
		tfcordVO.setTftype(tftype);
		tfcordVO.setPrice(price);
		tfcordVO.setTftime(tftime);
		tfcordVO.setTfstatus(tfstatus);
		dao.update(tfcordVO);
		return tfcordVO;
	}
	
	public void deleteTfcord(String tfno) {
		dao.delete(tfno);
	}
	
	public void changeTfcordStatus(String tfno) {
		dao.changeStatusBytfno(tfno);
	}
	
	public TfcordVO getOneTfcord(String tfno) {
		
		return dao.findByPrimaryKey(tfno);
	}
	
	public List<TfcordVO> getWhoAll(String mbrno){
		return dao.findWhoAll(mbrno);
	}
	
	public List<TfcordVO> getNotYetAll(){
		return dao.getNotYetAll();
	}
	
	public List<TfcordVO> getAll(){
		return dao.getAll();
	}
}
