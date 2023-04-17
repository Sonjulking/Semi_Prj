package com.board.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;
import com.project.controller.Action;
import com.project.controller.ActionForward;

public class BoardReplyPagingAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException, MessagingException, Exception {
		
		int rowsize = 10;
		
		// 아래에 보여질 페이지 최대 블럭 수
		int block = 10;
		
		// DB 상의 게시물의 전체 수
		int totalRecord = 0;

		// 전체 페이지 수
		int allPage = 0;
		
		// 현재 페이지 변수
		int page = 0;
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page").trim());
		}else {
			// 처음으로 "전체 게시물 목록" a 태그를 클릭한 경우
			page = 1;
		}
		
		// 해당 페이지에서 시작 번호 
		int startNo = (page * rowsize) - (rowsize - 1);
		
		// 해당 페이지에서 끝 번호 
		int endNo = (page * rowsize);
		
		// 해당 페이지에서 시작 블럭
		int startBlock = (((page - 1) / block) * block) + 1;
		
		// 해당 페이지에서 마지막 블럭
		int endBlock = (((page - 1) / block) * block) + block;
		
		
		BoardDAO dao = BoardDAO.getInstance();
		
		totalRecord = dao.getBoardCount();
		// 전체 게시물의 수를 한 페이지당 보여질 게시물의 수로 나누어 주어야 함
		// 이 과정을 거치면 전체 페이지 수가 나오게 됨
		// 이 때, 전체 페이지 수가 나올 때 나머지가 있으면 무조건 전체 페이지 수를 하나 올려 주어야 함
		allPage = (int)Math.ceil((totalRecord / (double)rowsize));
		
		if(endBlock > allPage) { // 페이지수에 맞춰 마지막 블럭 수 제한
			endBlock = allPage;
		}
		
		// 현재 페이지에 해당하는 게시물을 가져오는 메서드 호출
		List<BoardDTO> pageList = dao.getBoardList(page, rowsize);
		// 지금까지 페이징 처리 시 작업했던 모든 데이터들을  view page로 이동을 시키자
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
		
		out.println("page=" + page + "&" +
	            "rowsize=" + rowsize + "&" +
	            "block=" + block + "&" +
	            "totalRecord=" + totalRecord + "&" +
	            "allPage=" + allPage + "&" +
	            "startNo=" + startNo + "&" +
	            "endNo=" + endNo + "&" +
	            "startBlock=" + startBlock + "&" +
	            "endBlock=" + endBlock
	            );
		
		return null;
	}

}
