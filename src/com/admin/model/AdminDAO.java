package com.admin.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.member.model.MemberDTO;


public class AdminDAO {
	// DB와 연동하는 객체.
	Connection con = null;
	
	// DB에 SQL문을 전송하는 객체.
	PreparedStatement pstmt = null;
	
	// SQL문을 실행한 후에 결과값을 가지고 있는 객체.
	ResultSet rs = null;
	
	// 쿼리문을 저장할 객체.
	String sql = null;
	
	// AdminDAO 클래스를 싱클턴 방식으로 만들어 보자.
	// 1단계 : 싱글턴 방식으로 객체를 만들기 위해서는 우선적으로 기본생성자의 접근제어자를 public이 아니라 private으로 바꾸어 주저야 한다.
	// 		   즉, 외부에서는 직접적으로 기본생성자를 호출하지 못하게 하는 방식이다.
	
	// 2단계 : AdminDAO 클래스의 정적(static) 멤버로 선언을 해 주어야 한다.
	private static AdminDAO instance;
	
	private AdminDAO() {
	}	// 기본생성자
	
	// 3단계 : 기본생성자 대신에 싱그턴 객체를 return 해 주는
	//		 getInstance() 메서드를 만들어서 해당 getInstance() 메서드를 외부에서 접근할 수 있도록 해 주면 됨.
	public static AdminDAO getInstance() {
		if(instance == null) {
			instance = new AdminDAO();
		}
		
		return instance;
	}
	// getInstance() end
	
	// openConn() start
	// JDBC 방식이 아닌 DBCP 방식으로 DB와 연동 작업 진행
	public void openConn() {
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://gamemenchu.ciegzzti5gy2.us-west-1.rds.amazonaws.com:3306/semiProject";
		String user = "admin";
		String password = "12345678";

		try {
			// 1단계 : 오라클 드라이버를 메모리로 로딩 작업 진행.
			Class.forName(driver);

			// 2단계 : 오라클 데이터베이스와 연결 작업 진행.
			con = DriverManager.getConnection(url, user, password);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// openConn() end
			
	// DB에 연결되어 있던 자원 종료하는 메서드.
	public void closeConn(ResultSet rs, PreparedStatement pstmt, Connection con) {

		try {
			if(rs != null)
				rs.close();
			if(pstmt != null)
				pstmt.close();
			if(con != null)
				con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// closeConn() end
	
	// 관리자 로그인
    public int loginAdmin(String id, String pwd) {
    	int result = 0;
		try {
			openConn(); // 커넥션풀 방식으로 DB 연동 진행.
			sql = "select admin_pwd from admin where admin_id = ?";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				if (rs.getString("admin_pwd").equals(pwd)) {
					result = 1; // 로그인 성공
				} else {
					result = 0; // 비밀번호 불일치
				}

			} else {

				result = -1; // 아이디가없음
			}
		} catch (SQLException e) {
			// TODO 자동 생성된 catch 블록
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;
    }
	// loginAdmin() end
	
	
	// 회원 리스트 조회
	public List<MemberDTO> memberList() {
		
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		
		try {
			openConn();
			
			sql = "select * from member";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberDTO dto = new MemberDTO();
				
				dto.setMember_index(rs.getInt("member_index"));
				dto.setMember_id(rs.getString("member_id"));
				dto.setMember_pwd(rs.getString("member_pwd"));
				dto.setMember_nickname(rs.getString("member_nickname"));
				dto.setMember_email(rs.getString("member_email"));
				dto.setPoint(rs.getInt("member_point"));
				dto.setRegdate(rs.getString("member_regdate"));
				dto.setPhone(rs.getString("member_phone"));
				dto.setPrefer_lol(rs.getString("prefer_game1"));
				dto.setPrefer_battle_ground(rs.getString("prefer_game2"));
				dto.setPrefer_overwatch(rs.getString("prefer_game3"));
				dto.setMember_profile(rs.getString("member_profile"));
				
				list.add(dto);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		return list;
	}
	// memberList() end
	
	// board 테이블의 전체 게시물의 수를 확인하는 메서드
	public int getMemberCount() {
		
		int count = 0;
		
		try {
			openConn();
			
			sql = "select count(*) from member"; // max는 게시물 수가 아니라 수를 가져오는거기 때문에 나중에 문제가 생길 수 있음
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		return count;
	} // getBoardCount() 메서드 end
	
	
}
