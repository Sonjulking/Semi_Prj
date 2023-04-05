package com.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;
import com.member.model.MemberDTO;
import com.project.controller.Action;
import com.project.controller.ActionForward;

public class MyPageOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String member_id = request.getParameter("id").trim();
		String member_pwd = request.getParameter("db_pwd").trim();
		String member_nickname = request.getParameter("nickname").trim();
//		String member_email = request.getParameter("email").trim();
//		String member_phone = request.getParameter("phone").trim();
		String prefer_lol = request.getParameter("lol");
		String prefer_battle_ground = request.getParameter("battle_ground");
		String prefer_overwatch = request.getParameter("overwatch");

		String modify_pwd1 = request.getParameter("modify_pwd1").trim();
//		String modify_pwd2 = request.getParameter("modify_pwd2").trim();
		String curr_pwd = request.getParameter("curr_pwd").trim();
		String db_pwd = request.getParameter("db_pwd").trim();

		MemberDTO dto = new MemberDTO();
		dto.setMember_id(member_id);
		dto.setMember_pwd(member_pwd);
		dto.setMember_nickname(member_nickname);
//		dto.setMember_email(member_email);
//		dto.setPhone(member_phone);
		dto.setPrefer_lol(prefer_lol);
		dto.setPrefer_battle_ground(prefer_battle_ground);
		dto.setPrefer_overwatch(prefer_overwatch);

		MemberDAO dao = MemberDAO.getInstance();

		PrintWriter out = response.getWriter();

		int res = dao.updateMypage(dto, curr_pwd, modify_pwd1);

		if (res > 0) {
			out.println("<script>");
			out.println("alert('회원 수정 성공!!!')");
			out.println("history.back()");
			out.println("</script>");
		} else if (res == -1) {
			out.println("<script>");
			out.println("alert('회원 비번 틀림')");
			out.println("history.back()");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('수정실패')");
			out.println("history.back()");
			out.println("</script>");
		}
		return null;
	}

}
