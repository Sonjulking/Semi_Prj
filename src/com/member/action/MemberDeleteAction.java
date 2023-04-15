package com.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.MemberDAO;
import com.project.controller.Action;
import com.project.controller.ActionForward;

import oracle.net.aso.f;

public class MemberDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, MessagingException, Exception {
		//
		int member_index = Integer.parseInt(request.getParameter("no"));
		String member_id = request.getParameter("loginId");
		MemberDAO dao = MemberDAO.getInstance();
//		dao.updateIndex(member_index);

		int res = dao.deleteMember(member_id, member_index);
		System.out.println("res at action >> " + res);

		PrintWriter out = response.getWriter();
		int check = 0;
		HttpSession session = request.getSession();
		session.setAttribute("LoginCheck", check);
		if (res > 0) {
			out.println("<script>");
			out.println("alert('회원 탈퇴 성공')");
			out.println("location.href='main.jsp'");
			out.println("</script>");

		} else {
			out.println("<script>");
			out.println("alert('회원 탈퇴 실패')");
			out.println("history.back()");
			out.println("</script>");

		}
		return null;
	}

}
