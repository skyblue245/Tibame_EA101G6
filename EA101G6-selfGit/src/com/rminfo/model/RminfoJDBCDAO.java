package com.rminfo.model;

import java.sql.*;
import java.util.*;

public class RminfoJDBCDAO implements RminfoDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "EA101";
	String passwd = "123456";
	
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

			// Handle any driver errors
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
	public void update(RminfoVO rminfoVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, rminfoVO.getStatus());
			pstmt.setInt(2, rminfoVO.getReport());
			pstmt.setString(3, rminfoVO.getRmno());
			
			
			pstmt.executeUpdate();

			// Handle any driver errors
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
	public List<RminfoVO> getAll() {
		
		List<RminfoVO> list = new ArrayList<RminfoVO>();
		RminfoVO rminfoVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

			// Handle any driver errors
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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

	public static void main(String[] args) {
		RminfoJDBCDAO dao = new RminfoJDBCDAO();

//		RminfoVO rminfoVO1 = new RminfoVO();
//		rminfoVO1.setShopno("DS00001");
//		rminfoVO1.setCutoff(java.sql.Timestamp.valueOf("2020-06-10 12:00:00"));
//		rminfoVO1.setNaming("�o�Ѯv�b���T�p");
//		rminfoVO1.setUplimit(8);
//		rminfoVO1.setLowlimit(4);
//		rminfoVO1.setStarttime(java.sql.Timestamp.valueOf("2020-06-13 09:00:00"));
//		rminfoVO1.setEndtime(java.sql.Timestamp.valueOf("2020-06-13 16:30:00"));
//		rminfoVO1.setMbrno("BM00001");
//		rminfoVO1.setGame("���ѤH �s�ڳ��� �T���");
//		rminfoVO1.setRemarks("�O�o��ñ��");
//		rminfoVO1.setRestriction(0);
//		rminfoVO1.setConfirmed(0);
//		
//		dao.insert(rminfoVO1);
		

//		dao.update(2, "SR00010");
		

		List<RminfoVO> list = dao.findByShopno("DS00001");
		for (RminfoVO aRoom : list) {
			System.out.print(aRoom.getMbrno()+",");
			System.out.print(aRoom.getNaming()+",");
			System.out.print(aRoom.getShopno()+",");
			System.out.print(aRoom.getLowlimit()+",");
			System.out.print(aRoom.getUplimit()+",");
			System.out.print(aRoom.getStarttime()+",");
			System.out.print(aRoom.getEndtime()+",");
			System.out.print(aRoom.getGame()+",");
			System.out.print(aRoom.getCutoff()+",");
			System.out.print(aRoom.getRemarks()+",");
			System.out.print(aRoom.getRestriction()+",");
			System.out.print(aRoom.getReport()+",");
			System.out.println();
		}
		
		//��@�ж���T
//		RminfoVO rminfoVO2 = dao.findByRmno("SR00005");
//		
//		System.out.print(rminfoVO2.getMbrno()+",");
//		System.out.print(rminfoVO2.getNaming()+",");
//		System.out.print(rminfoVO2.getShopno()+",");
//		System.out.print(rminfoVO2.getLowlimit()+",");
//		System.out.print(rminfoVO2.getUplimit()+",");
//		System.out.print(rminfoVO2.getStarttime()+",");
//		System.out.print(rminfoVO2.getEndtime()+",");
//		System.out.print(rminfoVO2.getGame()+",");
//		System.out.print(rminfoVO2.getCutoff()+",");
//		System.out.print(rminfoVO2.getRemarks()+",");
//		System.out.print(rminfoVO2.getRestriction()+",");
//		System.out.print(rminfoVO2.getConfirmed());
//		System.out.println();
////		
	}
	
	
	
}
