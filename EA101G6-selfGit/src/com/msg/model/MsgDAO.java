package com.msg.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;

public class MsgDAO implements MsgDAO_interface {
	
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
		"INSERT INTO msg (msgno,mbrno,detail,artno,status) VALUES ('BMS'||LPAD(to_char(msg_seq.NEXTVAL),4,'0'), ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT msgno,mbrno,detail,artno,status FROM msg order by msgno";
	private static final String GET_ONE_STMT = 
		"SELECT msgno,mbrno,detail,artno,status FROM msg where msgno = ?";
	private static final String DELETE = 
		"DELETE FROM msg where msgno = ?";
	private static final String UPDATE = 
		"UPDATE msg set mbrno=?,detail=?,artno=?,status=? where msgno = ?";
	private static final String GET_BY_ARTNO = "SELECT msgno,mbrno,detail,artno,status FROM msg WHERE artno = ? ORDER BY msgno DESC";

	
	
	
	
	
	
	@Override
	public void insert(MsgVO msgVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			
			
			pstmt.setString(1, msgVO.getMbrno());
			pstmt.setString(2, msgVO.getDetail());
			pstmt.setString(3, msgVO.getArtno());
			pstmt.setInt(4, msgVO.getStatus());
			

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
	public void update(MsgVO msgVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, msgVO.getMbrno());
			pstmt.setString(2, msgVO.getDetail());
			pstmt.setString(3, msgVO.getArtno());
			pstmt.setInt(4, msgVO.getStatus());
			pstmt.setString(5, msgVO.getMsgno());


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
	public void delete(String msgno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, msgno);

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
	public MsgVO findByPrimaryKey(String msgno) {

		MsgVO msgVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, msgno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// msgVO 也稱為 Domain objects
				msgVO = new MsgVO();
				msgVO.setMsgno(rs.getString("msgno"));
				msgVO.setMbrno(rs.getString("mbrno"));
				msgVO.setDetail(rs.getString("detail"));
				msgVO.setArtno(rs.getString("artno"));
				msgVO.setStatus(rs.getInt("status"));
				
				
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
		return msgVO;
	}

	
	@Override
	public List<MsgVO> getAllByArtno(String artno) {
		List<MsgVO> list = new ArrayList<MsgVO>();
		MsgVO msgVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_ARTNO);
			pstmt.setString(1, artno);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// msgVO 也稱為 Domain objects
				msgVO = new MsgVO();
				msgVO.setMsgno(rs.getString("msgno"));
				msgVO.setMbrno(rs.getString("mbrno"));
				msgVO.setDetail(rs.getString("detail"));
				msgVO.setArtno(rs.getString("artno"));
				msgVO.setStatus(rs.getInt("status"));
				list.add(msgVO); // Store the row in the list
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
	public List<MsgVO> getAll() {
		List<MsgVO> list = new ArrayList<MsgVO>();
		MsgVO msgVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// msgVO 也稱為 Domain objects
				msgVO = new MsgVO();
				msgVO.setMsgno(rs.getString("msgno"));
				msgVO.setMbrno(rs.getString("mbrno"));
				msgVO.setDetail(rs.getString("detail"));
				msgVO.setArtno(rs.getString("artno"));
				msgVO.setStatus(rs.getInt("status"));
				list.add(msgVO); // Store the row in the list
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