<%@page import="java.io.PrintWriter"%>
<%@page import="com.matching.model.MatchingDTO"%>
<%@page import="com.matching.model.MatchingDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%

	String matching_user_id = request.getParameter("id").trim();
	
	MatchingDAO dao = MatchingDAO.getInstance();
	
	MatchingDTO dto = new MatchingDTO();
	
	dto.setMatching_user_id(matching_user_id);
	
	int res = dao.MachingAccept(matching_user_id);
	
	PrintWriter out1 = response.getWriter();
	// ajax에게 응답
	out1.println(res);

%>