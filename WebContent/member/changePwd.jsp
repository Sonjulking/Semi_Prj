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
		<h3>임시 비밀번호 인증 페이지</h3>
		<h4>받은메일함이나 스팸메일함으로 받은 임시 비밀번호를 입력해 주세요</h4>
		<hr width="50%" color="marmoon">
		<form method="post" action="<%=request.getContextPath()%>/find_pwd_ok.do">
			<input type="hidden" name="tempPwd" value="${TempPwd }">
			<input type="hidden" name="userId" value="<%=session.getAttribute("UserId") %>">
			
			<table border="1">
				<tr>
					<th>임시 비밀번호 입력</th>
					<td><input type="password" name="modify_pwd1"></td>
				</tr>
				<tr>
					<td colspan="4" align="center"><input type="submit"
						value="임시 비밀번호 제출">&nbsp;&nbsp; <input type="reset"
						value="다시작성"></td>
				</tr>
			</table>
		</form>
	</div>
	<script type="text/javascript" src="../js/join.js"></script>

</body>

</html>