package com.mallOr.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.mallOrDt.model.MallOrDtJDBCDaoImpl;
import com.mallOrDt.model.MallOrDtVO;
import com.mbrpf.model.MbrpfDAO;
import com.mbrpf.model.MbrpfVO;

public class MallOrJDBCDAO implements MallOrDAO_interface {

	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String NAME = "EA101";
	private static final String PSW = "123456";
	private static final String SQLADD = "INSERT INTO MALLOR (MALLORNO,MBRNO,ORDATE,TAKE,ADDRESS,STATUS,PAYSTATUS,BOXSTATUS,PRICE)"
			+ "VALUES(TO_CHAR(SYSDATE,'YYYYMMDD')||'-'||LPAD(TO_CHAR(MALLORNO_SEQ.NEXTVAL), 7, '0'),?,?,?,?,?,?,?,?)";
	private static final String SQLUPDATE = "UPDATE MALLOR "
			+ "SET STATUS=?,PAYSTATUS=?,BOXSTATUS=? " + "WHERE MALLORNO=?";
	private static final String SQLDELETE = "DELETE MALLOR WHERE MALLORNO=?";
	private static final String SQLSELALL = "SELECT * FROM MALLOR ORDER BY MALLORNO DESC ";
	private static final String SQLSELBYMBR = "SELECT * FROM MALLOR WHERE MBRNO=? ORDER BY MALLORNO DESC ";
	private static final String SQLSELBYSTATUS = "SELECT * FROM MALLOR WHERE STATUS=? ORDER BY MALLORNO DESC ";
	private static final String SQLSELBYBOXSTATUS = "SELECT * FROM MALLOR WHERE BOXSTATUS=? AND STATUS=0 ORDER BY MALLORNO ";
	private static final String SQLSELBYORNO = "SELECT * FROM MALLOR WHERE MALLORNO=?";

	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void add(MallOrVO mallor) {
		// TODO Auto-generated method stub

		Connection conn = null;
		PreparedStatement past = null;

		try {
			conn = DriverManager.getConnection(URL, NAME, PSW);

			conn.setAutoCommit(false);
			past = conn.prepareStatement(SQLADD);
			past.setString(1, mallor.getMbrNo());
			past.setTimestamp(2, mallor.getOrDate());
			past.setString(3, mallor.getTake());
			past.setString(4, mallor.getAddress());
			past.setInt(5, mallor.getStatus());
			past.setInt(6, mallor.getPayStatus());
			past.setInt(7, mallor.getBoxStatus());
			past.setInt(8, mallor.getPrice());
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
		} finally {

			try {
				conn.setAutoCommit(true);
				if (past != null)
					past.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	@Override
	public void update(MallOrVO mallor) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement past = null;

		try {
			conn = DriverManager.getConnection(URL, NAME, PSW);

			conn.setAutoCommit(false);
			past = conn.prepareStatement(SQLUPDATE);
			past.setInt(1, mallor.getStatus());
			past.setInt(2, mallor.getPayStatus());
			past.setInt(3, mallor.getBoxStatus());
			past.setString(4, mallor.getMallOrNo());

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
		} finally {

			try {
				conn.setAutoCommit(true);
				if (past != null)
					past.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	@Override
	public void delete(String mallOrNo) {
		// TODO Auto-generated method stub

		Connection conn = null;
		PreparedStatement past = null;

		try {
			conn = DriverManager.getConnection(URL, NAME, PSW);

			conn.setAutoCommit(false);
			past = conn.prepareStatement(SQLDELETE);
			past.setString(1, mallOrNo);
			past.executeUpdate();
			conn.commit();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {

			try {
				conn.setAutoCommit(true);
				if (past != null)
					past.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	@Override
	public Set<MallOrVO> getAll() {
		// TODO Auto-generated method stub

		Connection conn = null;
		PreparedStatement past = null;
		ResultSet rs = null;
		Set<MallOrVO> set = new LinkedHashSet<MallOrVO>();
		try {
			conn = DriverManager.getConnection(URL, NAME, PSW);
			past = conn.prepareStatement(SQLSELALL);
			rs = past.executeQuery();

			while (rs.next()) {
				MallOrVO mallor = new MallOrVO();
				mallor.setMallOrNo(rs.getString("MALLORNO"));
				mallor.setMbrNo(rs.getString("MBRNO"));
				mallor.setOrDate(rs.getTimestamp("ORDATE"));
				mallor.setTake(rs.getString("TAKE"));
				mallor.setAddress(rs.getString("ADDRESS"));
				mallor.setStatus(rs.getInt("STATUS"));
				mallor.setPayStatus(rs.getInt("PAYSTATUS"));
				mallor.setBoxStatus(rs.getInt("BOXSTATUS"));
				mallor.setPrice(rs.getInt("PRICE"));
				set.add(mallor);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (past != null)
					past.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return set;

	}

	@Override
	public Set<MallOrVO> findByMbrNo(String mbrNo) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement past = null;
		ResultSet rs = null;
		Set<MallOrVO> set = new LinkedHashSet<MallOrVO>();
		try {
			conn = DriverManager.getConnection(URL, NAME, PSW);
			past = conn.prepareStatement(SQLSELBYMBR);
			past.setString(1, mbrNo);
			rs = past.executeQuery();

			while (rs.next()) {
				MallOrVO mallor = new MallOrVO();
				mallor.setMallOrNo(rs.getString("MALLORNO"));
				mallor.setMbrNo(rs.getString("MBRNO"));
				mallor.setOrDate(rs.getTimestamp("ORDATE"));
				mallor.setTake(rs.getString("TAKE"));
				mallor.setAddress(rs.getString("ADDRESS"));
				mallor.setStatus(rs.getInt("STATUS"));
				mallor.setPayStatus(rs.getInt("PAYSTATUS"));
				mallor.setBoxStatus(rs.getInt("BOXSTATUS"));
				mallor.setPrice(rs.getInt("PRICE"));
				set.add(mallor);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (past != null)
					past.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return set;
	}

	@Override
	public Set<MallOrVO> findByStatus(Integer status) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement past = null;
		ResultSet rs = null;
		Set<MallOrVO> set = new LinkedHashSet<MallOrVO>();
		try {
			conn = DriverManager.getConnection(URL, NAME, PSW);
			past = conn.prepareStatement(SQLSELBYSTATUS);
			past.setInt(1, status);
			rs = past.executeQuery();

			while (rs.next()) {
				MallOrVO mallor = new MallOrVO();
				mallor.setMallOrNo(rs.getString("MALLORNO"));
				mallor.setMbrNo(rs.getString("MBRNO"));
				mallor.setOrDate(rs.getTimestamp("ORDATE"));
				mallor.setTake(rs.getString("TAKE"));
				mallor.setAddress(rs.getString("ADDRESS"));
				mallor.setStatus(rs.getInt("STATUS"));
				mallor.setPayStatus(rs.getInt("PAYSTATUS"));
				mallor.setBoxStatus(rs.getInt("BOXSTATUS"));
				mallor.setPrice(rs.getInt("PRICE"));
				set.add(mallor);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (past != null)
					past.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return set;
	}

	@Override
	public MallOrVO findOneByOrNo(String mallOrNo) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement past = null;
		ResultSet rs = null;
		MallOrVO mallor = null;
		try {
			conn = DriverManager.getConnection(URL, NAME, PSW);
			past = conn.prepareStatement(SQLSELBYORNO);
			past.setString(1, mallOrNo);
			rs = past.executeQuery();
			if (rs.next()) {
				mallor = new MallOrVO();
				mallor.setMallOrNo(rs.getString("MALLORNO"));
				mallor.setMbrNo(rs.getString("MBRNO"));
				mallor.setOrDate(rs.getTimestamp("ORDATE"));
				mallor.setTake(rs.getString("TAKE"));
				mallor.setAddress(rs.getString("ADDRESS"));
				mallor.setStatus(rs.getInt("STATUS"));
				mallor.setPayStatus(rs.getInt("PAYSTATUS"));
				mallor.setBoxStatus(rs.getInt("BOXSTATUS"));
				mallor.setPrice(rs.getInt("PRICE"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (past != null)
					past.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return mallor;
	}

	public String add(MallOrVO mallOr, List<MallOrDtVO> mallOrDtList) {
		Connection conn = null;
		PreparedStatement past = null;
		ResultSet rs = null;
		String sqe = "";
		try {
			conn = DriverManager.getConnection(URL, NAME, PSW);

			conn.setAutoCommit(false);
			String[] cols = { "MALLORNO" };
			past = conn.prepareStatement(SQLADD, cols);
			past.setString(1, mallOr.getMbrNo());
			past.setTimestamp(2, mallOr.getOrDate());
			past.setString(3, mallOr.getTake());
			past.setString(4, mallOr.getAddress());
			past.setInt(5, mallOr.getStatus());
			past.setInt(6, mallOr.getPayStatus());
			past.setInt(7, mallOr.getBoxStatus());
			past.setInt(8, mallOr.getPrice());
			past.executeUpdate();

			rs = past.getGeneratedKeys();
			if (rs.next()) {
				sqe = rs.getString(1);
			}

			MallOrDtJDBCDaoImpl mallOrDtDao = new MallOrDtJDBCDaoImpl();
			for (MallOrDtVO mallOrDtVo : mallOrDtList) {
				mallOrDtVo.setMallOrNo(sqe);
				mallOrDtDao.insertWithEmps(mallOrDtVo, conn);
			}

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
		} finally {

			try {
				conn.setAutoCommit(true);
				if (past != null)
					past.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return sqe;

	}

	@Override
	public Set<MallOrVO> findByBoxStatus(Integer boxStatus) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement past = null;
		ResultSet rs = null;
		Set<MallOrVO> set = new LinkedHashSet<MallOrVO>();
		try {
			conn = DriverManager.getConnection(URL, NAME, PSW);
			past = conn.prepareStatement(SQLSELBYBOXSTATUS);
			past.setInt(1, boxStatus);
			rs = past.executeQuery();

			while (rs.next()) {
				MallOrVO mallor = new MallOrVO();
				mallor.setMallOrNo(rs.getString("MALLORNO"));
				mallor.setMbrNo(rs.getString("MBRNO"));
				mallor.setOrDate(rs.getTimestamp("ORDATE"));
				mallor.setTake(rs.getString("TAKE"));
				mallor.setAddress(rs.getString("ADDRESS"));
				mallor.setStatus(rs.getInt("STATUS"));
				mallor.setPayStatus(rs.getInt("PAYSTATUS"));
				mallor.setBoxStatus(rs.getInt("BOXSTATUS"));
				mallor.setPrice(rs.getInt("PRICE"));
				set.add(mallor);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (past != null)
					past.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return set;
	}
	
	public String add(MallOrVO mallOr, List<MallOrDtVO> mallOrDtList,MbrpfVO mbrpfVo) {
		Connection conn = null;
		PreparedStatement past = null;
		ResultSet rs = null;
		String sqe = "";
		try {
			conn = DriverManager.getConnection(URL, NAME, PSW);

			conn.setAutoCommit(false);
			String[] cols = { "MALLORNO" };
			past = conn.prepareStatement(SQLADD, cols);
			past.setString(1, mallOr.getMbrNo());
			past.setTimestamp(2, mallOr.getOrDate());
			past.setString(3, mallOr.getTake());
			past.setString(4, mallOr.getAddress());
			past.setInt(5, mallOr.getStatus());
			past.setInt(6, mallOr.getPayStatus());
			past.setInt(7, mallOr.getBoxStatus());
			past.setInt(8, mallOr.getPrice());
			past.executeUpdate();

			rs = past.getGeneratedKeys();
			if (rs.next()) {
				sqe = rs.getString(1);
			}

			MallOrDtJDBCDaoImpl mallOrDtDao = new MallOrDtJDBCDaoImpl();
			for (MallOrDtVO mallOrDtVo : mallOrDtList) {
				mallOrDtVo.setMallOrNo(sqe);
				mallOrDtDao.insertWithEmps(mallOrDtVo, conn);
			}
			
			MbrpfDAO MbrpfDao=new MbrpfDAO();
			MbrpfDao.updatePoint(mbrpfVo,conn);

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
		} finally {

			try {
				conn.setAutoCommit(true);
				if (past != null)
					past.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return sqe;

	}

}
