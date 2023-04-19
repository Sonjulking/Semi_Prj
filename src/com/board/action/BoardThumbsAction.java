package com.board.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.project.controller.Action;
import com.project.controller.ActionForward;

public class BoardThumbsAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException, MessagingException, Exception{
		
		int board_no = Integer.parseInt(request.getParameter("no").trim());
		String board_writer = request.getParameter("board_id").trim();
		String loginMem = request.getParameter("id").trim();
		// 게시판 종류 가져오기
		String board_type = request.getParameter("type");
		
		System.out.println(board_no);
		System.out.println(board_writer);
		System.out.println(loginMem);
		
		BoardDAO dao = BoardDAO.getInstance();
		int check = dao.checkThumbs(loginMem, board_no, board_type);
		
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		
		if(check == 0) {
			dao.thumbsUpdate(loginMem, board_no, board_type);
			System.out.println("좋아요 성공");
			out.println("좋아요 누름");
		} else {
			dao.thumbsDelete(loginMem, board_no, board_type);
			System.out.println("좋아요 취소 성공");
			out.println("좋아요 취소함");
		}
		int res = dao.thumbscount(board_no, board_type);
		dao.memberPointUpdate(board_no, board_writer);
		
		
		out.println(res);
		
		
		
		return null;
	}

}
