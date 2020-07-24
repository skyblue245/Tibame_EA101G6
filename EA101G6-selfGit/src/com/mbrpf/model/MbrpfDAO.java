package com.mbrpf.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.mbrpf.model.MbrpfVO;

public class MbrpfDAO implements MbrpfDAO_interface {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
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
			"INSERT INTO mbrpf (mbrno,mbract,mbrpw,mbrname,mbrimg,birth,sex,mail,phone,mbrac,nickname,points,status,ratedtotal,startotal,unattend,ttattend) VALUES ('BM'||LPAD(to_char(mem_seq.NEXTVAL),5,'0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT mbrno,mbract,mbrpw,mbrname,mbrimg,to_char(birth,'yyyy-mm-dd') birth,sex,mail,phone,mbrac,nickname,points,status,ratedtotal,startotal,unattend,ttattend FROM mbrpf ORDER BY mbrno";
		private static final String GET_ONE_STMT = 
			"SELECT mbrno,mbract,mbrpw,mbrname,mbrimg,to_char(birth,'yyyy-mm-dd') birth,sex,mail,phone,mbrac,nickname,points,status,ratedtotal,startotal,unattend,ttattend FROM mbrpf WHERE mbrno = ?";
		private static final String DELETE = 
			"DELETE FROM mbrpf WHERE mbrno = ?";
		private static final String UPDATE = 
			"UPDATE mbrpf set mbract=?,mbrpw=?,mbrname=?,mbrimg=?,birth=?,sex=?,mail=?,phone=?,mbrac=?,nickname=?,points=?,status=?,ratedtotal=?,startotal=?,unattend=?,ttattend=? WHERE mbrno = ?";
		private static final String FORGET_PWD =
				"UPDATE mbrpf SET mbrpw = ? WHERE mail = ? AND mbract = ?";
		private static final String FIND_BY_MBRACT = 
				"SELECT mbract,mbrpw,mbrname FROM mbrpf WHERE mbract = ?";
		private static final String FIND_BY_MAIL =
				"SELECT * FROM mbrpf WHERE mail = ? AND mbract = ?";
		private static final String LOGIN =
				"SELECT * FROM mbrpf WHERE mbract = ?";
		private static final String UPDATEPOINT = 
				"UPDATE mbrpf set points=? where mbrno = ?";
	@Override
	public void insert(MbrpfVO mbrpfVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, mbrpfVO.getMbract());
			pstmt.setString(2, mbrpfVO.getMbrpw());
			pstmt.setString(3, mbrpfVO.getMbrname());
			pstmt.setBytes(4, mbrpfVO.getMbrimg());
			pstmt.setDate(5, mbrpfVO.getBirth());
			pstmt.setInt(6, mbrpfVO.getSex());
			pstmt.setString(7, mbrpfVO.getMail());
			pstmt.setString(8, mbrpfVO.getPhone());
			pstmt.setString(9, mbrpfVO.getMbrac());
			pstmt.setString(10, mbrpfVO.getNickname());
			pstmt.setInt(11, mbrpfVO.getPoints());
			pstmt.setInt(12, mbrpfVO.getStatus());
			pstmt.setInt(13, mbrpfVO.getRatedtotal());
			pstmt.setInt(14, mbrpfVO.getStartotal());
			pstmt.setInt(15, mbrpfVO.getUnattend());
			pstmt.setInt(16, mbrpfVO.getTtattend());

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
	public void update(MbrpfVO mbrpfVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, mbrpfVO.getMbract());
			pstmt.setString(2, mbrpfVO.getMbrpw());
			pstmt.setString(3, mbrpfVO.getMbrname());
			pstmt.setBytes(4, mbrpfVO.getMbrimg());
			pstmt.setDate(5, mbrpfVO.getBirth());
			pstmt.setInt(6, mbrpfVO.getSex());
			pstmt.setString(7, mbrpfVO.getMail());
			pstmt.setString(8, mbrpfVO.getPhone());
			pstmt.setString(9, mbrpfVO.getMbrac());
			pstmt.setString(10, mbrpfVO.getNickname());
			pstmt.setInt(11, mbrpfVO.getPoints());
			pstmt.setInt(12, mbrpfVO.getStatus());
			pstmt.setInt(13, mbrpfVO.getRatedtotal());
			pstmt.setInt(14, mbrpfVO.getStartotal());
			pstmt.setInt(15, mbrpfVO.getUnattend());
			pstmt.setInt(16, mbrpfVO.getTtattend());
			pstmt.setString(17, mbrpfVO.getMbrno());
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
	public String forgetPwd(String mail , String mbract) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String newPwd = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(FORGET_PWD);
			newPwd = getRanPwd();
			pstmt.setString(1, newPwd);
			pstmt.setString(2, mail);
			pstmt.setString(3, mbract);
			
			rs = pstmt.executeQuery();
		}catch(SQLException se) {
			se.printStackTrace();
		}finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException se){
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
		return newPwd;
	}
	
	@Override
	public void delete(String mbrno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, mbrno);

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
	public MbrpfVO findByPrimaryKey(String mbrno) {

		MbrpfVO mbrpfVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, mbrno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// mbrpfVO 也稱為 Domain objects
				mbrpfVO = new MbrpfVO();
				mbrpfVO.setMbrno(rs.getString("mbrno"));
				mbrpfVO.setMbract(rs.getString("mbract"));
				mbrpfVO.setMbrpw(rs.getString("mbrpw"));
				mbrpfVO.setMbrname(rs.getString("mbrname"));
				mbrpfVO.setMbrimg(rs.getBytes("mbrimg"));
				mbrpfVO.setBirth(rs.getDate("birth"));
				mbrpfVO.setSex(rs.getInt("sex"));
				mbrpfVO.setMail(rs.getString("mail"));
				mbrpfVO.setPhone(rs.getString("phone"));
				mbrpfVO.setMbrac(rs.getString("mbrac"));
				mbrpfVO.setNickname(rs.getString("nickname"));
				mbrpfVO.setPoints(rs.getInt("points"));
				mbrpfVO.setStatus(rs.getInt("status"));
				mbrpfVO.setRatedtotal(rs.getInt("ratedtotal"));
				mbrpfVO.setStartotal(rs.getInt("startotal"));
				mbrpfVO.setUnattend(rs.getInt("unattend"));
				mbrpfVO.setTtattend(rs.getInt("ttattend"));
				
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
		return mbrpfVO;
	}

	@Override
	public MbrpfVO findByMail(String mail ,String mbract) {
		MbrpfVO mbrpfVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_MAIL);

			pstmt.setString(1, mail);
			pstmt.setString(2, mbract);

			rs = pstmt.executeQuery();
			
			while(rs.next()){
				mbrpfVO = new MbrpfVO();

				mbrpfVO.setMbract(rs.getString("mbract"));
				mbrpfVO.setMbrname(rs.getString("mbrname"));
				mbrpfVO.setMbrpw(rs.getString("mbrpw"));
				mbrpfVO.setMail(rs.getString("mail"));
			}
			
		}catch(SQLException se) {
			se.printStackTrace();
		}finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException se){
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
		return mbrpfVO;
	}
	
	@Override
	public List<MbrpfVO> getAll() {
		List<MbrpfVO> list = new ArrayList<MbrpfVO>();
		MbrpfVO mbrpfVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// mbrpfVO 也稱為 Domain objects
				mbrpfVO = new MbrpfVO();
				mbrpfVO.setMbrno(rs.getString("mbrno"));
				mbrpfVO.setMbract(rs.getString("mbract"));
				mbrpfVO.setMbrpw(rs.getString("mbrpw"));
				mbrpfVO.setMbrname(rs.getString("mbrname"));
				mbrpfVO.setMbrimg(rs.getBytes("mbrimg"));
				mbrpfVO.setBirth(rs.getDate("birth"));
				mbrpfVO.setSex(rs.getInt("sex"));
				mbrpfVO.setMail(rs.getString("mail"));
				mbrpfVO.setPhone(rs.getString("phone"));
				mbrpfVO.setMbrac(rs.getString("mbrac"));
				mbrpfVO.setNickname(rs.getString("nickname"));
				mbrpfVO.setPoints(rs.getInt("points"));
				mbrpfVO.setStatus(rs.getInt("status"));
				mbrpfVO.setRatedtotal(rs.getInt("ratedtotal"));
				mbrpfVO.setStartotal(rs.getInt("startotal"));
				mbrpfVO.setUnattend(rs.getInt("unattend"));
				mbrpfVO.setTtattend(rs.getInt("ttattend"));
				list.add(mbrpfVO); // Store the row in the list
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

	@Override
	public MbrpfVO findByMbract(String mbract) {
			
			MbrpfVO mbrpfVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				
				con = ds.getConnection();
				pstmt = con.prepareStatement(FIND_BY_MBRACT);

				pstmt.setString(1, mbract);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// mbrpfVO  也稱為 Domain objects
					mbrpfVO = new MbrpfVO();
					mbrpfVO.setMbract(rs.getString("mbract"));
					mbrpfVO.setMbrpw(rs.getString("mbrpw"));
					mbrpfVO.setMbrname(rs.getString("mbrname"));
					mbrpfVO.setMbrimg(rs.getBytes("mbrimg"));
					
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
			return mbrpfVO;
		}

	@Override
	public MbrpfVO login(String mbract) {
		
		MbrpfVO mbrpfVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(LOGIN);

			pstmt.setString(1, mbract);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// mbrpfVO  也稱為 Domain objects
				mbrpfVO = new MbrpfVO();
				mbrpfVO.setMbrno(rs.getString("mbrno"));
				mbrpfVO.setMbract(rs.getString("mbract"));
				mbrpfVO.setMbrpw(rs.getString("mbrpw"));
				mbrpfVO.setMbrname(rs.getString("mbrname"));
				mbrpfVO.setMbrimg(rs.getBytes("mbrimg"));
				mbrpfVO.setBirth(rs.getDate("birth"));
				mbrpfVO.setSex(rs.getInt("sex"));
				mbrpfVO.setMail(rs.getString("mail"));
				mbrpfVO.setPhone(rs.getString("phone"));
				mbrpfVO.setMbrac(rs.getString("mbrac"));
				mbrpfVO.setNickname(rs.getString("nickname"));
				mbrpfVO.setPoints(rs.getInt("points"));
				mbrpfVO.setStatus(rs.getInt("status"));
				mbrpfVO.setRatedtotal(rs.getInt("ratedtotal"));
				mbrpfVO.setStartotal(rs.getInt("startotal"));
				mbrpfVO.setUnattend(rs.getInt("unattend"));
				mbrpfVO.setTtattend(rs.getInt("ttattend"));
				
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
		return mbrpfVO;
	}
	
	@Override
	public void updatePoint(MbrpfVO mbrpfVO, Connection conn) {

		PreparedStatement pstmt = null;

		try {

			pstmt = conn.prepareStatement(UPDATEPOINT);

			pstmt.setInt(1, mbrpfVO.getPoints());
			pstmt.setString(2, mbrpfVO.getMbrno());
			pstmt.executeUpdate();

		} catch (SQLException se) {

			try {
				conn.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.getStackTrace();
				}
			}
		}
	}
	
	@Override
	public void updatePoint(MbrpfVO mbrpfVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATEPOINT);

			pstmt.setInt(1, mbrpfVO.getPoints());
			pstmt.setString(2, mbrpfVO.getMbrno());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	//取得亂數密碼
	public static String getRanPwd() {
		char[] pwdAry =new char[5];//要拿來裝亂數密碼的陣列
		char[] all = new char[62];//將我可以顯示的所有字元存放進一個陣列，讓我之後選(26大寫英文+26小寫英文+10個數字)
		for(int i = 0; i < 26; i++) {//將英文字母放進陣列中
			all[i] = (char)(65+i);//0~25放A-Z，A的ASCIIcode為65
			all[26 + i] = (char)(97 + i);//26~51放a-z，a的ASCIIcode為97
		}
		for(int i = 0; i < 10; i++) {
			all[52 + i] = (char)(48 + i);//52~62放0-9，0的ASCIIcode為48
		}
		int[] num = new int[5];//亂數密碼共可放10碼
		for(int i = 0; i < 5; i++) {
			num[i] = (int)(Math.random()*all.length);//1-62的數字亂數取出10個數字，相當於將共62個元素，亂數抽出10個
			pwdAry[i] =  all[ num[i] ];//Ex：如果第一個random 抽出10，即為num[10]，num[] 第0個元素是10
						// ↑ i=0時，all[num[0]] => all[10]
			//↑all[]中第10個元素，為pwdAry[]陣列中第0個元素
		}
		String pwd = String.valueOf(pwdAry);
//		System.out.println(pwd);
//		System.out.println(pwd.length());
		return pwd;
		
		/*
		 
		String strStringType="my string"; //建立一個字串變數strStringType
		char[] chrCharArray; //建立一個字元陣列chrCharArray
		chrCharArray = strStringType.toCharArray(); //將字串變數轉換為字元陣列
		strStringType= String.valueOf(chrCharArray ); //將字元陣列轉換為字串
			
			（1）String類的toCharArray()方法，將字串轉換為字元陣列
			（2）String類的valueOf()方法，將char型別的陣列轉換為字串
		*/
	}

}