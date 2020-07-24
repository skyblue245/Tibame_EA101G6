package com.gmType.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class GmTypeJDBCDaoImpl implements GmTypeDao_interface {
	
	static{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static final String URL="jdbc:oracle:thin:@localhost:1521:xe";
	private static final String NAME="EA101";
	private static final String PSW="123456";
	private static final String SQLADD="INSERT INTO GMTYPE (TYPENO,TYPENAME)"
			+ "VALUES ('ZT'||LPAD(TO_CHAR(TYPENO_SEQ.NEXTVAL),5, '0'),?)";
	private static final String SQLUPDATE="UPDATE GMTYPE SET TYPENAME=? "
			+ "WHERE TYPENO=?";
	private static final String SQLDELETE="DELETE FROM GMTYPE WHERE TYPENO=?";
	private static final String SQLSELNO="SELECT * FROM GMTYPE WHERE TYPENO=?";
	private static final String SQLSELALL="SELECT * FROM GMTYPE ORDER BY TYPENO";
	
	@Override
	public String add(GmTypeVO gmType) {
		// TODO Auto-generated method stub
		Connection conn =null;
		PreparedStatement past = null;
		ResultSet rs = null;
		String seq="";
		
		try {
			conn =DriverManager.getConnection(URL,NAME,PSW);
			conn.setAutoCommit(false);
			String cols[] = {"TYPENO"};
			past=conn.prepareStatement(SQLADD,cols);
			past.setString(1,gmType.getTypeName());
			past.executeUpdate();
			conn.commit();
			
			rs = past.getGeneratedKeys();
			if (rs.next()) {
				seq = rs.getString(1);
			}
			
			
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
		return seq;
		
		
		
		
	}

	@Override
	public void delete(String typeNo) {
		// TODO Auto-generated method stub
		
		Connection conn =null;
		PreparedStatement past = null;
		
		try {
			conn =DriverManager.getConnection(URL,NAME,PSW);
			conn.setAutoCommit(false);
			past=conn.prepareStatement(SQLDELETE);
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

	@Override
	public void update(GmTypeVO gmType) {
		// TODO Auto-generated method stub
		
		Connection conn =null;
		PreparedStatement past = null;
		
		try {
			conn =DriverManager.getConnection(URL,NAME,PSW);
			conn.setAutoCommit(false);
			past=conn.prepareStatement(SQLUPDATE);
			past.setString(1,gmType.getTypeName());
			past.setString(2,gmType.getTypeNo());
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
	public Set<GmTypeVO> getAll() {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement past = null;
		ResultSet rs = null;
		Set<GmTypeVO> set = new LinkedHashSet<GmTypeVO>();
		try {
			conn = DriverManager.getConnection(URL,NAME,PSW);
			past = conn.prepareStatement(SQLSELALL);
			rs = past.executeQuery();
			while(rs.next()) {
				GmTypeVO gmType = new GmTypeVO();
				gmType.setTypeNo(rs.getString(1));
				gmType.setTypeName(rs.getString(2));
				set.add(gmType);
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
	public GmTypeVO findOneByTypeNo(String typeNo) {
		// TODO Auto-generated method stub
		
		Connection conn = null;
		PreparedStatement past = null;
		ResultSet rs = null;
		GmTypeVO gmType=null;
		try {
			conn = DriverManager.getConnection(URL,NAME,PSW);
			past = conn.prepareStatement(SQLSELNO);
			past.setString(1,typeNo);
			rs = past.executeQuery();
			if(rs.next()) {
				gmType = new GmTypeVO();
				gmType.setTypeNo(rs.getString(1));
				gmType.setTypeName(rs.getString(2));
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
		
		return gmType;
	}


}
