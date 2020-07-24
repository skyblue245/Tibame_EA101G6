package com.msgrp.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MsgrpJNDIDAO implements MsgrpDAO_interface {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

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

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, msgrpVO.getMsgno());
			pstmt.setString(2, msgrpVO.getDetail());
			pstmt.setString(3, msgrpVO.getMbrno());
			pstmt.setInt(4, msgrpVO.getStatus());
			

			pstmt.executeUpdate();

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

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, msgrpVO.getMsgno());
			pstmt.setString(2, msgrpVO.getDetail());
			pstmt.setString(3, msgrpVO.getMbrno());
			pstmt.setInt(4, msgrpVO.getStatus());

			pstmt.executeUpdate();

			// Handle any driver errors
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, msgrpno);

			pstmt.executeUpdate();

			// Handle any driver errors
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

			con = ds.getConnection();
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

			con = ds.getConnection();
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
}