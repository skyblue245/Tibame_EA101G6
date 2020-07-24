package com.shoprp.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.joinrm.model.JoinrmJDBCDAO;
import com.joinrm.model.JoinrmVO;
import com.rate.model.RateVO;

public class ShoprpJNDIDAO implements ShoprpDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO SHOPRP(MBRNO,RMNO,DETAIL,ATTEND) VALUES (?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM SHOPRP";
	private static final String DELETE ="DELETE FROM SHOPRP WHERE RMNO = ? AND MBRNO = ?";

	@Override
	public void insert(ShoprpVO shoprpVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, shoprpVO.getMbrno());
			pstmt.setString(2, shoprpVO.getRmno());
			pstmt.setString(3, shoprpVO.getDetail());
			pstmt.setInt(4, shoprpVO.getAttend());
			
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
	public List<ShoprpVO> getAll() {
		
		List<ShoprpVO> list = new ArrayList<ShoprpVO>();
		ShoprpVO shoprpVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, rmno);
			pstmt.setString(2, mbrno);
			

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
	
}
