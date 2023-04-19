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
 * Servlet implementation class idCheckServlet
 */
@WebServlet("/emailCheck.do")
public class emailCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public emailCheckServlet() {
		// TODO 자동 생성된 생성자 스텁
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");

		String userEmail = request.getParameter("email");
		MemberDAO dao = MemberDAO.getInstance();
		String res = dao.checkEmail(userEmail);
		System.out.println("email >>> " + res);
//		PrintWriter out = response.getWriter();
		response.getWriter().write(res);
	}

}
