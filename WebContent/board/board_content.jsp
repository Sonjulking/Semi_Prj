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
<script src="https://code.jquery.com/jquery-3.6.3.js"></script>
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
		<c:set var="dto" value="${content }"/>
		
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

						<td> <img src="img/thumbup.png" width="30" height="30" id="thumbs" onclick="thumbsClick()">${dto.getBoard_thumbs() }</td>

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
		
		<input type="button" value="글 수정" onclick="location.href='board_modify.do?no=${dto.getBoard_index() }&page=${Page }&type=${dto.getBoard_type() }'">&nbsp;&nbsp;
		<input type="button" value="글 삭제" onclick="if(confirm('정말로 삭제하시겠습니까?')) {
														location.href='board_delete.do?no=${dto.getBoard_index() }&page=${Page }&type=${dto.getBoard_type() }'
													}else { retrun; }">&nbsp;&nbsp;
		<input type="button" value="전체목록" onclick="location.href='board_list.do?type=${dto.getBoard_type() }'">
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
		    <div class="list">
		      
		    </div>
    	</div>
	</div>
	

<script type="text/javascript">

	$(async function() {
		
		// ajax에서 동일하게 사용되는 속성 설정
		$.ajaxSetup({
			// ajax에서 한글 깨짐 문제 해결
			contentType : "application/x-www-form-urlencoded;charset=UTF-8",
			type : "post"
		});
		
		
		// BOARD 테이블의 전체 데이터를 가져오는 함수.
		async function getList() {
			
			await $.ajax({
				url : "reply_list.do",
				data : {no : ${dto.getBoard_index() },
					type : "${dto.getBoard_type()}"
					},
				datatype : "xml", 
				success : function(data) {
					
					$(".list table").remove();
					
					let table = "";
					
					$(data).find("reply").each(function() {
						
						table += "<table border='1' cellspacing='0'>";
						table += "<tr>";
						table += "<td>"+$(this).find("comment_date").text()+"</td>"
						table += "<td>"+$(this).find("comment_writer_nickname").text()+"</td>";
						table += "<td> <input class='cont' value='"+$(this).find("comment_cont").text()+"'> </td>";
						table += "<td> <input type='button' class='modify' value='수정완료' onclick='modi("+$(this).find("comment_index").text()+")'> </td>";
						table += "<td> <input type='button' class='delete' value='삭제' onclick='del("+$(this).find("comment_index").text()+","+$(this).find("comment_writer_id").text()+")'> </td>";
						table += "</tr>";
						table += "</table>";
						
					});
					
					$(".list").append(table);
				},
				
				error : function() {
					alert("데이터 통신 오류입니다!!!.~~~");
				}
			});
			
		}  // getList() 함수 end
		
		async function thumbsCount() {
			await $.ajax({
				url: "board_thumbs_count.do",
				data: {
					no : ${dto.getBoard_index()},
					type : "${dto.getBoard_type()}"
				},
				success: function(count) {
					$(".thumbs_count").html(count);
				},
				error: function() {
					alert("데이터 통신 오류입니다!");
				}
			});
		}
		
		await getList();
		await thumbsCount();
		
	}); // onload end //////////////////////////////////////////////////
	
	function thumbsClick() {
		$.ajax({
			url : "board_thumbs.do",
			datatype: "text",
			data : {
				no : ${dto.getBoard_index() },
				id : "${member_id}",
				board_id : "${dto.getBoard_writer_id() }",
				type : "${dto.getBoard_type()}"
			},
			success : function(data) {
				$("#thumbs").html(data);
				thumbsCount();
			},
			error : function() {
				alert('데이터 통신 오류입니다.');
			}
		});
	}
	
	
	
	function modi(index) {
		$.ajax({
			type : "post",
			url : "reply_modify.do",
			data : {
				reply_index : index,
				member_id : "${member_id }",
				comment_cont : $(".modify").val(),
				type : "${dto.getBoard_type()}"
			},
			datatype : "text",
			success : function(data) {
				if(data > 0) {
					alert("댓글 수정 완료");
					getlist();
				}else {
					alert("댓글수정 실패");
				}
			},
			error : function() {
				alert("데이터 통신 오류입니다!!!.~~~");
			}
		});
	}
	
	
	// 댓글 작성 버튼을 눌렀을 때 DB에 댓글이 저장.
	$("#replyBtn").on("click", function() {
		
		$.ajax({
			type : "post",
			url : "reply_insert_ok.do",
			data : {	
					  writer_id : "${m_dto.getMember_id() }",
					  writer_nickname : "${m_dto.getMember_nickname() }",
				      cont : $("#re_content").val(),
				      bno : ${dto.getBoard_index() },
				      type : "${dto.getBoard_type()}"
					},
			datatype : "text",
			success : function(data) {
				if(data > 0) {
					alert("댓글 작성 완료!!!");
					
					getList();
					
					$("#re_content").val("");  
					
				}else {
					alert("댓글 작성이 실패 하였습니다.~~~");
				}
			},
			
			error : function() {
				alert("데이터 통신 오류입니다.~~~");
			}
		});
	});
	
	
	
</script>  


	 <jsp:include page="../include/footer.jsp"></jsp:include>


 
</body>
</html>