package com.matching.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.member.model.MemberDTO;

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
	public int insertMatching(MatchingDTO dto, MemberDTO mdto) {

		int result = 0;

		try {
			openConn();
			// 아이디 닉네임
			sql = "insert into matching values(default, default, ?, ?, ?, ?, ?, ?, now())";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, dto.getGame_name());
			pstmt.setString(2, dto.getTier());
			pstmt.setString(3, mdto.getMember_id());
			pstmt.setString(4, mdto.getMember_nickname());
			pstmt.setString(5, dto.getDiscord_nikname());
			pstmt.setString(6, dto.getKakao_id());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	} // insertMatching() 메서드 end

	// matchloading 에서 취소버튼 누르면 matching DB 삭제하는 메서드
	public int deleteMatching(MemberDTO mdto) {

		int result = 0;

		try {
			openConn();

			sql = "delete from matching where matching_user_id = ?";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, mdto.getMember_id());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}

		return result;
	} // deleteMatching() 메서드 end

	// matchloading 에서 매칭조건을 비교해서 서로 조건이 같으면 1을 반환, 조건이 없으면 0반환
	public int matchingcondition(String matched, String gamename, String tier, String matching_user_id, MatchingDTO dto) {

		int result = 0;

		String eqGame1 = "";
		String eqGame2 = "";

		String eqMatched1 = "";
		String eqMatched2 = "";

		String eqTier1 = "";
		String eqTier2 = "";

		try {

			openConn();

			// 11111111111111111 첫번째 매칭 조건 game_name
			// matching 테이블에서 팝업창에 입력된 아이디에 해당하는 열의 게임이름
			sql = "select game_name from matching where matching_user_id in (?)";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, matching_user_id);

			rs = pstmt.executeQuery();

			rs.next();

			eqGame1 = rs.getString("game_name"); // 현재 배틀그라운드 저장

			System.out.println("eqGame1 >>> " + eqGame1);

//			openConn();
//			// matching 테이블에서 로그인한 아이디를 제외한 나머지 열
//			sql = "select game_name from matching where matching_user_id not in (?)";

			openConn();

			sql = "select * from matching where game_name in (?) and tier in (?) and matching_user_id not in(?)";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, gamename);
			pstmt.setString(2, tier);
			pstmt.setString(3, matching_user_id);

			rs = pstmt.executeQuery();
			rs.next();
			eqGame2 = rs.getString("game_name"); // DB에 저장된 배틀그라운드 저장

			System.out.println("eqGame2 >>> " + eqGame2);

			// 22222222222222222 두번째 매칭 조건 matched
			// matching 테이블에서 팝업창에 입력된 아이디에 해당하는 열의 matched

			openConn();

			sql = "select matched from matching where matching_user_id in (?)";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, matching_user_id);

			rs = pstmt.executeQuery();

			rs.next();
			
			eqMatched1 = rs.getString("matched");
			System.out.println("eqMatched1 >>> " + eqMatched1);
			// matching 테이블에서 로그인한 입력된 아이디를 제외한 나머지 열

//			openConn();
//
//			sql = "select matched from matching where matching_user_id not in (?)";

			openConn();

			sql = "select matched from matching where game_name in (?) and tier in (?) and matching_user_id not in(?)";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, gamename);
			pstmt.setString(2, tier);
			pstmt.setString(3, matching_user_id);

			rs = pstmt.executeQuery();
			rs.next();
			eqMatched2 = rs.getString("matched"); // DB에 저장된 matched
			System.out.println("eqMatched2 >>> " + eqMatched2);
			// 3333333333333333 세번째 매칭 조건 tier
			// matching 테이블에서 팝업창에 입력된 아이디에 해당하는 tier

			openConn();

			sql = "select tier from matching where matching_user_id in (?)";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, matching_user_id);

			rs = pstmt.executeQuery();

			rs.next();

			eqTier1 = rs.getString("tier");
			System.out.println("eqTier1 >>> " + eqTier1);
//			openConn();
//			// matching 테이블에서 로그인한 입력된 아이디를 제외한 나머지 열
//			sql = "select tier from matching where matching_user_id not in (?)";

			openConn();

			sql = "select tier from matching where game_name in (?) and tier in (?) and matching_user_id not in(?)";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, gamename);
			pstmt.setString(2, tier);
			pstmt.setString(3, matching_user_id);

			rs = pstmt.executeQuery();

			rs.next();

			eqTier2 = rs.getString("tier"); // DB에 저장된 tier
			System.out.println("eqTier2 >>> " + eqTier2);
			
			//정민님을 위한 주석 : 매칭한 상대의 회원의 정보를 뽑아서 dto 객체에 저장해주는 작업입니다.
			openConn();
			
			sql ="select * from matching where game_name in (?) and tier in (?) and matching_user_id not in (?)";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, gamename);
			pstmt.setString(2, tier);
			pstmt.setString(3, matching_user_id);
			
			rs = pstmt.executeQuery();
			
			rs.next();
			
			dto.setMatching_user_id(rs.getString("matching_user_id"));
			dto.setDiscord_nikname(rs.getString("discord_nickname"));
			dto.setKakao_id(rs.getString("kakao_id"));
			
			// 매칭 조건이 서로 같은지 확인하는 최종 절차
			if (eqGame1.equals(eqGame2)) {
				if (eqMatched1.equals(eqMatched2)) {
					if (eqTier1.equals(eqTier2)) {
						result = 1;
						System.out.println("최종 리턴 >>> " + result);
					} else {
						result = 0;
						System.out.println("티어가 다른 리턴 >>> " + result);
					}
				} else {
					result = 0;
					System.out.println("matched가 다른 리턴 >>> " + result);
				}
			} else {
				result = 0;
				System.out.println("게임명이 다른 리턴 >>> " + result);
			}

		} catch (

		SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}

		return result;
	}

	// matching 테이블 상세 정보
	public MatchingDTO contentMatching(String member_id) {

		MatchingDTO dto = null;

		try {
			openConn();

			sql = "select * from matching where matching_user_id = ?";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, member_id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto = new MatchingDTO();

				dto.setMatched(rs.getString("matched"));
				dto.setAccept(rs.getString("accept"));
				dto.setGame_name(rs.getString("game_name"));
				dto.setTier(rs.getString("tier"));
				dto.setMatching_user_id(rs.getString("matching_user_id"));
				dto.setMatching_user_nikname(rs.getString("matching_user_nickname"));
				dto.setDiscord_nikname(rs.getString("discord_nickname"));
				dto.setKakao_id(rs.getString("kakao_id"));
				dto.setMatching_regdate(rs.getString("matching_regdate"));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return dto;
	} // contentMatching() 메서드 end

	// 매칭 수락을 누른 유저의 accept를 0에서 1로 수정하며
	// accept가 둘다 1로 같은지 확인해 값을 반환하는 메서드
	public int MachingAccept(String matching_user_id, String matched, String game_name, String tier) {

		int accept = 0;

		String eqAccept1 = "";
		String eqAccept2 = "";

		try {
			openConn();
			// accept 1로 변환
			sql = "update matching set accept = '1' where matching_user_id = ?";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, matching_user_id);

			pstmt.executeUpdate();

			// 11111111111111111 첫번째 매칭 조건 game_name
			// matching 테이블에서 팝업창에 입력된 아이디에 해당하는 열의 Accept
			openConn();

			sql = "select accept from matching where matching_user_id in (?)";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, matching_user_id);

			rs = pstmt.executeQuery();

			rs.next();

			eqAccept1 = rs.getString("accept"); // 현재 수락상태

			System.out.println("eqAccept1 >>> " + eqAccept1);

			openConn();
			// matching 테이블에서 로그인한 아이디를 제외한 나머지
			sql = "select accept from matching where game_name in (?) and tier in (?) and matching_user_id not in(?)";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, game_name);
			pstmt.setString(2, tier);
			pstmt.setString(3, matching_user_id);
			
			rs = pstmt.executeQuery();
			rs.next();
			eqAccept2 = rs.getString("accept"); // 현재 수락 상태

			System.out.println("eqAccept2 >>> " + eqAccept2);

			if (eqAccept1.equals(eqAccept2)) {
				accept = 1;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		System.out.println("Accept rtn >>> " + accept);
		return accept;
	} // MachingAccept() 메서드 end

	public MatchingDTO opponentContent(String matching_user_id, String matched, String game_name, String tier) {
		
		MatchingDTO dto = null;
	
		try {
			
			openConn();
			sql = "select * from matching where game_name in (?) and tier in (?) and matching_user_id not in(?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, game_name);
			pstmt.setString(2, tier);
			pstmt.setString(3, matching_user_id);
			
			rs = pstmt.executeQuery();
			
			rs.next();
			
			dto = new MatchingDTO();
			
			dto.setMatching_user_id(rs.getString("matching_user_id"));
			dto.setMatching_user_nikname(rs.getString("matching_user_nickname"));
			dto.setDiscord_nikname(rs.getString("discord_nickname"));
			dto.setKakao_id(rs.getString("kakao_id"));
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return dto;
	}//opponetContent() 메서드 end

}
