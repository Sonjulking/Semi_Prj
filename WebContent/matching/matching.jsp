<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>매칭 데이터 입력</title>

<script src="https://code.jquery.com/jquery-3.6.3.js"></script>


<link rel="stylesheet" href="matching.css" />

</head>
<body>

	<form method="post" name="f" action="<%=request.getContextPath() %>/matching_ok.do">
      <h2 align="center">매칭 데이터 입력</h2>
      <div class="container">
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
        	<button type="submit">매칭하기!</button>
        </div>
        
      </div>
     
    </form>
	
	<script type="text/javascript" src="js/pop.js"></script>

</body>
</html>