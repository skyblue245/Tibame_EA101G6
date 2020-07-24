package com.rate.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.rminfo.model.RminfoVO;

public class RateJDBCDAO implements RateDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "EA101";
	String passwd = "123456";
	
	private static final String INSERT_STMT ="INSERT INTO RATE(RATENO,RMNO,RATINGMBRNO,RATEDMBRNO,DETAIL,SCORE) VALUES ('SS'||LPAD(TO_CHAR(RATE_SEQ.NEXTVAL),5,'0'),?,?,?,?,?)";
	private static final String DELETE ="DELETE FROM RATE WHERE RATENO = ?";
	private static final String GET_ALL_STMT ="SELECT * FROM RATE";
	private static final String GET_ONE_BY_MBRNO = "SELECT * FROM RATE WHERE RATEDMBRNO = ?";
	
	@Override
	public void insert(RateVO rateVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, rateVO.getRmno());
			pstmt.setString(2, rateVO.getRatingmbrno());
			pstmt.setString(3, rateVO.getRatedmbrno());
			pstmt.setString(4, rateVO.getDetail());
			pstmt.setInt(5, rateVO.getScore());
			
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
	public void delete(String rateno) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, rateno);
			

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
	
	public static void main(String[] args) {
		RateJDBCDAO dao = new RateJDBCDAO();
		RateVO rateVO1 = new RateVO();
		rateVO1.setRmno("SR00004");
		rateVO1.setRatingmbrno("BM00005");
		rateVO1.setRatedmbrno("BM00004");
		rateVO1.setDetail("�ӯ���666");
		rateVO1.setScore(5);
		
		
		dao.insert(rateVO1);
		
	}
	
	@Override
	public List<RateVO> getAll() {
		
		List<RateVO> list = new ArrayList<RateVO>();
		RateVO rateVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				rateVO = new RateVO();
				rateVO.setRateno(rs.getString("rateno"));
				rateVO.setRmno(rs.getString("rmno"));
				rateVO.setRatingmbrno(rs.getString("ratingmbrno"));
				rateVO.setRatedmbrno(rs.getString("ratedmbrno"));
				rateVO.setDetail(rs.getString("detail"));
				rateVO.setScore(rs.getInt("score"));
				rateVO.setRatetime(rs.getTimestamp("ratetime"));
				
				list.add(rateVO); // Store the row in the list
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
	public List<RateVO> findByRatedmbrno(String ratedmbrno) {
		
		List<RateVO> list = new ArrayList<RateVO>();
		
		RateVO rateVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_BY_MBRNO);

			pstmt.setString(1,ratedmbrno);
			

			rs = pstmt.executeQuery();

			while (rs.next()) {
				rateVO = new RateVO();
				rateVO.setRatedmbrno(rs.getString("ratedmbrno"));
				list.add(rateVO);
				
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
}
