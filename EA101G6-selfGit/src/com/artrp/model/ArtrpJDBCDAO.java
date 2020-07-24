package com.artrp.model;

import java.util.*;



import java.sql.*;

public class ArtrpJDBCDAO implements ArtrpDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "EA101";
	String passwd = "123456";
	
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
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			
			pstmt.setString(1, artrpVO.getArtno());
			pstmt.setString(2, artrpVO.getDetail());
			pstmt.setString(3, artrpVO.getMbrno());
			pstmt.setInt(4, artrpVO.getStatus());
			
			pstmt.executeUpdate();
			
			
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
	public void update(ArtrpVO artrpVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, artrpVO.getStatus());
			pstmt.setString(2, artrpVO.getArtrpno());
			
			pstmt.executeUpdate();
			
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
	public void delete(String artrpno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, artrpno);
			
			pstmt.executeUpdate();
			
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
	public ArtrpVO findByPrimaryKey(String artrpno) {
		ArtrpVO artrpVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public List<ArtrpVO> getAllByArtno(String artno) {
		List<ArtrpVO> list = new ArrayList<ArtrpVO>();
		ArtrpVO artrpVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
	
	
	
	
	public static void main(String[] args) {
		
		ArtrpJDBCDAO dao = new ArtrpJDBCDAO();
		
		//新增
//		ArtrpVO a1 = new ArtrpVO();
//		a1.setArtno("MA00010");
//		a1.setDetail("寫太多了，看了想睡覺!");
//		a1.setMbrno("BM00013");
//		a1.setStatus(new Integer(0));
//		dao.insert(a1);
//		System.out.println("success");
		
		//修改
//		ArtrpVO a2 = new ArtrpVO();
//		a2.setStatus(new Integer(0));
//		a2.setArtrpno("MAR0007");
//		dao.update(a2);
//		System.out.println("success");
		
		//刪除
//		dao.delete("MAR0011");
//		System.out.println("success");
		
		
		//查單一
//		ArtrpVO a4 = dao.findByPrimaryKey("MAR0007");
//		System.out.print(a4.getArtrpno() + ",");
//		System.out.print(a4.getArtno() + ",");
//		System.out.print(a4.getDetail() + ",");
//		System.out.print(a4.getMbrno() + ",");
//		System.out.println(a4.getStatus());
		
		//查全部
//		List<ArtrpVO> list = dao.getAll();
//		for (ArtrpVO a5 : list) {
//			System.out.print(a5.getArtrpno() + ",");
//			System.out.print(a5.getArtno() + ",");
//			System.out.print(a5.getDetail() + ",");
//			System.out.print(a5.getMbrno() + ",");
//			System.out.println(a5.getStatus());
//			System.out.println();
//			System.out.println("==============================");
//			System.out.println();
//		}
		
		//查全部
				List<ArtrpVO> list = dao.getAllByStatus(1);
				for (ArtrpVO a5 : list) {
					System.out.print(a5.getArtrpno() + ",");
					System.out.print(a5.getArtno() + ",");
					System.out.print(a5.getDetail() + ",");
					System.out.print(a5.getMbrno() + ",");
					System.out.println(a5.getStatus());
					System.out.println();
					System.out.println("==============================");
					System.out.println();
				}
	}
	
}
