<%@page import="com.member.model.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	String userId = request.getParameter("paramId").trim();
	MemberDAO dao = MemberDAO.getInstance();
	
	String res = dao.checkMemberId(userId);
	
	// ajax에게 응답을 해주면 됨.
	out.println(res);

%>