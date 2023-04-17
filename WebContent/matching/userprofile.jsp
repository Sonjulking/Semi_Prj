<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>유저 프로필 </title>

<style type="text/css">

/* 전체적인 스타일 */
body {
   background-color: rgba(0,0,0,0.5);
}

/* 프로필 스타일 */
.profile {
  max-width: 800px;
  margin: 0 auto;
  padding: 50px;
  text-align: center;
}

.profile img {
  display: block;
  width: 200px;
  height: 200px;
  margin: 0 auto;
  border-radius: 50%;
}

.profile h2 {
  font-size: 24px;
  margin-top: 10px;
  color: #999;
}

.hide-button {
			display: none;
		}

</style>
</head>
<body onload="autoClick()">
	
	<div class="profile">
	   <c:set var="dto" value="${Cont }" />
		<input type="hidden" name="id" value="${dto.getMember_id() }">
	
	    <img src="프로필 사진 경로" alt="프로필 사진">
	    <h2>디스코드 아이디</h1>
	    <h2>카카오톡 아이디</h2>
	    
	    
	    <input type="submit" id = "cancel-btn" value="매칭종료" onclick="alert('매칭 데이터가 삭제되었습니다.')"
		  formaction="<%=request.getContextPath() %>/finaldelete_ok.do">
		  
	    
  	</div>
  	
  	<script type="text/javascript">
  	
  	document.getElementById("cancel-btn").classList.add("hide-button");
  	
  	function autoClick() {
	      setTimeout(function() {
	        document.getElementById("cancel-btn").click();
	      }, 20000); // 20초 후에 자동 클릭
	}
  	
  	
  	</script>

</body>
</html>