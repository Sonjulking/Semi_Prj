<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
int loginCheck = 0;

//Session을 받을때는 값이 null로 올때를 생각해서 조건문을 사용한다.
if (session.getAttribute("LoginCheck") != null) {
	//세션의 값을 가져오기
	loginCheck = (int) session.getAttribute("LoginCheck");
}
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>겜만추</title>

<!-- fontawesome cdn -->
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<link rel="stylesheet" href="css/main.css" />
<link rel="stylesheet" href="css/nav.css " />

<style>
body {
	background-image: url(img/BackgroundLOL.jpg);
	background-repeat: no-repeat;
	background-size: 120%;
}
</style>
</head>

<body>
	<header>
		<div class="main_header_wrap">
			<span id="main_logo_text"><a id="logo_link" href="main.jsp">겜만추</a></span>
			<!-- <img id="logo" src="../WebContent/img/thumbup.png" alt=""> -->
			<div class="login_wrap">
				<%
				if (loginCheck == 0) {
				%>
				<span class="Login"><a href="member/login.jsp">Login</a></span> <span
					class="Join"> / <a href="member/join.jsp">회원가입</a></span>

				<%
				} else if (loginCheck > 0) {
				%>

				<span class="Login"><a href="member/login.jsp">Logout</a></span> <span
					class="Join"> / <a href="member/join.jsp">MyPage</a></span>

				<%
				}
				;
				%>
			</div>
		</div>
	</header>

	<nav>
		<ul class="navcolor">
			<li><a href="<%=request.getContextPath()%>/board_list.do">Free
					Board</a></li>
			<li><a href="">Legend</a></li>
			<li><a href="">Notice </a></li>
			<li><a href="">ETC </a></li>
		</ul>
	</nav>

	<div class="main_wrap">
		<div class="pop_post_wrap main_box">
			<p class="box_text">실시간 인기글</p>
			<a href="">
				<div class="item">
					<div class="ranking">1</div>
					<img src="" class="sumimg" />
					<div class="title_con">
						<div class="title">야스오 강의</div>
						<div class="date_writer_con">
							<div class="date">2023-09-16</div>
							<div class="writer">실버 판테온</div>
						</div>
					</div>
					<div class="comment">[65]</div>
				</div>
			</a> <a href="">
				<div class="item">
					<div class="ranking">2</div>
					<img src="" class="sumimg" />
					<div class="title_con">
						<div class="title">야스오 강의</div>
						<div class="date_writer_con">
							<div class="date">2023-09-16</div>
							<div class="writer">실버 판테온</div>
						</div>
					</div>
					<div class="comment">[65]</div>
				</div>
			</a> <a href="">
				<div class="item">
					<div class="ranking">3</div>
					<img src="" class="sumimg" />
					<div class="title_con">
						<div class="title">야스오 강의</div>
						<div class="date_writer_con">
							<div class="date">2023-09-16</div>
							<div class="writer">실버 판테온</div>
						</div>
					</div>
					<div class="comment">[65]</div>
				</div>
			</a> <a href="">
				<div class="item">
					<div class="ranking">4</div>
					<img src="" class="sumimg" />
					<div class="title_con">
						<div class="title">야스오 강의</div>
						<div class="date_writer_con">
							<div class="date">2023-09-16</div>
							<div class="writer">실버 판테온</div>
						</div>
					</div>
					<div class="comment">[65]</div>
				</div>
			</a> <a href="">
				<div class="item">
					<div class="ranking">5</div>
					<img src="" class="sumimg" />
					<div class="title_con">
						<div class="title">야스오 강의</div>
						<div class="date_writer_con">
							<div class="date">2023-09-16</div>
							<div class="writer">실버 판테온</div>
						</div>
					</div>
					<div class="comment">[65]</div>
				</div>
			</a>
		</div>

		<div class="matching_wrap main_box">
			<p class="box_text">실시간 매칭</p>
			<div>
				<img class="matching-img" src="img/1.png" />
			</div>
			<input type="button" value="매칭하기" class="matching-btn" />
		</div>
	</div>

	<footer id="foot">
		<p>제작 : 석하림</p>
		<address>E-Mail : GMC@gmail.com</address>
		<p>Copyright &copy; All right reserved</p>
	</footer>

	<script src=""></script>

</body>
</html>