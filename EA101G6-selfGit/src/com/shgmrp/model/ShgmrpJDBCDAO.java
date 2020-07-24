package com.shgmrp.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;

public class ShgmrpJDBCDAO implements ShgmrpDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String user = "EA101";
	String password = "123456";

	private static final String INSERT_STMT = "INSERT INTO (shgmrpno,shgmno,suiterno,detail,status) VALUES ('CB'||LPAD(shgmrp_seq.NEXTVAL,5,'0'), ?, ?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE SHGMRP SET shgmno=?, suiterno=?, detail=?, status=? WHERE shgmrpno=?";
	private static final String DELETE_STMT = "DELETE FROM SHGMRP WHERE shgmno = ?";
	private static final String GET_ONE_STMT = "SELECT * FROM SHGMRP WHERE shgmrpno=?";
	private static final String GET_ONE_BY_SHGMNO = "SELECT * FROM SHGMRP WHERE shgmno=?";
	private static final String GET_ALL_STMT = "SELECT * FROM SHGMRP";
	private static final String SEARCH_STMT = "SELECT * FROM SHGMRP WHERE UPPER(shgmno) LIKE UPPER(?) ORDER BY CAST(SUBSTR(shgmrpno, 5) AS INT)";
	private static final String GET_ALL_UNCHECK = "SELECT * FROM SHGMRP WHERE status=0 ORDER BY CAST(SUBSTR(shgmrpno, 5) AS INT)";

	public static void main(String[] args) {
		ShgmrpJDBCDAO dao = new ShgmrpJDBCDAO();
		ShgmrpVO vo = dao.findByPrimaryKey("CB00002");
		System.out.println(vo.getShgmrpno());
		System.out.println(vo.getShgmno());
		System.out.println(vo.getSuiterno());
		System.out.println(vo.getDetail());
		System.out.println(vo.getStatus());
	}

	public void insert(ShgmrpVO shgmrpvo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, shgmrpvo.getShgmno());
			pstmt.setString(2, shgmrpvo.getSuiterno());
			pstmt.setString(3, shgmrpvo.getDetail());
			pstmt.setInt(4, shgmrpvo.getStatus());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public void update(ShgmrpVO shgmrpvo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, shgmrpvo.getShgmno());
			pstmt.setString(2, shgmrpvo.getSuiterno());
			pstmt.setString(3, shgmrpvo.getDetail());
			pstmt.setInt(4, shgmrpvo.getStatus());
			pstmt.setString(5, shgmrpvo.getShgmrpno());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void delete(String shgmrpno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setString(1, shgmrpno);

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public ShgmrpVO findByPrimaryKey(String shgmrpno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ShgmrpVO shgmrpvo = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, shgmrpno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				shgmrpvo = new ShgmrpVO();
				shgmrpvo.setShgmrpno(rs.getString(1));
				shgmrpvo.setShgmno(rs.getString(2));
				shgmrpvo.setSuiterno(rs.getString(3));
				java.sql.Clob clob = rs.getClob(4);
				String detail = clob.getSubString(1, (int) clob.length());
				shgmrpvo.setDetail(detail);
				shgmrpvo.setStatus(rs.getInt(5));
			}

			rs.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return shgmrpvo;
	}

	public ShgmrpVO findByShgmno(String shgmno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ShgmrpVO shgmrpvo = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(GET_ONE_BY_SHGMNO);

			pstmt.setString(1, shgmno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				shgmrpvo = new ShgmrpVO();
				shgmrpvo.setShgmrpno(rs.getString(1));
				shgmrpvo.setShgmno(rs.getString(2));
				shgmrpvo.setSuiterno(rs.getString(3));
				java.sql.Clob clob = rs.getClob(4);
				String detail = clob.getSubString(1, (int) clob.length());
				shgmrpvo.setDetail(detail);
				shgmrpvo.setStatus(rs.getInt(5));
			}

			rs.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return shgmrpvo;
	}

	public Set<ShgmrpVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Set<ShgmrpVO> set = new LinkedHashSet<ShgmrpVO>();
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ShgmrpVO shgmrpvo = new ShgmrpVO();
				shgmrpvo.setShgmrpno(rs.getString(1));
				shgmrpvo.setShgmno(rs.getString(2));
				shgmrpvo.setSuiterno(rs.getString(3));
				java.sql.Clob clob = rs.getClob(4);
				String detail = clob.getSubString(1, (int) clob.length());
				shgmrpvo.setDetail(detail);
				shgmrpvo.setStatus(rs.getInt(5));

				set.add(shgmrpvo);
			}

			rs.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return set;
	}
	
	@Override
	public Set<ShgmrpVO> searchForBackend(String word) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Set<ShgmrpVO> set = new LinkedHashSet<ShgmrpVO>();

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(SEARCH_STMT);
			pstmt.setString(1, "%" + word + "%");

			rs = pstmt.executeQuery();

			while (rs.next()) {
				ShgmrpVO shgmrpvo = new ShgmrpVO();
				shgmrpvo.setShgmrpno(rs.getString(1));
				shgmrpvo.setShgmno(rs.getString(2));
				shgmrpvo.setSuiterno(rs.getString(3));
				java.sql.Clob clob = rs.getClob(4);
				String detail = clob.getSubString(1, (int) clob.length());
				shgmrpvo.setDetail(detail);
				shgmrpvo.setStatus(rs.getInt(5));

				set.add(shgmrpvo);
			}
			
			rs.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return set;
	}

	@Override
	public Set<ShgmrpVO> getAllUncheck() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Set<ShgmrpVO> set = new LinkedHashSet<ShgmrpVO>();
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			pstmt = con.prepareStatement(GET_ALL_UNCHECK);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ShgmrpVO shgmrpvo = new ShgmrpVO();
				shgmrpvo.setShgmrpno(rs.getString(1));
				shgmrpvo.setShgmno(rs.getString(2));
				shgmrpvo.setSuiterno(rs.getString(3));
				java.sql.Clob clob = rs.getClob(4);
				String detail = clob.getSubString(1, (int) clob.length());
				shgmrpvo.setDetail(detail);
				shgmrpvo.setStatus(rs.getInt(5));

				set.add(shgmrpvo);
			}

			rs.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return set;
	}

}
