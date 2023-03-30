package com.board.model;

import java.sql.Connection;
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
		
		try {
			// 1단계 : JNDI 서버 객체 생성
			// 자바의 네이밍 서비스(JNDI)에서 이름과 실제 객체를 연결해 주는 개념이 Context 객체이며,
			// InitialContext 객체는 네이밍 서비스를 이용하기 위한 시작점이 됨.
			Context initCtx = new InitialContext();
			
			// 2단계 : Context 객체를 얻어와야 함.
			// "java:comp/env" 라는 이름의 인수로 Context 객체를 얻어옴.
			// "java:comp/env"는 현재 웹 애플리케이션에서 네이밍 서비스를 이용 시 루트 디렉토리라고 생각하면 됨.
			// 즉, 현재 웹 애플리케이션이 사용할 수 있는 모든 자원은 "java:comp/env" 아래에 위치를 하게 됨.
			Context ctx = (Context)initCtx.lookup("java:comp/env");
			
			// 3단계 : lookup() 메서드를 이용하여 매칭되는 커넥션을 찾게 됨.
			//		 "java:comp/env" 아래에 위치한 "jcbc/myoracle" 자원을 얻어옴,
			//		이 자원이 바로 데이터소스(커넥션풀)임.
			//		여기서 "jdbc/myoracle" 은 context.xml 파일에 추가했던 <Resource> 태그 안에 있던 name 속성의 값임.
			DataSource ds = (DataSource)ctx.lookup("jdbc/myoracle");
			
			// 4단계 : DataSource 객체를 이용하여 커넥션을 하나 가져온다.
			con = ds.getConnection();
			
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
				
				sql = "select count(*) from board"; // max는 게시물 수가 아니라 수를 가져오는거기 때문에 나중에 문제가 생길 수 있음
				
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
			
			sql = "select * from (select row_number() over(order by board_index desc) rnum, b.* from board b) where rnum >= ? and rnum <= ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, startNo);
			pstmt.setInt(2, endNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				
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
			
			sql = "select max(board_index) from board";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1) + 1;
			}
			
			sql = "insert into upload values (?, ?, ?, ?, ?, ?, '', ?, ?, default, default, sysdate, '')";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, count);
			pstmt.setString(2, dto.getBoard_title());
			pstmt.setString(3, dto.getBoard_cont());
			pstmt.setString(4, dto.getBoard_writer_id());
			pstmt.setString(5, dto.getBoard_writer_nickname());
			pstmt.setString(6, dto.getUpload_file());
			pstmt.setString(7, dto.getBoard_type());
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
			
			sql = "update board set board_hit = board_hit + 1 where board_index = ?";
			
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
			
			sql = "select * from upload where upload_no = ?";
			
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
