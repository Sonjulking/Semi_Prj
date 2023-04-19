<%@page import="org.apache.tomcat.util.net.AprEndpoint.Sendfile"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="loginCheck" value="0" />
<c:if test="${!empty sessionScope.LoginCheck }">
	<c:set var="loginCheck" value="${sessionScope.LoginCheck }" />
</c:if>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- JQuery 라이브러리 CDN -->
<script src="https://code.jquery.com/jquery-3.6.3.js"></script>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@3.4.1/dist/css/bootstrap.min.css"
	integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@3.4.1/dist/css/bootstrap-theme.min.css"
	integrity="sha384-6pzBo3FDv/PJ8r2KRkGHifhEocL+1X2rVCTTkUfGk7/0pbek5mMa1upzvWbrUbOZ"
	crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@3.4.1/dist/js/bootstrap.min.js"
	integrity="sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd"
	crossorigin="anonymous"></script>

<script type="text/javascript">
	function check() {
		if (f.board_type.value == '') {
			alert('게시판을 선택하세요');
			return false;
		}
	}

	function check() {
		if (f.board_heading.value == '') {
			alert('말머리를 선택하세요');
			return false;
		}
	}

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

	    <%@ include file="../include/header.jsp"%>
	

	<div align="center">
		<hr width="50%" color="gray">
		<h3>board 테이블 게시판 글쓰기</h3>
		<hr width="50%" color="gray">
		<br>
		<c:set var="m_dto" value="${Cont }" />
	
		<%-- enctype : 파일을 업로드하기 위한 속성, 값: --%>
		<form method="post" enctype="multipart/form-data" name="f"
			action="<%=request.getContextPath()%>/board_write_ok.do"
			onsubmit="return check()">
			<input type="hidden" name="board_writer_id"value="${m_dto.getMember_id() }"> 
			<input type="hidden"
				name="board_writer_nickname" value="${m_dto.getMember_nickname() }">


			<select name="board_type" id="board_type">
				<option value="">게시판선택</option>
				<option value="free">자유게시판</option>
				<option value="legend">레전드게시판</option>
				<option value="etc">ETC</option>
			</select> <select name="board_heading">
				<option value="">말머리선택</option>

				<option class="board_heading1" value="humor">유머</option>
				<option class="board_heading1" value="life">일상</option>
				<option class="board_heading1" value="info">정보</option>
				<option class="board_heading1" value="etc">기타</option>

				<option class="board_heading2" value="league">리그오브레전드</option>
				<option class="board_heading2" value="battle">배틀그라운드</option>
				<option class="board_heading2" value="over">오버워치2</option>

				<option class="board_heading3" value="police">신고</option>
				<option class="board_heading3" value="etc">기타</option>
			</select> <input type="text" name="board_title" placeholder="제목을 입력해주세요">
			<br>

			<textarea rows="20" cols="150" name="board_cont"></textarea>

			<input type="file" name="upload_file"> <input type="button"
				value="취소"
				onclick="if(confirm('정말로 취소하시겠습니까?')) {
														location.href='history.back()'
														}else {return; }">
			<input type="submit" value="글쓰기">&nbsp;



		</form>
	</div>
		    <%@ include file="../include/footer.jsp"%>
	
</body>
</html>