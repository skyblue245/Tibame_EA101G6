package com.shoprpdt.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShoprpdtJDBCDAO implements ShoprpdtDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "EA101";
	String passwd = "123456";
	
	private static final String INSERT_STMT =
			"INSERT INTO SHOPRPDT (SHOPRPNO, RMNO, MBRNO, SHOPNO, DETAIL, STATUS) VALUES ('DR'||LPAD(TO_CHAR(SHOPRPDT_SEQ.NEXTVAL), 5, '0'), ?, ?, ?, ?, ?)";
	private static final String UPDATE =
			"UPDATE SHOPRPDT SET STATUS=? WHERE SHOPRPNO = ?";
	private static final String GET_ALL_STMT =
			"SELECT * FROM SHOPRPDT ORDER BY SHOPRPNO";
	private static final String GET_SOME_STMT =
			"SELECT * FROM SHOPRPDT WHERE SHOPRPNO = ? OR STATUS = ?";
	@Override
	public void insert(ShoprpdtVO shoprpdtVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, shoprpdtVO.getRmno());
			pstmt.setString(2, shoprpdtVO.getMbrno());
			pstmt.setString(3, shoprpdtVO.getShopno());
			pstmt.setString(4, shoprpdtVO.getDetail());
			pstmt.setInt(5, shoprpdtVO.getStatus());
			
			pstmt.executeUpdate();
			
		}catch(ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
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
	public void update(ShoprpdtVO shoprpdtVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			
			pstmt.setInt(1, shoprpdtVO.getStatus());
			pstmt.setString(2, shoprpdtVO.getShoprpno());
			
			pstmt.executeUpdate();
			
		}catch(ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
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
	public List<ShoprpdtVO> findByPrimaryKey(String shoprpdtno, Integer status) {
		List<ShoprpdtVO> list = new ArrayList<ShoprpdtVO>();
		ShoprpdtVO shoprpdtVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_SOME_STMT);
			
			pstmt.setString(1, shoprpdtno);
			pstmt.setInt(2, status);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				shoprpdtVO = new ShoprpdtVO();
				shoprpdtVO.setShoprpno(rs.getString("shoprpno"));
				shoprpdtVO.setRmno(rs.getString("rmno"));
				shoprpdtVO.setMbrno(rs.getString("mbrno"));
				shoprpdtVO.setShopno(rs.getString("shopno"));
				shoprpdtVO.setDetail(rs.getString("detail"));
				shoprpdtVO.setStatus(rs.getInt("status"));
				list.add(shoprpdtVO);
			}
			
		}catch(ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
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
	public List<ShoprpdtVO> getAll() {
		List<ShoprpdtVO> list = new ArrayList<ShoprpdtVO>();
		ShoprpdtVO shoprpdtVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
					
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				shoprpdtVO = new ShoprpdtVO();
				shoprpdtVO.setShoprpno(rs.getString("shoprpno"));
				shoprpdtVO.setRmno(rs.getString("rmno"));
				shoprpdtVO.setMbrno(rs.getString("mbrno"));
				shoprpdtVO.setShopno(rs.getString("shopno"));
				shoprpdtVO.setDetail(rs.getString("detail"));
				shoprpdtVO.setStatus(rs.getInt("status"));
				list.add(shoprpdtVO);
			}
		}catch(ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
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
		ShoprpdtJDBCDAO dao = new ShoprpdtJDBCDAO();
		ShoprpdtService svc = new ShoprpdtService();
		// �s�W
//		ShoprpdtVO shoprpdtVO1 = new ShoprpdtVO();
//		shoprpdtVO1.setRmno("SR00010");
//		shoprpdtVO1.setMbrno("BM00001");
//		shoprpdtVO1.setShopno("DS00010");
//		shoprpdtVO1.setDetail("������ٳ�");
//		shoprpdtVO1.setStatus(0);
//		dao.insert(shoprpdtVO1);
		// �ק�
//		ShoprpdtVO shoprpdtVO = new ShoprpdtVO();
//		shoprpdtVO.setShoprpno("DR00002");
//		shoprpdtVO.setStatus(1);
//		dao.update(shoprpdtVO);
		
		// �d��
//		List<ShoprpdtVO> list = dao.findByPrimaryKey("DR00002", 0);
//		for(ShoprpdtVO shoprpdt : list) {
//			System.out.println(shoprpdt.getShoprpno() + ",");
//			System.out.println(shoprpdt.getRmno() + ",");
//			System.out.println(shoprpdt.getMbrno() + ",");
//			System.out.println(shoprpdt.getShopno() + ",");
//			System.out.println(shoprpdt.getDetail() + ",");
//			System.out.println(shoprpdt.getStatus());
//			System.out.println();
//		}
		
		// �d��
		List<ShoprpdtVO> list2 = svc.getByStatus();
		for(ShoprpdtVO shoprpdt : list2) {
		System.out.println(shoprpdt.getShoprpno() + ",");
		System.out.println(shoprpdt.getRmno() + ",");
		System.out.println(shoprpdt.getMbrno() + ",");
		System.out.println(shoprpdt.getShopno() + ",");
		System.out.println(shoprpdt.getDetail() + ",");
		System.out.println(shoprpdt.getStatus());
			System.out.println();
		}
	}
}
