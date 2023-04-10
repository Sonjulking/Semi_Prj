package com.board.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;
import com.project.controller.Action;
import com.project.controller.ActionForward;

public class BoardSearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 검색 폼 창에 넘어온 검색어를 가지고 db에서 검색어에 해당하는 모든 게시물을 가져와서 view page로 이동시키는 비지니스 로직

		String field = request.getParameter("field").trim();
		String keyword = request.getParameter("keyword").trim();
		int page = Integer.parseInt(request.getParameter("page").trim());
		
		BoardDAO dao = BoardDAO.getInstance();
		
		List<BoardDTO> searchList = dao.searchBoardList(field, keyword);
		
		request.setAttribute("Search", searchList);
		request.setAttribute("page", page);
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		
		forward.setPath("board/board_search.jsp");
		
		return forward;
	}

}
