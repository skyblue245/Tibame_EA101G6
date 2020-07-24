package com.joinrm.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class JoinrmJNDIDAO implements JoinrmDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
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


			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, joinrmVO.getMbrno());
			pstmt.setString(2, joinrmVO.getRmno());
			
			pstmt.executeUpdate();

			
		}  catch (SQLException se) {
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

			con = ds.getConnection();
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, rmno);
			pstmt.setString(2, mbrno);
			

			pstmt.executeUpdate();

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
	public List<JoinrmVO> getAll() {
		
		List<JoinrmVO> list2 = new ArrayList<JoinrmVO>();
		
		JoinrmVO joinrmVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);


			rs = pstmt.executeQuery();

			while (rs.next()) {
				joinrmVO = new JoinrmVO();
				joinrmVO.setRmno(rs.getString("rmno"));
				joinrmVO.setMbrno(rs.getString("mbrno"));
				list2.add(joinrmVO);
				
			}

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

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, joinrmVO.getRatereport());
			pstmt.setInt(2, joinrmVO.getShopreport());
			pstmt.setString(3, joinrmVO.getRmno());
			pstmt.setString(4, joinrmVO.getMbrno());
			
			
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
}
