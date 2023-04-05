package com.board.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.CommentDTO;
import com.project.controller.Action;
import com.project.controller.ActionForward;

public class BoardReplyInsertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		
		
		
		// 여기부터 다시 전부 손봐야함 (작업중)
		
		
		
		
		
		// 답변글 폼에서 넘어온 데이터들을 가지고 DB에 저장하는 비지니스 로직.
		String reply_writer = 
			request.getParameter("writer").trim();
		
		String reply_cont = 
			request.getParameter("cont").trim();
		int reply_bno = 
			Integer.parseInt(request.getParameter("bno").trim());
		
		CommentDTO dto = new CommentDTO();
		
		//dto.setBno(reply_bno);
		//dto.setRewriter(reply_writer);
		//dto.setRecont(reply_cont);
		
		//TblDAO dao = TblDAO.getInstance();
		
		//int check = dao.replyInsert(dto);
		
		PrintWriter out = response.getWriter();
		
		// 결과값을 클라이언트(Ajax)에 보내주면 됨.
		//out.println(check);
		
		return null;
	}

}
