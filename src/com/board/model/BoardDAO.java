package com.board.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {
	// DB와 연동하는 객체.
	Connection con = null;
	
	// DB에 SQL문을 전송하는 객체.
	PreparedStatement pstmt = null;
	
	// SQL문을 실행한 후에 결과값을 가지고 있는 객체.
	ResultSet rs = null;
	
	// 쿼리문을 저장할 객체.
	String sql = null;
	
	// BoardDAO 클래스를 싱클턴 방식으로 만들어 보자.
	// 1단계 : 싱글턴 방식으로 객체를 만들기 위해서는 우선적으로 기본생성자의 접근제어자를 public이 아니라 private으로 바꾸어 주저야 한다.
	// 		   즉, 외부에서는 직접적으로 기본생성자를 호출하지 못하게 하는 방식이다.
	
	// 2단계 : BoardDAO 클래스의 정적(static) 멤버로 선언을 해 주어야 한다.
	private static BoardDAO instance;
	
	private BoardDAO() {
	}	// 기본생성자
	
	// 3단계 : 기본생성자 대신에 싱그턴 객체를 return 해 주는
	//		 getInstance() 메서드를 만들어서 해당 getInstance() 메서드를 외부에서 접근할 수 있도록 해 주면 됨.
	public static BoardDAO getInstance() {
		if(instance == null) {
			instance = new BoardDAO();
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
	
	
	
	
	// board 테이블의 전체 게시물의 수를 확인하는 메서드
		public int getBoardCount() {
			
			int count = 0;
			
			try {
				openConn();
				
				sql = "select count(*) from free_board"; // max는 게시물 수가 아니라 수를 가져오는거기 때문에 나중에 문제가 생길 수 있음
				
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
	
		
		
	
	public List<BoardDTO> getBoardList(int page, int rowsize) {
		
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		
		// 해당 페이지에서 시작 번호 
		int startNo = (page * rowsize) - (rowsize - 1);
		
		// 해당 페이지에서 끝 번호 
		int endNo = (page * rowsize);
		
		try {
			openConn();
			
			sql = "select * from (select row_number() over(order by board_index desc) rnum, b.* from free_board b) where rnum >= ? and rnum <= ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, startNo);
			pstmt.setInt(2, endNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				
				dto.setBoard_type(rs.getString("board_type"));
				dto.setBoard_index(rs.getInt("board_index"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_cont(rs.getString("board_cont"));
				dto.setBoard_writer_id(rs.getString("board_writer_id"));
				dto.setBoard_writer_nickname(rs.getString("board_writer_nickname"));
				dto.setUpload_file(rs.getString("upload_file"));
				dto.setUpload_fileImg(rs.getString("upload_fileImg"));
				dto.setBoard_heading(rs.getString("board_heading"));
				dto.setBoard_hit(rs.getInt("board_hit"));
				dto.setBoard_thumbs(rs.getInt("board_thumbs"));
				dto.setBoard_date(rs.getString("board_date"));
				dto.setBoard_update(rs.getString("board_update"));
				
				list.add(dto);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		return list;
		
	} // getBoardList() end
	
	
	public int insertBoard(BoardDTO dto) {
		
		int result = 0, count = 0;
		
		try {
			openConn();
			
			sql = "select max(board_index) from free_board";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1) + 1;
			}
			
			sql = "insert into free_board values (?, ?, ?, ?, ?, ?, '', ?, ?, default, default, now(), '')";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getBoard_type());
			pstmt.setInt(2, count);
			pstmt.setString(3, dto.getBoard_title());
			pstmt.setString(4, dto.getBoard_cont());
			pstmt.setString(5, dto.getBoard_writer_id());
			pstmt.setString(6, dto.getBoard_writer_nickname());
			pstmt.setString(7, dto.getUpload_file());
			pstmt.setString(8, dto.getBoard_heading());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		return result;
	} // insertBoard() end
	
	
	public void boardHit(int no) {
		
		try {
			openConn();
			
			sql = "update free_board set board_hit = board_hit + 1 where board_index = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
	} // boardHit() end 
	
	
	
	
	public BoardDTO boardContent(int no) {
		
		BoardDTO dto = null;
		
		try {
			openConn();
			
			sql = "select * from free_board where board_no = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new BoardDTO();
				
				dto.setBoard_index(rs.getInt("board_index"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_cont(rs.getString("board_cont"));
				dto.setBoard_writer_id(rs.getString("board_writer_id"));
				dto.setBoard_writer_nickname(rs.getString("board_writer_nickname"));
				dto.setUpload_file(rs.getString("upload_file"));
				dto.setUpload_fileImg(rs.getString("upload_fileImg"));
				dto.setBoard_type(rs.getString("board_type"));
				dto.setBoard_heading(rs.getString("board_heading"));
				dto.setBoard_hit(rs.getInt("board_hit"));
				dto.setBoard_thumbs(rs.getInt("board_thumbs"));
				dto.setBoard_date(rs.getString("board_date"));
				dto.setBoard_update(rs.getString("board_update"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		return dto;
	} // boardContent() end
	
	
}
