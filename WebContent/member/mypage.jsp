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
		<c:set var="dto" value="${memCont}" />

		<%-- enctype : 파일을 업로드하기 위한 속성 --%>
		<hr width="50%" color="marmoon">
		<h3>${dto.getMember_nickname() }님의MyPage</h3>
		<hr width="50%" color="marmoon">

		<form method="post" action="<%=request.getContextPath()%>/myPageOk.do">


			<table border="1">
				<tr>
					<th>아이디</th>
					<td>${dto.getMember_id()}</td>
				</tr>
				<tr>
					<th>닉네임</th>
					<td><input type="text" name="upload_title"
						value="${dto.getMember_nickname() }"></td>
				</tr>

				<tr>
					<th>E-Mail</th>
					<td>${dto.getMember_email()}</td>
				</tr>

				<tr>
					<th>포인트</th>
					<td>${dto.getPoint()}</td>
				</tr>
				<tr>
					<th>비밀번호 변경</th>
					<td><input type="password" name="upload_pwd"></td>
				</tr>
				<tr>
					<th>비밀번호 확인</th>
					<td><input type="password" name="upload_pwd"></td>
				</tr>
				<tr>
					<th>현재 비밀번호</th>
					<td><input type="password" name="upload_pwd"></td>
				</tr>
				<tr>

					<td colspan="4"><label class="game-title">선호 게임</label><br>
						<label for="lol" class="game1"> <input type="checkbox"
							name="lol" value="lol">LOL
					</label> <br> <label class="game2" for="battle_ground"><input
							type="checkbox" name="battle_ground" value="battle_ground">Battleground</label>
						<br> <label for="overwatch" class="game3"><input
							type="checkbox" name="overwatch" value="overwatch2">OverWatch2</label>
					</td>
				</tr>
				<tr>
					<td colspan="4" align="center"><input type="submit"
						value="회원 정보 수정">&nbsp;&nbsp; <input type="reset"
						value="다시작성"></td>
				</tr>
			</table>
		</form>
	</div>

</body>
</html>