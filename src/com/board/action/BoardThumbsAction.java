package com.board.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.project.controller.Action;
import com.project.controller.ActionForward;

public class BoardThumbsAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		int thumbs = Integer.parseInt(request.getParameter("no").trim());
		
		BoardDAO dao = BoardDAO.getInstance();
		
		int res = dao.thumbsBaord(thumbs);
		
		PrintWriter out = response.getWriter();
		
		out.println(res);
		
		return null;
	}

}
