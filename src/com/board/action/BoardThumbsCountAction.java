package com.board.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.project.controller.Action;
import com.project.controller.ActionForward;

public class BoardThumbsCountAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, MessagingException, Exception {
		//
		int board_no = Integer.parseInt(request.getParameter("no").trim());
		
		BoardDAO dao = BoardDAO.getInstance();
		
		int count = dao.thumbscount(board_no);
		
		PrintWriter out = response.getWriter();
		
		out.println(count);
		
		out.close();
		
		return null;
	}

}
