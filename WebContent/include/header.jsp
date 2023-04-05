<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<header>
		<div class="main_header_wrap">
			<span id="main_logo_text"><a id="logo_link" href="main.jsp">겜만추</a></span>
			<!-- <img id="logo" src="../WebContent/img/thumbup.png" alt=""> -->
			<div class="login_wrap">

				<c:if test="${loginCheck == 0 }">
					<span class="Login"><a href="member/login.jsp">Login</a></span>
					<span class="Join"> / <a href="member/join.jsp">회원가입</a></span>
				</c:if>

				<c:if test="${loginCheck > 0 }">
					<span class="Login"><a href="member/logout.jsp">Logout</a></span>
					<span class="Join"> / <a href="<%=request.getContextPath()%>/myPage.do?loginId=${member_id}">MyPage</a></span>
				</c:if>
			</div>
		</div>
	</header>

	<nav>
		<ul class="navcolor">
			<li><a href="<%=request.getContextPath()%>/board_list.do">FreeBoard</a></li>
			<li><a href="">Legend</a></li>
			<li><a href="">Notice </a></li>
			<li><a href="">ETC </a></li>
		</ul>
	</nav>
</body>
</html>