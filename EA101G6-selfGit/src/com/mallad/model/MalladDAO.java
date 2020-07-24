package com.mallad.model;

import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;

import com.art.model.ArtVO;


public class MalladDAO implements MalladDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO mallad (malladno,commno,gmadtt,detail,startt,stopt) VALUES ('MMA'||LPAD(to_char(mallad_seq.NEXTVAL),4,'0'), ?, ?, ?, ?, ?)";
	private static final String DELETE = "DELETE FROM mallad WHERE malladno = ?";
	private static final String GET_ALL_STMT = "SELECT malladno,commno,gmadtt,detail,to_char(startt,'yyyy-mm-dd')startt,to_char(stopt,'yyyy-mm-dd')stopt FROM mallad ORDER BY malladno DESC";
	private static final String GET_ONE_STMT = "SELECT malladno,commno,gmadtt,detail,to_char(startt,'yyyy-mm-dd')startt,to_char(stopt,'yyyy-mm-dd')stopt FROM mallad WHERE malladno = ?";
	
	
	

	@Override
	public void insert(MalladVO malladVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, malladVO.getCommno());
			pstmt.setString(2, malladVO.getGmadtt());
			pstmt.setBytes(3, malladVO.getDetail());
			pstmt.setDate(4, malladVO.getStartt());
			pstmt.setDate(5, malladVO.getStopt());
			

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
	public void delete(String malladno) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, malladno);

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
	public MalladVO findByPrimaryKey(String malladno) {
		MalladVO malladVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, malladno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				malladVO = new MalladVO();
				malladVO.setMalladno(rs.getString("malladno"));
				malladVO.setCommno(rs.getString("commno"));
				malladVO.setGmadtt(rs.getString("gmadtt"));
				malladVO.setDetail(rs.getBytes("detail"));
				malladVO.setStartt(rs.getDate("startt"));
				malladVO.setStopt(rs.getDate("stopt"));
				
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
		
		return malladVO;
	}

	@Override
	public List<MalladVO> getAll() {
		List<MalladVO> list = new ArrayList<MalladVO>();
		MalladVO malladVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				malladVO = new MalladVO();
				malladVO.setMalladno(rs.getString("malladno"));
				malladVO.setCommno(rs.getString("commno"));
				malladVO.setGmadtt(rs.getString("gmadtt"));
				malladVO.setDetail(rs.getBytes("detail"));
				malladVO.setStartt(rs.getDate("startt"));
				malladVO.setStopt(rs.getDate("stopt"));
				list.add(malladVO); // Store the row in the list
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
	
	
	
	
	

}
