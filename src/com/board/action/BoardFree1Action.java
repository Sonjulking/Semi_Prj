package com.board.action;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;
import com.project.controller.Action;
import com.project.controller.ActionForward;

public class BoardFree1Action implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, MessagingException, Exception {
		
		BoardDAO dao = BoardDAO.getInstance();
		
		BoardDTO dto = dao.boardFree1Content();
		
		request.setAttribute("free1Dto", dto);
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		
		forward.setPath("main.jsp");
		
		return forward;
	}

}
