package com.shopad.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.artrp.model.ArtrpVO;

public class ShopadDAO implements ShopadDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO shopad (shopadno,shopno,shopadtt,startt,stopt,status) VALUES ('MSA'||LPAD(to_char(shopad_seq.NEXTVAL),4,'0'), ?, ?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE shopad set status = ? WHERE shopadno = ?";
	private static final String DELETE = "DELETE FROM shopad WHERE shopadno = ?";
	private static final String GET_ALL_STMT = "SELECT shopadno,shopno,shopadtt,startt,stopt,status FROM shopad ORDER BY shopadno DESC";
	private static final String GET_ONE_STMT = "SELECT shopadno,shopno,shopadtt,startt,stopt,status FROM shopad WHERE shopadno = ?";
	private static final String GET_ONE_STATUS = "SELECT shopadno,shopno,shopadtt,startt,stopt,status FROM shopad WHERE status = ?";
	
	@Override
	public void insert(ShopadVO shopadVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, shopadVO.getShopno());
			pstmt.setString(2, shopadVO.getShopadtt());
			pstmt.setDate(3, shopadVO.getStartt());
			pstmt.setDate(4, shopadVO.getStopt());
			pstmt.setInt(5, shopadVO.getStatus());
			
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
	public void update(ShopadVO shopadVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, shopadVO.getStatus());
			pstmt.setString(2, shopadVO.getShopadno());
			
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
	public void delete(String shopadno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, shopadno);
			
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
	public ShopadVO findByPrimaryKey(String shopadno) {
		ShopadVO shopadVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, shopadno);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				shopadVO = new ShopadVO();
				shopadVO.setShopadno(rs.getString("shopadno"));
				shopadVO.setShopno(rs.getString("shopno"));
				shopadVO.setShopadtt(rs.getString("shopadtt"));
				shopadVO.setStartt(rs.getDate("startt"));
				shopadVO.setStopt(rs.getDate("stopt"));
				shopadVO.setStatus(rs.getInt("status"));
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
		return shopadVO;
	}

	

	@Override
	public List<ShopadVO> getAll() {
		List<ShopadVO> list = new ArrayList<ShopadVO>();
		ShopadVO shopadVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			
			
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				shopadVO = new ShopadVO();
				shopadVO.setShopadno(rs.getString("shopadno"));
				shopadVO.setShopno(rs.getString("shopno"));
				shopadVO.setShopadtt(rs.getString("shopadtt"));
				shopadVO.setStartt(rs.getDate("startt"));
				shopadVO.setStopt(rs.getDate("stopt"));
				shopadVO.setStatus(rs.getInt("status"));
				list.add(shopadVO);
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
	public List<ShopadVO> getAllStatus(Integer status) {
		List<ShopadVO> list = new ArrayList<ShopadVO>();
		ShopadVO shopadVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STATUS);
			
			pstmt.setInt(1, status);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				shopadVO = new ShopadVO();
				shopadVO.setShopadno(rs.getString("shopadno"));
				shopadVO.setShopno(rs.getString("shopno"));
				shopadVO.setShopadtt(rs.getString("shopadtt"));
				shopadVO.setStartt(rs.getDate("startt"));
				shopadVO.setStopt(rs.getDate("stopt"));
				shopadVO.setStatus(rs.getInt("status"));
				list.add(shopadVO);
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

}
