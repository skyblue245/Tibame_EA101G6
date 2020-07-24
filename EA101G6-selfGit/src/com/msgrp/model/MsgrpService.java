package com.msgrp.model;

import java.util.List;

public class MsgrpService {

	private MsgrpDAO_interface dao;

	public MsgrpService() {
		dao = new MsgrpDAO();
	}

	public MsgrpVO addMsgrp(String msgno,String detail,String mbrno,Integer status) {

		MsgrpVO msgrpVO = new MsgrpVO();

		msgrpVO.setMsgno(msgno);
		msgrpVO.setDetail(detail);
		msgrpVO.setMbrno(mbrno);
		msgrpVO.setStatus(status);
		dao.insert(msgrpVO);

		return msgrpVO;
	}

	public MsgrpVO updateMsgrp(String msgrpno,String msgno,String detail,String mbrno,Integer status) {

		MsgrpVO msgrpVO = new MsgrpVO();

		msgrpVO.setMsgno(msgno);
		msgrpVO.setDetail(detail);
		msgrpVO.setMbrno(mbrno);
		msgrpVO.setStatus(status);
		dao.update(msgrpVO);

		return msgrpVO;
	}

	public void deleteMsgrp(String msgrpno) {
		dao.delete(msgrpno);
	}

	public MsgrpVO getOneMsgrp(String msgrpno) {
		return dao.findByPrimaryKey(msgrpno);
	}

	public List<MsgrpVO> getAll() {
		return dao.getAll();
	}
}
