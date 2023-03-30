<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- JQuery 라이브러리 CDN -->
<script src="https://code.jquery.com/jquery-3.6.3.js"></script>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.4.1/dist/css/bootstrap.min.css" integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.4.1/dist/css/bootstrap-theme.min.css" integrity="sha384-6pzBo3FDv/PJ8r2KRkGHifhEocL+1X2rVCTTkUfGk7/0pbek5mMa1upzvWbrUbOZ" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.4.1/dist/js/bootstrap.min.js" integrity="sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd" crossorigin="anonymous"></script>

<script type="text/javascript">
	
	function check() {
		if(f.board_type.value == '') {
			alert('게시판을 선택하세요');
			return false;
		}
	}

	function check() {
		if(f.board_heading.value == '') {
			alert('말머리를 선택하세요');
			return false;
		}
	}

	$(function() {
		$("#board_type").on("change", function () {
			$(".board_heading1, .board_heading2, .board_heading3").hide();
			
			let state = $("#board_type option:selected").val();
			if(state == 'free') {
				$(".board_heading1").show();
			}else if(state == 'legend') {
				$(".board_heading2").show();
			}else if(state == 'etc') {
				$(".board_heading3").show();
			}
		});
	});
	
</script>
</head>
<body>
	<div align="center">
		<hr width="50%" color="gray">
			<h3>board 테이블 게시판 글쓰기</h3>
		<hr width="50%" color="gray">
		<br>
		
		<%-- enctype : 파일을 업로드하기 위한 속성, 값: --%>
		<form method="post" enctype="multipart/form-data" name="f" action="<%=request.getContextPath() %>/board_write_ok.do" onsubmit="return check()">
			
			<select name="board_type" id="board_type">
				<option value="">게시판선택</option>
				<option value="free">자유게시판</option>
				<option value="legend">레전드게시판</option>
				<option value="etc">ETC</option>
			</select>
			
			<select name="board_heading">
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
			</select>
			
			<input type="text" name="board_title" placeholder="제목을 입력해주세요">
		
		
				
	<!-- 썸머노트 -->
	<div class="container">
		<br>
		
		<textarea name="board_cont" id="summernote" rows="15" cols="30" class="form-control"></textarea>
    
	</div>
	
	<!-- include summernote css/js -->
	<!-- summernote 스타일관련 cdn -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.6/summernote.css">
	<!-- summernote 자바스크립트 cdn -->
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.6/summernote.js"></script>
	<!-- summernote 한글처리관련 cdn -->
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.6/lang/summernote-ko-KR.js"></script>
	
	<script type="text/javascript">
	
		$(function() {
			$("#summernote").summernote({
				lang: 'ko-KR',
				height: 500,
				collbacks: {
					onImageUpload: functo
				}
			});
		});
	
	
	</script>	
				
	<input type="button" value="취소" onclick="if(confirm('정말로 취소하시겠습니까?')) {
												location.href='board_list.do'
												}else {return; }">	
	<input type="submit" value="글쓰기">&nbsp;
			
				
		
		</form>
	</div>
</body>
</html>