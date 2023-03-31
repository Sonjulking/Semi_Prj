package com.board.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;
import com.project.controller.Action;
import com.project.controller.ActionForward;

public class BoardContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// get방식으로 넘어온 글번호에 해당하는 게시글읠 상세내역을 DB에서 조회하여 view page로 이동시키는 비지니스 로직
		
		int board_no = Integer.parseInt(request.getParameter("no").trim());
		
		BoardDAO dao = BoardDAO.getInstance();
		
		// 조회수를 증가시켜주는 메서드 호출
		dao.boardHit(board_no);
		
		// 글번호에 해당하는 게시글의 상세내역을 조회하는 메서드 호출
		BoardDTO cont = dao.boardContent(board_no);
		
		request.setAttribute("Cont", cont);
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		
		forward.setPath("board/board_content.jsp");
		
		return forward;
	}

}
