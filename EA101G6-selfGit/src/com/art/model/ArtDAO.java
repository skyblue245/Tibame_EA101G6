package com.art.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class ArtDAO implements ArtDAO_interface {

	//一個應用程式中，針對一個資料庫，共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO art (artno,mbrno,detail,arttt,pdate,status,atno,apic) VALUES ('MA'||LPAD(to_char(art_seq.NEXTVAL),5,'0'), ?, ?, ?, CURRENT_DATE, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT artno,mbrno,detail,arttt,to_char(pdate,'yyyy-mm-dd')pdate,status,atno,apic FROM art order by artno";
	private static final String GET_ONE_STMT = "SELECT artno,mbrno,detail,arttt,to_char(pdate,'yyyy-mm-dd')pdate,status,atno,apic FROM art where artno = ?";

	private static final String GET_STMT_MBRNO = "SELECT artno,mbrno,detail,arttt,to_char(pdate,'yyyy-mm-dd')pdate,status,atno,apic FROM art where mbrno = ?";
	private static final String GET_STMT_ATNO = "SELECT artno,mbrno,detail,arttt,to_char(pdate,'yyyy-mm-dd')pdate,status,atno,apic FROM art where atno = ?";
	private static final String GET_STMT_STATUS = "SELECT artno,mbrno,detail,arttt,to_char(pdate,'yyyy-mm-dd')pdate,status,atno,apic FROM art where status = ?";
	private static final String DELETE = "DELETE FROM art where artno = ?";
	private static final String UPDATE = "UPDATE art set detail=?, arttt=?, atno=?, apic=? where artno = ?";
	private static final String GET_ARTTT = "SELECT artno,mbrno,detail,arttt,to_char(pdate,'yyyy-mm-dd')pdate,atno,apic FROM art WHERE arttt LIKE ?";
	private static final String UPDATE_ART_STATUS = "UPDATE art set status = ? WHERE artno = ?";
	private static final String DELETE_ARTRP = "DELETE FROM artrp WHERE artno = ?";
	
	
	@Override
	public void insert(ArtVO artVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, artVO.getMbrno());
			pstmt.setString(2, artVO.getDetail());
			pstmt.setString(3, artVO.getArttt());
			pstmt.setInt(4, artVO.getStatus());
			pstmt.setString(5, artVO.getAtno());
			pstmt.setBytes(6, artVO.getApic());

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
	public void update(ArtVO artVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, artVO.getDetail());
			pstmt.setString(2, artVO.getArttt());
			
			
			pstmt.setString(3, artVO.getAtno());
			pstmt.setBytes(4, artVO.getApic());
			pstmt.setString(5, artVO.getArtno());
			
			
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
	public void update_art_status(ArtVO artVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_ART_STATUS);

			
			pstmt.setInt(1, artVO.getStatus());
			pstmt.setString(2, artVO.getArtno());
			
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
	public void delete(String artno) {
		int updateCount_ARTs = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			
			//1.設定於pstmt.executeUpdate()之前
			con.setAutoCommit(false);
			
			//先刪除文章檢舉
			pstmt = con.prepareStatement(DELETE_ARTRP);
			pstmt.setString(1, artno);
			updateCount_ARTs = pstmt.executeUpdate();
			
			//再刪除分類
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, artno);
			pstmt.executeUpdate();
			
			//2.設定於pstmt.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("刪除分類編號" + artno + "時,共有文章" + updateCount_ARTs + "篇同時被刪除");
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
	public ArtVO findByPrimaryKey(String artno) {
		ArtVO artVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, artno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				artVO = new ArtVO();
				artVO.setArtno(rs.getString("artno"));
				artVO.setMbrno(rs.getString("mbrno"));
				artVO.setDetail(rs.getString("detail"));
				artVO.setArttt(rs.getString("arttt"));
				artVO.setPdate(rs.getDate("pdate"));
				
				artVO.setStatus(rs.getInt("status"));
				artVO.setAtno(rs.getString("atno"));
				artVO.setApic(rs.getBytes("apic"));
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
		return artVO;
	}
	
	

	@Override
	public List<ArtVO> getAll() {
		List<ArtVO> list = new ArrayList<ArtVO>();
		ArtVO artVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				artVO = new ArtVO();
				artVO.setArtno(rs.getString("artno"));
				artVO.setMbrno(rs.getString("mbrno"));
				artVO.setDetail(rs.getString("detail"));
				artVO.setArttt(rs.getString("arttt"));
				artVO.setPdate(rs.getDate("pdate"));
				
				artVO.setStatus(rs.getInt("status"));
				artVO.setAtno(rs.getString("atno"));
				artVO.setApic(rs.getBytes("apic"));
				
				list.add(artVO); // Store the row in the list
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

	@Override
	public List<ArtVO> getArtsByArttt(String arttt) {
		List<ArtVO> list = new ArrayList<ArtVO>();
		ArtVO artVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ARTTT);
			
			pstmt.setString(1,"%" +arttt+ "%");
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				artVO = new ArtVO();
				artVO.setArtno(rs.getString("artno"));
				artVO.setMbrno(rs.getString("mbrno"));
				artVO.setDetail(rs.getString("detail"));
				artVO.setArttt(rs.getString("arttt"));
				artVO.setPdate(rs.getDate("pdate"));
				
				artVO.setAtno(rs.getString("atno"));
				artVO.setApic(rs.getBytes("apic"));
				
				list.add(artVO);
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
	public List<ArtVO> getArtsByMbrno(String mbrno) {
		List<ArtVO> list = new ArrayList<ArtVO>();
		ArtVO artVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_STMT_MBRNO);
			
			pstmt.setString(1, mbrno);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				artVO = new ArtVO();
				artVO.setArtno(rs.getString("artno"));
				artVO.setMbrno(rs.getString("mbrno"));
				artVO.setArttt(rs.getString("arttt"));
				artVO.setDetail(rs.getString("detail"));
				artVO.setPdate(rs.getDate("pdate"));
				
				artVO.setStatus(rs.getInt("status"));
				artVO.setAtno(rs.getString("atno"));
				artVO.setApic(rs.getBytes("apic"));
				
				list.add(artVO);
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
	public List<ArtVO> getArtsByAtno(String atno) {
		List<ArtVO> list = new ArrayList<ArtVO>();
		ArtVO artVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_STMT_ATNO);
			
			pstmt.setString(1, atno);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				artVO = new ArtVO();
				artVO.setArtno(rs.getString("artno"));
				artVO.setMbrno(rs.getString("mbrno"));
				artVO.setArttt(rs.getString("arttt"));
				artVO.setDetail(rs.getString("detail"));
				artVO.setPdate(rs.getDate("pdate"));
				
				artVO.setStatus(rs.getInt("status"));
				artVO.setAtno(rs.getString("atno"));
				artVO.setApic(rs.getBytes("apic"));
				
				list.add(artVO);
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
	public List<ArtVO> getArtsByStatus(Integer status) {
		List<ArtVO> list = new ArrayList<ArtVO>();
		ArtVO artVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_STMT_STATUS);
			
			pstmt.setInt(1, status);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				artVO = new ArtVO();
				artVO.setArtno(rs.getString("artno"));
				artVO.setMbrno(rs.getString("mbrno"));
				artVO.setArttt(rs.getString("arttt"));
				artVO.setDetail(rs.getString("detail"));
				artVO.setPdate(rs.getDate("pdate"));
				
				artVO.setStatus(rs.getInt("status"));
				artVO.setAtno(rs.getString("atno"));
				artVO.setApic(rs.getBytes("apic"));
				
				list.add(artVO);
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
