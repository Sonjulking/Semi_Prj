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

public class FindPwdOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, MessagingException, Exception {

		//
		String userId = request.getParameter("userId");
		String tempPwd = request.getParameter("tempPwd").trim();
		String modify_pwd1 = request.getParameter("modify_pwd1").trim();

		PrintWriter out = response.getWriter();

		System.out.println(userId);
		System.out.println(tempPwd);
		System.out.println(modify_pwd1);

		MemberDAO dao = MemberDAO.getInstance();
		System.out.println(tempPwd.equals(modify_pwd1));
		if (tempPwd.equals(modify_pwd1)) {
			dao.updatePwd(tempPwd, userId);
			out.println("<script>");
			out.println("alert('임시 비밀번호로 로그인해주세요.')");
			out.println("location.href='member/myPage.jsp'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('임시 비번 틀림')");
			out.println("history.back()");
			out.println("</script>");
		}

		return null;
	}

}
