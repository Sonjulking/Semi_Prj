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
	<form method="post"
		action="<%=request.getContextPath()%>/member_findPwd.do">
		<div align center>
			<c:set var="sendEmail" value="${emailCont }" />
			<label for="username">아이디</label> <input type="text" id="username"
				name="id" placeholder="아이디를 입력하세요."> <label for="email_find">이메일</label>
			<input id="email_find" name="email_find" placeholder="이메일을 입력하세요.">
			<a href=""> <br> <i id="site-face"
				class="fa fa-facebook-square" aria-hidden="true"></i>
			</a> <a href=""> <i id="site-goo" class="fa fa-google-plus-square"
				aria-hidden="true"></i> <input type="submit" value="전송하기">
			</a>

			<c:if test="${sendEmail.equals('fail') }">
         메일발송이 실패했습니다.
     </c:if>
			<c:if test="${!sendEmail.equals('fail')  }">
         이메일이 ${userEmail }로 발송되었습니다. 인증코드를 입력해 주세요.
     </c:if>
		</div>

	</form>
</body>
</html>