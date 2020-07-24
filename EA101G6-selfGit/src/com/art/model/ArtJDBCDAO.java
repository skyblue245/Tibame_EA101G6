package com.art.model;

import java.util.*;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;

public class ArtJDBCDAO implements ArtDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "EA101";
	String passwd = "123456";
	
	private static final String INSERT_STMT = "INSERT INTO art (artno,mbrno,detail,arttt,pdate,status,atno,apic) VALUES ('MA'||LPAD(to_char(art_seq.NEXTVAL),5,'0'), ?, ?, ?, CURRENT_DATE, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT artno,mbrno,detail,arttt,to_char(pdate,'yyyy-mm-dd')pdate,status,atno,apic FROM art order by artno";
	private static final String GET_ONE_STMT = "SELECT artno,mbrno,detail,arttt,to_char(pdate,'yyyy-mm-dd')pdate,status,atno,apic FROM art where artno = ?";
	private static final String GET_STMT_MBRNO = "SELECT artno,mbrno,detail,arttt,to_char(pdate,'yyyy-mm-dd')pdate,status,atno,apic FROM art where mbrno = ?";
	private static final String GET_STMT_ATNO = "SELECT artno,mbrno,detail,arttt,to_char(pdate,'yyyy-mm-dd')pdate,status,atno,apic FROM art where atno = ?";
	private static final String DELETE = "DELETE FROM art where artno = ?";
	private static final String UPDATE = "UPDATE art set detail=?, arttt=?, atno=?, apic=? where artno = ?";
	private static final String UPDATE_F = "UPDATE art SET detail=?, arttt=?, atno=?, apic=? where artno = ?";
	private static final String GET_ARTTT = "SELECT artno,mbrno,detail,arttt,to_char(pdate,'yyyy-mm-dd')pdate,atno,apic FROM art WHERE arttt LIKE ?";
	private static final String UPDATE_ART_STATUS = "UPDATE art set status = ? WHERE artno = ?";
	private static final String DELETE_ARTRP = "DELETE FROM artrp WHERE artno = ?";
	private static final String GET_STMT_STATUS = "SELECT artno,mbrno,detail,arttt,to_char(pdate,'yyyy-mm-dd')pdate,status,atno,apic FROM art where status = ?";
	
	
	@Override
	public List<ArtVO> getArtsByStatus(Integer status) {
		List<ArtVO> list = new ArrayList<ArtVO>();
		ArtVO artVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_STMT_STATUS);
			
			pstmt.setInt(1, status);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				artVO = new ArtVO();
				artVO.setArtno(rs.getString("artno"));
				artVO.setMbrno(rs.getString("mbrno"));
				artVO.setArttt(rs.getString("arttt"));
				artVO.setDetail(rs.getString("detail"));
				artVO.setPdate(rs.getDate("pdate"));
				
				artVO.setStatus(rs.getInt("status"));
				artVO.setAtno(rs.getString("atno"));
				artVO.setApic(rs.getBytes("apic"));
				
				list.add(artVO);
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
	
	
	
	@Override
	public void insert(ArtVO artVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, artVO.getMbrno());
			pstmt.setString(2, artVO.getDetail());
			pstmt.setString(3, artVO.getArttt());
			pstmt.setInt(4, artVO.getStatus());
			pstmt.setString(5, artVO.getAtno());
			pstmt.setBytes(6, artVO.getApic());

			pstmt.executeUpdate();

			// Handle any SQL errors
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
	public void update(ArtVO artVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_F);

			pstmt.setString(1, artVO.getDetail());
			pstmt.setString(2, artVO.getArttt());
			
			pstmt.setString(3, artVO.getAtno());
			pstmt.setBytes(4, artVO.getApic());
			pstmt.setString(5, artVO.getArtno());
			
			
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
	public void update_art_status(ArtVO artVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_ART_STATUS);

			
			pstmt.setInt(1, artVO.getStatus());
			pstmt.setString(2, artVO.getArtno());
			
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
	public void delete(String artno) {
		int updateCount_ARTs = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			//1.設定於pstmt.executeUpdate()之前
			con.setAutoCommit(false);
			
			//先刪除文章檢舉
			pstmt = con.prepareStatement(DELETE_ARTRP);
			pstmt.setString(1, artno);
			updateCount_ARTs = pstmt.executeUpdate();
			
			//再刪除分類
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, artno);
			pstmt.executeUpdate();
			
			//2.設定於pstmt.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("刪除分類編號" + artno + "時,共有文章" + updateCount_ARTs + "篇同時被刪除");

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
	public ArtVO findByPrimaryKey(String artno) {
		ArtVO artVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, artno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				artVO = new ArtVO();
				artVO.setArtno(rs.getString("artno"));
				artVO.setMbrno(rs.getString("mbrno"));
				artVO.setDetail(rs.getString("detail"));
				artVO.setArttt(rs.getString("arttt"));
				artVO.setPdate(rs.getDate("pdate"));
				
				artVO.setStatus(rs.getInt("status"));
				artVO.setAtno(rs.getString("atno"));
				artVO.setApic(rs.getBytes("apic"));
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
		return artVO;
	}
	
	

	@Override
	public List<ArtVO> getAll() {
		List<ArtVO> list = new ArrayList<ArtVO>();
		ArtVO artVO = null;

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
				artVO = new ArtVO();
				artVO.setArtno(rs.getString("artno"));
				artVO.setMbrno(rs.getString("mbrno"));
				artVO.setDetail(rs.getString("detail"));
				artVO.setArttt(rs.getString("arttt"));
				artVO.setPdate(rs.getDate("pdate"));
				
				artVO.setStatus(rs.getInt("status"));
				artVO.setAtno(rs.getString("atno"));
				artVO.setApic(rs.getBytes("apic"));
				
				list.add(artVO); // Store the row in the list
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
	public List<ArtVO> getArtsByArttt(String arttt) {
		List<ArtVO> list = new ArrayList<ArtVO>();
		ArtVO artVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ARTTT);
			
			
			pstmt.setString(1, "%" + arttt + "%");
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				artVO = new ArtVO();
				artVO.setArtno(rs.getString("artno"));
				artVO.setMbrno(rs.getString("mbrno"));
				artVO.setDetail(rs.getString("detail"));
				artVO.setArttt(rs.getString("arttt"));
				artVO.setPdate(rs.getDate("pdate"));
				
				artVO.setAtno(rs.getString("atno"));
				artVO.setApic(rs.getBytes("apic"));
				
				list.add(artVO);
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
	
	@Override
	public List<ArtVO> getArtsByMbrno(String mbrno) {
		List<ArtVO> list = new ArrayList<ArtVO>();
		ArtVO artVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_STMT_MBRNO);
			
			
			pstmt.setString(1, mbrno);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				artVO = new ArtVO();
				artVO.setArtno(rs.getString("artno"));
				artVO.setMbrno(rs.getString("mbrno"));
				artVO.setArttt(rs.getString("arttt"));
				artVO.setDetail(rs.getString("detail"));
				artVO.setPdate(rs.getDate("pdate"));
				
				artVO.setStatus(rs.getInt("status"));
				artVO.setAtno(rs.getString("atno"));
				artVO.setApic(rs.getBytes("apic"));
				
				list.add(artVO);
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
	
	@Override
	public List<ArtVO> getArtsByAtno(String atno) {
		List<ArtVO> list = new ArrayList<ArtVO>();
		ArtVO artVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_STMT_ATNO);
			
			
			pstmt.setString(1, atno);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				artVO = new ArtVO();
				artVO.setArtno(rs.getString("artno"));
				artVO.setMbrno(rs.getString("mbrno"));
				artVO.setArttt(rs.getString("arttt"));
				artVO.setDetail(rs.getString("detail"));
				artVO.setPdate(rs.getDate("pdate"));
			
				artVO.setStatus(rs.getInt("status"));
				artVO.setAtno(rs.getString("atno"));
				artVO.setApic(rs.getBytes("apic"));
				
				list.add(artVO);
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
	
	
	public static byte[] setPic(String path) throws IOException {
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
		
		ArtJDBCDAO dao = new ArtJDBCDAO();
		
//		新增
//	    ArtVO artVO1 = new ArtVO();
//		artVO1.setMbrno("BM00010");
//		artVO1.setDetail("0606上課進度0");
//		artVO1.setArttt("啥都沒學到");
//		artVO1.setStatus(new Integer(0));
//		artVO1.setAtno("AT01");
//		try {
//			byte[] pic = setPic("WebContent/back-end/art/images/12.jpg");
//			artVO1.setApic(pic);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		dao.insert(artVO1);
//		System.out.println("作者編號: " + artVO1.getMbrno() + " 已新增成功文章");
		
		//修改
		ArtVO artVO2 = new ArtVO();
		artVO2.setDetail("韶憶都害羞了，我是沒感覺啦");
		artVO2.setArttt("狂看表特版");
		
		artVO2.setStatus(new Integer(0));
		artVO2.setAtno("AT02");
		artVO2.setArtno("MA00013");
		try {
			byte[] pic = setPic("WebContent/back-end/art/images/31.jpg");
			artVO2.setApic(pic);
		} catch (IOException e) {
			e.printStackTrace();
		}
		dao.update(artVO2);
		System.out.println("文章編號 " + artVO2.getArtno() + " 已修改成功");
		
		//修改文章狀態
//		ArtVO artVO6 = new ArtVO();
//		artVO6.setStatus(new Integer(0));
//		artVO6.setArtno("MA00010");
//		dao.update_art_status(artVO6);
//		System.out.println("文章編號 " + artVO6.getArtno() + " 的文章狀態已修改成功");
		
		//刪除
//		ArtVO artVO3 = new ArtVO();
//		String artno3 = "MA00003";
//		dao.delete(artno3);
//		System.out.println("文章編號 " + artno3 + " 已刪除成功");
		
		//查詢
//		ArtVO artVO4 = dao.findByPrimaryKey("MA00008");
//		System.out.print(artVO4.getArtno() + ",");
//		System.out.print(artVO4.getMbrno() + ",");
//		System.out.print(artVO4.getDetail() + ",");
//		System.out.print(artVO4.getArttt() + ",");
//		System.out.print(artVO4.getPdate() + ",");

//		System.out.print(artVO4.getStatus() + ",");
//		System.out.print(artVO4.getApic() + ",");
//		System.out.println(artVO4.getAtno());
		
		
		//查全部
//		List<ArtVO> list = dao.getAll();
//		for (ArtVO aArt : list) {
//			System.out.print(aArt.getArtno() + ",");
//			System.out.print(aArt.getMbrno() + ",");
//			System.out.print(aArt.getDetail() + ",");
//			System.out.print(aArt.getArttt() + ",");
//			System.out.print(aArt.getPdate() + ",");

//			System.out.print(aArt.getStatus() + ",");
//			System.out.print(aArt.getApic() + ",");
//			System.out.println(aArt.getAtno());
//			System.out.println();
//			System.out.println("==============================");
//			System.out.println();
//		}
		
		//查標題
//		List<ArtVO> list = dao.getArtsByArttt("大衛");
//		for (ArtVO aArt : list) {
//			System.out.print(aArt.getArtno() + ",");
//			System.out.print(aArt.getMbrno() + ",");
//			System.out.print(aArt.getArttt() + ",");
//			System.out.print(aArt.getPdate() + ",");

//			System.out.print(aArt.getApic() + ",");
//			System.out.println(aArt.getAtno());
//			System.out.println();
//			System.out.println("==============================");
//			System.out.println();
//		}
		
		//查作者的文章
//		List<ArtVO> list = dao.getArtsByMbrno("BM00007");
//		for (ArtVO aArt : list) {
//			System.out.print(aArt.getArtno() + ",");
//			System.out.print(aArt.getMbrno() + ",");
//			System.out.print(aArt.getArttt() + ",");
//			System.out.print(aArt.getDetail() + ",");
//			System.out.print(aArt.getPdate() + ",");

//			System.out.print(aArt.getStatus() + ",");
//			System.out.print(aArt.getApic() + ",");
//			System.out.println(aArt.getAtno());
//			System.out.println();
//			System.out.println("==============================");
//			System.out.println();
//		}
		
		//查分類的文章
				List<ArtVO> list = dao.getArtsByStatus(0);
				for (ArtVO aArt : list) {
					System.out.print(aArt.getArtno() + ",");
					System.out.print(aArt.getMbrno() + ",");
					System.out.print(aArt.getArttt() + ",");
					System.out.print(aArt.getDetail() + ",");
					System.out.print(aArt.getPdate() + ",");
				
					System.out.print(aArt.getStatus() + ",");
					System.out.print(aArt.getApic() + ",");
					System.out.println(aArt.getAtno());
					System.out.println();
					System.out.println("==============================");
					System.out.println();
				}
	
	
	}
	
}
