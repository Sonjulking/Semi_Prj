package com.admin.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.admin.model.AdminDAO;
import com.member.model.MemberDAO;
import com.member.model.MemberDTO;
import com.project.controller.Action;
import com.project.controller.ActionForward;

public class AdminLoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, MessagingException, Exception {
		String admin_id = request.getParameter("adminId");
		String admin_pwd = request.getParameter("adminPwd");
		AdminDAO dao = AdminDAO.getInstance();

//		ActionForward forward = new ActionForward();
		int check = dao.loginAdmin(admin_id, admin_pwd);
		List<MemberDTO> cont = dao.memberList();

		PrintWriter out = response.getWriter();

		if (check == 1) {
			
		   HttpSession session = request.getSession();
		   session.setAttribute("LoginCheck", check); 
		   session.setAttribute("member_id", admin_id);
		   session.setAttribute("member_pwd", admin_pwd);
		   session.setAttribute("Cont", cont);
		 

			out.println("<script>");
			out.println("alert('로그인 성공!')");
			out.println("location.href='admin/admin_main.jsp'");
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
