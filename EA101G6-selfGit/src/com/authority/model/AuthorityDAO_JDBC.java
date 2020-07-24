package com.authority.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorityDAO_JDBC implements AuthorityDAO_Interface {
	
	public static final String driver = "oracle.jdbc.driver.OracleDriver";
	public static final String url = "jdbc:oracle:thin:@localhost:1521:XE";
	public static final String userid = "EA101";
	public static final String passwd = "123456";
	
	private static final String INSERT_PSTMT = "INSERT INTO AUTHORITY (EMPNO, FTNO) VALUES (?, ?)";	
	private static final String DELETE_PSTMT = "DELETE FROM AUTHORITY WHERE EMPNO = ? AND FTNO = ?";
	private static final String DELETE_EMP_AUTH_PSTMT = "DELETE FROM AUTHORITY WHERE EMPNO = ?";
	private static final String FIND_BY_EMP = "SELECT * FROM AUTHORITY WHERE EMPNO = ?";//找某員工有哪些權限
	private static final String FIND_BY_FT = "SELECT * FROM AUTHORITY WHERE FTNO = ?";
	private static final String GET_ALL = "SELECT * FROM AUTHORITY";

	@Override
	public void insert(AuthorityVO authorityVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(INSERT_PSTMT);

			pstmt.setString(1, authorityVO.getEmpno());
			pstmt.setString(2, authorityVO.getFtno());
			
			pstmt.executeUpdate();
		}catch(ClassNotFoundException ce){
			ce.printStackTrace();
		}catch(SQLException se) {
			se.printStackTrace();
		}finally {
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
	public void delete(String empno, String ftno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_PSTMT);

			pstmt.setString(1, empno);
			pstmt.setString(2, ftno);
			
		
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
	public void deleteEmpAuth(String empno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_EMP_AUTH_PSTMT);

			pstmt.setString(1, empno);
		
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
	public List<AuthorityVO> findByEmpno(String empno) {
		List<AuthorityVO> authorityEmpList = new ArrayList(); 
		AuthorityVO authorityVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FIND_BY_EMP);
			pstmt.setString(1, empno);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				authorityVO = new AuthorityVO();
				
				authorityVO.setEmpno(rs.getString("empno"));
				authorityVO.setFtno(rs.getString("ftno"));
				authorityEmpList.add(authorityVO);
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
		return authorityEmpList;
	}

	@Override
	public List<AuthorityVO> findByFtno(String ftno) {
		List<AuthorityVO> authorityFtList = new ArrayList(); 
		AuthorityVO authorityVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FIND_BY_FT);
			pstmt.setString(1, ftno);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				authorityVO = new AuthorityVO();
				
				authorityVO.setEmpno(rs.getString("empno"));
				authorityVO.setFtno(rs.getString("ftno"));
				authorityFtList.add(authorityVO);
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
		return authorityFtList;
	}

	@Override
	public List<AuthorityVO> getAll() {
		List<AuthorityVO> authorityAll = new ArrayList();
		AuthorityVO authorityVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				authorityVO = new AuthorityVO();
				
				authorityVO.setEmpno(rs.getString("empno"));
				authorityVO.setFtno(rs.getString("ftno"));
				authorityAll.add(authorityVO);
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
		return authorityAll;
	}
	
	public static void main(String[] args) {
		
//新增測試
//		AuthorityDAO_JDBC dao = new AuthorityDAO_JDBC();
//		AuthorityVO authorityVO = new AuthorityVO();
//		authorityVO.setEmpno("LE00003");
//		authorityVO.setFtno("LF00002");
//		dao.insert(authorityVO);
		
//刪除測試
//		AuthorityDAO_JDBC dao = new AuthorityDAO_JDBC();
//		dao.delete("LE00003", "LF00002");
		
//查詢某員工擁有的權限測試
//		AuthorityDAO_JDBC dao = new AuthorityDAO_JDBC();
//		List<AuthorityVO> authorityVOEmpList = dao.findByEmpno("LE00001");
//		for(AuthorityVO authorityVO1 : authorityVOEmpList) {
//			System.out.print(authorityVO1.getEmpno() + "：");
//			System.out.print(authorityVO1.getFtno());
//			System.out.println();
//		}
		
//查詢所有擁有某權限的員工測試
//		AuthorityDAO_JDBC dao = new AuthorityDAO_JDBC();
//		List<AuthorityVO> authorityVOEmpList = dao.findByFtno("LF00003");
//		for(AuthorityVO authorityVO2 : authorityVOEmpList) {
//			System.out.print(authorityVO2.getEmpno() + "：");
//			System.out.print(authorityVO2.getFtno());
//			System.out.println();
//		}
//查詢全部的員工擁有的權限
//		AuthorityDAO_JDBC dao = new AuthorityDAO_JDBC();
//		List<AuthorityVO> authorityAll = dao.getAll();
//		for(AuthorityVO authorityVO3 : authorityAll) {
//			System.out.print(authorityVO3.getEmpno() + "：");
//			System.out.print(authorityVO3.getFtno());
//			System.out.println();
//		}
	}

}
