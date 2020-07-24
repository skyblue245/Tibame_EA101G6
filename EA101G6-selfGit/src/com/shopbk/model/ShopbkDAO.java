package com.shopbk.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.shopbk.model.ShopbkDAO_interface;

public class ShopbkDAO implements ShopbkDAO_interface{

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
			"INSERT INTO SHOPBK (SHOPBKNO, SHOPNO, OFDTABLE, SHOPPDS, SHOPPDE, PAYINFOHR, PAYINFODAY) VALUES ('DB'||LPAD(TO_CHAR(SHOPBK_SEQ.NEXTVAL), 5, '0'), ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE =
			"UPDATE SHOPBK SET SHOPNO=?, OFDTABLE=?, SHOPPDS=?, SHOPPDE=?, PAYINFOHR=?, PAYINFODAY=? WHERE SHOPBKNO = ?";
	private static final String GET_ALL_AFTER_NOW_STMT =
			"SELECT SHOPBKNO,SHOPNO,OFDTABLE,SHOPPDS,SHOPPDE,PAYINFOHR,PAYINFODAY FROM SHOPBK WHERE SHOPPDS >= SYSDATE ORDER BY SHOPPDS";
	private static final String GET_ALL_STMT =
			"SELECT SHOPBKNO,SHOPNO,OFDTABLE,SHOPPDS,SHOPPDE,PAYINFOHR,PAYINFODAY FROM SHOPBK WHERE ORDER BY SHOPPDS";
	private static final String GET_SOME_STMT_BY_TIME =
			"SELECT SHOPBKNO,SHOPNO,OFDTABLE,SHOPPDS,SHOPPDE,PAYINFOHR,PAYINFODAY FROM SHOPBK WHERE SHOPPDS <=? AND SHOPPDE >=? ORDER BY SHOPPDS";
	private static final String GET_SOME_STMT_BY_SHOPNO =
			"SELECT SHOPBKNO,SHOPNO,OFDTABLE,SHOPPDS,SHOPPDE,PAYINFOHR,PAYINFODAY FROM SHOPBK WHERE SHOPNO = ?";
	private static final String GET_ONE_STMT =
			"SELECT SHOPBKNO,SHOPNO,OFDTABLE,SHOPPDS,SHOPPDE,PAYINFOHR,PAYINFODAY FROM SHOPBK WHERE SHOPBKNO = ?";
	private static final String DELETE =
			"DELETE FROM SHOPBK WHERE SHOPBKNO =?";
	
	@Override
	public void insert(ShopbkVO shopbkVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, shopbkVO.getShopno());
			pstmt.setInt(2, shopbkVO.getOfdtable());
			pstmt.setTimestamp(3, shopbkVO.getShoppds());
			pstmt.setTimestamp(4, shopbkVO.getShoppde());
			pstmt.setInt(5, shopbkVO.getPayinfohr());
			pstmt.setInt(6, shopbkVO.getPayinfoday());
			
			
			pstmt.executeUpdate();
			
			
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
	public void update(ShopbkVO shopbkVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, shopbkVO.getShopno());
			pstmt.setInt(2, shopbkVO.getOfdtable());
			pstmt.setTimestamp(3, shopbkVO.getShoppds());
			pstmt.setTimestamp(4, shopbkVO.getShoppde());
			pstmt.setInt(5, shopbkVO.getPayinfohr());
			pstmt.setInt(6, shopbkVO.getPayinfoday());
			pstmt.setString(7, shopbkVO.getShopbkno());
			
			pstmt.executeUpdate();
			
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
	public void delete(String shopbkno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, shopbkno);
			
			pstmt.executeUpdate();
			
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
	public ShopbkVO findByPrimaryKey(String shopbkno) {
		ShopbkVO shopbkVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, shopbkno);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				shopbkVO = new ShopbkVO();
				shopbkVO.setShopbkno(rs.getString("shopbkno"));
				shopbkVO.setShopno(rs.getString("shopno"));
				shopbkVO.setOfdtable(rs.getInt("ofdtable"));
				shopbkVO.setShoppds(rs.getTimestamp("shoppds"));
				shopbkVO.setShoppde(rs.getTimestamp("shoppde"));
				shopbkVO.setPayinfohr(rs.getInt("payinfohr"));
				shopbkVO.setPayinfoday(rs.getInt("payinfoday"));			
			}
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
		return shopbkVO;
	}

	@Override
	public List<ShopbkVO> findByShoppd(Timestamp shoppds) {
		List<ShopbkVO> list = new ArrayList<ShopbkVO>();
		ShopbkVO shopbkVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_SOME_STMT_BY_TIME);
			
			pstmt.setTimestamp(1, shoppds);
			pstmt.setTimestamp(2, shoppds);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				shopbkVO = new ShopbkVO();
				shopbkVO.setShopbkno(rs.getString("shopbkno"));
				shopbkVO.setShopno(rs.getString("shopno"));
				shopbkVO.setOfdtable(rs.getInt("ofdtable"));
				shopbkVO.setShoppds(rs.getTimestamp("shoppds"));
				shopbkVO.setShoppde(rs.getTimestamp("shoppde"));
				shopbkVO.setPayinfohr(rs.getInt("payinfohr"));
				shopbkVO.setPayinfoday(rs.getInt("payinfoday"));
				list.add(shopbkVO);				
			}
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
	public List<ShopbkVO> findByShop(String shopno) {
		List<ShopbkVO> list = new ArrayList<ShopbkVO>();
		ShopbkVO shopbkVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_SOME_STMT_BY_SHOPNO);
			
			pstmt.setString(1, shopno);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				shopbkVO = new ShopbkVO();
				shopbkVO.setShopbkno(rs.getString("shopbkno"));
				shopbkVO.setShopno(rs.getString("shopno"));
				shopbkVO.setOfdtable(rs.getInt("ofdtable"));
				shopbkVO.setShoppds(rs.getTimestamp("shoppds"));
				shopbkVO.setShoppde(rs.getTimestamp("shoppde"));
				shopbkVO.setPayinfohr(rs.getInt("payinfohr"));
				shopbkVO.setPayinfoday(rs.getInt("payinfoday"));
				list.add(shopbkVO);				
			}
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
	public List<ShopbkVO> getAll() {
		List<ShopbkVO> list = new ArrayList<ShopbkVO>();
		ShopbkVO shopbkVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				shopbkVO = new ShopbkVO();
				shopbkVO.setShopbkno(rs.getString("shopbkno"));
				shopbkVO.setShopno(rs.getString("shopno"));
				shopbkVO.setOfdtable(rs.getInt("ofdtable"));
				shopbkVO.setShoppds(rs.getTimestamp("shoppds"));
				shopbkVO.setShoppde(rs.getTimestamp("shoppde"));
				shopbkVO.setPayinfohr(rs.getInt("payinfohr"));
				shopbkVO.setPayinfoday(rs.getInt("payinfoday"));
				list.add(shopbkVO);				
			}
									
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
	public List<ShopbkVO> getAllAfterNow() {
		List<ShopbkVO> list = new ArrayList<ShopbkVO>();
		ShopbkVO shopbkVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_AFTER_NOW_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				shopbkVO = new ShopbkVO();
				shopbkVO.setShopbkno(rs.getString("shopbkno"));
				shopbkVO.setShopno(rs.getString("shopno"));
				shopbkVO.setOfdtable(rs.getInt("ofdtable"));
				shopbkVO.setShoppds(rs.getTimestamp("shoppds"));
				shopbkVO.setShoppde(rs.getTimestamp("shoppde"));
				shopbkVO.setPayinfohr(rs.getInt("payinfohr"));
				shopbkVO.setPayinfoday(rs.getInt("payinfoday"));
				list.add(shopbkVO);				
			}
									
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
	

	


}
