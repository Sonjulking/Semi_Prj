package com.member.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;
import com.member.model.MemberDTO;
import com.project.controller.Action;
import com.project.controller.ActionForward;

public class MyPageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String curr_id = request.getParameter("loginId").trim();
		MemberDAO dao = MemberDAO.getInstance();

		MemberDTO memCont = dao.contentMember(curr_id);

		request.setAttribute("memCont", memCont); // cont라는 참조변수(주소값) 집어넣음.

		ActionForward forward = new ActionForward();

		forward.setRedirect(false);

		forward.setPath("member/myPage.jsp");

		return forward;

	}

}
