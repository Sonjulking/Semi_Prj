package com.board.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.CommentDTO;
import com.project.controller.Action;
import com.project.controller.ActionForward;

public class BoardReplyInsertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// 답변글 폼에서 넘어온 데이터들을 가지고 DB에 저장하는 비지니스 로직.
		String reply_writer_id = request.getParameter("writer_id").trim();
		System.out.println("댓글 action 작성자"+reply_writer_id);

		String reply_writer_nickname = request.getParameter("writer_nickname").trim();
		System.out.println("댓글  action 닉네임"+reply_writer_nickname);
		
		String reply_cont = request.getParameter("cont").trim();
		String board_type = request.getParameter("type").trim();
		int reply_bno = Integer.parseInt(request.getParameter("bno").trim());
		System.out.println(reply_writer_id);
		System.out.println(reply_writer_nickname);
		System.out.println(reply_cont);
		System.out.println(reply_bno);
		
		CommentDTO dto = new CommentDTO();
		
		dto.setComment_index(reply_bno);
		dto.setComment_writer_id(reply_writer_id);
		dto.setComment_writer_nickname(reply_writer_nickname);
		dto.setComment_cont(reply_cont);
		
		
		BoardDAO dao = BoardDAO.getInstance();
		
		int check = dao.replyInsert(dto, board_type);
		
		PrintWriter out = response.getWriter();
		
		// 결과값을 클라이언트(Ajax)에 보내주면 됨.
		out.println(check);
		
		return null;
	}

}
