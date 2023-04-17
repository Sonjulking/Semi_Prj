package com.matching.action;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.matching.model.MatchingDAO;
import com.matching.model.MatchingDTO;
import com.project.controller.Action;
import com.project.controller.ActionForward;

public class MatchingAcceptOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, MessagingException, Exception {
		// TODO Auto-generated method stub
		
		String matching_user_id = request.getParameter("id").trim();
		
		MatchingDAO dao = MatchingDAO.getInstance();
		
		MatchingDTO dto = new MatchingDTO();
		
		dto.setMatching_user_id(matching_user_id);
		
		int res = dao.MachingAccept(matching_user_id);
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		
		forward.setPath("matching/userprofile.jsp");
		
		return forward;
		
	}

}
