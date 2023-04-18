<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>유저 프로필</title>

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

#home-btn {
	visibility: hidden;
}
</style>
</head>
<body>
	<h1>유저 프로필!</h1>


	<form method="post" name="f" id="YesOrNO">

		<c:set var="mdto" value="${Match }" />
		<input type="hidden" name="id" value="${mdto.getMatching_user_id() }">
		<input type="submit" id="home-btn" class="Nobutton" value="홈 화면으로"
			formaction="<%=request.getContextPath()%>/justdelete_ok.do">
	</form>


	<script src="https://code.jquery.com/jquery-3.6.3.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#home-btn').trigger('click');
		});
	</script>
	<!-- <script type="text/javascript" src ="/js/yesOrNo.js"></script> -->

</body>
</html>
