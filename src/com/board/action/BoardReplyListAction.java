package com.board.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
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
		
		PrintWriter out = response.getWriter();
		
		out.println(str);
		return null;
	}

}
