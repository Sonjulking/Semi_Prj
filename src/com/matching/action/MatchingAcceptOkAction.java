package com.matching.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.matching.model.MatchingDAO;
import com.matching.model.MatchingDTO;
import com.project.controller.Action;
import com.project.controller.ActionForward;

public class MatchingAcceptOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, MessagingException, Exception {

		String matching_user_id = request.getParameter("id").trim();
		String matched = request.getParameter("matched");
		String game_name = request.getParameter("gamename").trim();
		String tier = request.getParameter("tier").trim();

		// 아이디 값 확인 출력
		System.out.println(matching_user_id);

		MatchingDAO dao = MatchingDAO.getInstance();

		MatchingDTO dto = new MatchingDTO();

		dto.setMatching_user_id(matching_user_id);
		dto.setMatched(matched);
		dto.setGame_name(game_name);
		dto.setTier(tier);

		int res = dao.MachingAccept(matching_user_id, matched, game_name, tier);
		
		System.out.println("Acceept res값 >>> " + res);

		ActionForward forward = new ActionForward();

		MatchingDTO opponent = dao.opponentContent(matching_user_id, matched, game_name, tier);
		
		request.setAttribute("Op", opponent);
		
		
		if (res == 1) {

			forward.setRedirect(false);

			forward.setPath("./matching/userprofile.jsp");

		} else {
			PrintWriter out = response.getWriter();

			out.println("<script>");
			out.println("alert('상대가 수락안함')");
			out.println("location.href='./matching/acceptCancel.jsp'");
			out.println("</script>");
		}

		return forward;
	}

}
