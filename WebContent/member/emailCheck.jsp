<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<form method="post" enctype="multipart/form-data"
			action="<%=request.getContextPath()%>/insert_member.do"
			class="joinForm">
			<input type="hidden" name="profile_img" value="${Member_Profile}">
			<input type="hidden" id="username" name="id" value="${ID}"> <input
				type="hidden" id="password1" name="pwd" value="${Member_Pwd}">
			<input type="hidden" id="name" name="name" value="${Member_Nickname}">
			<input type="hidden" id="email" name="email" value="${Member_Email}">
			<input type="hidden" id="phone" name="phone" value="${Member_Phone}">
			<input type="hidden" name="lol" value="${Prefer_lol}"> <input
				type="hidden" name="battle_ground" value="${Prefer_battle_ground}">
			<input type="hidden" name="overwatch" value="${ Prefer_overwatch}">
			<input type="hidden" name="email_key" value="${AuthenticationKey }">


			<input type="text" name="temp_key">

			<button id="signup-btn" class="signup submit-btn" type="submit"
				name="join">임시키 제출</button>
		</form>
	</div>
	<script type="text/javascript"
		src="https://code.jquery.com/jquery-3.6.1.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/preventClick.js"></script>
</body>
</html>