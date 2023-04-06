<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="loginCheck" value="0"/>
<c:if test="${!empty sessionScope.LoginCheck}">
    <c:set var="loginCheck" value="${sessionScope.LoginCheck}"/>
</c:if>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>

<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js" integrity="sha384-+sLIOodYLS7CIrQpBjl+C7nPvqq+FbNUBDunl/OZv93DB7Ln/533i8e/mZXLi/P+" crossorigin="anonymous"></script>

<style type="text/css">
	.pagination {
		justify-content: center;
	}
</style>

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
					<span class="Join"> / <a href="member/mypage.jsp">MyPage</a></span>
				</c:if>
			</div>
		</div>
	</header>

	<div align="center">
		<hr width="50%" color="red">
			<h3>BOARD 테이블 전체 게시물 목록 페이지</h3>
		<hr width="50%" color="red">
		<br>
		
		<table border="1" cellspacing="0" width="1000">
		
			<tr>
				<td colspan="6" align="right">
					전체 게시물 수 : ${totalRecord } 개
				</td>
			</tr>
			
			<tr>
				<th>No.</th> <th>제목</th> <th>글쓴이</th>
				<th>조회수</th> <th>추천수</th> <th>작성일자</th> 
			</tr>
			
			<c:set var="list" value="${List }"/>
			<c:if test="${!empty list }">
				<c:forEach items="${list }" var="dto">
					<tr>
						<td> ${dto.getBoard_index() } </td>
						<td> 
							<a href="<%=request.getContextPath()%>/board_content.do?no=${dto.getBoard_index() }&page=${page }">${dto.getBoard_title() }</a> 
						</td>
						<td> ${dto.getBoard_writer_nickname() } </td>
						<td> ${dto.getBoard_hit() } </td>
						<td> ${dto.getBoard_thumbs() } </td>
						<td> ${dto.getBoard_date().substring(0, 10) } </td>
					</tr>
				</c:forEach>
			</c:if>
			
			<c:if test="${empty list }">
				<tr>
					<td colspan="6" align="center">
						<h3>전체 게시물 리스트가 없습니다...</h3>
					</td>
				<tr>
			</c:if>
		</table>
		<br>
		
		<form method="post" action="<%=request.getContextPath() %>/board_search.do">
		<input type="hidden" name="page" value="${page }">
			<select name="field">
				<option value="title">제목</option>
				<option value="cont">내용</option>
				<option value="title_cont">제목+내용</option>
				<option value="writer">작성자</option>
			</select>
			
			<input type="text" name="keyword">&nbsp;&nbsp;
			<input type="submit" value="검색">
		</form>
		<br>
		
		<input type="button" value="게시글 작성" onclick="location.href='board_write.do'">
		<br>
		<br>
		
		<%-- 페이징 처리 영역 --%>
		<nav>
			<ul class="pagination">
				<li class="page-item">
					<a class="page-link" href="board_list.do?page=1">First</a>
				</li>
				<li>
					<a class="page-link" href="board_list.do?page=${page -1 }">Previous</a>
				</li>
				
				<c:forEach begin="${startBlock }" end="${endBlock }" var="i">
					<c:if test="${i == page }">
						<li class="page-item active" aria-current="page">
							<a class="page-link" href="board_list.do?page=${i }">${i }</a>
						</li>
					</c:if>
					
					<c:if test="${i != page }">
						<li class="page-item">
							<a class="page-link" href="board_list.do?page=${i }">${i }</a>
						</li>
					</c:if>
				</c:forEach>
				
				<c:if test="${endBlock < allPage }">
					<li class="page-item">
						<a class="page-link" href="board_list.do?page=${page + 1 }">Next</a>
					</li>
					
					<li class="page-item">
						<a class="page-link" href="board_list.do?page=${allPage }">End</a>
					</li>
				</c:if>
			</ul>
		</nav>
		
		
		
	</div>
	
	
</body>
</html>