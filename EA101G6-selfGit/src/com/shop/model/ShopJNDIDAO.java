package com.shop.model;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.shop.model.ShopDAO_interface;

import java.io.*;
import java.sql.*;

public class ShopJNDIDAO implements ShopDAO_interface{

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = 
		"INSERT INTO SHOP (SHOPNO,SHOPACT,SHOPPW,SHOPNAME,SHOPLOC,SHOPCY,SHOPPHONE,SHOPIMG,STATUS) VALUES ('DS'||LPAD(TO_CHAR(SHOP_SEQ.NEXTVAL), 5, '0'), ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALLOWED_STMT = 
		"SELECT SHOPNO,SHOPACT,SHOPPW,SHOPNAME,SHOPLOC,SHOPCY,SHOPPHONE,SHOPIMG,STATUS FROM SHOP WHERE STATUS = 1 ORDER BY SHOPNO";
	private static final String GET_ALL_STMT = 
		"SELECT SHOPNO,SHOPACT,SHOPPW,SHOPNAME,SHOPLOC,SHOPCY,SHOPPHONE,SHOPIMG,STATUS FROM SHOP ORDER BY STATUS";
	private static final String GET_ONE_STMT = 
		"SELECT SHOPNO,SHOPACT,SHOPPW,SHOPNAME,SHOPLOC,SHOPCY,SHOPPHONE,SHOPIMG,STATUS FROM SHOP WHERE SHOPNO = ?";
	private static final String UPDATE = 
		"UPDATE SHOP SET SHOPACT=?, SHOPPW=?, SHOPNAME=?, SHOPLOC=?, SHOPCY=?, SHOPPHONE=? ,SHOPIMG=?, STATUS=? WHERE SHOPNO = ?";
	private static final String LOGIN = 
		"SELECT SHOPNO FROM SHOP WHERE SHOPACT=? AND SHOPPW=?";
	private static final String UPDATE_BY_MANAGER = 
		"UPDATE SHOP SET STATUS=? WHERE SHOPNO = ?";
	@Override
	public void insert(ShopVO shopVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			
			pstmt.setString(1, shopVO.getShopact());
			pstmt.setString(2, shopVO.getShoppw());
			pstmt.setString(3, shopVO.getShopname());
			pstmt.setString(4, shopVO.getShoploc());
			pstmt.setString(5, shopVO.getShopcy());
			pstmt.setInt(6, shopVO.getShopphone());
			pstmt.setBytes(7, shopVO.getShopimg());
			pstmt.setInt(8, shopVO.getStatus());
			
			pstmt.executeUpdate();
			
			
			
		}catch (SQLException se) {
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
	public void update(ShopVO shopVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, shopVO.getShopact());
			pstmt.setString(2, shopVO.getShoppw());
			pstmt.setString(3, shopVO.getShopname());
			pstmt.setString(4, shopVO.getShoploc());
			pstmt.setString(5, shopVO.getShopcy());
			pstmt.setInt(6, shopVO.getShopphone());
			pstmt.setBytes(7, shopVO.getShopimg());
			pstmt.setInt(8, shopVO.getStatus());
			pstmt.setString(9, shopVO.getShopno());

			pstmt.executeUpdate();

			// Handle any driver errors
		}catch (SQLException se) {
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
	public void update_back(ShopVO shopVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_BY_MANAGER);

			pstmt.setInt(1, shopVO.getStatus());
			pstmt.setString(2, shopVO.getShopno());

			pstmt.executeUpdate();

			// Handle any driver errors
		}catch (SQLException se) {
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
	public ShopVO findByPrimaryKey(String shopno) {
		ShopVO shopVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, shopno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// shopVo �]�٬� Domain objects
				shopVO = new ShopVO();
				shopVO.setShopno(rs.getString("shopno"));
				shopVO.setShopact(rs.getString("shopact"));
				shopVO.setShoppw(rs.getString("shoppw"));
				shopVO.setShopname(rs.getString("shopname"));
				shopVO.setShoploc(rs.getString("shoploc"));
				shopVO.setShopcy(rs.getString("shopcy"));
				shopVO.setShopphone(rs.getInt("shopphone"));
				shopVO.setShopimg(rs.getBytes("shopimg"));
				shopVO.setStatus(rs.getInt("status"));
			}

			// Handle any driver errors
		}catch (SQLException se) {
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
		return shopVO;
	}

	@Override
	public List<ShopVO> getAllowedShop() {
		List<ShopVO> list = new ArrayList<ShopVO>();
		ShopVO shopVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALLOWED_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// shopVO �]�٬� Domain objects
				shopVO = new ShopVO();
				shopVO.setShopno(rs.getString("shopno"));
				shopVO.setShopact(rs.getString("shopact"));
				shopVO.setShoppw(rs.getString("shoppw"));
				shopVO.setShopname(rs.getString("shopname"));
				shopVO.setShoploc(rs.getString("shoploc"));
				shopVO.setShopcy(rs.getString("shopcy"));
				shopVO.setShopphone(rs.getInt("shopphone"));
				shopVO.setShopimg(rs.getBytes("shopimg"));
				shopVO.setStatus(rs.getInt("status"));
				list.add(shopVO); // Store the row in the list
			}

			// Handle any driver errors
		}catch (SQLException se) {
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
	public List<ShopVO> getAll() {
		List<ShopVO> list = new ArrayList<ShopVO>();
		ShopVO shopVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// shopVO �]�٬� Domain objects
				shopVO = new ShopVO();
				shopVO.setShopno(rs.getString("shopno"));
				shopVO.setShopact(rs.getString("shopact"));
				shopVO.setShoppw(rs.getString("shoppw"));
				shopVO.setShopname(rs.getString("shopname"));
				shopVO.setShoploc(rs.getString("shoploc"));
				shopVO.setShopcy(rs.getString("shopcy"));
				shopVO.setShopphone(rs.getInt("shopphone"));
				shopVO.setShopimg(rs.getBytes("shopimg"));
				shopVO.setStatus(rs.getInt("status"));
				list.add(shopVO); // Store the row in the list
			}

			// Handle any driver errors
		}catch (SQLException se) {
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
	public ShopVO login(String shopact, String shoppw) {
		ShopVO shopVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(LOGIN);

			pstmt.setString(1, shopact);
			pstmt.setString(2, shoppw);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// shopVo �]�٬� Domain objects
				shopVO = new ShopVO();
				shopVO.setShopno(rs.getString("shopno"));
			}

			// Handle any driver errors
		}catch (SQLException se) {
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
		return shopVO;
	}
	
//	@Override
//	public void updateStatus(ShopVO shopVO) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(UPDATE);
//
//			pstmt.setInt(1, shopVO.getStatus());
//			pstmt.setString(2, shopVO.getShopno());
//
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//	}
	
	// �ϥ�InputStream��Ƭy�覡
		public static InputStream getPictureStream(String path) throws IOException {
			File file = new File(path);
			FileInputStream fis = new FileInputStream(file);
			return fis;
		}

		// �ϥ�byte[]�覡
		public static byte[] getPictureByteArray(String path) throws IOException {
			File file = new File(path);
			FileInputStream fis = new FileInputStream(file);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[8192];
			int i;
			while ((i = fis.read(buffer)) != -1) {
				baos.write(buffer, 0, i);
			}
			baos.close();
			fis.close();

			return baos.toByteArray();
		}
	
}
