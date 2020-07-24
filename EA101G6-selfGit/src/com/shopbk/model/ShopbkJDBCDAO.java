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

import com.shopbk.model.ShopbkDAO_interface;

public class ShopbkJDBCDAO implements ShopbkDAO_interface{

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "EA101";
	String passwd = "123456";
	
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, shopbkVO.getShopno());
			pstmt.setInt(2, shopbkVO.getOfdtable());
			pstmt.setTimestamp(3, shopbkVO.getShoppds());
			pstmt.setTimestamp(4, shopbkVO.getShoppde());
			pstmt.setInt(5, shopbkVO.getPayinfohr());
			pstmt.setInt(6, shopbkVO.getPayinfoday());
			
			
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
	public void update(ShopbkVO shopbkVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, shopbkVO.getShopno());
			pstmt.setInt(2, shopbkVO.getOfdtable());
			pstmt.setTimestamp(3, shopbkVO.getShoppds());
			pstmt.setTimestamp(4, shopbkVO.getShoppde());
			pstmt.setInt(5, shopbkVO.getPayinfohr());
			pstmt.setInt(6, shopbkVO.getPayinfoday());
			pstmt.setString(7, shopbkVO.getShopbkno());
			
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
	public void delete(String shopbkno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, shopbkno);
			
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
	public ShopbkVO findByPrimaryKey(String shopbkno) {
		ShopbkVO shopbkVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public List<ShopbkVO> findByShop(String shopno) {
		List<ShopbkVO> list = new ArrayList<ShopbkVO>();
		ShopbkVO shopbkVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public List<ShopbkVO> getAll() {
		List<ShopbkVO> list = new ArrayList<ShopbkVO>();
		ShopbkVO shopbkVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public List<ShopbkVO> getAllAfterNow() {
		List<ShopbkVO> list = new ArrayList<ShopbkVO>();
		ShopbkVO shopbkVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		ShopbkJDBCDAO dao = new ShopbkJDBCDAO();
		ShopbkService svc = new ShopbkService();
		
		
		// �s�W
//		ShopbkVO shopbkVO1 = new ShopbkVO();
//		shopbkVO1.setShopno("DS00003");
//		shopbkVO1.setOfdtable(30);
//		Timestamp t1 = new Timestamp(System.currentTimeMillis());
//		t1 = Timestamp.valueOf("2020-07-01 12:00:00");
//		Timestamp t2 = new Timestamp(System.currentTimeMillis());
//		t2 = Timestamp.valueOf("2020-07-01 20:00:00");
//		shopbkVO1.setShoppds(t1);
//		shopbkVO1.setShoppde(t2);
//		shopbkVO1.setPayinfohr(30);
//		shopbkVO1.setPayinfoday(130);
//		dao.insert(shopbkVO1);
		// �ק�
		ShopbkVO shopbkVO = new ShopbkVO();
		shopbkVO.setShopbkno("DB00001");
		shopbkVO.setShopno("DS00001");
		shopbkVO.setOfdtable(30);
		Timestamp t1 = new Timestamp(System.currentTimeMillis());
		shopbkVO.setShoppds(t1);
		Timestamp t2 = new Timestamp(System.currentTimeMillis());
		shopbkVO.setShoppde(t2);
		shopbkVO.setPayinfohr(50);
		shopbkVO.setPayinfoday(150);
		svc.updateShopbk("DB00001","DS00001",60,t1,t2,700,5000);
		// �R��
//		dao.delete("DB00011");
		// �d��
//		Timestamp t1 = new Timestamp(System.currentTimeMillis());
//		t1 = Timestamp.valueOf("2020-07-01 12:00:00");
//		List<ShopbkVO> list = dao.findByShoppd(t1);
//		for(ShopbkVO shopbk : list) {
//			System.out.println(shopbk.getShopbkno() + ",");
//			System.out.println(shopbk.getShopno() + ",");
//			System.out.println(shopbk.getOfdtable() + ",");
//			System.out.println(shopbk.getShoppds() + ",");
//			System.out.println(shopbk.getShoppde() + ",");
//			System.out.println(shopbk.getPayinfohr() + ",");
//			System.out.println(shopbk.getPayinfoday() + ",");
//			System.out.println();
//		}
//		List<ShopbkVO> list = dao.findByShop("DS00001");
//		for(ShopbkVO shopbk : list) {
//			System.out.println(shopbk.getShopbkno() + ",");
//			System.out.println(shopbk.getShopno() + ",");
//			System.out.println(shopbk.getOfdtable() + ",");
//			System.out.println(shopbk.getShoppds() + ",");
//			System.out.println(shopbk.getShoppde() + ",");
//			System.out.println(shopbk.getPayinfohr() + ",");
//			System.out.println(shopbk.getPayinfoday() + ",");
//			System.out.println();
//		}
		// �d��
//		List<ShopbkVO> list2 = dao.getAll();
//		for(ShopbkVO shopbk : list2) {
//			System.out.println(shopbk.getShopbkno() + ",");
//			System.out.println(shopbk.getShopno() + ",");
//			System.out.println(shopbk.getOfdtable() + ",");
//			System.out.println(shopbk.getShoppds() + ",");
//			System.out.println(shopbk.getShoppde() + ",");
//			System.out.println(shopbk.getPayinfohr() + ",");
//			System.out.println(shopbk.getPayinfoday() + ",");
//			System.out.println();
//		}
	}

	


}
