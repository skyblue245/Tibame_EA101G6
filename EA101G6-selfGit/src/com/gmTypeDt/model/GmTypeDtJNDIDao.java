package com.gmTypeDt.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class GmTypeDtJNDIDao implements GmTypeDtDao_interface {
	
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
	
	private static final String SQLADD="INSERT INTO GMTYPEDT (TYPENO,COMMNO)"
			+ "VALUES(?,?)";
	private static final String SQLDELETE="DELETE FROM GMTYPEDT WHERE TYPENO=? AND COMMNO=?";
	private static final String SQLDELETEBYCOMMNO="DELETE FROM GMTYPEDT WHERE COMMNO=?";
	private static final String SQLDELETEBYTYPENO="DELETE FROM GMTYPEDT WHERE TYPENO=?";
	private static final String SQLSELALL="SELECT * FROM GMTYPEDT";
	private static final String SQLSELBYTYPE="SELECT * FROM GMTYPEDT WHERE TYPENO=?";
	private static final String SQLSELBYCOMM="SELECT * FROM GMTYPEDT WHERE COMMNO=?";
	@Override
	public void add(GmTypeDtVO gmTypeDt) {
		// TODO Auto-generated method stub
		
		Connection conn =null;
		PreparedStatement past = null;
		
		try {
			conn =ds.getConnection();
			
			conn.setAutoCommit(false);
			past=conn.prepareStatement(SQLADD);
			past.setString(1,gmTypeDt.getTypeNo());
			past.setString(2,gmTypeDt.getCommNo());
			past.executeUpdate();
			
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			e.printStackTrace();
		}finally {
			
			try {
				conn.setAutoCommit(true);
				if(past!=null)
					past.close();
				if(conn!=null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
	}
	


	@Override
	public void delete(String typeNo,String commNo) {
		// TODO Auto-generated method stub
		
		Connection conn =null;
		PreparedStatement past =null;
		
		try {
			conn=ds.getConnection();
			
			conn.setAutoCommit(false);
			past=conn.prepareStatement(SQLDELETE);
			past.setString(1,typeNo);
			past.setString(2,commNo);
			past.executeUpdate();
			
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			
			try {
				conn.setAutoCommit(true);
				if(past!=null)
					past.close();
				if(conn!=null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		}
		
	}

//	@Override
//	public void update(GmTypeDtVO gmTypeDt) {
//		// TODO Auto-generated method stub
//		
//	}

	@Override
	public Set<GmTypeDtVO> getAll() {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement past =null;
		ResultSet rs=null;
		Set<GmTypeDtVO> set =new LinkedHashSet<GmTypeDtVO>();
		try {
			conn=ds.getConnection();
			past=conn.prepareStatement(SQLSELALL);
			rs=past.executeQuery();
			while(rs.next()) {
				GmTypeDtVO vo =new GmTypeDtVO();
				vo.setTypeNo(rs.getString(1));
				vo.setCommNo(rs.getString(2));
				set.add(vo);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			try {
				if(rs!=null)
					rs.close();
				if(past!=null)
					past.close();
				if(conn!=null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return set;
	}

	@Override
	public Set<GmTypeDtVO> findByTypeNo(String typeNo) {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement past =null;
		ResultSet rs=null;
		Set<GmTypeDtVO> set =new LinkedHashSet<GmTypeDtVO>();
		try {
			conn=ds.getConnection();
			past=conn.prepareStatement(SQLSELBYTYPE);
			past.setString(1,typeNo);
			rs=past.executeQuery();
			while(rs.next()) {
				GmTypeDtVO vo =new GmTypeDtVO();
				vo.setTypeNo(rs.getString(1));
				vo.setCommNo(rs.getString(2));
				set.add(vo);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			try {
				if(rs!=null)
					rs.close();
				if(past!=null)
					past.close();
				if(conn!=null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return set;
	}

	@Override
	public Set<GmTypeDtVO> findByCommNo(String commNo) {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement past =null;
		ResultSet rs=null;
		Set<GmTypeDtVO> set =new LinkedHashSet<GmTypeDtVO>();
		try {
			conn=ds.getConnection();
			past=conn.prepareStatement(SQLSELBYCOMM);
			past.setString(1,commNo);
			rs=past.executeQuery();
			while(rs.next()) {
				GmTypeDtVO vo =new GmTypeDtVO();
				vo.setTypeNo(rs.getString(1));
				vo.setCommNo(rs.getString(2));
				set.add(vo);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			try {
				if(rs!=null)
					rs.close();
				if(past!=null)
					past.close();
				if(conn!=null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return set;
		
	}

	
	@Override
	public void deleteByCommNo(String commNo) {
		// TODO Auto-generated method stub
		
		Connection conn =null;
		PreparedStatement past =null;
		
		try {
			conn=ds.getConnection();
			
			conn.setAutoCommit(false);
			past=conn.prepareStatement(SQLDELETEBYCOMMNO);
			past.setString(1,commNo);
			past.executeUpdate();
			
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			
			try {
				conn.setAutoCommit(true);
				if(past!=null)
					past.close();
				if(conn!=null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		}
		
	}
	
	@Override
	public void deleteByTypeNo(String typeNo) {
		// TODO Auto-generated method stub
		
		Connection conn =null;
		PreparedStatement past =null;
		
		try {
			conn=ds.getConnection();
			
			conn.setAutoCommit(false);
			past=conn.prepareStatement(SQLDELETEBYTYPENO);
			past.setString(1,typeNo);
			past.executeUpdate();
			
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			
			try {
				conn.setAutoCommit(true);
				if(past!=null)
					past.close();
				if(conn!=null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		}
		
	}




}
