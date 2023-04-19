<%@page import="java.io.PrintWriter"%>
<%@page import="com.matching.model.MatchingDTO"%>
<%@ page import="com.matching.model.MatchingDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	
	String matched = request.getParameter("matched");
	//String accept = request.getParameter("accept");
	String game_name = request.getParameter("gamename").trim();	
	String tier = request.getParameter("tier").trim();
	String matching_user_id = request.getParameter("id").trim();
	
	MatchingDAO dao = MatchingDAO.getInstance();
	
	MatchingDTO dto = new MatchingDTO();
	
	dto.setMatched(matched);  
	//dto.setAccept(accept);
	dto.setGame_name(game_name);
	dto.setTier(tier);
	dto.setMatching_user_id(matching_user_id);
	
	int res = dao.matchingcondition(matched, game_name, tier, matching_user_id, dto);
	
	PrintWriter out1 = response.getWriter();
	// ajax에게 응답
	out1.println(res);




%>