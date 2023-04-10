package com.matching.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.matching.model.MatchingDAO;
import com.matching.model.MatchingDTO;
import com.project.controller.Action;
import com.project.controller.ActionForward;

public class MatchingOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 매칭 데이터 입력창에서 넘어온 데이터를 DB 저장시키는 로직.
		
			String match_gamename = 
				request.getParameter("gamename").trim();
			String match_tier = 
				request.getParameter("tier").trim();
			String match_DiscortID = 
				request.getParameter("DiscordID").trim();
			String match_KakaoID = 
					request.getParameter("KakaoID").trim();
		
			MatchingDTO dto = new MatchingDTO();
			
			dto.setGame_name(match_gamename);
			dto.setTier(match_tier);
			dto.setDiscord_nikname(match_DiscortID);
			dto.setKakao_id(match_KakaoID);
			
			MatchingDAO dao = MatchingDAO.getInstance();
			
			int check = dao.insertMatching(dto);
			
			PrintWriter out = response.getWriter();
			
			ActionForward forward = new ActionForward();
			
			forward.setRedirect(false);
			
			forward.setPath("matching/matchloading.jsp");
			
			return forward;
		
	}

}
