<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<c:set var="loginCheck" value="0"/>
	<c:if test="${!empty sessionScope.LoginCheck }">
    <c:set var="loginCheck" value="${sessionScope.LoginCheck }"/>
    </c:if>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<header>
		<div class="free_board_wrap">
			<span id="main_logo_text"><a href="free_board.jsp">자유게시판</a></span>
			<!-- <img id="logo" src="../WebContent/img/thumbup.png" alt=""> -->
			<div class="login_wrap">
				<c:if test="${loginCheck == 0 }">
					<span class="Login"><a href="member/login.jsp">Login</a></span> 
					<span class="Join"> / <a href="member/join.jsp">회원가입</a></span>
					
				</c:if>
				
				<c:if test="${loginCheck > 0 }">
					<span class="Login"><a href="member/login.jsp">Logout</a></span> 
					<span class="Join"> / <a href="member/join.jsp">MyPage</a></span>
					
					<c:set var="dto" value="${sessionScope.Cont }"/>
				</c:if>
			</div>
		</div>
	</header>
</body>
</html>