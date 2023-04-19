package com.matching.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.matching.model.MatchingDAO;
import com.matching.model.MatchingDTO;
import com.member.model.MemberDAO;
import com.member.model.MemberDTO;
import com.mysql.cj.Session;
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
		String match_DiscordID = 
			request.getParameter("DiscordID").trim();
		String match_KakaoID = 
				request.getParameter("KakaoID").trim();
	
		MatchingDTO dto = new MatchingDTO();
		
		dto.setGame_name(match_gamename);
		dto.setTier(match_tier);
		dto.setDiscord_nikname(match_DiscordID);
		dto.setKakao_id(match_KakaoID);
		
		MatchingDAO dao = MatchingDAO.getInstance();
		
		

		//  회원 아이디, 닉네임
		String member_id = request.getParameter("id").trim();
		String nickname = request.getParameter("nickname").trim();
		
		MemberDTO mdto = new MemberDTO();
		
		mdto.setMember_id(member_id);
		mdto.setMember_nickname(nickname);
		
		int check = dao.insertMatching(dto, mdto);
		
		
		
		// matching 세션
		MatchingDTO match = dao.contentMatching(member_id);
		
		HttpSession session = request.getSession();
		session.setAttribute("match_gamename", match_gamename);
		session.setAttribute("match_tier", match_tier);
		
		session.setAttribute("Match", match);
		
		// PrintWriter out = response.getWriter();
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		
		forward.setPath("matching/matchloading.jsp");
		
		return forward;
		
	}

}
