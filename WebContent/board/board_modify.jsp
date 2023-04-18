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
		<div class="main_header_wrap">
			<span id="main_logo_text"><a id="logo_link" href="main.jsp">겜만추</a><i class="snes-jp-logo"></i></span>
		
			<!-- <img id="logo" src="../WebContent/img/thumbup.png" alt=""> -->
			<div class="login_wrap">

				<c:if test="${loginCheck == 0 }">
					<span class="Login"><a href="member/login.jsp" class="nes-text is-primary">Login</a></span>
					<span class="Join"> / <a href="member/join.jsp" class="nes-text is-success">Join</a></span>
 					<i class="fa fa-envelope" aria-hidden="true"></i>
				</c:if>
 
				<c:if test="${loginCheck > 0 }">
				   <c:set var="m_dto" value="${Cont }"/>
					<span class="Login"><a href="member/logout.jsp" class="nes-text is-warning">Logout</a></span>
					<span class="Join"> / <a
						href="<%=request.getContextPath()%>/myPage.do?loginId=${member_id}" class="nes-text is-error">MyPage</a></span>
				    <a href="<%=request.getContextPath()%>/chat.do"><i class="fa fa-envelope" aria-hidden="true"></i></a>
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
		
	<div align="center">
		<c:set var="dto" value="${Modify }"/>
		<hr width="50%" color="lightgray">
			<h3>${dto.getBoard_writer_nickname() }의 수정페이지</h3>
		<hr width="50%" color="lightgray">
		
		<form method="post" enctype="multipart/form-data" action="<%=request.getContextPath() %>/board_modify_ok.do?no=${dto.getBoard_index() }">
		<input type="hidden" name="num" value="${dto.getBoard_index() }">
		<input type="hidden" name="page" value="${Page }">
		
			<table border="1" cellspacing="0" width="300">
				<tr>
					<th>게시판종류</th>
					<td><input type="text" name="type" value="${dto.getBoard_type() }"></td>
				</tr>
				
				<tr>
					<th>머릿말</th>
					<td><input type="text" name="heading" value="${dto.getBoard_heading() } "></td>
				</tr>
				
				<tr>
					<th>글 작성자</th>
					<td><input type="text" name="writer_nickname" value="${dto.getBoard_writer_nickname() }" readonly></td>
				</tr>
				<tr>
					<th>글 제목</th>
					<td><input type="text" name="title" value="${dto.getBoard_title() }"></td>
				</tr>
				<tr>
					<th>글 내용</th>
					<td><textarea rows="7" cols="25" name="cont">${dto.getBoard_cont() }</textarea>
				</tr>

				<tr>
					<th>첨부파일</th>
					<td> 
						<img src="<%=request.getContextPath() %>/fileUpload${dto.getUpload_file() }" alt="등록된 파일이 없습니다." width="120" height="120" border="0"> 
						<input type="file" name="new_file">
	            	
					</td>
				</tr>
				
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="수정완료">&nbsp;
						<input type="reset" value="초기화">
					</td>
				</tr>
				
			</table>
			<br>
		</form>
	</div>
</body>
</html>