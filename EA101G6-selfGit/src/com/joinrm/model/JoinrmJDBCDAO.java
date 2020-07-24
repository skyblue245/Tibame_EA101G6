package com.joinrm.model;

import java.sql.*;
import java.util.*;

public class JoinrmJDBCDAO implements JoinrmDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "EA101";
	String passwd = "123456";
	
	private static final String INSERT_STMT = "INSERT INTO JOINRM(MBRNO,RMNO) VALUES (?,?)";
	private static final String GET_ROOMMENBER_STMT = "SELECT * FROM JOINRM WHERE RMNO = ? OR MBRNO = ?";
	private static final String DELETE = "DELETE FROM JOINRM WHERE RMNO = ? AND MBRNO = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM JOINRM";
	private static final String UPDATE = "UPDATE JOINRM SET RATEREPORT =? AND SHOPREPORT = ? WHERE RMNO = ? AND MBRNO = ?";
	
	@Override
	public void insert(JoinrmVO joinrmVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, joinrmVO.getMbrno());
			pstmt.setString(2, joinrmVO.getRmno());
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}
	
	
	
	@Override
	public List<JoinrmVO> findByPK(String rmno,String mbrno) {
		
		List<JoinrmVO> list = new ArrayList<JoinrmVO>();
		
		JoinrmVO joinrmVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ROOMMENBER_STMT);

			pstmt.setString(1,rmno);
			pstmt.setString(2,mbrno);
			

			rs = pstmt.executeQuery();

			while (rs.next()) {
				joinrmVO = new JoinrmVO();
				joinrmVO.setRmno(rs.getString("rmno"));
				joinrmVO.setMbrno(rs.getString("mbrno"));
				list.add(joinrmVO);
				
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	



	@Override
	public void delete(String rmno, String mbrno) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, rmno);
			pstmt.setString(2, mbrno);
			

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}



	public static void main(String[] args) {
		JoinrmJDBCDAO dao = new JoinrmJDBCDAO();
		//�s�W(�|���[�J�ж�)
//		JoinrmVO joinrmVO1 = new JoinrmVO();
//		joinrmVO1.setMbrno("BM00002");
//		joinrmVO1.setRmno("SR00011");
//		dao.insert(joinrmVO1);
		
		//�d�|�����[�J���ж�OR�d�ж������|��
//		List<JoinrmVO> list = dao.findByPK("SR00001","");
//		for(JoinrmVO rmmbr : list) {
//			System.out.println(rmmbr.getRmno());
//			System.out.println(rmmbr.getMbrno());
//		}
//		
//		List<JoinrmVO> list = dao.findByPK("","BM00004");
//		for(JoinrmVO rm : list) {
//			System.out.println(rm.getRmno());
//			System.out.println(rm.getMbrno());
//		}
		
		//�R��(�|�����}�ж�)
		dao.delete("SR00005", "BM00010");
	}
	
	@Override
	public List<JoinrmVO> getAll() {
		
		List<JoinrmVO> list2 = new ArrayList<JoinrmVO>();
		
		JoinrmVO joinrmVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);


			rs = pstmt.executeQuery();

			while (rs.next()) {
				joinrmVO = new JoinrmVO();
				joinrmVO.setRmno(rs.getString("rmno"));
				joinrmVO.setMbrno(rs.getString("mbrno"));
				list2.add(joinrmVO);
				
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list2;
	}
	
	@Override
	public void update(JoinrmVO joinrmVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, joinrmVO.getRatereport());
			pstmt.setInt(2, joinrmVO.getShopreport());
			pstmt.setString(3, joinrmVO.getRmno());
			pstmt.setString(4, joinrmVO.getMbrno());
			
			
			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

		
	}
}
