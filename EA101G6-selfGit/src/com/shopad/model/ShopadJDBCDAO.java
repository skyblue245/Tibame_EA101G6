package com.shopad.model;

import java.util.*;

import java.sql.*;

public class ShopadJDBCDAO implements ShopadDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "EA101";
	String passwd = "123456";
	
	
	private static final String INSERT_STMT = "INSERT INTO shopad (shopadno,shopno,shopadtt,startt,stopt,status) VALUES ('MSA'||LPAD(to_char(shopad_seq.NEXTVAL),4,'0'), ?, ?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE shopad set status = ? WHERE shopadno = ?";
	private static final String DELETE = "DELETE FROM shopad WHERE shopadno = ?";
	private static final String GET_ALL_STMT = "SELECT shopadno,shopno,shopadtt,startt,stopt,status FROM shopad ORDER BY shopadno";
	private static final String GET_ONE_STMT = "SELECT shopadno,shopno,shopadtt,startt,stopt,status FROM shopad WHERE shopadno = ?";
	private static final String GET_ONE_STATUS = "SELECT shopadno,shopno,shopadtt,startt,stopt,status FROM shopad WHERE status = ?";
	
	
	@Override
	public void insert(ShopadVO shopadVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, shopadVO.getShopno());
			pstmt.setString(2, shopadVO.getShopadtt());
			pstmt.setDate(3, shopadVO.getStartt());
			pstmt.setDate(4, shopadVO.getStopt());
			pstmt.setInt(5, shopadVO.getStatus());
			
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
	public void update(ShopadVO shopadVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, shopadVO.getStatus());
			pstmt.setString(2, shopadVO.getShopadno());
			
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
	public void delete(String shopadno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, shopadno);
			
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
	public ShopadVO findByPrimaryKey(String shopadno) {
		ShopadVO shopadVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public List<ShopadVO> getAllStatus(Integer status) {
		List<ShopadVO> list = new ArrayList<ShopadVO>();
		ShopadVO shopadVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		
		ShopadJDBCDAO dao = new ShopadJDBCDAO();
		
		//新增
//		ShopadVO sa1 = new ShopadVO();
//		sa1.setShopno("DS00007");
//		sa1.setShopadtt("老闆跳樓大特賣，打到你骨折");
//		sa1.setStartt(java.sql.Date.valueOf("2020-06-15"));
//		sa1.setStopt(java.sql.Date.valueOf("2020-07-15"));
//		sa1.setStatus(new Integer(1));
//		dao.insert(sa1);
//		System.out.println("success");
		
		//修改
//		ShopadVO sa2 = new ShopadVO();
//		sa2.setStatus(new Integer(1));
//		sa2.setShopadno("MSA0008");
//		dao.update(sa2);
//		System.out.println("success");
		
		//刪除
//		dao.delete("MSA0009");
//		System.out.println("success");
		
		//查單一
//		ShopadVO sa3 = dao.findByPrimaryKey("MSA0005");
//		System.out.print(sa3.getShopadno() + ",");
//		System.out.print(sa3.getShopno() + ",");
//		System.out.print(sa3.getShopadtt() + ",");
//		System.out.print(sa3.getStartt() + ",");
//		System.out.print(sa3.getStopt() + ",");
//		System.out.println(sa3.getStatus());
		
		
		//查全部
//		List<ShopadVO> list = dao.getAll();
//		for(ShopadVO sa4 : list) {
//			System.out.print(sa4.getShopadno() + ",");
//			System.out.print(sa4.getShopno() + ",");
//			System.out.print(sa4.getShopadtt() + ",");
//			System.out.print(sa4.getStartt() + ",");
//			System.out.print(sa4.getStopt() + ",");
//			System.out.print(sa4.getStatus());
//			System.out.println();
//			System.out.println("==============================");
//			System.out.println();
//		}
		
		List<ShopadVO> list = dao.getAllStatus(1);
		for(ShopadVO sa4 : list) {
			System.out.print(sa4.getShopadno() + ",");
			System.out.print(sa4.getShopno() + ",");
			System.out.print(sa4.getShopadtt() + ",");
			System.out.print(sa4.getStartt() + ",");
			System.out.print(sa4.getStopt() + ",");
			System.out.print(sa4.getStatus());
			System.out.println();
			System.out.println("==============================");
			System.out.println();
		}
	}

}
