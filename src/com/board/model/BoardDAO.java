package com.board.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
		public int getBoardCount(String type) {
			
			int count = 0;
			
			try {
				openConn();
				
				sql = "select count(*) from "+type+"_board"; // max는 게시물 수가 아니라 수를 가져오는거기 때문에 나중에 문제가 생길 수 있음
				
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
	
		
		
	
	public List<BoardDTO> getBoardList(int page, int rowsize, String type) {
		
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		
		// 해당 페이지에서 시작 번호 
		int startNo = (page * rowsize) - (rowsize - 1);
		
		// 해당 페이지에서 끝 번호 
		int endNo = (page * rowsize);
		
		try {
			openConn();
			
			sql = "select * from (select row_number() over(order by board_index desc) rnum, b.* from "+type+"_board b) b_rownum where rnum >= ? and rnum <= ?";
			
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
	
	
	public int insertBoard(BoardDTO dto, String type) {
		
		int result = 0, count = 0;
		
		try {
			openConn();
			
			sql = "select max(board_index) from "+type+"_board";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1) + 1;
			}
			
			sql = "insert into "+type+"_board values ('"+type+"', ?, ?, ?, ?, ?, ?, default, ?, default, default, now(), default)";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, count);
			pstmt.setString(2, dto.getBoard_title());
			pstmt.setString(3, dto.getBoard_cont());
			System.out.println("내용 >>>"+dto.getBoard_cont());
			pstmt.setString(4, dto.getBoard_writer_id());
			System.out.println("아이디 >>>"+dto.getBoard_writer_id());
			pstmt.setString(5, dto.getBoard_writer_nickname());
			System.out.println("닉네임 >>" +dto.getBoard_writer_nickname());
			pstmt.setString(6, dto.getUpload_file());
			pstmt.setString(7, dto.getBoard_heading());
			System.out.println("헤딩 >>" +dto.getBoard_heading());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		return result;
	} // insertBoard() end
	
	
	
	// board 테이블의 게시물 번호에 해당하는 게시글을 수정하는 메서드
	public int updateBoard(BoardDTO dto, String type) {
		
		int result = 0;
		
		try {
			openConn();
			
			if(dto.getUpload_file() != null) {
				sql = "update "+type+"_board set board_type = ?, board_heading = ?, board_title = ?, board_cont = ?, board_update = now(), upload_file = ? where board_index = ?";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, dto.getBoard_type());
				pstmt.setString(2, dto.getBoard_heading());
				pstmt.setString(3, dto.getBoard_title());
				pstmt.setString(4, dto.getBoard_cont());
				pstmt.setString(5, dto.getUpload_file());
				pstmt.setInt(6, dto.getBoard_index());
				
			}else {
				sql = "update "+type+"_board set board_type = ?, board_heading = ?, board_title = ?, board_cont = ?, board_update = now() where board_index = ?";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, dto.getBoard_type());
				pstmt.setString(2, dto.getBoard_heading());
				pstmt.setString(3, dto.getBoard_title());
				pstmt.setString(4, dto.getBoard_cont());
				pstmt.setInt(5, dto.getBoard_index());
			}
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
			
		return result;
	} // updateBoard() 메서드 end

	
	
	
	public void boardHit(int no, String type) {
		
		try {
			openConn();
			
			sql = "update "+type+"_board set board_hit = board_hit + 1 where board_index = ?";
			
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
	
	
	public int maxCount(String type) {
		int maxCount = 0;
		
		try {
			openConn();
			
			sql = "select max(board_index) from "+type+"_board";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				maxCount = rs.getInt(1);
			}
			
			System.out.println("맥스카운트"+maxCount);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		return maxCount;
	}
	
	public BoardDTO boardContent(int no, String type) {
		
		BoardDTO dto = null;
		
		try {
			openConn();
			
			sql = "select * from "+type+"_board where board_index = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new BoardDTO();
				
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
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		return dto;
	} // boardContent() end
	
	
	public int deleteBoard(int no, String type) {
		int result = 0;
		
		try {
			openConn();
			
			sql = "delete from "+type+"_board where board_index = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	} // deleteBoard() end
	
	
	public void updateSequence(int no, String type) {
		
		try {
			openConn();
			
			sql = "update "+type+"_board set board_index = board_index -1 where board_index > ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
	} //deleteSequence() 메서드 end

	
	
	
	public List<BoardDTO> searchBoardList(String field, String keyword, int start, int end, String type) {
		
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		
		try {
			openConn();
			
			if(field.equals("board_title_cont")) {
				sql = "select * from (select row_number() over(order by board_index desc) rnum, b.* from "+type+"_board b where board_title like '%"+keyword+"%' or board_cont like '%"+keyword+"%') b_rownum where rnum >= ? and rnum <= ?";
			}else {
				sql = "select * from (select row_number() over(order by board_index desc) rnum, b.* from "+type+"_board b where "+field+" like '%"+keyword+"%') b_rownum where rnum >= ? and rnum <= ?";
			}
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				// board테이블에서 하나의 레코드를 가져와서 각각 컬럼의 데이터를 dto객체의 setter() 메서드의 인자로 전달
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
		
	} // searchBoardList()메서드 end
	
	
	
	public int getTotalRecord(String field, String keyword, String type) {
		
		int result = 0;
		
		openConn();
		
		try {
			if(field.equals("board_title_cont")) {
				sql = "select count(*) from "+type+"_board where board_title like '%"+keyword+"%'or board_cont like '%"+keyword+"%'";
			}else {
				sql = "select count(*) from "+type+"_board where "+field+" like '%"+keyword+"%'";
			}
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	}// getTotalRecord() end
	
	
	
	public String getReplyList(int no, String type) {
		
		String res = "";
		
		try {
			openConn();
			
			sql = "select * from "+type+"_comment where board_comment_index = ? order by comment_date desc";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			res += "<replys>";
			
			while(rs.next()) {
				res += "<reply>";
				res += "<comment_index>"+rs.getInt("comment_index")+"</comment_index>";
				res += "<board_comment_index>"+rs.getInt("board_comment_index")+"</board_comment_index>";
				res += "<comment_cont>"+rs.getString("comment_cont")+"</comment_cont>";
				res += "<comment_writer_id>"+rs.getString("comment_writer_id")+"</comment_writer_id>";
				res += "<comment_writer_nickname>"+rs.getString("comment_writer_nickname")+"</comment_writer_nickname>";
				res += "<comment_date>"+rs.getString("comment_date")+"</comment_date>";
				res += "<comment_update>"+rs.getString("comment_update")+"</comment_update>";
				res += "<comment_hit>"+rs.getInt("comment_hit")+"</comment_hit>";
				res += "</reply>";
			}
			
			res += "</replys>";
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		return res;
	}  // getReplyList() 메서드 end
	
	
	
	// 답변 내용을 tbl_reply 테이블에 저장하는 메서드.
	public int replyInsert(CommentDTO dto, String type) {
		
		int result = 0, count = 0;
		
		try {
			openConn();
			
			sql = "select max(comment_index) from "+type+"_comment";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1) + 1;
			}
			openConn();
			
			sql = "insert into "+type+"_comment values(?, ?, ?, ?, ?, now(), default, default)";	
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, count);
			
			pstmt.setInt(2, dto.getComment_index());
			
			pstmt.setString(3, dto.getComment_cont());
			System.out.println("댓글 작성자 아이디" +dto.getComment_writer_id());
			System.out.println("댓글 작성자 닉네임" +dto.getComment_writer_nickname());
			pstmt.setString(4, dto.getComment_writer_id());
			pstmt.setString(5, dto.getComment_writer_nickname());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		return result;
	}  // replyInsert() 메서드 end
	
	
	public int replyModify(int no, String member_id, String comment_cont, String type) {
		
		String writer_id = null;
		int result = 0;
		
		try {
			openConn();
			
			sql = "select comment_writer_id from "+type+"_comment where comment_index = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				writer_id = rs.getString("comment_writer_id");
			}
			
			if(writer_id.equals(member_id)) {
				
				sql = "update "+type+"_comment set comment_cont = ?, comment_update = now() where comment_index = ?";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, comment_cont);

				pstmt.setInt(2, no);
				
				result = pstmt.executeUpdate();
				
			}else {
				result = -1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	}// replyModify() end
	
	
	
	public int replyDelete(int no, String member_id, String type) {
		
		String writer_id = null;
		int result = 0;
		
		try {
			openConn();
			
			sql = "select comment_writer_id from "+type+"_comment where comment_index = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				writer_id = rs.getString("comment_writer_id");
			}
			
			if(writer_id.equals(member_id)) {
				
				sql = "delete from "+type+"_comment where comment_index = ?";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, no);
				
				result = pstmt.executeUpdate();
				
				sql = "update "+type+"_comment set comment_index = comment_index -1 where comment_index > ?";

				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, no);
				
				pstmt.executeUpdate();
				
			}else {
				result = -1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	}// replyDelete() end
	
	
	
	public int checkThumbs(String loginMem, int board_no, String type) {
		int res = 0;
		
		try {
			openConn();
			
			sql = "select count(*) from board_thumbs where member_id = ? and board_index = ? and board_type = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, loginMem);
			pstmt.setInt(2, board_no);
			pstmt.setString(3, type);
			
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				res = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		} 
		return res;
	}
	
	
    public void thumbsUpdate(String loginMem, int board_no, String type) {
    	try {
    		openConn();
    		
    		sql = "insert into board_thumbs values(?, ?, ?)";
    		
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, loginMem);
			pstmt.setString(2, type);
			pstmt.setInt(3, board_no);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
    }
    
    public void thumbsDelete(String loginMem, int board_no, String type) {
    	
    	try {
    		openConn();
    		
    		sql = "delete from board_thumbs where member_id = ? and board_index = ? and board_type = ?";
    		
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, loginMem);
			pstmt.setInt(2, board_no);
			pstmt.setString(3, type);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
    
    }
    
    public void memberPointUpdate(int board_no, String id) {
    	
    	try {
    		openConn();
    		
    		sql = "update member set member_point = (select count(*) from board_thumbs where member_id = ?) where member_id = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, id);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
    }
    
    public int thumbscount(int board_no, String type) {
    	int res = 0;
    	try {
    		openConn();
    		
    		sql = "update "+type+"_board set board_thumbs = (select count(*) from board_thumbs where board_index = ? and board_type = ?) where board_index = ? and board_type = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, board_no);
			pstmt.setString(2, type);
			pstmt.setInt(3, board_no);
			pstmt.setString(4, type);
			
			res = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
    	return res;
    }
    
}   
    
    
    
    
    
    
