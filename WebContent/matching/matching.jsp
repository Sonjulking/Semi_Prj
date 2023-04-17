<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>매칭 데이터 입력</title>

<script src="https://code.jquery.com/jquery-3.6.3.js"></script>

<!-- <link rel="stylesheet" href="matching.css" /> -->
<!-- 동적으로 CSS 적용하는 방법  -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/matching/matching.css" /> 
</head>
<body>
	<br>
	<br>

	<form method="post" name="f" action="<%=request.getContextPath() %>/matching_ok.do">
      <h2 align="center">매칭 데이터 입력</h2>
      <div class="container">
      <c:set var = "dto" value = "${Cont }"/>
      <input type = "hidden" name="id" value = "${dto.getMember_id() }">
      <input type = "hidden" name="nickname" value = "${dto.getMember_nickname() }">
	
        <label for="gamename"><b>게임명</b></label>
        <input type="text" placeholder="이용할 게임을 입력하세요" name="gamename" required>
        
        <label for="tier"><b>티어</b></label>
        <input type="text" placeholder="티어를 입력하세요" name="tier" required>
        
        <label for="DiscordID"><b>디스코드 아이디</b></label>
        <input type="text" placeholder="디스코드 아이디를 입력하세요" name="DiscordID" required>
       	
        <label for="KakaoID"><b>카카오 아이디</b></label>
        <input type="text" placeholder="카카오 아이디를 입력하세요" name="KakaoID" required>
        
	    <br>
        
        <div align="center">
        	<button type="submit" class="Matching">매칭하기!</button>
        </div>
        
      </div>
     
    </form>
	
	<script type="text/javascript" src="js/pop.js"></script>

</body>
</html>