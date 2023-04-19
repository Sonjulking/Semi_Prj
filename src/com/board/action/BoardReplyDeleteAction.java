package com.board.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.project.controller.Action;
import com.project.controller.ActionForward;

public class BoardReplyDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException, MessagingException, Exception {
		
		int no = Integer.parseInt(request.getParameter("reply_index").trim());
		String member_id = request.getParameter("member_id").trim();
		String board_type = request.getParameter("type").trim();
		
		BoardDAO dao = BoardDAO.getInstance();
		
		int res = dao.replyDelete(no, member_id, board_type);
		
		PrintWriter out = response.getWriter();
		
		out.println(res);
		
		return null;
	}

}
