package com.mall.model;


import java.util.List;
import java.util.Set;

import com.gmType.model.GmTypeVO;

public class MallService {

	private MallDAO_interface dao ;
	
	public MallService() {
		dao = new MallDAO();
	}
	
	//新增ok
	public MallVO add(String commName, Integer price, Integer quantity, byte[] img, String intro,
			String age, String player, Integer status) {
			MallVO mall = new MallVO();
			mall.setCommName(commName);
			mall.setPrice(price);
			mall.setQuantity(quantity);
			mall.setImg(img);
			mall.setIntro(intro);
			mall.setAge(age);
			mall.setPlayer(player);
			mall.setStatus(status);
			mall.setCommNo(dao.add(mall));
			return mall;
	}
	//刪除ok
	public String delete(String commno){
		dao.delete(commno);
		return "刪除成功";
	}
	//修改ok
	public MallVO update(String commNo, String commName, Integer price, Integer quantity, byte[] img, String intro,
			String age, String player, Integer status){
			MallVO mall = new MallVO();
			mall.setCommNo(commNo);
			mall.setCommName(commName);
			mall.setPrice(price);
			mall.setQuantity(quantity);
			mall.setImg(img);
			mall.setIntro(intro);
			mall.setAge(age);
			mall.setPlayer(player);
			mall.setStatus(status);
			dao.update(mall);
		return mall;
		
		
	}
	//用商品名稱查詢，因為我查的可能不只一項回傳list ok
	public Set<MallVO> findByName(String name){
		return dao.findByName(name);
		
	}
	//用商品名稱查詢上架版，因為我查的可能不只一項回傳list
	public Set<MallVO> findByNameUp(String name){
		return dao.findByNameUp(name);
		
	}
	//拿全部ok
	public Set<MallVO> getAll(){
		return dao.getAll();
		
	}
	//拿全部ok
	public Set<MallVO> getAllUp(){
		return dao.getAllUp();
		
	}
	//取得最新商品前五比 ok
	public Set<MallVO> getNew(){
		return dao.getNew();
		
	}
	//ok
	public MallVO findOneByNo(String commNo){
		return dao.findOneByNo(commNo);
		
	}
	
	public Set<GmTypeVO> getType(String commNo){
		return dao.getType(commNo);
	}
	
	public Set<MallVO> findByType(String typeno){
		return dao.findByType(typeno);
	}

	
	
//	public static void main(String[] args) {
//		MallService ser = new MallService();
//
//		
//		byte[] img =null ;
//		try {
//			FileInputStream in =new FileInputStream(new File("C:\\Users\\Java\\Desktop\\WORKSQL\\MALLIMG\\1.jpg"));
//			BufferedInputStream bufin =new BufferedInputStream(in);
//			byte[] buffer = new byte[2048];
//			ByteArrayOutputStream baos = new ByteArrayOutputStream();
//			
//			while(bufin.read(buffer,0,2048)!=-1) 
//				baos.write(buffer);
//				
//			img =baos.toByteArray();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
//		ser.add("大富翁", 1000, 20, img, "大家一起來玩吧","4-6","8歲以上", 0);
//		ser.delete("11");
//		ser.update("ZM00012","豪華版大富翁", 1000, 20, img, "大家一起來玩吧","4-6","8歲以上", 0);
//		List<MallVO> list=ser.getAll();
//		for(MallVO vo : list)
//			System.out.println(vo.getCommNo()+vo.getCommName());
//	
//		//System.out.println(ser.getOneByNo("ZM00012").getCommName());
//		List<String> list =ser.getType("ZM00009");
//		for(String x : list)
//			System.out.println(x);
//		System.out.println(ser.add("大富翁", 1000, 20, null, "大家一起來玩吧","4-6","8歲以上", 0));
		
//	}
	
	
}
