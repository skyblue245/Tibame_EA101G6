package com.shoprpdt.model;

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

public class ShoprpdtJNDIDAO implements ShoprpdtDAO_interface {

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
			"INSERT INTO SHOPRPDT (SHOPRPNO, RMNO, MBRNO, SHOPNO, DETAIL, STATUS) VALUES ('DR'||LPAD(TO_CHAR(SHOPRPDT_SEQ.NEXTVAL), 5, '0'), ?, ?, ?, ?, ?)";
	private static final String UPDATE =
			"UPDATE SHOPRPDT SET STATUS=? WHERE SHOPRPNO = ?";
	private static final String GET_ALL_STMT =
			"SELECT * FROM SHOPRPDT ORDER BY SHOPRPNO";
	private static final String GET_SOME_STMT =
			"SELECT * FROM SHOPRPDT WHERE SHOPRPNO = ? OR STATUS = ?";
	@Override
	public void insert(ShoprpdtVO shoprpdtVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, shoprpdtVO.getRmno());
			pstmt.setString(2, shoprpdtVO.getMbrno());
			pstmt.setString(3, shoprpdtVO.getShopno());
			pstmt.setString(4, shoprpdtVO.getDetail());
			pstmt.setInt(5, shoprpdtVO.getStatus());
			
			pstmt.executeUpdate();
			
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
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
	public void update(ShoprpdtVO shoprpdtVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			
			pstmt.setInt(1, shoprpdtVO.getStatus());
			pstmt.setString(2, shoprpdtVO.getShoprpno());
			
			pstmt.executeUpdate();
			
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
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
	public List<ShoprpdtVO> findByPrimaryKey(String shoprpdtno, Integer status) {
		List<ShoprpdtVO> list = new ArrayList<ShoprpdtVO>();
		ShoprpdtVO shoprpdtVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_SOME_STMT);
			
			pstmt.setString(1, shoprpdtno);
			pstmt.setInt(2, status);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				shoprpdtVO = new ShoprpdtVO();
				shoprpdtVO.setShoprpno(rs.getString("shoprpno"));
				shoprpdtVO.setRmno(rs.getString("rmno"));
				shoprpdtVO.setMbrno(rs.getString("mbrno"));
				shoprpdtVO.setShopno(rs.getString("shopno"));
				shoprpdtVO.setDetail(rs.getString("detail"));
				shoprpdtVO.setStatus(rs.getInt("status"));
				list.add(shoprpdtVO);
			}
			
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
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
	public List<ShoprpdtVO> getAll() {
		List<ShoprpdtVO> list = new ArrayList<ShoprpdtVO>();
		ShoprpdtVO shoprpdtVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
					
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				shoprpdtVO = new ShoprpdtVO();
				shoprpdtVO.setShoprpno(rs.getString("shoprpno"));
				shoprpdtVO.setRmno(rs.getString("rmno"));
				shoprpdtVO.setMbrno(rs.getString("mbrno"));
				shoprpdtVO.setShopno(rs.getString("shopno"));
				shoprpdtVO.setDetail(rs.getString("detail"));
				shoprpdtVO.setStatus(rs.getInt("status"));
				list.add(shoprpdtVO);
			}
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
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
