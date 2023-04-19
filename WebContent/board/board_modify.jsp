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
<script src="https://code.jquery.com/jquery-3.6.3.js"></script>
<script type="text/javascript">
	$(function() {
		
		$("#board_type").on("change", function() {
			
			$(".board_heading1, .board_heading2, .board_heading3").hide();
			
			let state = $("#board_type option:selected").val();
			
			if (state == 'free') {
				$(".board_heading1").show();
			} else if (state == 'legend') {
				$(".board_heading2").show();
			} else if (state == 'etc') {
				$(".board_heading3").show();
			}
		});
	});
</script>
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
		
		<form method="post" enctype="multipart/form-data" action="<%=request.getContextPath() %>/board_modify_ok.do?no=${dto.getBoard_index() }&old_type=${old_type }">
		<input type="hidden" name="num" value="${dto.getBoard_index() }">
		<input type="hidden" name="page" value="${Page }">
		
			<table border="1" cellspacing="0" width="300">
				<tr>
					<th>게시판종류</th>
					<td>
						<select name="type" id="board_type">
						  	<c:if test="${dto.getBoard_type() == 'free'}">
						   	 	<option value="free">자유게시판</option>
						    	<option value="legend">레전드게시판</option>
						    	<option value="etc">ETC</option>
						  	</c:if>
						 	<c:if test="${dto.getBoard_type() == 'legend'}">
						 		<option value="legend">레전드게시판</option>
						    	<option value="free">자유게시판</option>
						    	<option value="etc">ETC</option>
						  </c:if>
						  	<c:if test="${dto.getBoard_type() == 'etc'}">
						  		<option value="etc">ETC</option>
						    	<option value="free">자유게시판</option>
						    	<option value="legend">레전드게시판</option>
						  	</c:if>
						</select>
					</td>
				</tr>
				
				<tr>
					<th>머릿말</th>
					<td>
						<select name="heading">
							<option class="board_heading1" value="humor" ${dto.getBoard_heading() == 'humor' ? 'selected' : ''}>유머</option>
							<option class="board_heading1" value="life" ${dto.getBoard_heading() == 'life' ? 'selected' : ''}>일상</option>
							<option class="board_heading1" value="info" ${dto.getBoard_heading() == 'info' ? 'selected' : ''}>정보</option>
							<option class="board_heading1" value="etc1" ${dto.getBoard_heading() == 'etc1' ? 'selected' : ''}>기타</option>
							
							<option class="board_heading2" value="league" ${dto.getBoard_heading() == 'league' ? 'selected' : ''}>리그오브레전드</option>
							<option class="board_heading2" value="battle" ${dto.getBoard_heading() == 'battle' ? 'selected' : ''}>배틀그라운드</option>
							<option class="board_heading2" value="over" ${dto.getBoard_heading() == 'over' ? 'selected' : ''}>오버워치2</option>
							
							<option class="board_heading3" value="police" ${dto.getBoard_heading() == 'police' ? 'selected' : ''}>신고</option>
							<option class="board_heading3" value="etc2" ${dto.getBoard_heading() == 'etc2' ? 'selected' : ''}>기타</option>
						</select>
					</td>
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