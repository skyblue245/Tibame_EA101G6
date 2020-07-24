package com.msg.model;

import java.util.List;

public class MsgService {

	private MsgDAO_interface dao;

	public MsgService() {
		dao = new MsgDAO();
	}

	public MsgVO addMsg(String mbrno,String detail,String artno,Integer status) {

		MsgVO msgVO = new MsgVO();

		msgVO.setMbrno(mbrno);
		msgVO.setDetail(detail);
		msgVO.setArtno(artno);
		msgVO.setStatus(status);
		dao.insert(msgVO);

		return msgVO;
	}

	public MsgVO updateMsg(String msgno,String mbrno,String detail,String artno,Integer status) {

		MsgVO msgVO = new MsgVO();

		msgVO.setMsgno(msgno);
		msgVO.setMbrno(mbrno);
		msgVO.setDetail(detail);
		msgVO.setArtno(artno);
		msgVO.setStatus(status);
		dao.update(msgVO);

		return msgVO;
	}

	public void deleteMsg(String msgno) {
		dao.delete(msgno);
	}

	public MsgVO getOneMsg(String msgno) {
		return dao.findByPrimaryKey(msgno);
	}

	public List<MsgVO> getAll() {
		return dao.getAll();
	}
	
	public List<MsgVO> getAllByArtno(String artno){
		return dao.getAllByArtno(artno);
	}
}
