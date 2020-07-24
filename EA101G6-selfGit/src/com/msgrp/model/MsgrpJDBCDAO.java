package com.msgrp.model;

import java.util.*;
import java.sql.*;

public class MsgrpJDBCDAO implements MsgrpDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "EA101";
	String passwd = "123456";

	private static final String INSERT_STMT = 
		"INSERT INTO msgrp (msgrpno,msgno,detail,mbrno,status) VALUES (bmp_seq.NEXTVAL, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT msgrpno,msgno,detail,mbrno,status FROM msgrp order by msgrpno";
	private static final String GET_ONE_STMT = 
		"SELECT msgrpno,msgno,detail,mbrno,status FROM msgrp where msgrpno = ?";
	private static final String DELETE = 
		"DELETE FROM msgrp where msgrpno = ?";
	private static final String UPDATE = 
		"UPDATE msgrp set msgrpno=?,msgno=?,detail=?,mbrno=?,status=? where msgrpno = ?";

	@Override
	public void insert(MsgrpVO msgrpVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			
			
			pstmt.setString(1, msgrpVO.getMsgno());
			pstmt.setString(2, msgrpVO.getDetail());
			pstmt.setString(3, msgrpVO.getMbrno());
			pstmt.setInt(4, msgrpVO.getStatus());
			

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
	public void update(MsgrpVO msgrpVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, msgrpVO.getMsgno());
			pstmt.setString(2, msgrpVO.getDetail());
			pstmt.setString(3, msgrpVO.getMbrno());
			pstmt.setInt(4, msgrpVO.getStatus());

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
	public void delete(String msgrpno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, msgrpno);

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
	public MsgrpVO findByPrimaryKey(String msgrpno) {

		MsgrpVO msgrpVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, msgrpno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// msgrpVO 也稱為 Domain objects
				msgrpVO = new MsgrpVO();
				msgrpVO.setMsgrpno(rs.getString("msgrpno"));
				msgrpVO.setMsgno(rs.getString("msgno"));
				msgrpVO.setDetail(rs.getString("detail"));
				msgrpVO.setMbrno(rs.getString("mbrno"));
				msgrpVO.setStatus(rs.getInt("status"));
				
				
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
		return msgrpVO;
	}

	@Override
	public List<MsgrpVO> getAll() {
		List<MsgrpVO> list = new ArrayList<MsgrpVO>();
		MsgrpVO msgrpVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// msgrpVO 也稱為 Domain objects
				msgrpVO = new MsgrpVO();
				msgrpVO.setMsgrpno(rs.getString("msgrpno"));
				msgrpVO.setMsgno(rs.getString("msgno"));
				msgrpVO.setDetail(rs.getString("detail"));
				msgrpVO.setMbrno(rs.getString("mbrno"));
				msgrpVO.setStatus(rs.getInt("status"));
				list.add(msgrpVO); // Store the row in the list
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

	public static void main(String[] args) {

		MsgrpDAO dao = new MsgrpDAO();

		// 新增
		MsgrpVO msgrpVO1 = new MsgrpVO();
		msgrpVO1.setMsgrpno("555");
		msgrpVO1.setMsgno("555");
		msgrpVO1.setDetail("555");
		msgrpVO1.setMbrno("555");
		msgrpVO1.setStatus(1);
		
		dao.insert(msgrpVO1);

		// 修改
		MsgrpVO msgrpVO2 = new MsgrpVO();
		msgrpVO2.setMsgrpno("666");
		msgrpVO2.setMsgno("555");
		msgrpVO2.setDetail("555");
		msgrpVO2.setMbrno("555");
		msgrpVO2.setStatus(1);
		
		dao.update(msgrpVO2);

		// 刪除
		dao.delete("666");

		// 查詢
		MsgrpVO msgrpVO3 = dao.findByPrimaryKey("666");
		System.out.print(msgrpVO3.getMsgrpno() + ",");
		System.out.print(msgrpVO3.getMsgno() + ",");
		System.out.print(msgrpVO3.getDetail() + ",");
		System.out.print(msgrpVO3.getMbrno() + ",");
		System.out.print(msgrpVO3.getStatus() + ",");
		
		System.out.println("---------------------");

		// 查詢
		List<MsgrpVO> list = dao.getAll();
		for (MsgrpVO aMsgrp : list) {
			System.out.print(aMsgrp.getMsgrpno() + ",");
			System.out.print(aMsgrp.getMsgno() + ",");
			System.out.print(aMsgrp.getDetail() + ",");
			System.out.print(aMsgrp.getMbrno() + ",");
			System.out.print(aMsgrp.getStatus() + ",");
			
			System.out.println();
		}
	}
}