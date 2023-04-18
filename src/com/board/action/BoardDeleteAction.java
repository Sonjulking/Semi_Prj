package com.board.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.project.controller.Action;
import com.project.controller.ActionForward;

public class BoardDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 삭제 폼 페이지에서 넘어온 글번호와 글 비밀번호를 가지고 board테이블에서 해당 게시글을 삭제하는 비지니스 로직
		int board_index = Integer.parseInt(request.getParameter("no").trim());
		int nowPage = Integer.parseInt(request.getParameter("page").trim());
		String board_type = request.getParameter("type");
		BoardDAO dao = BoardDAO.getInstance();
		
		int check = dao.deleteBoard(board_index, board_type);
		
		PrintWriter out = response.getWriter();
		
		if(check > 0) {
			dao.updateSequence(board_index, board_type);
			out.println("<script>");
			out.println("alert('삭제 성공')");
			out.println("location.href='board_list.do?page="+nowPage+"&type="+board_type+"'");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('삭제 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return null;

	}

}
