package com.rminfo.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class RminfoJNDIDAO implements RminfoDAO_interface {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO RMINFO(RMNO,SHOPNO,CUTOFF,NAMING,UPLIMIT,LOWLIMIT,STARTTIME,ENDTIME,MBRNO,GAME,REMARKS,RESTRICTION) VALUES ('SR'||LPAD(TO_CHAR(RMINFO_SEQ.NEXTVAL),5,'0'),?,?,?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM RMINFO ORDER BY RMNO DESC";
	private static final String GET_ONE_STMT = "SELECT * FROM RMINFO WHERE RMNO = ?";
	private static final String UPDATE = "UPDATE RMINFO SET STATUS = ?, REPORT = ? WHERE RMNO = ?";
	private static final String GET_ONE_BY_SHOPNO = "SELECT * FROM RMINFO WHERE SHOPNO = ?";

	@Override
	public void insert(RminfoVO rminfoVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, rminfoVO.getShopno());
			pstmt.setTimestamp(2, rminfoVO.getCutoff());
			pstmt.setString(3, rminfoVO.getNaming());
			pstmt.setInt(4, rminfoVO.getUplimit());
			pstmt.setInt(5, rminfoVO.getLowlimit());
			pstmt.setTimestamp(6, rminfoVO.getStarttime());
			pstmt.setTimestamp(7, rminfoVO.getEndtime());
			pstmt.setString(8, rminfoVO.getMbrno());
			pstmt.setString(9, rminfoVO.getGame());
			pstmt.setString(10, rminfoVO.getRemarks());
			pstmt.setInt(11, rminfoVO.getRestriction());

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
	public void update(RminfoVO rminfoVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, rminfoVO.getStatus());
			pstmt.setInt(2, rminfoVO.getReport());
			pstmt.setString(3, rminfoVO.getRmno());
			
			
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
	public List<RminfoVO> getAll() {
		
		List<RminfoVO> list = new ArrayList<RminfoVO>();
		RminfoVO rminfoVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				rminfoVO = new RminfoVO();
				rminfoVO.setRmno(rs.getString("rmno"));
				rminfoVO.setMbrno(rs.getString("mbrno"));
				rminfoVO.setNaming(rs.getString("naming"));
				rminfoVO.setShopno(rs.getString("shopno"));
				rminfoVO.setLowlimit(rs.getInt("lowlimit"));
				rminfoVO.setUplimit(rs.getInt("uplimit"));
				rminfoVO.setStarttime(rs.getTimestamp("starttime"));
				rminfoVO.setEndtime(rs.getTimestamp("endtime"));
				rminfoVO.setGame(rs.getString("game"));
				rminfoVO.setCutoff(rs.getTimestamp("cutoff"));
				rminfoVO.setRemarks(rs.getString("remarks"));
				rminfoVO.setRestriction(rs.getInt("restriction"));
				rminfoVO.setCreatetime(rs.getTimestamp("createtime"));
				rminfoVO.setStatus(rs.getInt("status"));
				rminfoVO.setReport(rs.getInt("report"));
				list.add(rminfoVO); // Store the row in the list
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public RminfoVO findByRmno(String rmno) {
		
		RminfoVO rminfoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, rmno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				rminfoVO = new RminfoVO();
				rminfoVO.setMbrno(rs.getString("mbrno"));
				rminfoVO.setNaming(rs.getString("naming"));
				rminfoVO.setShopno(rs.getString("shopno"));
				rminfoVO.setLowlimit(rs.getInt("lowlimit"));
				rminfoVO.setUplimit(rs.getInt("uplimit"));
				rminfoVO.setStarttime(rs.getTimestamp("starttime"));
				rminfoVO.setEndtime(rs.getTimestamp("endtime"));
				rminfoVO.setGame(rs.getString("game"));
				rminfoVO.setCutoff(rs.getTimestamp("cutoff"));
				rminfoVO.setRemarks(rs.getString("remarks"));
				rminfoVO.setRestriction(rs.getInt("restriction"));
				rminfoVO.setStatus(rs.getInt("status"));
				rminfoVO.setReport(rs.getInt("report"));
			}

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
	
		return rminfoVO;
	}
	
	@Override
	public List<RminfoVO> findByShopno(String shopno) {
		
		List<RminfoVO> list = new ArrayList<RminfoVO>();
		RminfoVO rminfoVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;


		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_BY_SHOPNO);
			
			pstmt.setString(1, shopno);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				rminfoVO = new RminfoVO();
				rminfoVO.setRmno(rs.getString("rmno"));
				rminfoVO.setMbrno(rs.getString("mbrno"));
				rminfoVO.setNaming(rs.getString("naming"));
				rminfoVO.setShopno(rs.getString("shopno"));
				rminfoVO.setLowlimit(rs.getInt("lowlimit"));
				rminfoVO.setUplimit(rs.getInt("uplimit"));
				rminfoVO.setStarttime(rs.getTimestamp("starttime"));
				rminfoVO.setEndtime(rs.getTimestamp("endtime"));
				rminfoVO.setGame(rs.getString("game"));
				rminfoVO.setCutoff(rs.getTimestamp("cutoff"));
				rminfoVO.setRemarks(rs.getString("remarks"));
				rminfoVO.setRestriction(rs.getInt("restriction"));
				rminfoVO.setReport(rs.getInt("report"));
				rminfoVO.setStatus(rs.getInt("status"));
				list.add(rminfoVO); // Store the row in the list
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
