package com.tfcord.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.mbrpf.model.MbrpfDAO;
import com.mbrpf.model.MbrpfVO;

public class TfcordDAO implements TfcordDAO_Interface {
	private static DataSource ds = null;
	 
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		}catch(NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_PSTMT = "INSERT INTO TFCORD(TFNO, MBRNO, TFTYPE, PRICE, TFTIME, TFSTATUS) VALUES (TO_CHAR(SYSDATE,'YYYYMMDD')||'-'||LPAD(TO_CHAR(TFCORD_SEQ.NEXTVAL),7,'0'), ?, ?, ?, CURRENT_TIMESTAMP, ?)";
	private static final String UPDATE_PSTMT = "UPDATE TFCORD SET MBRNO = ?, TFTYPE = ?, PRICE = ?, TFTIME = ?, TFSTATUS = ? WHERE TFNO = ?";//換點數的修改，按修改時，TFNO會自動抓要修改的那筆的號碼
	private static final String DELETE_PSTMT = "DELETE FROM　TFCORD WHERE TFNO = ?"; //刪除某筆申請的換錢紀錄
	private static final String CHANGE_BY_PK = "UPDATE TFCORD SET TFSTATUS = 1 WHERE TFNO = ?";//審核通過某筆訂單狀態
	private static final String FIND_BY_PK = "SELECT * FROM TFCORD WHERE TFNO = ?";//查詢某筆訂單
	private static final String GET_MBR_ALL = "SELECT * FROM TFCORD WHERE MBRNO = ? ORDER BY TFNO DESC";//查某會員有哪些紀錄，也可用於帳戶管理
	private static final String GET_NOTYET_ALL = "SELECT * FROM TFCORD WHERE TFSTATUS = 0";
	private static final String GET_ALL = "SELECT * FROM TFCORD ORDER BY TFNO DESC";//查所有點數紀錄
	
	@Override
	public void insert(TfcordVO tfcordVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_PSTMT);
			
			pstmt.setString(1, tfcordVO.getMbrno());
			pstmt.setString(2, tfcordVO.getTftype());
			pstmt.setInt(3, tfcordVO.getPrice());
			pstmt.setInt(4,tfcordVO.getTfstatus());
			pstmt.executeUpdate();
			
			
			
		}catch(SQLException se) {
			se.printStackTrace();
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}			
			}			
			if(con != null) {
				try {
					con.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public void update(TfcordVO tfcordVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_PSTMT);
			
			pstmt.setString(1, tfcordVO.getMbrno());
			pstmt.setString(2, tfcordVO.getTftype());
			pstmt.setInt(3, tfcordVO.getPrice());
			pstmt.setTimestamp(4, tfcordVO.getTftime());
			pstmt.setInt(5, tfcordVO.getTfstatus());
			pstmt.setString(6, tfcordVO.getTfno());
			
			pstmt.executeUpdate();
		}catch(SQLException se) {
			se.printStackTrace();
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
		}	
	}
	
	@Override
	public void delete(String tfno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_PSTMT);
			pstmt.setString(1, tfno);
			
			pstmt.executeUpdate();
		}catch(SQLException se) {
			se.printStackTrace();
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
				if(con != null) {
					try {
						con.close();
					}catch(SQLException se) {
						se.printStackTrace();
					}
				}
			}
		}	
	}
	
	@Override
	public TfcordVO findByPrimaryKey(String tfno) {
		TfcordVO tfcordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setString(1, tfno);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				tfcordVO = new TfcordVO();
				
				tfcordVO.setTfno(rs.getString("tfno"));
				tfcordVO.setMbrno(rs.getString("mbrno"));
				tfcordVO.setTftype(rs.getString("tftype"));
				tfcordVO.setPrice(rs.getInt("price"));
				tfcordVO.setTftime(rs.getTimestamp("tftime"));
				tfcordVO.setTfstatus(rs.getInt("tfstatus"));
			}
		}catch (SQLException se) {
			se.printStackTrace();
		}finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
		}
		return tfcordVO;
	}
	@Override
	public List<TfcordVO> findWhoAll(String mbrno) {
		List<TfcordVO> mbrTfAll = new ArrayList();
		TfcordVO tfcord = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_MBR_ALL);
			
			pstmt.setString(1, mbrno);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				tfcord = new TfcordVO();
				tfcord.setTfno(rs.getString("tfno"));
				tfcord.setMbrno(rs.getString("mbrno"));
				tfcord.setTftype(rs.getString("tftype"));
				tfcord.setPrice(rs.getInt("price"));
				tfcord.setTftime(rs.getTimestamp("tftime"));
				tfcord.setTfstatus(rs.getInt("tfstatus"));
				mbrTfAll.add(tfcord);
			}
		}catch(SQLException se) {
			se.printStackTrace();
		}finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
		}
		return mbrTfAll;
	}
	@Override
	public List<TfcordVO> getAll() {
		List<TfcordVO> tfAll = new ArrayList();
		TfcordVO tfcord = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				tfcord = new TfcordVO();
				tfcord.setTfno(rs.getString("tfno"));
				tfcord.setMbrno(rs.getString("mbrno"));
				tfcord.setTftype(rs.getString("tftype"));
				tfcord.setPrice(rs.getInt("price"));
				tfcord.setTftime(rs.getTimestamp("tftime"));
				tfcord.setTfstatus(rs.getInt("tfstatus"));
				tfAll.add(tfcord);
			}	
		}catch(SQLException se) {
			se.printStackTrace();
		}finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
			
			if(pstmt != null){
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
		}
		return tfAll;
	}
	
	@Override
	public List<TfcordVO> getNotYetAll() {
		List<TfcordVO> tfAll = new ArrayList();
		TfcordVO tfcord = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_NOTYET_ALL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				tfcord = new TfcordVO();
				tfcord.setTfno(rs.getString("tfno"));
				tfcord.setMbrno(rs.getString("mbrno"));
				tfcord.setTftype(rs.getString("tftype"));
				tfcord.setPrice(rs.getInt("price"));
				tfcord.setTftime(rs.getTimestamp("tftime"));
				tfcord.setTfstatus(rs.getInt("tfstatus"));
				tfAll.add(tfcord);
			}	
		}catch(SQLException se) {
			se.printStackTrace();
		}finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
			
			if(pstmt != null){
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
		}
		return tfAll;
	}
	
	@Override
	public void changeStatusBytfno(String tfno) {
		TfcordVO tfcordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(CHANGE_BY_PK);
			pstmt.setString(1, tfno);
			pstmt.executeUpdate();
			
		}catch (SQLException se) {
			se.printStackTrace();
		}finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
		}
	}
	
	//傳入我要新增的tfcordVO和已經改好點數的會員mbrpfVO(修改的會員物件是在Servlet修改)
	@Override
	public String insert2(TfcordVO tfcordVO, MbrpfVO mbrpfVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sequence = null;
		try {
			con = ds.getConnection();
			
			//先將自autoCommit關掉，不然我交易過程有錯誤，會沒辦法rollback
			con.setAutoCommit(false);
			
			String[] cols = {"TFNO"};//將要綁定出來的自增主鍵欄位名是什麼
			
			//因為這邊我只是要更新會員的點數，會員沒有需要我的自增主鍵，所以不需要綁定出來
			
			//將傳入的點數物件的資訊set進去
			pstmt = con.prepareStatement(INSERT_PSTMT,cols);
			//沒有 cols，就不會有自增主鍵的綁定
			pstmt.setString(1, tfcordVO.getMbrno());
			pstmt.setString(2, tfcordVO.getTftype());
			pstmt.setInt(3, tfcordVO.getPrice());
			pstmt.setInt(4,tfcordVO.getTfstatus());
			pstmt.executeUpdate();
			
			rs = pstmt.getGeneratedKeys();//透過getGeneratedKeys()方法：取出自增主鍵
			
			if(rs.next()) {
				sequence = rs.getString(1);
				//如果要綁出來的自增主鍵有兩到三個
				//就是rs.getString(1)、rs.getString(2)、rs.getString(3)...等
			}
			
			MbrpfDAO mbrpfDAO = new MbrpfDAO();
			mbrpfDAO.updatePoint(mbrpfVO, con);//將傳進來更新好的會員物件和現在同一條連線，透過updatePoint()去修改
			con.commit();//如過過程中沒有問題就commit
		}catch(SQLException se) {
			try {
				con.rollback();//如果有錯誤就rollback
			} catch (SQLException e) {
				e.printStackTrace();
			}
			se.printStackTrace();
		}finally {
				try {
					con.setAutoCommit(true);//最後關連線之前，告知交易結束
					if(rs != null) {
						rs.close();
					}
					if(pstmt != null) {
						pstmt.close();
					}
					if(con != null) {
						con.close();
					}		
				}catch(SQLException se) {
					se.printStackTrace();
				}			
			}
		return sequence;
	}
	
	
	
	public static void main(String[] args) {
		
//新增測試
//		TfcordDAO_JDBC dao = new  TfcordDAO_JDBC();
//		TfcordVO tfcordVO = new TfcordVO();
//		tfcordVO.setMbrno("BM00001");
//		tfcordVO.setTftype("M");
//		tfcordVO.setPrice(10000);
//		tfcordVO.setTfstatus(0);
//		dao.insert(tfcordVO);
	
//修改測試
//		TfcordDAO_JDBC dao = new TfcordDAO_JDBC();
//		TfcordVO tfcordVO1 = new TfcordVO();
//		tfcordVO1.setMbrno("BM00005");
//		tfcordVO1.setTftype("P");
//		tfcordVO1.setPrice(2000);
//		tfcordVO1.setTftime(new java.sql.Timestamp(new java.util.Date().getTime()));
//		tfcordVO1.setTfstatus(0);
//		tfcordVO1.setTfno("20200611-0000015");
//		dao.update(tfcordVO1);	
		
//刪除測試
//		TfcordDAO_JDBC dao = new TfcordDAO_JDBC();
//		dao.delete("20200611-0000015");

//查詢某筆轉換紀錄測試
//		TfcordDAO_JDBC dao = new TfcordDAO_JDBC();
//		TfcordVO tfcordVO = dao.findByPrimaryKey("20200611-0000014");
//		System.out.println(tfcordVO.getTfno());
//		System.out.println(tfcordVO.getMbrno());
//		System.out.println(tfcordVO.getTftype());
//	    System.out.println(tfcordVO.getPrice());
//	    System.out.println(tFormat(tfcordVO.getTftime()));
//	    System.out.println(tfcordVO.getTfstatus());

//審核狀態測試
//		TfcordDAO_JDBC dao = new TfcordDAO_JDBC();
//		dao.changeStatusBytfno("20200630-0000050");
		
//查詢某會員轉換紀錄測試
//		TfcordDAO_JDBC dao = new TfcordDAO_JDBC();
//		List<TfcordVO> mbrTfAll = dao.findWhoAll("BM00001");
//	    for(TfcordVO tfcordVO3 : mbrTfAll) {
//	    	System.out.print(tfcordVO3.getTfno() + ",");
//	    	System.out.print(tfcordVO3.getMbrno() + ",");
//	    	System.out.print(tfcordVO3.getTftype() + ",");
//	    	System.out.print(tfcordVO3.getPrice() + ",");
//	    	System.out.print(tFormat(tfcordVO3.getTftime()) + ",");
//	    	System.out.print(tfcordVO3.getTfstatus());
//	    	System.out.println();    	
//	    }
		
//查詢所有轉換紀錄測試
//		TfcordDAO_JDBC dao = new TfcordDAO_JDBC();
//		List<TfcordVO> tfcordAll = dao.getAll();
//	    for(TfcordVO tfcordVO4 : tfcordAll) {
//	    	System.out.print(tfcordVO4.getTfno() + ",");
//	    	System.out.print(tfcordVO4.getMbrno() + ",");
//	    	System.out.print(tfcordVO4.getTftype() + ",");
//	    	System.out.print(tfcordVO4.getPrice() + ",");
//	    	System.out.print(tFormat(tfcordVO4.getTftime()) + ",");
//	    	System.out.print(tfcordVO4.getTfstatus());
//	    	System.out.println();    	
//	    }
		
//查詢所有還沒處理的轉換紀錄測試
		TfcordDAO_JDBC dao = new TfcordDAO_JDBC();
		List<TfcordVO> tfcordAll = dao.getNotYetAll();
	    for(TfcordVO tfcordVO9 : tfcordAll) {
	    	System.out.print(tfcordVO9.getTfno() + ",");
	    	System.out.print(tfcordVO9.getMbrno() + ",");
	    	System.out.print(tfcordVO9.getTftype() + ",");
	    	System.out.print(tfcordVO9.getPrice() + ",");
	    	System.out.print(tFormat(tfcordVO9.getTftime()) + ",");
	    	System.out.print(tfcordVO9.getTfstatus());
	    	System.out.println();    	
	    }
		
	}
	
	public static String tFormat(java.sql.Timestamp time) {
		java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = df.format(time);//format(util.Date)，sql.Timestamp是util.Date的子類別，所以也可以放進去
		return str;
	}	
		
	
//	public static Timestamp timeFormat() {
//		java.util.Date du = new java.util.Date();
//		long long1 = du.getTime();
//		java.sql.Timestamp dts = new java.sql.Timestamp(long1);
//		java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String str = df.format(dts);
//		java.sql.Timestamp datest = java.sql.Timestamp().valueOf(str); 
//		return datest;
//	}
	
	
	

}
