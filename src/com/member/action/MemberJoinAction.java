package com.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;
import com.member.model.MemberDTO;
import com.project.controller.Action;
import com.project.controller.ActionForward;

public class MemberJoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//
		String member_id = request.getParameter("id");
		String member_pwd = request.getParameter("pwd");
		String member_nickname = request.getParameter("name");
		String member_email = request.getParameter("email");
		String member_phone = request.getParameter("phone");
		String prefer_lol = request.getParameter("lol");
		String prefer_battle_ground = request.getParameter("battle_ground");
		String prefer_overwatch = request.getParameter("overwatch");

		MemberDTO dto = new MemberDTO();
		dto.setMember_id(member_id);
		dto.setMember_pwd(member_pwd);
		dto.setMember_nickname(member_nickname);
		dto.setMember_email(member_email);
		dto.setPhone(member_phone);
		dto.setPrefer_lol(prefer_lol);
		dto.setPrefer_battle_ground(prefer_battle_ground);
		dto.setPrefer_overwatch(prefer_overwatch);

		MemberDAO dao = MemberDAO.getInstance();

		ActionForward forward = new ActionForward();
		int check = dao.memberInsert(dto);

		PrintWriter out = response.getWriter();

		if (check > 0) {
			out.println("<script>");
			out.println("alert('회원가입 성공!')");
			out.println("</script>");
		} else {

			out.println("<script>");
			out.println("alert('회원가입 실패~')");
			out.println("</script>");
		}
		return forward;

	}

}
