package com.mallOrDt.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.mall.model.MallVO;
import com.mallOr.model.MallOrVO;

public class MallOrDtJDBCDaoImpl implements MallOrDtDao_interface {
	
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
	private static final String SQLADD="INSERT INTO MALLORDT(MALLORNO,COMMNO,QUANTITY,PRICE)"
			+ "VALUES(?,?,?,?)";
	private static final String SQLUPDATE="UPDATE MALLORDT SET QUANTITY=?,PRICE=?"
			+ "WHERE MALLORNO=? AND COMMNO=?";
	private static final String SQLDELETE="DELETE FROM MALLORDT WHERE MALLORNO=? AND COMMNO=?";
	private static final String SQLSELALL="SELECT * FROM MALLORDT";
	private static final String SQLSELORNO="SELECT * FROM MALLORDT WHERE MALLORNO=?";
	private static final String SQLSELONE="SELECT * FROM MALLORDT WHERE MALLORNO=? AND COMMNO=?";
	
	
	@Override
	public void add(MallOrDtVO mallOrDt) {
		// TODO Auto-generated method stub
		Connection conn =null;
		PreparedStatement past =null;
		
		try {
			conn=DriverManager.getConnection(URL,NAME,PSW);
			
			conn.setAutoCommit(false);
			past=conn.prepareStatement(SQLADD);
			past.setString(1,mallOrDt.getMallOrNo());
			past.setString(2,mallOrDt.getCommNo());
			past.setInt(3,mallOrDt.getQuantity());
			past.setInt(4,mallOrDt.getPrice());
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
	public void delete(String mallOrNo,String commNo) {
		// TODO Auto-generated method stub
		Connection conn =null;
		PreparedStatement past =null;
		
		try {
			conn=DriverManager.getConnection(URL,NAME,PSW);
			
			conn.setAutoCommit(false);
			past=conn.prepareStatement(SQLDELETE);
			past.setString(1,mallOrNo);
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

	@Override
	public void update(MallOrDtVO mallOrDt) {
		// TODO Auto-generated method stub
		Connection conn =null;
		PreparedStatement past =null;
		
		try {
			conn=DriverManager.getConnection(URL,NAME,PSW);
			
			conn.setAutoCommit(false);
			past=conn.prepareStatement(SQLUPDATE);
			past.setInt(1,mallOrDt.getQuantity());
			past.setInt(2,mallOrDt.getPrice());
			past.setString(3,mallOrDt.getMallOrNo());
			past.setString(4,mallOrDt.getCommNo());
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
	public Set<MallOrDtVO> getAll() {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement past=null;
		ResultSet rs=null;
		Set<MallOrDtVO> set=new LinkedHashSet<MallOrDtVO>();
		try {
			conn=DriverManager.getConnection(URL,NAME,PSW);
			past=conn.prepareStatement(SQLSELALL);
			rs=past.executeQuery();
			
			while(rs.next()){
				MallOrDtVO vo =new MallOrDtVO();
				vo.setMallOrNo(rs.getString(1));
				vo.setCommNo(rs.getString(2));
				vo.setQuantity(rs.getInt(3));
				vo.setPrice(rs.getInt(4));
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
	public Set<MallOrDtVO> getByOrNo(String mallOrNo) {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement past=null;
		ResultSet rs=null;
		Set<MallOrDtVO> set=new LinkedHashSet<MallOrDtVO>();
		try {
			conn=DriverManager.getConnection(URL,NAME,PSW);
			past=conn.prepareStatement(SQLSELORNO);
			past.setString(1,mallOrNo);
			rs=past.executeQuery();
			
			while(rs.next()){
				MallOrDtVO vo =new MallOrDtVO();
				vo.setMallOrNo(rs.getString(1));
				vo.setCommNo(rs.getString(2));
				vo.setQuantity(rs.getInt(3));
				vo.setPrice(rs.getInt(4));
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
	public MallOrDtVO findOneByFk(String mallOrNo, String commNo) {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement past=null;
		ResultSet rs=null;
		MallOrDtVO vo =new MallOrDtVO();
		try {
			conn=DriverManager.getConnection(URL,NAME,PSW);
			past=conn.prepareStatement(SQLSELONE);
			past.setString(1,mallOrNo);
			past.setString(2,commNo);
			rs=past.executeQuery();
			
			if(rs.next()){
				vo.setMallOrNo(rs.getString(1));
				vo.setCommNo(rs.getString(2));
				vo.setQuantity(rs.getInt(3));
				vo.setPrice(rs.getInt(4));
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
		
		return vo;
	}

	public void insertWithEmps(MallOrDtVO mallOrDt,java.sql.Connection conn) {
		// TODO Auto-generated method stub
		PreparedStatement past =null;
		
		try {
			
			past=conn.prepareStatement(SQLADD);
			past.setString(1,mallOrDt.getMallOrNo());
			past.setString(2,mallOrDt.getCommNo());
			past.setInt(3,mallOrDt.getQuantity());
			past.setInt(4,mallOrDt.getPrice());
			past.executeUpdate();
			
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
				
				if(past!=null)
					past.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	

}
