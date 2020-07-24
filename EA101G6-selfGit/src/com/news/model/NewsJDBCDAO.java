package com.news.model;

import java.util.*;

import java.sql.*;

public class NewsJDBCDAO implements NewsDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "EA101";
	String passwd = "123456";
	
	
	private static final String INSERT_STMT = "INSERT INTO news (newsno,newstt,detail,pdate) VALUES ('MN'||LPAD(to_char(news_seq.NEXTVAL),5,'0'), ?, ?, CURRENT_DATE)";
	private static final String UPDATE = "UPDATE news SET newstt = ?, detail = ?, pdate = CURRENT_DATE where newsno = ?";
	private static final String GET_ONE_STMT = "SELECT newsno, newstt, detail, pdate FROM news WHERE newsno = ?";
	private static final String GET_ALL_STMT = "SELECT newsno, newstt, detail, pdate FROM news ORDER BY newsno";
	private static final String DELETE = "DELETE FROM news WHERE newsno = ?";
	

	@Override
	public void insert(NewsVO newsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			
			pstmt.setString(1, newsVO.getNewstt());
			pstmt.setString(2, newsVO.getDetail());
			
			
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
	public void update(NewsVO newsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, newsVO.getNewstt());
			pstmt.setString(2, newsVO.getDetail());
			pstmt.setString(3, newsVO.getNewsno());
			
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
	public void delete(String newsno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, newsno);
			
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
	public NewsVO findByPrimaryKey(String newsno) {
		NewsVO newsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, newsno);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				newsVO = new NewsVO();
				newsVO.setNewsno(rs.getString("newsno"));
				newsVO.setNewstt(rs.getString("newstt"));
				newsVO.setDetail(rs.getString("detail"));
				newsVO.setPdate(rs.getDate("pdate"));
				
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
		return newsVO;
	}

	@Override
	public List<NewsVO> getAll() {
		List<NewsVO> list = new ArrayList<NewsVO>();
		NewsVO newsVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			
			
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				newsVO = new NewsVO();
				newsVO.setNewsno(rs.getString("newsno"));
				newsVO.setNewstt(rs.getString("newstt"));
				newsVO.setDetail(rs.getString("detail"));
				newsVO.setPdate(rs.getDate("pdate"));
				list.add(newsVO);
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
		NewsJDBCDAO dao = new NewsJDBCDAO();
		
		//新增
//		NewsVO n1 = new NewsVO();
//		n1.setNewstt("桌遊列國的英文名字");
//		n1.setDetail("其實是叫作 GAMING ON BOARD喔喔喔喔喔喔喔 土鳳梨酥!");
//		dao.insert(n1);
//		System.out.print("success");
		
		//修改
		NewsVO n2 = new NewsVO();
		n2.setNewsno("MN00006");
		n2.setNewstt("桌遊列國的英文名字");
		n2.setDetail("才怪!ffffffffffffffffffff");
		dao.update(n2);
		System.out.print("success");
		
		//刪除
//		dao.delete("MN00006");
//		System.out.print("success");
		
		//查單一
//		NewsVO n3 = dao.findByPrimaryKey("MN00001");
//		System.out.println(n3.getNewsno());
//		System.out.println(n3.getNewstt());
//		System.out.println(n3.getDetail());
//		System.out.println(n3.getPdate());
		
		//查全部
//		List<NewsVO> list = dao.getAll();
//		for(NewsVO n4 : list) {
//			System.out.println(n4.getNewsno());
//			System.out.println(n4.getNewstt());
//			System.out.println(n4.getDetail());
//			System.out.println(n4.getPdate());
//			System.out.println();
//			System.out.println("==============================");
//			System.out.println();
//		}
	}

}
