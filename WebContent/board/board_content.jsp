<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<c:set var="dto" value="${Cont }"/>
		<hr width="50%" color="gray">
			<h3>${dto.getBoard_title() }</h3>
		<hr width="50%" color="gray">
		<br>
		
		<c:if test="${!empty dto }">
			<div>
				<ul>
					<li>${dto.getBoard_type() }</li>
					<li>${dto.getBoard_heading() }</li>
					<li>${dto.getBoard_writer_nickname() }</li>
					<li>
						<c:if test="${!empty dto.getBoard_update }">
							${dto.getBoard_date() }
						</c:if>

						<c:if test="${!empty dto.getBoard_update }">
							${dto.getBoard_update() }
						</c:if>
					</li>
					<li>${dto.getBoard_hit() }</li>
					<li>${dto.getBoard_thumbs() }</li>
					<li>${dto.getBoard_cont() }</li>
					<li>
						<c:if test="${!empty dto.getBoard_file() }">
							<a href="<%=request.getContextPath() %>/fileUpload/${dto.getBoard_file() }" target="_blank">${dto.getBoard_file() }</a>
						</c:if>
						
						<c:if test="${empty dto.getBoard_file() }">
							
						</c:if>
					</li>
				</ul>
			</div>
		</c:if>	
		
		<%-- 데이터가 없는 경우 --%>
		<c:if test="${empty dto }">
			<span>삭제된 게시물입니다</span>
		</c:if>
		<br>
		
		<%-- 로그인 되어있는 경우하고 안되어있는 경우하고 구분해서 내일여기부터 (mysql이랑 연동하고...) --%>
		<input type="button" value="글 수정" onclick="location.href='board_modify.do?no=${dto.getBoard_index() }'">&nbsp;&nbsp;
		<input type="button" value="글 삭제" onclick="if(confirm('정말로 삭제하시겠습니까?')) {
														location.href='board_delete.do?no=${dto.getBoard_index() }'&nbsp;
													}else { retrun; }">&nbsp;&nbsp;
		<input type="button" value="전체목록" onclick="location.href='board_list.do'">
		
	</div>
</body>
</html>