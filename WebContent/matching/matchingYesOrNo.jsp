<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">
#YesOrNO {
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
	position: fixed;
	z-index: 9999;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background-color: rgba(0, 0, 0, 0.5);
}

#Yesbutton {
	padding: 10px 20px;
	margin-right: 50px;
	border: none;
	border-radius: 5px;
	background-color: #ffffff;
	color: #000000;
	font-size: 16px;
	cursor: pointer;
	transition: background-color 0.3s ease;
}

#Yesbutton:hover {
	background-color: #cccccc;
}

.Nobutton {
	padding: 10px 20px;
	border: none;
	border-radius: 5px;
	background-color: #ffffff;
	color: #000000;
	font-size: 16px;
	cursor: pointer;
	transition: background-color 0.3s ease;
}

.Nobutton:hover {
	background-color: #cccccc;
}
</style>
 </head>
<body>

	<form method="post" name="f" id="YesOrNO">

		<c:set var="mdto" value="${Match }" />
		<input type="hidden" name="id" value="${mdto.getMatching_user_id() }">
		<input type="hidden" name="matched" value="${mdto.getMatched() }">
		<input type="hidden" name="gamename" value="${mdto.getGame_name() }">
		<input type="hidden" name="tier" value="${mdto.getTier() }">
		
		

		<input type="submit" id="Yesbutton" value="수락"
			formaction="<%=request.getContextPath()%>/matchingAccept.do">

		<input type="submit" class="Nobutton" value="취소"
			formaction="<%=request.getContextPath()%>/matchingdelete_ok.do">

	</form>


	<script src="https://code.jquery.com/jquery-3.6.3.js"></script>
	<!-- <script type="text/javascript" src ="/js/yesOrNo.js"></script> -->

</body>
</html>
