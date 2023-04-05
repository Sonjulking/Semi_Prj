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
					<span class="Join"> / <a href="member/join.jsp">MyPage</a></span>
				</c:if>
			</div>
		</div>
	</header>
	
	
	<div align="center">
		<c:set var="dto" value="${Cont }"/>
		<hr width="50%" color="gray">
			<h3>${dto.getBoard_title() }</h3>
		<hr width="50%" color="gray">
		<br>
		
		<c:if test="${!empty dto }">
			<div>
				<table border="1" cellspacing="0" width="450">
					<tr>
						<th>게사판</th>
						<td>${dto.getBoard_type() }</td>
					</tr>
					<tr>
						<th>머리말</th>
						<td>${dto.getBoard_heading() }</td>
					</tr>
					<tr>
						<c:if test="${empty dto.getBoard_update() }">
							<th>등록일자</th>
							<td>${dto.getBoard_date() } </td>
						</c:if>

						<c:if test="${!empty dto.getBoard_update() }">
							<th>수정일자</th>
							<td>${dto.getBoard_update() } </td>
						</c:if>
					</tr>
					
					<tr>
						<th>조회수</th>
						<td>${dto.getBoard_hit() }</td>
					</tr>	

					<tr>
						<th>추천수</th>
						<td>${dto.getBoard_thumbs() }</td>
					</tr>
					
					<tr>
						<th>글내용</th>
						<td>${dto.getBoard_cont() }</td>
					</tr>
					
					<tr>
						<th>첨부파일</th>
						<c:if test="${!empty dto.getUpload_file() }">
							<td><a href="<%=request.getContextPath() %>/fileUpload/${dto.getUpload_file() }" target="_blank">${dto.getUpload_file() }</a></td>
						</c:if>
						
						<c:if test="${empty dto.getUpload_file() }">
							<td> </td>
						</c:if>
					</tr>
					
					
					
				</table>
			</div>
		</c:if>	
		
		<%-- 데이터가 없는 경우 --%>
		<c:if test="${empty dto }">
			<span>삭제된 게시물입니다</span>
		</c:if>
		<br>
		
		<%-- 로그인 되어있는 경우하고 안되어있는 경우하고 구분해서 내일여기부터 (mysql이랑 연동하고...) --%>
		<input type="button" value="글 수정" onclick="location.href='board_modify.do?no=${dto.getBoard_index() }&page=${Page }'">&nbsp;&nbsp;
		<input type="button" value="글 삭제" onclick="if(confirm('정말로 삭제하시겠습니까?')) {
														location.href='board_delete.do?no=${dto.getBoard_index() }&page=${Page }'
													}else { retrun; }">&nbsp;&nbsp;
		<input type="button" value="전체목록" onclick="location.href='board_list.do'">
		<br>
		<br>
		
		<%-- 댓글 폼 --%>
		<div>
	    	<table cellspacing="0" width="400">
	    	   <tr>
	    	      <th>댓글내용</th>
	    	      <td> 
	    	      	<textarea rows="5" cols="40" name="re_content" id="re_content"> </textarea>
	    	      </td>
	    	   </tr>
	    	   
	    	   <tr>
	    	      <td colspan="2" align="right">
	    	         <input type="button" id="replyBtn" value="댓글작성">
	    	      </td>
	    	   </tr>
	    	</table>
	    	<br>
	   		
	   		<h3>댓글 목록</h3>
		    <div>
		       <table border="1" cellspacing="0" class="list" cellspacing="0" width="400">
		          <tr>
		             <td colspan="2">작성자</td>
		          </tr>
		          <tr>
		             <td>댓글내용</td> <td>작성일자</td>
		          </tr>
		       </table>
		    </div>
    	</div>
	</div>
	

<script type="text/javascript">

	$(function() {
		
		// ajax에서 동일하게 사용되는 속성 설정
		$.ajaxSetup({
			// ajax에서 한글 깨짐 문제 해결
			ContentType : "application/x-www-form-urlencoded;charset=UTF-8",
			type : "post"
		});
		
		// TBL_BOARD 테이블의 전체 데이터를 가져오는 함수.
		function getList() {
			
			$.ajax({
				url : "reply_list.do",
				data : {no : ${dto.getBoard_index() } },
				datatype : "xml", 
				success : function(data) {
					// 테이블 태그의 타이틀 태그를 제외한
					// 나머지 댓글 목록을 지우는 작업.
					$(".list tr:gt(1)").remove();
					
					let table = "";
					
					$(data).find("reply").each(function() {
						table += "<tr>";
						table += "<td colspan='2'>"+$(this).find("comment_writer_nickname").text()+"</td>";
						table += "</tr>";
						
						table += "<tr>";
						table += "<td>"+$(this).find("board_comment_index").text()+"</td>";
						table += "<td>"+$(this).find("commemt_date").text()+"</td>";
						table += "</tr>";
						
						table += "<tr>";
						table += "<td colspan='2'>&nbsp;</td>";
						table += "</tr>";
					});
					
					$(".list tr:eq(1)").after(table);
				},
				
				error : function() {
					alert("데이터 통신 오류입니다.~~~");
				}
			});
			
		}  // getList() 함수 end
		
		
		// 댓글 작성 버튼을 눌렀을 때 DB에 댓글이 저장.
		$("#replyBtn").on("click", function() {
			
			$.ajax({
				url : "/project/reply_insert_ok.do",
				data : {
						  writer : $("#re_writer").val(),
					      cont : $("#re_content").val(),
					      bno : ${dto.getBno() }
						},
				datatype : "text",
				success : function(data) {
					if(data > 0) {
						alert("댓글 작성 완료!!!");
						
						// 댓글 작성 후 다시 전체 댓글 리스트를
						// 화면에 뿌려주면 됨.
						getList();
						
						// input 태그에 입력된 내용 지우기.
						$("input[type=text]").each(function() {
							$(this).val("");  // 입력된 값 지우기.
						});
					}else {
						alert("댓글 작성이 실패 하였습니다.~~~");
					}
				},
				
				error : function() {
					alert("데이터 통신 오류입니다.~~~");
				}
			});
		});
		
		// 제이쿼리 실행 시마다 전체 댓글 목록 화면에 출력이 되어야 함.
		getList();
		
	});




</script>  


</body>
</html>