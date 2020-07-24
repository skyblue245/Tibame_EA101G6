package com.mallad.model;

import java.util.*;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;

public class MalladJDBCDAO implements MalladDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "EA101";
	String passwd = "123456";
	
	private static final String INSERT_STMT = "INSERT INTO mallad (malladno,commno,gmadtt,detail,startt,stopt) VALUES ('MMA'||LPAD(to_char(mallad_seq.NEXTVAL),4,'0'), ?, ?, ?, ?, ?)";
	private static final String DELETE = "DELETE FROM mallad WHERE malladno = ?";
	private static final String GET_ALL_STMT = "SELECT malladno,commno,gmadtt,detail,to_char(startt,'yyyy-mm-dd')startt,to_char(stopt,'yyyy-mm-dd')stopt FROM mallad ORDER BY malladno";
	private static final String GET_ONE_STMT = "SELECT malladno,commno,gmadtt,detail,to_char(startt,'yyyy-mm-dd')startt,to_char(stopt,'yyyy-mm-dd')stopt FROM mallad WHERE malladno = ?";
	
	
	

	@Override
	public void insert(MalladVO malladVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, malladVO.getCommno());
			pstmt.setString(2, malladVO.getGmadtt());
			pstmt.setBytes(3, malladVO.getDetail());
			pstmt.setDate(4, malladVO.getStartt());
			pstmt.setDate(5, malladVO.getStopt());
			

			pstmt.executeUpdate();

			// Handle any SQL errors
		}  catch (ClassNotFoundException e) {
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
	public void delete(String malladno) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, malladno);

			pstmt.executeUpdate();

			// Handle any driver errors
		}  catch (ClassNotFoundException e) {
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
	public MalladVO findByPrimaryKey(String malladno) {
		MalladVO malladVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	
	
	
	
	public static byte[] setPic(String path) throws IOException{
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte [8192];
		int i;
		while((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		fis.close();
		return baos.toByteArray();
	}
	
	public static void main(String[] args) {
		
		MalladJDBCDAO dao = new MalladJDBCDAO();
		
		//新增
		MalladVO m1 = new MalladVO();
		m1.setCommno("ZM00011");
		m1.setGmadtt("烽火連天");
		try {
			byte[] pic = setPic("WebContent/back-end/mallad/images/fire.png");
			m1.setDetail(pic);
		} catch (IOException e) {
			e.printStackTrace();
		}
		m1.setStartt(java.sql.Date.valueOf("2020-06-13"));
		m1.setStopt(java.sql.Date.valueOf("2020-07-13"));
		dao.insert(m1);
		System.out.println("success");
		
		//刪除
//		dao.delete("MMA0012");
//		System.out.println("success");
		
		//查單一
//		MalladVO m2 = dao.findByPrimaryKey("MMA0002");
//		System.out.print(m2.getMalladno() + ",");
//		System.out.print(m2.getCommno() + ",");
//		System.out.print(m2.getGmadtt() + ",");
//		System.out.print(m2.getDetail() + ",");
//		System.out.print(m2.getStartt() + ",");
//		System.out.println(m2.getStopt());
		
		//查全部
//		List<MalladVO> list = dao.getAll();
//		for(MalladVO m3 : list) {
//			System.out.print(m3.getMalladno() + ",");
//			System.out.print(m3.getCommno() + ",");
//			System.out.print(m3.getGmadtt() + ",");
//			System.out.print(m3.getDetail() + ",");
//			System.out.print(m3.getStartt() + ",");
//			System.out.println(m3.getStopt());
//			System.out.println();
//			System.out.println("==============================");
//			System.out.println();
//		}
	}
}
