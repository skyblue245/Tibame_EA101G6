package com.msg.model;

import java.util.*;
import java.sql.*;

public class MsgJDBCDAO implements MsgDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "EA101";
	String passwd = "123456";

	private static final String INSERT_STMT = 
		"INSERT INTO msg (msgno,mbrno,detail,artno,status) VALUES (msg_seq.NEXTVAL, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT msgno,mbrno,detail,artno,status FROM msg order by msgno";
	private static final String GET_ONE_STMT = 
		"SELECT msgno,mbrno,detail,artno,status FROM msg where msgno = ?";
	private static final String DELETE = 
		"DELETE FROM msg where msgno = ?";
	private static final String UPDATE = 
		"UPDATE msg set msgno=?,mbrno=?,detail=?,artno=?,status=? where msgno = ?";
	private static final String GET_BY_ARTNO = "SELECT msgno,mbrno,detail,artno,status FROM msg WHERE artno = ? ORDER BY msgno DESC";

	@Override
	public List<MsgVO> getAllByArtno(String artno) {
		List<MsgVO> list = new ArrayList<MsgVO>();
		MsgVO msgVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_BY_ARTNO);
			pstmt.setString(1, artno);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// msgVO 也稱為 Domain objects
				msgVO = new MsgVO();
				msgVO.setMsgno(rs.getString("msgno"));
				msgVO.setMbrno(rs.getString("mbrno"));
				msgVO.setDetail(rs.getString("detail"));
				msgVO.setArtno(rs.getString("artno"));
				msgVO.setStatus(rs.getInt("status"));
				list.add(msgVO); // Store the row in the list
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
		return list;
	}
	
	@Override
	public void insert(MsgVO msgVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			
			
			pstmt.setString(1, msgVO.getMbrno());
			pstmt.setString(2, msgVO.getDetail());
			pstmt.setString(3, msgVO.getArtno());
			pstmt.setInt(4, msgVO.getStatus());
			

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
	public void update(MsgVO msgVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, msgVO.getMbrno());
			pstmt.setString(2, msgVO.getDetail());
			pstmt.setString(3, msgVO.getArtno());
			pstmt.setInt(4, msgVO.getStatus());

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
	public void delete(String msgno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, msgno);

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
	public MsgVO findByPrimaryKey(String msgno) {

		MsgVO msgVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, msgno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// msgVO 也稱為 Domain objects
				msgVO = new MsgVO();
				msgVO.setMsgno(rs.getString("msgno"));
				msgVO.setMbrno(rs.getString("mbrno"));
				msgVO.setDetail(rs.getString("detail"));
				msgVO.setArtno(rs.getString("artno"));
				msgVO.setStatus(rs.getInt("status"));
				
				
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
		return msgVO;
	}

	@Override
	public List<MsgVO> getAll() {
		List<MsgVO> list = new ArrayList<MsgVO>();
		MsgVO msgVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// msgVO 也稱為 Domain objects
				msgVO = new MsgVO();
				msgVO.setMsgno(rs.getString("msgno"));
				msgVO.setMbrno(rs.getString("mbrno"));
				msgVO.setDetail(rs.getString("detail"));
				msgVO.setArtno(rs.getString("artno"));
				msgVO.setStatus(rs.getInt("status"));
				list.add(msgVO); // Store the row in the list
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
		return list;
	}

	public static void main(String[] args) {

		MsgJDBCDAO dao = new MsgJDBCDAO();

		// 新增
//				MsgVO msgVO1 = new MsgVO();
//				msgVO1.setMsgno("555");
//				msgVO1.setMbrno("555");
//				msgVO1.setDetail("555");
//				msgVO1.setArtno("555");
//				msgVO1.setStatus(1);
//				
//				dao.insert(msgVO1);
//
//		// 修改
//				MsgVO msgVO2 = new MsgVO();
//				msgVO2.setMsgno("666");
//				msgVO2.setMbrno("555");
//				msgVO2.setDetail("555");
//				msgVO2.setArtno("555");
//				msgVO2.setStatus(1);
//				
//				dao.update(msgVO2);
//
//				// 刪除
//				dao.delete("666");
//
//				// 查詢
//				MsgVO msgVO3 = dao.findByPrimaryKey("666");
//				System.out.print(msgVO3.getMsgno() + ",");
//				System.out.print(msgVO3.getMbrno() + ",");
//				System.out.print(msgVO3.getDetail() + ",");
//				System.out.print(msgVO3.getArtno() + ",");
//				System.out.print(msgVO3.getStatus() + ",");
//				
//				System.out.println("---------------------");
//
//				// 查詢
//				List<MsgVO> list = dao.getAll();
//				for (MsgVO aMsg : list) {
//					System.out.print(msgVO3.getMsgno() + ",");
//					System.out.print(msgVO3.getMbrno() + ",");
//					System.out.print(msgVO3.getDetail() + ",");
//					System.out.print(msgVO3.getArtno() + ",");
//					System.out.print(msgVO3.getStatus() + ",");
//					
//					System.out.println();
//				}
		List<MsgVO> list = dao.getAllByArtno("MA00007");
		for (MsgVO aMsg : list) {
			System.out.print(aMsg.getMsgno() + ",");
			System.out.print(aMsg.getMbrno() + ",");
			System.out.print(aMsg.getDetail() + ",");
			System.out.print(aMsg.getArtno() + ",");
			System.out.print(aMsg.getStatus() + ",");
			
			System.out.println();
		}
				
			}
		}