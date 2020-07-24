package com.mall.model;



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

import com.gmType.model.GmTypeVO;

public class MallJNDIDAO implements MallDAO_interface {
	
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
	

	private static final String SQLADD="INSERT INTO MALL "
			+ "(COMMNO,COMMNAME,PRICE,QUANTITY,IMG,INTRO,AGE,PLAYER,STATUS) "
			+"VALUES('ZM'||LPAD(TO_CHAR(COMMNO_SEQ.NEXTVAL),5, '0') , ? , ? , ? , ? , ? , ? , ?, ?)";
	private static final String SQLUPDATE="UPDATE MALL "
			+ "SET COMMNAME=?,PRICE=?,QUANTITY=?,IMG=?,INTRO=?,AGE=?,PLAYER=?,STATUS=? "
			+ "WHERE COMMNO=? ";
	private static final String SQLDELETE="DELETE FROM MALL MALL WHERE COMMNO = ?";
	private static final String SQLSELNAME="SELECT * FROM MALL WHERE COMMNAME LIKE ?";
	private static final String SQLSELALL="SELECT * FROM MALL ORDER BY COMMNO";
	private static final String SQLSELALLUP="SELECT * FROM MALL WHERE STATUS=1 ORDER BY COMMNO ";
	private static final String SQLSELNAMEUP="SELECT * FROM MALL WHERE STATUS=1 AND COMMNAME LIKE ? ORDER BY COMMNO";
	//查詢編號最後五筆等於最新五筆
	private static final String SQLSELNEW="SELECT * FROM (SELECT * FROM MALL ORDER BY COMMNO DESC) WHERE ROWNUM <= 6";
	private static final String SQLSELONE="SELECT * FROM MALL WHERE COMMNO=? ";
	private static final String SQLSELTYPE="SELECT GMTYPE.TYPENO,TYPENAME FROM MALL "
			+ " JOIN GMTYPEDT ON mall.commno=gmtypedt.commno"
			+ " JOIN GMTYPE ON gmtypedt.typeno=gmtype.typeno"
			+ " WHERE MALL.COMMNO=?";
	private static final String SQLSELMALLBYTYPE="SELECT * FROM MALL "+
	"JOIN GMTYPEDT ON mall.commno=gmtypedt.commno "+
	"WHERE GMTYPEDT.TYPENO=? AND MALL.STATUS=1";
	
	@Override
	public String add(MallVO mall) {
		// TODO Auto-generated method stub
		Connection conn =null;
		PreparedStatement past=null;
		ResultSet rs = null;
		String seq="";
		try {
			conn=ds.getConnection();
			
			conn.setAutoCommit(false);
			String cols[] = {"COMMNO"};
			past = conn.prepareStatement(SQLADD,cols);
			past.setString(1,mall.getCommName());
			past.setInt(2,mall.getPrice());
			past.setInt(3,mall.getQuantity());
			past.setBytes(4,mall.getImg());
			past.setString(5,mall.getIntro());
			past.setString(6,mall.getAge());
			past.setString(7,mall.getPlayer());
			past.setInt(8,mall.getStatus());
			past.executeUpdate();
			
			conn.commit();
			
			rs = past.getGeneratedKeys();
			if (rs.next()) {
				seq = rs.getString(1);
			}
			
			
		}catch(SQLException e){
			e.getMessage();
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}finally {
			
			try {
				conn.setAutoCommit(true);
				if(rs!=null)
					rs.close();
				if(past!=null)
					past.close();
				if(conn!=null)
					conn.close();
			}catch(SQLException e1){
				e1.printStackTrace();
			}
			
		}
		
		return seq;
		
	}
	
	@Override
  	public void update(MallVO mall) {
		// TODO Auto-generated method stub
		Connection conn = null ;
		PreparedStatement past = null;
		try {
			conn = ds.getConnection();
			
			conn.setAutoCommit(false);
			past =conn.prepareStatement(SQLUPDATE);
			past.setString(1,mall.getCommName());
			past.setInt(2,mall.getPrice());
			past.setInt(3,mall.getQuantity());
			past.setBytes(4,mall.getImg());
			past.setString(5,mall.getIntro());
			past.setString(6,mall.getAge());
			past.setString(7,mall.getPlayer());
			past.setInt(8,mall.getStatus());
			past.setString(9,mall.getCommNo());
			
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
			}catch(SQLException e1){
				e1.printStackTrace();
			}
			
		}
		
	}

	@Override
	public void delete(String commno) {
		// TODO Auto-generated method stub
		Connection conn = null ;
		PreparedStatement past = null ;
		try {
			conn = ds.getConnection();
			
			conn.setAutoCommit(false);
			past = conn.prepareStatement(SQLDELETE);
			past.setString(1,commno);
			past.executeUpdate();
			
			conn.commit();
		}catch(SQLException e) {
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
	public Set<MallVO> findByName(String name) {
		// TODO Auto-generated method stub
		Set<MallVO> set = new LinkedHashSet<MallVO>();
		Connection conn=null;
		PreparedStatement past =null;
		ResultSet rs = null;
		try {
			conn=ds.getConnection();
			past=conn.prepareStatement(SQLSELNAME);
			past.setString(1,"%"+name+"%");
			rs = past.executeQuery();
			while(rs.next()) {
			MallVO mall = new MallVO();	
			mall.setCommNo(rs.getString("COMMNO"));
			mall.setCommName(rs.getString("COMMNAME"));
			mall.setPrice(rs.getInt("PRICE"));
			mall.setQuantity(rs.getInt("QUANTITY"));
			mall.setImg(rs.getBytes("IMG"));
			mall.setIntro(rs.getString("INTRO"));
			mall.setAge(rs.getString("AGE"));
			mall.setPlayer(rs.getString("PLAYER"));
			mall.setStatus(rs.getInt("STATUS"));
			set.add(mall);
			}
			 
		}catch(SQLException e) {
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
	public Set<MallVO> getAll() {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement past = null;		
		ResultSet rs = null;
		Set<MallVO> set = new LinkedHashSet<MallVO>();
		try {
			conn=ds.getConnection();
			past = conn.prepareStatement(SQLSELALL);
			rs=past.executeQuery();
			while(rs.next()) {
				MallVO mall = new MallVO();	
				mall.setCommNo(rs.getString("COMMNO"));
				mall.setCommName(rs.getString("COMMNAME"));
				mall.setPrice(rs.getInt("PRICE"));
				mall.setQuantity(rs.getInt("QUANTITY"));
				mall.setImg(rs.getBytes("IMG"));
				mall.setIntro(rs.getString("INTRO"));
				mall.setAge(rs.getString("AGE"));
				mall.setPlayer(rs.getString("PLAYER"));
				mall.setStatus(rs.getInt("STATUS"));
				set.add(mall);
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
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
	public Set<MallVO> getAllUp() {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement past = null;		
		ResultSet rs = null;
		Set<MallVO> set = new LinkedHashSet<MallVO>();
		try {
			conn=ds.getConnection();
			past = conn.prepareStatement(SQLSELALLUP);
			rs=past.executeQuery();
			while(rs.next()) {
				MallVO mall = new MallVO();	
				mall.setCommNo(rs.getString("COMMNO"));
				mall.setCommName(rs.getString("COMMNAME"));
				mall.setPrice(rs.getInt("PRICE"));
				mall.setQuantity(rs.getInt("QUANTITY"));
				mall.setImg(rs.getBytes("IMG"));
				mall.setIntro(rs.getString("INTRO"));
				mall.setAge(rs.getString("AGE"));
				mall.setPlayer(rs.getString("PLAYER"));
				mall.setStatus(rs.getInt("STATUS"));
				set.add(mall);
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
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
	public Set<MallVO> getNew() {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement past = null;		
		ResultSet rs = null;
		Set<MallVO> set = new LinkedHashSet<MallVO>();
		try {
			conn=ds.getConnection();
			past = conn.prepareStatement(SQLSELNEW);
			rs=past.executeQuery();
			while(rs.next()) {
				MallVO mall = new MallVO();	
				mall.setCommNo(rs.getString("COMMNO"));
				mall.setCommName(rs.getString("COMMNAME"));
				mall.setPrice(rs.getInt("PRICE"));
				mall.setQuantity(rs.getInt("QUANTITY"));
				mall.setImg(rs.getBytes("IMG"));
				mall.setIntro(rs.getString("INTRO"));
				mall.setAge(rs.getString("AGE"));
				mall.setPlayer(rs.getString("PLAYER"));
				mall.setStatus(rs.getInt("STATUS"));
				set.add(mall);
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
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
	public MallVO findOneByNo(String commno) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement past = null;		
		ResultSet rs = null;
		MallVO mall = new MallVO();	
		try {
			conn=ds.getConnection();
			past = conn.prepareStatement(SQLSELONE);
			past.setString(1,commno);
			rs=past.executeQuery();
			if(rs.next()) {
				mall.setCommNo(rs.getString("COMMNO"));
				mall.setCommName(rs.getString("COMMNAME"));
				mall.setPrice(rs.getInt("PRICE"));
				mall.setQuantity(rs.getInt("QUANTITY"));
				mall.setImg(rs.getBytes("IMG"));
				mall.setIntro(rs.getString("INTRO"));
				mall.setAge(rs.getString("AGE"));
				mall.setPlayer(rs.getString("PLAYER"));
				mall.setStatus(rs.getInt("STATUS"));
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
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
		
		
		return mall;
	}
	
	
	@Override
	public Set<GmTypeVO> getType(String commno) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement past = null;		
		ResultSet rs = null;
		Set<GmTypeVO> set = new LinkedHashSet<GmTypeVO>();

		try {
			conn=ds.getConnection();
			past = conn.prepareStatement(SQLSELTYPE);
			past.setString(1,commno);
			rs=past.executeQuery();
			while(rs.next()) {
				GmTypeVO gmTypeVo = new GmTypeVO();
				gmTypeVo.setTypeNo(rs.getString(1));
				gmTypeVo.setTypeName(rs.getString(2));
				set.add(gmTypeVo);
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
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
	public Set<MallVO> findByNameUp(String name) {
		// TODO Auto-generated method stub
				Set<MallVO> set = new LinkedHashSet<MallVO>();
				Connection conn=null;
				PreparedStatement past =null;
				ResultSet rs = null;
				try {
					conn=ds.getConnection();
					past=conn.prepareStatement(SQLSELNAMEUP);
					past.setString(1,"%"+name+"%");
					rs = past.executeQuery();
					while(rs.next()) {
					MallVO mall = new MallVO();	
					mall.setCommNo(rs.getString("COMMNO"));
					mall.setCommName(rs.getString("COMMNAME"));
					mall.setPrice(rs.getInt("PRICE"));
					mall.setQuantity(rs.getInt("QUANTITY"));
					mall.setImg(rs.getBytes("IMG"));
					mall.setIntro(rs.getString("INTRO"));
					mall.setAge(rs.getString("AGE"));
					mall.setPlayer(rs.getString("PLAYER"));
					mall.setStatus(rs.getInt("STATUS"));
					set.add(mall);
					}
					 
				}catch(SQLException e) {
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
	public Set<MallVO> findByType(String typeno) {
		// TODO Auto-generated method stub
				Connection conn = null;
				PreparedStatement past = null;		
				ResultSet rs = null;
				Set<MallVO> set = new LinkedHashSet<MallVO>();
				try {
					conn=ds.getConnection();
					past = conn.prepareStatement(SQLSELMALLBYTYPE);
					past.setString(1,typeno);
					rs=past.executeQuery();
					while(rs.next()) {
						MallVO mall = new MallVO();	
						mall.setCommNo(rs.getString("COMMNO"));
						mall.setCommName(rs.getString("COMMNAME"));
						mall.setPrice(rs.getInt("PRICE"));
						mall.setQuantity(rs.getInt("QUANTITY"));
						mall.setImg(rs.getBytes("IMG"));
						mall.setIntro(rs.getString("INTRO"));
						mall.setAge(rs.getString("AGE"));
						mall.setPlayer(rs.getString("PLAYER"));
						mall.setStatus(rs.getInt("STATUS"));
						set.add(mall);
					}
					
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					
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
	
	


//	public static void main(String[] args) {
//		byte[] img =null ;
//		try {
//			FileInputStream in =new FileInputStream(new File("img/dog.jpg"));
//			BufferedInputStream bufin =new BufferedInputStream(in);
//			byte[] buffer = new byte[2048];
//			ByteArrayOutputStream baos = new ByteArrayOutputStream();
//			
//			while(bufin.read(buffer,0,2048)!=-1) 
//				baos.write(buffer);
//				
//			img =baos.toByteArray();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
//		MallVO malladd = new MallVO(0,"狼人殺",5000,100,img,"超好玩超好玩","10歲以上" ,"4人",0);
//		MallJDBCDAO malldao = new MallJDBCDAO();
//		malldao.add(malladd);
//		
//		MallVO mall2 = new MallVO(1001,"哈哈哈哈",5000,100,img,"超好玩超好玩","10歲以上" ,"4人",0);
//		malldao.update(mall2);
//		
//		malldao.delete(1003);
		
//		List<MallVO> list =malldao.findByName("狼");
//		for(MallVO mall : list) {
//			System.out.print(mall.getCommNo()+" "+mall.getCommName()+" "+mall.getIntro()+" "+mall.getImg()+"\n");
//			try {
//				FileOutputStream file = new FileOutputStream("img/123.jpg");
//				if(mall.getImg()!=null)
//					file.write(mall.getImg());
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		
		
//	}




	
	
}
