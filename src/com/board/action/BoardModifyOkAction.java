package com.board.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;
import com.project.controller.Action;
import com.project.controller.ActionForward;

public class BoardModifyOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 수정 폼 페이지에서 넘어온 데이터들을 db에 전송하여 게시글을 수정하는 비지니스 로직
				String board_type = request.getParameter("type").trim();
				String board_heading = request.getParameter("heading").trim();
				String board_title = request.getParameter("title").trim();
				String board_cont = request.getParameter("cont").trim();
				
				// type ="hidden"으로 넘어온 데이터들도 받아주어야 한다.
				int board_index = Integer.parseInt(request.getParameter("num").trim());
				int nowPage = Integer.parseInt(request.getParameter("page").trim());
				
				BoardDTO dto = new BoardDTO();
				
				dto.setBoard_type(board_type);
				dto.setBoard_heading(board_heading);
				dto.setBoard_title(board_title);
				dto.setBoard_cont(board_cont);
				dto.setBoard_index(board_index);
				
				BoardDAO dao = BoardDAO.getInstance();
				
				int check = dao.updateBoard(dto);
				
				PrintWriter out = response.getWriter();
				
				if(check > 0) {
					out.println("<script>");
					out.println("alert('게시물 수정 성공')");
					out.println("location.href='board_content.do?no="+board_index+"&page="+nowPage+"'");
					out.println("</script>");
				}else {
					out.println("<script>");
					out.println("alert('게시물 수정 성공')");
					out.println("history.back()");
					out.println("</script>");
				}
				
				
		return null;
	}

}
