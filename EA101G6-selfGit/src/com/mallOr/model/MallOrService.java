package com.mallOr.model;

import java.util.List;
import java.util.Set;

import com.mallOrDt.model.MallOrDtVO;
import com.mbrpf.model.MbrpfVO;


public class MallOrService {
	
private MallOrDAO_interface dao ;
	
	public MallOrService() {
		dao = new MallOrDAO();
	}
	
	//新增 ok
	public String add(String mbrNo, java.sql.Timestamp orDate, String take, String address, Integer status,
			Integer payStatus, Integer boxStatus,Integer price) {
		MallOrVO mallOrVo = new MallOrVO();
		mallOrVo.setMbrNo(mbrNo);
		mallOrVo.setOrDate(orDate);
		mallOrVo.setTake(take);
		mallOrVo.setAddress(address);
		mallOrVo.setStatus(status);
		mallOrVo.setPayStatus(payStatus);
		mallOrVo.setBoxStatus(boxStatus);
		mallOrVo.setPrice(price);
		dao.add(mallOrVo);
		return "新增成功";
	}
	//刪除
	public String delete(String mallOrNo){
		dao.delete(mallOrNo);
		return "刪除成功";
	}
	//修改
	public MallOrVO update(String mallOrNo, Integer status,
			Integer payStatus, Integer boxStatus){
		MallOrVO mallOrVo = new MallOrVO();
		mallOrVo.setMallOrNo(mallOrNo);
		mallOrVo.setStatus(status);
		mallOrVo.setPayStatus(payStatus);
		mallOrVo.setBoxStatus(boxStatus);
		dao.update(mallOrVo);
		
		return mallOrVo;
		
		
	}
	//ok
	public Set<MallOrVO> getAll(){
		return dao.getAll();
	}
	//ok
	public Set<MallOrVO> findByMbrNo(String mbrNo){
		return dao.findByMbrNo(mbrNo);
	}
	
	//ok
	public Set<MallOrVO> findByBoxStatus(Integer boxStatus){
		return dao.findByBoxStatus(boxStatus);
	}
	
	//ok
	public Set<MallOrVO> findByStatus(Integer status){
		return dao.findByStatus(status);
	}
	//ok
	public MallOrVO findOneByOrNo(String mallOrNo) {
		return dao.findOneByOrNo(mallOrNo);
	}
	
	public MallOrVO add(String mbrNo, java.sql.Timestamp orDate, String take, String address, Integer status,
			Integer payStatus, Integer boxStatus,Integer price,List<MallOrDtVO> mallOrDtList) {
		
		MallOrVO mallOrVo = new MallOrVO();
		mallOrVo.setMbrNo(mbrNo);
		mallOrVo.setOrDate(orDate);
		mallOrVo.setTake(take);
		mallOrVo.setAddress(address);
		mallOrVo.setStatus(status);
		mallOrVo.setPayStatus(payStatus);
		mallOrVo.setBoxStatus(boxStatus);
		mallOrVo.setPrice(price);
		mallOrVo.setMallOrNo(dao.add(mallOrVo,mallOrDtList));
		
		return mallOrVo;
		
	}
	
	public MallOrVO add(String mbrNo, java.sql.Timestamp orDate, String take, String address, Integer status,
			Integer payStatus, Integer boxStatus,Integer price,List<MallOrDtVO> mallOrDtList,MbrpfVO mbrpfVo) {
		
		MallOrVO mallOrVo = new MallOrVO();
		mallOrVo.setMbrNo(mbrNo);
		mallOrVo.setOrDate(orDate);
		mallOrVo.setTake(take);
		mallOrVo.setAddress(address);
		mallOrVo.setStatus(status);
		mallOrVo.setPayStatus(payStatus);
		mallOrVo.setBoxStatus(boxStatus);
		mallOrVo.setPrice(price);
		mallOrVo.setMallOrNo(dao.add(mallOrVo,mallOrDtList,mbrpfVo));
		
		return mallOrVo;
		
	}
	
	/**測試
	public static void main(String[] args) {
		MallOrService ser = new MallOrService();
		//ser.add("BM00001",new java.sql.Timestamp(new java.util.Date().getTime()),"超商取貨","330桃園市桃園區力行路7號",1,1,2);
		//ser.delete("20200605-0000013");
		//ser.update("20200605-0000013","BM00001",new java.sql.Timestamp(new java.util.Date().getTime()),"住家","330桃園市桃園區力行路7號",1,1,2);
		List<MallOrVO> list=ser.getByMbrNo("BM00001");
		for(MallOrVO vo : list)
			System.out.println(vo.getMallOrNo());
	
		System.out.println(ser.getOneByOrNo("20200605-0000013").getMbrNo());
	}
	 **/
	
}
