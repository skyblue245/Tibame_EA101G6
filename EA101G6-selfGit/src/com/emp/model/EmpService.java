package com.emp.model;

import java.util.List;

import com.authority.model.*;

public class EmpService {
	
	private EmpDAO_Interface dao;//宣告dao的型態是介面，此處是為了框架鋪路，未來可達到0相依
	
	public EmpService(){
		dao = new EmpDAO();
	}
	
	//因為empno是sequence產生，所以不用set
    //之後會有sequence產生的同時，也可以拿來用的做法
	public EmpVO addEmp(byte[] pic, String empname, String mail, String sex, Integer empstatus, String[] ftno) {
		
		EmpVO empVO = new EmpVO();
		
		empVO.setPic(pic);
		empVO.setEmpname(empname);
		empVO.setEmppwd(getRanPwd());
		empVO.setMail(mail);
		empVO.setSex(sex);
		empVO.setEmpstatus(empstatus);
		
		String empno = dao.insert(empVO);
		
		EmpMailService empMailSvc = new EmpMailService(empVO, empno);
		empMailSvc.start();//將員工物件傳給負責寄送訊息的EmpMailService
		
		
		AuthorityService authoritySvc = new AuthorityService();
		authoritySvc.addAuthority(empno, ftno);//將員工編號及授權給他的權限陣列傳給負責增加權限的AuthorityService
		
		return empVO;
	}
	
	public EmpVO updateEmpSTAT(byte[] pic, String empname, String mail, String sex, Integer empstatus, String empno, String[] ftno) {
		EmpVO empVO = new EmpVO();
		empVO.setPic(pic);
		empVO.setEmpname(empname);
		empVO.setMail(mail);
		empVO.setSex(sex);
		empVO.setEmpstatus(empstatus);
		empVO.setEmpno(empno);
		
		dao.update(empVO);
		
		AuthorityService authoritySvc = new AuthorityService();
		authoritySvc.deleteEmpAuth(empno);
		authoritySvc.addAuthority(empno, ftno);
		
		return empVO;
	}
	
	public String getNewPwd(String mail, String empno) {
		return dao.forgetPwd(mail,empno);
	}
	
	public EmpVO getOneEmp(String empno) {
		return dao.findByPrimaryKey(empno);
	}
	
	public EmpVO getOneEmpForName(String empname) {
		return dao.findByEmpname(empname);
	}
	
	public EmpVO getOneEmpForMail(String mail, String empno) {
		return dao.findByMail(mail, empno);
	}
	
	public List<EmpVO> getAllEmp(){
		return dao.getAll();
	}
	
	public EmpVO checkLogin(String empno) {
		return dao.login(empno);
	}
	
	public void updateEmpPwd(String emppwd, String empno) {
		dao.updatePwd(emppwd, empno);
	}
	
	public List<String> getAuthorityByEmpno(String empno){
		return dao.getAuthorityByEmpno(empno);
	}
	
	public static String getRanPwd() {
		char[] pwdAry =new char[5];//要拿來裝亂數密碼的陣列
		char[] all = new char[62];//將我可以顯示的所有字元存放進一個陣列，讓我之後選(26大寫英文+26小寫英文+10個數字)
		for(int i = 0; i < 26; i++) {//將英文字母放進陣列中
			all[i] = (char)(65+i);//0~25放A-Z，A的ASCIIcode為65
			all[26 + i] = (char)(97 + i);//26~51放a-z，a的ASCIIcode為97
		}
		for(int i = 0; i < 10; i++) {
			all[52 + i] = (char)(48 + i);//52~62放0-9，0的ASCIIcode為48
		}
		int[] num = new int[5];//亂數密碼共可放10碼
		for(int i = 0; i < 5; i++) {
			num[i] = (int)(Math.random()*all.length);//1-62的數字亂數取出10個數字，相當於將共62個元素，亂數抽出10個
			pwdAry[i] =  all[ num[i] ];//Ex：如果第一個random 抽出10，即為num[10]，num[] 第0個元素是10
						// ↑ i=0時，all[num[0]] => all[10]
			//↑all[]中第10個元素，為pwdAry[]陣列中第0個元素
		}
		String pwd = String.valueOf(pwdAry);
//		System.out.println(pwd);
//		System.out.println(pwd.length());
		return pwd;
		
		/*
		 
		String strStringType="my string"; //建立一個字串變數strStringType
		char[] chrCharArray; //建立一個字元陣列chrCharArray
		chrCharArray = strStringType.toCharArray(); //將字串變數轉換為字元陣列
		strStringType= String.valueOf(chrCharArray ); //將字元陣列轉換為字串
			
			（1）String類的toCharArray()方法，將字串轉換為字元陣列
			（2）String類的valueOf()方法，將char型別的陣列轉換為字串
		*/
	}
	

}
