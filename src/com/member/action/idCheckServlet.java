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
@WebServlet("/idCheck.do")
public class idCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public idCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("application/json");
		
		String userId = request.getParameter("id");
		MemberDAO dao = MemberDAO.getInstance();
		String str = dao.checkMemberId(userId);
		String check = "Y";
		response.getWriter().append(str.equals(check) ? "true" : "false");
	}

}
