<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>

	<div align="center">

		<%-- enctype : 파일을 업로드하기 위한 속성 --%>
		<hr width="50%" color="marmoon">
		<h3>아이디 찾기</h3>
		<h4>회원가입 시 입력한 이메일 주소를 입력해 주세요</h4>
		<hr width="50%" color="marmoon">
		<form method="post" action="<%=request.getContextPath()%>/find_id.do">
			
			<table border="1">
				<tr>
					<th>이메일</th>
					<td><input type="text" name="id_find"></td>
				</tr>
				<tr>
					<td colspan="4" align="center"><input type="submit"
						value="아이디 찾기">&nbsp;&nbsp; <input type="reset"
						value="다시작성"></td>
				</tr>
			</table>
		</form>
	</div>
	<script type="text/javascript" src="../js/join.js"></script>

</body>

</html>