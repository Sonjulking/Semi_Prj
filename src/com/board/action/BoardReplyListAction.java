package com.board.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;
import com.project.controller.Action;
import com.project.controller.ActionForward;

public class BoardReplyListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 글번호에 해당하는 댓글 전체 리스트를 DB에서 조회하여 상세내역 view page로 이동시키는 비지니스 로직.
		int reply_no = Integer.parseInt(request.getParameter("no").trim());
		String board_type = request.getParameter("type");
		BoardDAO dao = BoardDAO.getInstance();
		
		String str = dao.getReplyList(reply_no, board_type);
		
		int rowsize = 10;
		int block = 10;
		int totalRecord = 0;
		int allPage = 0;
		int page = 0;
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page").trim());
		}else {
			// 처음으로 "전체 게시물 목록" a 태그를 클릭한 경우
			page = 1;
		}
		
		int startNo = (page * rowsize) - (rowsize - 1);
		int endNo = (page * rowsize);
		int startBlock = (((page - 1) / block) * block) + 1;
		int endBlock = (((page - 1) / block) * block) + block;
		
		BoardDAO dao = BoardDAO.getInstance();
		
		totalRecord = dao.getBoardCount();
		
		allPage = (int)Math.ceil((totalRecord / (double)rowsize)); 
		
		if(endBlock > allPage) { // 페이지수에 맞춰 마지막 블럭 수 제한
			endBlock = allPage;
		}
		
		// 현재 페이지에 해당하는 게시물을 가져오는 메서드 호출
		List<BoardDTO> pageList = dao.getBoardList(page, rowsize);
		// 지금까지 페이징 처리 시 작업했던 모든 데이터들을  view page로 이동을 시키자
		request.setAttribute("check", "board_list.do?");
		request.setAttribute("page", page);
		request.setAttribute("rowsize", rowsize);
		request.setAttribute("block", block);
		request.setAttribute("totalRecord", totalRecord);
		request.setAttribute("allPage", allPage);
		request.setAttribute("startNo", startNo);
		request.setAttribute("endNo", endNo);
		request.setAttribute("startBlock", startBlock);
		request.setAttribute("endBlock", endBlock);
		request.setAttribute("List", pageList);
		
		
		PrintWriter out = response.getWriter();
		
		out.println(str);
		return null;
	}

}
