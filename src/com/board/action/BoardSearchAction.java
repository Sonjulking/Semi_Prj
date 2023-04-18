package com.board.action;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

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
		String board_type = request.getParameter("type").trim();
		StringTokenizer st = new StringTokenizer(board_type, "'");

		BoardDAO dao = BoardDAO.getInstance();
		
		int rowsize = 10;
		int block = 10;
		int totalRecord = 0;
		int allPage = 0;
		int page = 0;
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page").trim());
		}else {
			page = 1;
		}
		int startNo = (page * rowsize) - (rowsize - 1);
		int endNo = (page * rowsize);
		int startBlock = (((page - 1) / block) * block) + 1;
		int endBlock = (((page - 1) / block) * block) + block;

		
		List<BoardDTO> searchList = dao.searchBoardList(field, keyword, startNo, endNo, board_type);
		
		totalRecord = dao.getTotalRecord(field, keyword, board_type);
		
		allPage = (int)Math.ceil((totalRecord / (double)rowsize)); 
		
		if(endBlock > allPage) {
			endBlock = allPage;
		}
		
		// 지금까지 페이징 처리 시 작업했던 모든 데이터들을  view page로 이동을 시키자
		request.setAttribute("check", "board_search.do?field="+field+"&keyword="+keyword+"&");
		request.setAttribute("page", page);
		request.setAttribute("rowsize", rowsize);
		request.setAttribute("block", block);
		request.setAttribute("totalRecord", totalRecord);
		request.setAttribute("allPage", allPage);
		request.setAttribute("startNo", startNo);
		request.setAttribute("endNo", endNo);
		request.setAttribute("startBlock", startBlock);
		request.setAttribute("endBlock", endBlock);
		request.setAttribute("List", searchList);
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		forward.setPath("board/"+board_type+"_board.jsp");
		return forward;
	}

}
