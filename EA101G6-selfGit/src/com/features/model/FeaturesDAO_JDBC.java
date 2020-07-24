package com.features.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FeaturesDAO_JDBC implements FeaturesDAO_Interface{
	public static final String driver = "oracle.jdbc.driver.OracleDriver";
	public static final String url = "jdbc:oracle:thin:@localhost:1521:XE";
	public static final String userid = "EA101";
	public static final String passwd = "123456";
	
	private static final String INSERT_PSTMT = "INSERT INTO FEATURES (FTNO, FTNAME) VALUES ('LF'||LPAD(TO_CHAR(FEATURES_SEQ.NEXTVAL),5,'0'), ?)";
	private static final String UPDATE_PSTMT = "UPDATE FEATURES SET FTNAME = ? WHERE FTNO = ?"; 	
	private static final String DELETE_PSTMT = "DELETE FROM FEATURES WHERE FTNO = ?";
	private static final String FIND_BY_PK = "SELECT * FROM FEATURES WHERE FTNO = ?";
	private static final String GET_ALL = "SELECT * FROM FEATURES";
	
	@Override
	public void insert(FeaturesVO featuresVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(INSERT_PSTMT);

			pstmt.setString(1, featuresVO.getFtname());
			pstmt.executeUpdate();
		}catch(ClassNotFoundException ce){
			ce.printStackTrace();
			System.out.println("載入驅動失敗");
		}catch(SQLException se) {
			se.printStackTrace();
			System.out.println("建立連線失敗");
		}finally {//做關閉連線的動作
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
	}
	
	@Override
	public void update(FeaturesVO featuresVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_PSTMT);
			
			pstmt.setString(1, featuresVO.getFtname());
			pstmt.setString(2, featuresVO.getFtno());
		
			pstmt.executeUpdate();
		}catch(ClassNotFoundException ce) {
			ce.printStackTrace();
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
	public void delete(String ftno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_PSTMT);

			pstmt.setString(1, ftno);
		
			pstmt.executeUpdate();
		}catch(ClassNotFoundException ce) {
			ce.printStackTrace();
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
	public FeaturesVO findByPrimaryKey(String ftno) {
		FeaturesVO featuresVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setString(1, ftno);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				featuresVO = new FeaturesVO();
				
				featuresVO.setFtno(rs.getString("ftno"));
				featuresVO.setFtname(rs.getString("ftname"));
			}
		}catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}catch (SQLException se) {
			se.printStackTrace();
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
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
		}
		return featuresVO;
	}
	@Override
	public List<FeaturesVO> getAll() {
		List<FeaturesVO> featuresAll = new ArrayList();
		FeaturesVO featuresVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				featuresVO = new FeaturesVO();
				
				featuresVO.setFtno(rs.getString("ftno"));
				featuresVO.setFtname(rs.getString("ftname"));
				featuresAll.add(featuresVO);
			}
		}catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}catch (SQLException se) {
			se.printStackTrace();
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
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
		}
		return featuresAll;	
	}
	

	public static void main(String[] args) {
		
//新增測試
//		FeaturesDAO_JDBC dao = new FeaturesDAO_JDBC();
//		FeaturesVO featuresVO = new FeaturesVO();
//		featuresVO.setFtname("測試權限名字");
//		dao.insert(featuresVO);
		
//修改測試
//		FeaturesDAO_JDBC dao = new FeaturesDAO_JDBC();
//		FeaturesVO featuresVO1 = new FeaturesVO();
//		featuresVO1.setFtname("修改權限測試名字");
//		featuresVO1.setFtno("LF00008");
//		dao.update(featuresVO1);
		
//刪除測試
//		FeaturesDAO_JDBC dao = new FeaturesDAO_JDBC();
//		dao.delete("LF00008");
		
//查詢某個權限
//		FeaturesDAO_JDBC dao = new FeaturesDAO_JDBC();
//		FeaturesVO featuresVO2 = dao.findByPrimaryKey("LF00007");
//		System.out.println(featuresVO2.getFtno());
//		System.out.println(featuresVO2.getFtname());
		
//查詢全部權限
		FeaturesDAO_JDBC dao = new FeaturesDAO_JDBC();
		List<FeaturesVO> featuresAll = dao.getAll();
		for(FeaturesVO featuresVO3 : featuresAll) {
			System.out.print(featuresVO3.getFtno() + ",");
			System.out.print(featuresVO3.getFtname());
			System.out.println();
		}
	}
	
	

}
