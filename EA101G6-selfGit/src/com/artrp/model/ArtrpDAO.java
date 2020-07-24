package com.artrp.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ArtrpDAO implements ArtrpDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO artrp (artrpno,artno,detail,mbrno,status) VALUES ('MAR'||LPAD(to_char(artrp_seq.NEXTVAL),4,'0'), ?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE artrp set status = ? WHERE artrpno = ?";
	private static final String DELETE = "DELETE FROM artrp WHERE artrpno = ?";
	private static final String GET_ALL_STMT = "SELECT artrpno,artno,detail,mbrno,status FROM artrp ORDER BY artrpno";
	private static final String GET_ONE_STMT = "SELECT artrpno,artno,detail,mbrno,status FROM artrp WHERE artrpno = ?";
	private static final String GET_ALL_BY_ARTNO = "SELECT artrpno,artno,detail,mbrno,status FROM artrp WHERE artno = ? ORDER BY artrpno DESC";
	private static final String GET_ALL_BY_STATUS = "SELECT artrpno,artno,detail,mbrno,status FROM artrp WHERE status = ? ORDER BY artrpno DESC";
	
	
	

	@Override
	public void insert(ArtrpVO artrpVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			
			pstmt.setString(1, artrpVO.getArtno());
			pstmt.setString(2, artrpVO.getDetail());
			pstmt.setString(3, artrpVO.getMbrno());
			pstmt.setInt(4, artrpVO.getStatus());
			
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
	public void update(ArtrpVO artrpVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, artrpVO.getStatus());
			pstmt.setString(2, artrpVO.getArtrpno());
			
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
	public void delete(String artrpno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, artrpno);
			
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
	public ArtrpVO findByPrimaryKey(String artrpno) {
		ArtrpVO artrpVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, artrpno);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				artrpVO = new ArtrpVO();
				artrpVO.setArtrpno(rs.getString("artrpno"));
				artrpVO.setArtno(rs.getString("artno"));
				artrpVO.setDetail(rs.getString("detail"));
				artrpVO.setMbrno(rs.getString("mbrno"));
				artrpVO.setStatus(rs.getInt("status"));
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
		
		return artrpVO;
	}

	@Override
	public List<ArtrpVO> getAll() {
		List<ArtrpVO> list = new ArrayList<ArtrpVO>();
		ArtrpVO artrpVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			
			
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				artrpVO = new ArtrpVO();
				artrpVO.setArtrpno(rs.getString("artrpno"));
				artrpVO.setArtno(rs.getString("artno"));
				artrpVO.setDetail(rs.getString("detail"));
				artrpVO.setMbrno(rs.getString("mbrno"));
				artrpVO.setStatus(rs.getInt("status"));
				list.add(artrpVO);
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
	public List<ArtrpVO> getAllByArtno(String artno) {
		List<ArtrpVO> list = new ArrayList<ArtrpVO>();
		ArtrpVO artrpVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_ARTNO);
			
			pstmt.setString(1, artno);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				artrpVO = new ArtrpVO();
				artrpVO.setArtrpno(rs.getString("artrpno"));
				artrpVO.setArtno(rs.getString("artno"));
				artrpVO.setDetail(rs.getString("detail"));
				artrpVO.setMbrno(rs.getString("mbrno"));
				artrpVO.setStatus(rs.getInt("status"));
				list.add(artrpVO);
			}
		} catch(SQLException se) {
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
	public List<ArtrpVO> getAllByStatus(Integer status) {
		List<ArtrpVO> list = new ArrayList<ArtrpVO>();
		ArtrpVO artrpVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_STATUS);
			
			pstmt.setInt(1, status);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				artrpVO = new ArtrpVO();
				artrpVO.setArtrpno(rs.getString("artrpno"));
				artrpVO.setArtno(rs.getString("artno"));
				artrpVO.setDetail(rs.getString("detail"));
				artrpVO.setMbrno(rs.getString("mbrno"));
				artrpVO.setStatus(rs.getInt("status"));
				list.add(artrpVO);
			}
		} catch(SQLException se) {
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
