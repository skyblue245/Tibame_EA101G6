package com.emp.model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.authority.model.AuthorityVO;

public class EmpDAO implements EmpDAO_Interface {
		
	private static DataSource ds = null;
	
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		}catch(NamingException e) {
			e.printStackTrace();
		}
	}
	
	//一開始INSERT的是給他們驗證的帳密?
	private static final String INSERT_PSTMT = "INSERT INTO EMP (PIC, EMPNO, EMPNAME, EMPPWD, MAIL, SEX, EMPSTATUS) VALUES (?, 'LE'||LPAD(TO_CHAR(EMP_SEQ.NEXTVAL),5,'0'), ?, ?, ?, ?, ? )";
	private static final String UPDATE_PSTMT = "UPDATE EMP SET PIC = ?, EMPNAME = ?, MAIL = ?, SEX = ?, EMPSTATUS = ? WHERE EMPNO = ?";
	private static final String UPDATE_PWD_PSTMT = "UPDATE EMP SET EMPPWD = ? WHERE EMPNO = ?";
	private static final String FORGET_PWD_PSTMT = "UPDATE EMP SET EMPPWD = ? WHERE MAIL = ? AND EMPNO = ?";
	private static final String FIND_BY_PK = "SELECT * FROM EMP WHERE EMPNO = ?";
	private static final String FIND_BY_NAME = "SELECT * FROM EMP WHERE EMPNAME = ?";
	private static final String FIND_BY_MAIL = "SELECT * FROM EMP WHERE MAIL = ? AND EMPNO = ?";
	private static final String GET_ALL = "SELECT * FROM EMP";
	private static final String LOGIN = "SELECT * FROM EMP WHERE EMPNO= ?";
	private static final String GET_AUTHORITY_BY_EMPNO = "SELECT * FROM AUTHORITY WHERE EMPNO = ? ORDER BY FTNO";
	//private static final String EMP_SEQUENCE = "SELECT 'LE'||LPAD(TO_CHAR(EMP_SEQ.CURRVAL),5,'0') FROM DUAL";//查詢目前的sequence到多少了
																								//DUAL為：虛擬資料表，且只有1個欄位
	//實作介面的方法
	@Override
	public String insert(EmpVO empVO){
		Connection con = null;//先給初始值，後續才有辦法做if-else的判斷
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sequence = null;
		try {
			
			con = ds.getConnection();//建立連線
			String[] cols = {"EMPNO"};//將要綁定出來的自增主鍵欄位名是什麼
			pstmt = con.prepareStatement(INSERT_PSTMT,cols);//藉由連線送出指令
			//沒有 cols，就不會有自增主鍵的綁定
			
			pstmt.setBytes(1, empVO.getPic());
			pstmt.setString(2, empVO.getEmpname());
			pstmt.setString(3, empVO.getEmppwd());
			pstmt.setString(4, empVO.getMail());
			pstmt.setString(5, empVO.getSex());
			pstmt.setInt(6, empVO.getEmpstatus());
			
			pstmt.executeUpdate();
			
			rs = pstmt.getGeneratedKeys();//透過getGeneratedKeys()方法：取出自增主鍵
			
			if(rs.next()) {
				sequence = rs.getString(1);//如果要綁出來的自增主鍵有兩到三個
										   //就是rs.getString(1)、rs.getString(2)、rs.getString(3)...等
			}
			
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());//要將錯誤顯示在前端，讓其他支java程式能夠知道有錯誤，如果將錯誤顯示於console，其他支會不知道有錯，前端畫面也會沒有顯示錯誤，而不好除錯
		}finally {//做關閉連線的動作
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
		}
		return sequence;
	}
	@Override
	public void update(EmpVO empVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_PSTMT);
			pstmt.setBytes(1, empVO.getPic());
			pstmt.setString(2, empVO.getEmpname());
			pstmt.setString(3, empVO.getMail());
			pstmt.setString(4, empVO.getSex());
			pstmt.setInt(5, empVO.getEmpstatus());
			pstmt.setString(6, empVO.getEmpno());
			
			pstmt.executeUpdate();
		}catch(SQLException se) {
			se.printStackTrace();
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
		}	
	}
	@Override
	public EmpVO findByPrimaryKey(String empno) {
		EmpVO empVO =null;//宣告在外面才有辦法於try外回傳
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			
			pstmt.setString(1, empno);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				empVO = new EmpVO();
				//取出查詢結果資料欄 getType(欄位名 或 欄位index)
				empVO.setPic(rs.getBytes("pic"));
				empVO.setEmpno(rs.getString("empno"));
				empVO.setEmpname(rs.getString("empname"));
				empVO.setEmppwd(rs.getString("emppwd"));
				empVO.setMail(rs.getString("mail"));
				empVO.setSex(rs.getString("sex"));
				empVO.setEmpstatus(rs.getInt("empstatus"));
			}
			
		}catch(SQLException se) {
			se.printStackTrace();
		}finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException se){
					se.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
		}
		return empVO;
	}
	
	@Override
	public EmpVO findByEmpname(String empname) {
		EmpVO empVO =null;//宣告在外面才有辦法於try外回傳
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_NAME);
			
			pstmt.setString(1, empname);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				empVO = new EmpVO();
				//取出查詢結果資料欄 getType(欄位名 或 欄位index)
				empVO.setPic(rs.getBytes("pic"));
				empVO.setEmpno(rs.getString("empno"));
				empVO.setEmpname(rs.getString("empname"));
				empVO.setEmppwd(rs.getString("emppwd"));
				empVO.setMail(rs.getString("mail"));
				empVO.setSex(rs.getString("sex"));
				empVO.setEmpstatus(rs.getInt("empstatus"));
			}
			
		}catch(SQLException se) {
			se.printStackTrace();
		}finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException se){
					se.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
		}
		return empVO;
	}
	
	@Override
	public EmpVO findByMail(String mail ,String empno) {
		EmpVO empVO =null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_MAIL);
			
			pstmt.setString(1, mail);
			pstmt.setString(2, empno);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				empVO = new EmpVO();

				empVO.setEmpno(rs.getString("empno"));
				empVO.setEmpname(rs.getString("empname"));
				empVO.setEmppwd(rs.getString("emppwd"));
				empVO.setMail(rs.getString("mail"));
			}
			
		}catch(SQLException se) {
			se.printStackTrace();
		}finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException se){
					se.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
		}
		return empVO;
	}
	
	@Override
	public List<EmpVO> getAll() {
		List<EmpVO> empList = new ArrayList<>();
		EmpVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				empVO = new EmpVO();
				empVO.setPic(rs.getBytes("pic"));
				empVO.setEmpno(rs.getString("empno"));
				empVO.setEmpname(rs.getString("empname"));
				empVO.setEmppwd(rs.getString("emppwd"));
				empVO.setMail(rs.getString("mail"));
				empVO.setSex(rs.getString("sex"));
				empVO.setEmpstatus(rs.getInt("empstatus"));
				empList.add(empVO);	
			}
		}catch(SQLException se) {
			se.printStackTrace();
		}finally {
			if(rs != null)
			{
				try {
					rs.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
			if(con != null) {
				try{
					con.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
		}
		return empList;
	}
	
	
	
	@Override
	public EmpVO login(String empno) {
		EmpVO empVO =null;//宣告在外面才有辦法於try外回傳
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(LOGIN);
			
			pstmt.setString(1, empno);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				empVO = new EmpVO();
				//取出查詢結果資料欄 getType(欄位名 或 欄位index)
				empVO.setPic(rs.getBytes("pic"));
				empVO.setEmpno(rs.getString("empno"));
				empVO.setEmpname(rs.getString("empname"));
				empVO.setEmppwd(rs.getString("emppwd"));
				empVO.setMail(rs.getString("mail"));
				empVO.setSex(rs.getString("sex"));
				empVO.setEmpstatus(rs.getInt("empstatus"));
			}
			
		}catch(SQLException se) {
			se.printStackTrace();
		}finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException se){
					se.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
		}
		return empVO;
	}
	
	public void updatePwd(String emppwd, String empno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_PWD_PSTMT);
			
			pstmt.setString(1, emppwd);
			pstmt.setString(2, empno);
			
			rs = pstmt.executeQuery();
			
		}catch(SQLException se) {
			se.printStackTrace();
		}finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException se){
					se.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
		}
	}
	
	public String forgetPwd(String mail, String empno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String newPwd = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FORGET_PWD_PSTMT);
			newPwd = getRanPwd();
			pstmt.setString(1, newPwd);
			pstmt.setString(2, mail);
			pstmt.setString(3, empno);
			
			rs = pstmt.executeQuery();
			
		}catch(SQLException se) {
			se.printStackTrace();
		}finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException se){
					se.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
		}
		return newPwd;
	}
	
	@Override
	public List<String> getAuthorityByEmpno(String empno) {
		List<String> authList = new ArrayList<>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_AUTHORITY_BY_EMPNO);
			pstmt.setString(1, empno);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				authList.add(rs.getString("ftno"));
			}
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}		
		}
		return authList;
	}
	
	
	public static void main(String[] args) {
//新增測試
//		EmpDAO_JDBC dao = new EmpDAO_JDBC();
//		EmpVO empVO1 = new EmpVO();
//		byte[] pic;
//		try {
//			pic = getPicByteArray("C:/G6_PIC/EMP/1.jpg");
//			empVO1.setPic(pic);
//			empVO1.setEmpname("新增測試");
//			empVO1.setEmppwd(getRanPwd());
//			empVO1.setMail("166@gmail.com");
//			empVO1.setSex("male");
//			empVO1.setEmpstatus(1);
//			dao.insert(empVO1);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

		
//修改測試
//		EmpDAO_JDBC dao = new EmpDAO_JDBC();
//		EmpVO empVO2 = new EmpVO();
//		byte[] pic;
//		try {
//			pic = getPicByteArray("C:/G6_PIC/EMP/1.jpg");
//			empVO2.setPic(pic);
//			empVO2.setEmpname("修改測試");
//			empVO2.setMail("222@gmail.com");
//			empVO2.setSex("FEMALE");
//			empVO2.setEmpstatus(0);
//			empVO2.setEmpno("LE00011");
//			dao.update(empVO2);
//		}catch(IOException ie) {
//			ie.printStackTrace();
//		}
		
//單一查詢
//		EmpDAO_JDBC dao =new EmpDAO_JDBC();
//		EmpVO empVO4 = dao.findByPrimaryKey("LE00001");
//		System.out.println(empVO4.getPic());
//		System.out.println(empVO4.getEmpno());
//		System.out.println(empVO4.getEmpname());
//		System.out.println(empVO4.getEmppwd());
//		System.out.println(empVO4.getMail());
//		System.out.println(empVO4.getSex());
//		System.out.println(empVO4.getEmpstatus());
		
//單一查詢(透過名字)
//		EmpDAO_JDBC dao =new EmpDAO_JDBC();
//		EmpVO empVO9 = dao.findByEmpname("李采庭");
//		System.out.println(empVO9.getPic());
//		System.out.println(empVO9.getEmpno());
//		System.out.println(empVO9.getEmpname());
//		System.out.println(empVO9.getEmppwd());
//		System.out.println(empVO9.getMail());
//		System.out.println(empVO9.getSex());
//		System.out.println(empVO9.getEmpstatus());
	
//查詢所有員工
//		EmpDAO_JDBC dao = new EmpDAO_JDBC();
//		List<EmpVO> empList = dao.getAll();
//		for (EmpVO empVO : empList) {
//			System.out.println(empVO.getPic());
//			System.out.println(empVO.getEmpno());
//			System.out.println(empVO.getEmpname());
//			System.out.println(empVO.getEmppwd());
//			System.out.println(empVO.getMail());
//			System.out.println(empVO.getSex());
//			System.out.println(empVO.getEmpstatus());
//		}
		
//修改測試(沒有密碼)
//		EmpDAO_JDBC dao = new EmpDAO_JDBC();
//		EmpVO empVO5 = new EmpVO();
//		byte[] pic;
//		try {
//			pic = getPicByteArray("C:/G6_PIC/EMP/1.jpg");
//			empVO5.setPic(pic);
//			empVO5.setEmpname("修改修改測試");
//			empVO5.setMail("888@gmail.com");
//			empVO5.setSex("MALE");
//			empVO5.setEmpstatus(1);
//			empVO5.setEmpno("LE00021");
//			dao.updateTest(empVO5);
//		}catch(IOException ie) {
//			ie.printStackTrace();
//		}
		
//透過帳號傳EmpVO
//		EmpDAO_JDBC dao =new EmpDAO_JDBC();
//		EmpVO empVO6 = dao.login("A123456");
//		System.out.println(empVO6.getPic());
//		System.out.println(empVO6.getEmpno());
//		System.out.println(empVO6.getEmpname());
//		System.out.println(empVO6.getEmppwd());
//		System.out.println(empVO6.getMail());
//		System.out.println(empVO6.getSex());
//		System.out.println(empVO6.getEmpstatus());
		
//修改密碼		
//		EmpDAO_JDBC dao = new EmpDAO_JDBC();
//		dao.updatePwd("A123456", "LE00001");

//查詢某員工所擁有的權限 測試
		EmpDAO_JDBC dao =new EmpDAO_JDBC();
		List<String> authList = dao.getAuthorityByEmpno("LE00001");
		for(String authority : authList){
			System.out.println(authority);
			
		}	
	}
	public static byte[] getPicByteArray(String path) throws IOException {
			File pic = new File(path);
			FileInputStream fis = new FileInputStream(pic);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] eachBuffer = new byte[4096];//一次送多少出去
			int currentBytes;//當前送多少bytes出去 (ex:總共有200 bytes，一次送150 bytes，所以會送三次，每次currentBytes分別為150，150，50)
			while((currentBytes = fis.read(eachBuffer)) != -1){
				baos.write(eachBuffer, 0, currentBytes);
			}
			baos.close();
			fis.close();
			return baos.toByteArray();//回傳管子內建的byte陣列，取得裝有位元資料的byte陣列 陣列	
	}

	//取得亂數密碼
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
