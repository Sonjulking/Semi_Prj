<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="loginCheck" value="0" />
<c:if test="${!empty sessionScope.LoginCheck}">
	<c:set var="loginCheck" value="${sessionScope.LoginCheck}" />
</c:if>

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


</head>

<body>
	<%@ include file="include/header.jsp"%>

	<div class="main_wrap">

		<img class="ruru" src="img/ruru.png" alt="" title="귀여운 루루"> <img
			class="nar" src="img/nar.png" alt="" title="포악한 나르"> <img
			class="timo" src="img/timo.png" alt="" title="혐모"> <img
			class="youme" src="img/youme.png" alt="" title="버스충 유미">

		<div class="pop_post_wrap main_box1">
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

		<div class="matching_wrap main_box2">
			<p class="box_text">실시간 매칭</p>
			<div>
				<!-- <img class="matching-img" src="../WebContent/img/1.png" /> -->
			</div>
			<input type="button" value="매칭하기" class="matching-btn" />
		</div>

	</div>


	<%@ include file="include/footer.jsp"%>

</body>
</html>