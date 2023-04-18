package com.matching.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.matching.model.MatchingDAO;
import com.member.model.MemberDTO;
import com.project.controller.Action;
import com.project.controller.ActionForward;

public class MatchingDeleteOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, MessagingException, Exception {
		// 세션으로 저장된 로그인 아이디로 DB 삭제 후 매칭 팝업창으로 돌아감

		String member_id = request.getParameter("id").trim();
		
		MatchingDAO dao = MatchingDAO.getInstance();
		
		MemberDTO mdto = new MemberDTO();
		
		mdto.setMember_id(member_id);
		
		int check = dao.deleteMatching(mdto);
		
		// PrintWriter out = response.getWriter();
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		
		forward.setPath("matching/matching.jsp");
		
		return forward;

	}

}
