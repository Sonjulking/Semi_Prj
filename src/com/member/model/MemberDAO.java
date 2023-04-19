package com.member.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MemberDAO {
	// DB와 연동하는 객체.
	Connection con = null;

	// DB에 SQL문을 전송하는 객체.
	PreparedStatement pstmt = null;

	// SQL문을 실행한 후에 결과값을 가지고 있는 객체.
	ResultSet rs = null;

	// 쿼리문을 저장할 객체.
	String sql = null;

	// MemberDAO 클래스를 싱클턴 방식으로 만들어 보자.
	// 1단계 : 싱글턴 방식으로 객체를 만들기 위해서는 우선적으로 기본생성자의 접근제어자를 public이 아니라 private으로 바꾸어 주저야
	// 한다.
	// 즉, 외부에서는 직접적으로 기본생성자를 호출하지 못하게 하는 방식이다.

	// 2단계 : MemberDAO 클래스의 정적(static) 멤버로 선언을 해 주어야 한다.
	private static MemberDAO instance;

	private MemberDAO() {
	} // 기본생성자

	// 3단계 : 기본생성자 대신에 싱그턴 객체를 return 해 주는
	// getInstance() 메서드를 만들어서 해당 getInstance() 메서드를 외부에서 접근할 수 있도록 해 주면 됨.
	public static MemberDAO getInstance() {
		if (instance == null) {
			instance = new MemberDAO();
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

	   // view에서 회원가입 시 db에 회원정보 삽입
	   public int memberInsert(MemberDTO dto) {

	      int res = 0, count = 0;

	      try {
	         openConn();

	         sql = "select max(member_index) from member";

	         pstmt = con.prepareStatement(sql);

	         rs = pstmt.executeQuery();

	         if (rs.next()) {
	            count = rs.getInt(1) + 1;
	         }

	         sql = "insert ignore into member values(?, ?, ?, ?, ?, default, now() ,? ,? ,? ,? ,?)";

	         pstmt = con.prepareStatement(sql);

	         pstmt.setInt(1, count);
	         pstmt.setString(2, dto.getMember_id());
	         pstmt.setString(3, dto.getMember_pwd());
	         pstmt.setString(4, dto.getMember_nickname());
	         pstmt.setString(5, dto.getMember_email());
	         pstmt.setString(6, dto.getPhone());
	         pstmt.setString(7, dto.getPrefer_lol());
	         pstmt.setString(8, dto.getPrefer_battle_ground());
	         pstmt.setString(9, dto.getPrefer_overwatch());
	         pstmt.setString(10, dto.getMember_profile());

	         res = pstmt.executeUpdate();

	         System.out.println("dao" + res);

	      } catch (Exception e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      } finally {
	         closeConn(rs, pstmt, con);
	      }
	      return res;
	   }
	   // memberInsert() end

	public int loginMember(String userID, String userPassword) {

		int result = 0;
		try {
			openConn(); // 커넥션풀 방식으로 DB 연동 진행.
			sql = "select member_pwd from member where member_id = ?";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, userID);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				if (rs.getString("member_pwd").equals(userPassword)) {
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

	public MemberDTO contentMember(String id) {
		MemberDTO dto = null;

		try {
			openConn();

			sql = "select * from member where member_id = ?";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto = new MemberDTO();

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
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return dto;
	} // contentMember() end

	

	// checkMemberId() start
	public String checkMemberId(String id) {
		String res = "사용 가능";

		try {
			openConn();

			sql = "select * from member where member_id = ?";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				// 중복 값 존재함
				res = "중복";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}

		return res;
	}
	// checkMemberId() end

	// nickCheck() start
	public String nickCheck(String name) {
		String res = "사용 가능";

		try {
			openConn();

			sql = "select * from member where member_nickname = ?";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, name);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				// 중복 값 존재함
				res = "중복";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}

		return res;

	}
	// nickCheck() end

	public String checkEmail(String email) {

		String res = "사용 가능";

		try {
			openConn();

			sql = "select * from member where member_email = ?";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, email);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				// 중복 값 존재함
				res = "중복";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}

		return res;

	}

	// updateMypage() start
	public int updateMypage(MemberDTO dto, String curr_pwd, String pw1) {

		int result = 0;
		try {
			openConn();

			sql = "select * from member where member_id = ?";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, dto.getMember_id());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				if (curr_pwd.equals(rs.getString("member_pwd"))) {
					sql = "update ignore member set member_nickname = ?, member_pwd = ?, prefer_game1 = ? , prefer_game2 = ? , prefer_game3 = ?, member_profile = ?";

					pstmt = con.prepareStatement(sql);

					pstmt.setString(1, dto.getMember_nickname());
					pstmt.setString(2, pw1);
					pstmt.setString(3, dto.getPrefer_lol());
					pstmt.setString(4, dto.getPrefer_battle_ground());
					pstmt.setString(5, dto.getPrefer_overwatch());
					pstmt.setString(6, dto.getMember_profile());

					result = pstmt.executeUpdate();
				} else {
					// 비밀번호가 틀린 경우
					result = -1;
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}

		return result;

	}

	// checkPwdFind() start
	public int checkPwdFind(String userId, String to_email) {
		int res = 0;

		try {
			openConn();

			sql = "select * from member where member_id = ?";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, userId);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				if (rs.getString("member_email").equals(to_email)) {
					sql = "select * from member where member_email = ?";

					pstmt = con.prepareStatement(sql);

					pstmt.setString(1, to_email);

					rs = pstmt.executeQuery();

					res = 2;
				} else {
					res = 1;
					System.out.println(rs.getString("member_email"));
					System.out.println(to_email);

				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		System.out.println("res >> " + res);
		return res;
	}

	// checkPwdFind() end

	// updatePwd() start
	public void updatePwd(String tempPwd, String userId) {

		try {
			openConn();

			sql = "update member set member_pwd = ? where member_id = ?";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, tempPwd);
			pstmt.setString(2, userId);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
	}
	// updatePwd() end

	// updateIndex() start, 삭제 시 인덱스 재정렬
//	public void updateIndex(int index) {
//		try {
//			openConn();
//
//			sql = "update member set member_index = member_index - 1 where member_index > ?";
//
//			pstmt = con.prepareStatement(sql);
//
//			pstmt.setInt(1, index);
//
//			pstmt.executeUpdate();
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			closeConn(rs, pstmt, con);
//		}
//	}
	// updateIndex()

	// 회원 삭제
	public int deleteMember(String id, int index) {
		int res = 0;

		try {
			openConn();

			System.out.println("아이디>>" + id);
			System.out.println("인덱스 >>> " + index);

			sql = "delete from member where member_id = ?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			res = pstmt.executeUpdate();

			sql = "update member set member_index = member_index - 1 where member_index> ? ";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, index);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		System.out.println("res at dao >> " + res);
		return res;
	}
	// deleteMember() end

	// findMemberId(), 아이디 찾기 메서드
	public String findMemberId(String email) {
		String res = "";

		try {
			openConn();

			sql = "select member_id from member where member_email = ?";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, email);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				res = rs.getString("member_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return res;
	}
	// findMemberId() end

	// 이메일 있는지 확인(아이디 비밀번호 찾기 시)
	public int emailCheck(String email) {
		int res = 0;

		try {
			openConn();

			sql = "select * from member where member_email = ?";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, email);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				return 1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return res;
	}
	// emailCheck() end

	// 이메일 있는지 확인(아이디 비밀번호 찾기 시)
	public int findId(String id, String email) {
		int res = 0;

		try {
			openConn();

			sql = "select * from member where member_id = ?";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, id);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (email.equals(rs.getString("member_email"))) {
					return 1;
				} else {
					return -1;
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return res;
	}
	// emailCheck() end

}
