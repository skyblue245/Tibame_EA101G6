package com.gmlist.model;

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

import com.gmlist.model.GmlistDAO_interface;

public class GmlistJNDIDAO implements GmlistDAO_interface{

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
		"INSERT INTO gmlist (gmno, shopno) VALUES (?, ?)";
	private static final String DELETE = 
		"DELETE FROM gmlist where gmno = ? AND shopno = ?";	
	private static final String GET_ONE_GMLIST =
			"SELECT GMNO,SHOPNO FROM GMLIST WHERE GMNO = ? AND SHOPNO = ?";
	private static final String GET_GMLIST_BY_GAME =
		"SELECT GMNO,SHOPNO FROM GMLIST WHERE GMNO = ?";
	private static final String GET_GMLIST_BY_SHOP =
		"SELECT GMNO,SHOPNO FROM GMLIST WHERE SHOPNO = ?";
	private static final String GET_ALL_GMLIST =
			"SELECT GMNO,SHOPNO FROM GMLIST ORDER BY GMNO, SHOPNO";	
			
	@Override
	public void insert(GmlistVO gmlistVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, gmlistVO.getGmno());
			pstmt.setString(2, gmlistVO.getShopno());

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
	public void insert2(GmlistVO gmlistVO, java.sql.Connection con) {
		PreparedStatement pstmt = null;
		try {

			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, gmlistVO.getGmno());
			pstmt.setString(2, gmlistVO.getShopno());

			pstmt.executeUpdate();

			// Handle any driver errors
		}catch (SQLException se) {
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
		}
	}

	@Override
	public void delete(String gmno, String shopno) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, gmno);
			pstmt.setString(2, shopno);

			pstmt.executeUpdate();

			// Handle any driver errors
		}catch (SQLException se) {
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
	public GmlistVO findByPrimaryKey(String gmno, String shopno) {
		
		GmlistVO gmlistVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_GMLIST);
			
			pstmt.setString(1, gmno);
			pstmt.setString(2, shopno);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				gmlistVO = new GmlistVO();
				gmlistVO.setGmno(rs.getString("gmno"));
				gmlistVO.setShopno(rs.getString("shopno"));
			}
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		}finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
			
		return gmlistVO;		
	}

	@Override
	public List<GmlistVO> findByGame(String gmno) {
		List<GmlistVO> list = new ArrayList<GmlistVO>();
		GmlistVO gmlistVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_GMLIST_BY_GAME);
			
			pstmt.setString(1, gmno);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				gmlistVO = new GmlistVO();
				gmlistVO.setGmno(rs.getString("gmno"));
				gmlistVO.setShopno(rs.getString("shopno"));
				list.add(gmlistVO);
			}
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		}finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
			
		return list;		
	}

	@Override
	public List<GmlistVO> findByShop(String shopno) {
		
		List<GmlistVO> list = new ArrayList<GmlistVO>();
		GmlistVO gmlistVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_GMLIST_BY_SHOP);
			
			pstmt.setString(1, shopno);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				gmlistVO = new GmlistVO();
				gmlistVO.setGmno(rs.getString("gmno"));
				gmlistVO.setShopno(rs.getString("shopno"));
				list.add(gmlistVO);
			}
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		}finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
			
		return list;		
	}
	
	@Override
	public List<GmlistVO> getAll() {
		List<GmlistVO> list = new ArrayList<GmlistVO>();
		GmlistVO gmlistVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_GMLIST);		
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				gmlistVO = new GmlistVO();
				gmlistVO.setGmno(rs.getString("gmno"));
				gmlistVO.setShopno(rs.getString("shopno"));
				list.add(gmlistVO);
			}
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		}finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
			
		return list;
	}
	

}
