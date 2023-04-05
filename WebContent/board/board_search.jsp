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
		<hr width="50%" color="red">
			<h3>BOARD 테이블 게시물 검색 목록 페이지</h3>
		<hr width="50%" color="red">
		<br>
		
		<%-- 검색 관련 요청 태그 --%>
		<form method="post" action="<%=request.getContextPath()%>/board_search.do">
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
		
		<table border="1" cellspacing="0" width="450">
			<tr>
				<th>No.</th> <th>제목</th> <th>글쓴이</th>
				<th>조회수</th> <th>추천수</th> <th>작성일자</th>
			</tr>
			
			<c:set var="search" value="${Search }" />
			<c:if test="${search.size() != 0 }">
				<c:forEach items="${Search }" var="dto">
					<tr>
						<td> ${dto.getBoard_index() } </td>
						<td> 
							<a href="<%=request.getContextPath()%>/board_content.do?no=${dto.getBoard_index() }&page=${Page }"> ${dto.getBoard_title() }</a> 
						</td>
						<td> ${dto.getBoard_writer_nickname() } </td>
						<td> ${dto.getBoard_hit() } </td>
						<td> ${dto.getBoard_thumbs() } </td>
						<td> ${dto.getBoard_date().substring(0, 10) } </td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		<br>
		
		<input type="button" value="전체게시물" onclick="location.href='board_list.do'">
		
	</div>

</body>
</html>