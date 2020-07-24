package com.shoprp.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.joinrm.model.JoinrmJDBCDAO;
import com.joinrm.model.JoinrmVO;
import com.rate.model.RateVO;

public class ShoprpJDBCDAO implements ShoprpDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "EA101";
	String passwd = "123456";
	
	private static final String INSERT_STMT = "INSERT INTO SHOPRP(MBRNO,RMNO,DETAIL,ATTEND) VALUES (?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM SHOPRP";
	private static final String DELETE ="DELETE FROM SHOPRP WHERE RMNO = ? AND MBRNO = ?";

	@Override
	public void insert(ShoprpVO shoprpVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, shoprpVO.getMbrno());
			pstmt.setString(2, shoprpVO.getRmno());
			pstmt.setString(3, shoprpVO.getDetail());
			pstmt.setInt(4, shoprpVO.getAttend());
			
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
	public List<ShoprpVO> getAll() {
		
		List<ShoprpVO> list = new ArrayList<ShoprpVO>();
		ShoprpVO shoprpVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				shoprpVO = new ShoprpVO();
				shoprpVO.setMbrno(rs.getString("mbrno"));
				shoprpVO.setRmno(rs.getString("rmno"));
				shoprpVO.setRpdate(rs.getTimestamp("rpdate"));
				shoprpVO.setAttend(rs.getInt("attend"));
				shoprpVO.setDetail(rs.getString("detail"));
				
				list.add(shoprpVO); // Store the row in the list
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
	public void delete(String rmno,String mbrno) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, rmno);
			pstmt.setString(2, mbrno);
			

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
		ShoprpJDBCDAO dao = new ShoprpJDBCDAO();
		ShoprpVO shoprpVO1 = new ShoprpVO();
		shoprpVO1.setMbrno("BM00001");
		shoprpVO1.setRmno("SR00011");
		shoprpVO1.setDetail("");
		shoprpVO1.setAttend(1);
		
		
		dao.insert(shoprpVO1);
		
	}
	
	

}
