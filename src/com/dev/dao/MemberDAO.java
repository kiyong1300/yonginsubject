package com.dev.dao;

import com.dev.vo.MemberVO;
import java.sql.*;

public class MemberDAO {
	private static MemberDAO dao = new MemberDAO();
	private MemberDAO() {}
	public static MemberDAO getInstance() {
		return dao;
	}
	
	private Connection connect() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/test2?serverTimezone=UTC";
			conn = DriverManager.getConnection(url,"root","Kidragon!13");
		}catch(Exception e) {
			System.out.print("MDAO:connect" + e);
		}
		return conn;
	}             
	public void close(Connection conn, PreparedStatement pstmt) {
		if(pstmt != null) {
			try {
			pstmt.close();	
			}catch(Exception e) {
				System.out.print("pstmt close error"+e);
			}
		}
		if(conn != null) {
			try {
				conn.close();
			}catch(Exception e) {
				System.out.print("conn close error"+e);
			}
		}
	}
	public void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			}catch(Exception e){
				System.out.print("rs close error" + e);
			}
		}
		close(conn,pstmt);
	}
	
	public void memberInsert(MemberVO member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = connect();
			pstmt = conn.prepareStatement("insert into member values(?,?,?);");
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPwd());
			pstmt.setString(3, member.getName());
			pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.print("MDAO:insert" + e);
		}finally {
			close(conn,pstmt);                         
		}
	}
	public MemberVO memberSearch(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO member = null;
		try {
			conn = connect();
			pstmt = conn.prepareStatement("select * from member where id=?;");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				member = new MemberVO();
				member.setId(rs.getString(1));
				member.setPwd(rs.getString(2));
				member.setName(rs.getString(3));
			}
		}catch(Exception e) {
			System.out.print("MDAO:search" + e);
		}finally {
			close(conn, pstmt, rs);
		}
		return member;
	}
	
	public void memberUpdate(MemberVO member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = connect();
			pstmt = conn.prepareStatement("update member set pwd=?, name=? where id=?;");
			pstmt.setString(3, member.getId());
			pstmt.setString(1, member.getPwd());
			pstmt.setString(2, member.getName());
			pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.print("MDAO:insert" + e);
		}finally {
			close(conn,pstmt);                         
		}
		
	}
}
