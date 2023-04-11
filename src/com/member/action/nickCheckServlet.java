package com.member.action;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;

/**
 * Servlet implementation class nickCheckServlet
 */
@WebServlet("/nicknameCheck.do")
public class nickCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public nickCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 파라미터로 넘어온 아이디가 customer 테이블에 존재하는 아이디인지 여부를 확인하는 비지니스 로직.
		response.setContentType("text/html; charset=UTF-8");
		
		String nickname = request.getParameter("name");
		
		MemberDAO dao = MemberDAO.getInstance();
		String res = dao.nickCheck(nickname);
		
		// 아이디 중복 체크 결과를 클라이언트(Ajax)로 전송
//		PrintWriter out = response.getWriter();
		response.getWriter().write(res);
	}

}
