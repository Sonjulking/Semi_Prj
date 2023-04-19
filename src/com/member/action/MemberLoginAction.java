package com.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.MemberDAO;
import com.member.model.MemberDTO;
import com.project.controller.Action;
import com.project.controller.ActionForward;

public class MemberLoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String member_id = request.getParameter("id");
		String member_pwd = request.getParameter("pwd");
		MemberDAO dao = MemberDAO.getInstance();

//		ActionForward forward = new ActionForward();
		int check = dao.loginMember(member_id, member_pwd);
		MemberDTO cont = dao.contentMember(member_id);
		
		String nickname = cont.getMember_nickname();

		PrintWriter out = response.getWriter();

		if (check == 1) {

			HttpSession session = request.getSession();
			session.setAttribute("LoginCheck", check);
			session.setAttribute("member_id", member_id);
			session.setAttribute("member_pwd", member_pwd);
			session.setAttribute("Cont", cont);
			session.setAttribute("nickname", nickname);

			out.println("<script>");
			out.println("alert('로그인 성공!')");
			out.println("location.href='main.jsp'");
			out.println("</script>");

		} else if (check == 0) {

			out.println("<script>");
			out.println("alert('비밀번호 불일치~')");
			out.println("history.back()");
			out.println("</script>");

		} else {

			out.println("<script>");
			out.println("alert('아이디 없음~')");
			out.println("history.back()");
			out.println("</script>");

		}

		return null;
	}

}
