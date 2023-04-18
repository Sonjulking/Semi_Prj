package com.board.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;
import com.project.controller.Action;
import com.project.controller.ActionForward;

public class BoardModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// get방식으로 넘어온 게시 글번호에 해당하는 상세내역을 DB에서 조회하여 수정 폼 페이지로 이동시키는 비지니스
		int board_index = Integer.parseInt(request.getParameter("no").trim());
		int nowPage = Integer.parseInt(request.getParameter("page").trim());
		String board_type = request.getParameter("type");
		BoardDAO dao = BoardDAO.getInstance();
		
		BoardDTO cont = dao.boardContent(board_index, board_type);
		
		request.setAttribute("Modify", cont);
		request.setAttribute("Page", nowPage);
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		
		forward.setPath("board/board_modify.jsp");
		
		return forward;
	}

}
