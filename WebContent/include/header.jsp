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
			<span id="main_logo_text"><a id="logo_link" href="main.jsp">겜만추</a><i class="snes-jp-logo"></i></span>
		
			<!-- <img id="logo" src="../WebContent/img/thumbup.png" alt=""> -->
			<div class="login_wrap">

				<c:if test="${loginCheck == 0 }">
					<span class="Login"><a href="member/login.jsp" class="nes-text is-primary">Login</a></span>
					<span class="Join"> / <a href="member/join.jsp" class="nes-text is-success">Join</a></span>
				</c:if>
 
				<c:if test="${loginCheck > 0 }">
					<span class="Login"><a href="member/logout.jsp" class="nes-text is-warning">Logout</a></span>
					<span class="Join"> / <a
						href="<%=request.getContextPath()%>/myPage.do?loginId=${member_id}" class="nes-text is-error">MyPage</a></span>
				</c:if>
			</div>
		</div>
	</header>

	<nav>
		<ul class="navcolor nes-container">
			<li><a href="<%=request.getContextPath()%>/board_list.do?type=free"
				class="nes-text is-primary">FreeBoard</a></li>
			<li><a href="<%=request.getContextPath()%>/board_list.do?type=legend" class="nes-text is-success">Legend</a></li>
			<li><a href="<%=request.getContextPath()%>/board_list.do?type=notice" class="nes-text is-warning">Notice </a></li>
			<li><a href="<%=request.getContextPath()%>/board_list.do?type=etc" class="nes-text is-error">ETC </a></li>
		</ul>
	</nav>
</body>
</html>