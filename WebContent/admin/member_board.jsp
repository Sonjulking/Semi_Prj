<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${!empty sessionScope.LoginCheck}">
    <c:set var="loginCheck" value="${sessionScope.LoginCheck}"/>
</c:if>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js" integrity="sha384-+sLIOodYLS7CIrQpBjl+C7nPvqq+FbNUBDunl/OZv93DB7Ln/533i8e/mZXLi/P+" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>

<style type="text/css">
	.pagination {
		justify-content: center;
	}
</style>

</head>
<body>

    <header>
		<div class="free_board_wrap">
			<span id="main_logo_text"><a href="member_board.jsp">회원 목록 게시판</a></span>
			<!-- <img id="logo" src="../WebContent/img/thumbup.png" alt=""> -->
			<div class="login_wrap">
				<c:if test="${loginCheck == 0 }">
					<span class="Login"><a href="admin_login.jsp">Login</a></span> 
				</c:if>
				
				<c:if test="${loginCheck > 0 }">
					<span class="Login"><a href="logout.jsp">Logout</a></span> 
				</c:if>
			</div>
		</div>
	</header>

	<div align="center">
		<hr width="50%" color="red">
			<h3>회원 목록 게시판</h3>
		<hr width="50%" color="red">
		<br>
		<table border="1" cellspacing="0" width="1000">
		
			<tr>
				<td colspan="14" align="right">
					전체 게시물 수 : ${totalRecord } 개
				</td>
			</tr>
			
			<tr>
				<th>No.</th> <th>프로필 사진</th> <th>아이디</th> <th>닉네임</th>
				<th>이메일</th> <th>포인트</th> <th>가입일</th>
				<th>전화번호</th> <th>선호게임1:</th> <th>선호게임2:</th>
				<th>선호게임3:</th>				 
			</tr>
			<c:set var="list" value="${List }"/>
			<c:if test="${!empty list }" >
				<c:forEach items="${list }" var="dto">
					<tr>
						<td> ${dto.getMember_index() } </td>
						<td> ${dto.getMember_profile() } </td>
						<td> ${dto.getMember_id() }</td>
						<td> ${dto.getMember_nickname() } </td>
						<td> ${dto.getMember_email() } </td>
						<td> ${dto.getPoint() } </td>
						<td> ${dto.getRegdate().substring(0, 10) } </td>
						<td> ${dto.getPhone() } </td>
						<td> ${dto.getPrefer_lol() } </td>
						<td> ${dto.getPrefer_battle_ground() } </td>
						<td> ${dto.getPrefer_overwatch() } </td>
						<td> <input type="button" value="회원 삭제하기" onclick="location.href='../delete_member.do?no=${dto.getMember_index()}&id=${dto.getMember_id() }'"> </td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty list }">
				<tr>
					<td colspan="14" align="center">
						<h3>조회된 회원이 없습니다...</h3>
					</td>
				<tr>
			</c:if>
		</table>
		<br>
		
		<%-- 페이징 처리 영역 --%>
		<nav>
			<ul class="pagination">
				<li class="page-item">
					<a class="page-link" href="${check }page=1">First</a>
				</li>
				<li>
					<a class="page-link" href="${check }page=${page -1 }">Previous</a>
				</li>
				
				<c:forEach begin="${startBlock }" end="${endBlock }" var="i">
					<c:if test="${i == page }">
						<li class="page-item active" aria-current="page">
							<a class="page-link" href="${check }page=${i }">${i }</a>
						</li>
					</c:if>
					
					<c:if test="${i != page }">
						<li class="page-item">
							<a class="page-link" href="${check }page=${i }">${i }</a>
						</li>
					</c:if>
				</c:forEach>
				
				<c:if test="${endBlock < allPage }">
					<li class="page-item">
						<a class="page-link" href="${check }page=${page + 1 }">Next</a>
					</li>
					
					<li class="page-item">
						<a class="page-link" href="${check }page=${allPage }">End</a>
					</li>
				</c:if>
			</ul>
		</nav>
	</div>

</body>
</html>