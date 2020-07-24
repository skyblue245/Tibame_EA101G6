package com.atype.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.art.model.ArtVO;

public class AtypeDAO implements AtypeDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO atype (atno,atname) VALUES ('AT'||LPAD(to_char(atype_seq.NEXTVAL),2,'0'), ?)";
	private static final String GET_ALL_STMT = "SELECT atno, atname FROM atype";
	private static final String GET_ONE_STMT = "SELECT atno, atname FROM atype where atno = ?";
	private static final String GET_Arts_ByAtno_STMT = "SELECT artno,mbrno,detail,arttt,to_char(pdate,'yyyy-mm-dd')pdate,status,atno FROM ART where atno = ? order by artno";
	
	private static final String DELETE_ARTs = "DELETE FROM art where atno = ?";
	private static final String DELETE_ATYPE = "DELETE FROM atype where atno = ?";
	
	private static final String UPDATE = "UPDATE atype set atname=? where atno = ?";
	
	@Override
	public void insert(AtypeVO atypeVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
						
			pstmt.setString(1, atypeVO.getAtname());
			
			pstmt.executeUpdate();
			
			//Handel any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			//Clean up JDBA resources
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
	public void update(AtypeVO atypeVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, atypeVO.getAtname());
			pstmt.setString(2, atypeVO.getAtno());
			
			pstmt.executeUpdate();
		
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(String atno) {
		int updateCount_ARTs = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			
			//1.設定於pstmt.executeUpdate()之前
			con.setAutoCommit(false);
			
			//先刪除文章
			pstmt = con.prepareStatement(DELETE_ARTs);
			pstmt.setString(1, atno);
			updateCount_ARTs = pstmt.executeUpdate();
			//再刪除分類
			pstmt = con.prepareStatement(DELETE_ATYPE);
			pstmt.setString(1, atno);
			pstmt.executeUpdate();
			
			//2.設定於pstmt.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("刪除分類編號" + atno + "時,共有文章" + updateCount_ARTs + "篇同時被刪除");
		
		} catch (SQLException se) {
			if (con != null) {
				try {
					//3.設定於當有exception發生時之catch區塊內
					con.rollback();
				} catch (SQLException excep) {
				throw new RuntimeException("rollback error occured. " + excep.getMessage());
			    }
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());
				 
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
	public AtypeVO findByPrimaryKey(String atno) {
		
		AtypeVO atypeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, atno);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				//atypeVO 也稱為 Domain objects
				atypeVO = new AtypeVO();
				atypeVO.setAtno(rs.getString("atno"));
				atypeVO.setAtname(rs.getString("atname"));
			}
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		
		return atypeVO;
	}


	@Override
	public List<AtypeVO> getAll() {
		List<AtypeVO> list = new ArrayList<AtypeVO>();
		AtypeVO atypeVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				atypeVO = new AtypeVO();
				atypeVO.setAtno(rs.getString("atno"));
				atypeVO.setAtname(rs.getString("atname"));
				list.add(atypeVO);
			}
		
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public Set<ArtVO> getArtsByAtno(String atno) {
		Set<ArtVO> set = new LinkedHashSet<ArtVO>();
		ArtVO artVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_Arts_ByAtno_STMT);
			
			pstmt.setString(1, atno);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				artVO = new ArtVO();
				artVO.setArtno(rs.getString("artno"));
				artVO.setMbrno(rs.getString("mbrno"));
				artVO.setDetail(rs.getString("detail"));
				artVO.setArttt(rs.getString("arttt"));
				artVO.setPdate(rs.getDate("pdate"));
				artVO.setStatus(rs.getInt("status"));
				artVO.setAtno(rs.getString("atno"));
				set.add(artVO);
			}
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
				} catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}	
		return set;
	}

	

	
}
