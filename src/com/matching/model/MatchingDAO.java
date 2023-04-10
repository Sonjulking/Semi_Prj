package com.matching.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class MatchingDAO {
	
	// DB와 연동하는 객체.
		Connection con = null;

		// DB에 SQL문을 전송하는 객체.
		PreparedStatement pstmt = null;

		// SQL문을 실행한 후에 결과값을 가지고 있는 객체.
		ResultSet rs = null;

		// 쿼리문을 저장할 객체.
		String sql = null;

		// MatchingDAO 클래스를 싱클턴 방식으로 만들어 보자.
		// 1단계 : 싱글턴 방식으로 객체를 만들기 위해서는 우선적으로 기본생성자의 접근제어자를 public이 아니라 private으로 바꾸어 주저야
		// 한다.
		// 즉, 외부에서는 직접적으로 기본생성자를 호출하지 못하게 하는 방식이다.

		// 2단계 : MatchingDAO 클래스의 정적(static) 멤버로 선언을 해 주어야 한다.
		private static MatchingDAO instance;

		private MatchingDAO() {
		} // 기본생성자

		// 3단계 : 기본생성자 대신에 싱그턴 객체를 return 해 주는
		// getInstance() 메서드를 만들어서 해당 getInstance() 메서드를 외부에서 접근할 수 있도록 해 주면 됨.
		public static MatchingDAO getInstance() {
			if (instance == null) {
				instance = new MatchingDAO();
			}

			return instance;
		}
		// getInstance() end

		// openConn() start
		// JDBC 방식이 아닌 DBCP 방식으로 DB와 연동 작업 진행

		// DB를 연동하는 작업을 진행하는 메서드.
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
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// closeConn() end
		
		
		// matching 테이블에 매칭데이터 추가하는 메서드
		public int insertMatching(MatchingDTO dto) {
			
			int result = 0;
			
			try {
				openConn();
																		//  아이디       닉네임
				sql = "insert into matching values(default, default, ?, ?, 'choi4	', 'choi4', ?, ?, now())";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, dto.getGame_name());
				pstmt.setString(2, dto.getTier());	
				pstmt.setString(3, dto.getDiscord_nikname());
				pstmt.setString(4, dto.getKakao_id());
				
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeConn(rs, pstmt, con);
			}
			return result;
		}	// insertMatching() 메서드 end
		
		
		// matchloading 에서 취소버튼 누르면 matching DB 삭제하는 메서드
		public int deleteMatching(String id) {
			
			int result = 0;
			
			try {
				openConn();
				
				if(rs.next()) {
				
				sql = "delete from matching where discord_nickname = ?";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, id);
				
				result = pstmt.executeUpdate();
				
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeConn(rs, pstmt, con);
			}
			
			return result;
		}	// deleteMatching() 메서드 end

}
