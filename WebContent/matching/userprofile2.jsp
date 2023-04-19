<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div class="profile">
	    <c:set var="mdto" value = "${Match }" />
		<input type="hidden" name="id" value="${mdto.getMatching_user_id() }">
	
	    <img src="프로필 사진 경로" alt="프로필 사진">
	    <h2>디스코드 아이디</h1>
	    <h2>카카오톡 아이디</h2>
	    
	    
  	</div>
</body>
</html>