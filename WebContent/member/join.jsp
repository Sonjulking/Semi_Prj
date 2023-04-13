<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>회원가입</title>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- fontawesome cdn -->
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="../css/join.css" />
<style>
</style>

</head>
<body>
	<div class="container1">
		<span class="title">겜만추&nbsp;<i class="fa fa-thumbs-o-up"
			aria-hidden="true"></i></span> <br> <br> <span class="text1">겜만추에서
			수 많은 사람들과 플레이하고,</span> <span class="text1">레전드 순간을 함께하세요!</span>
	</div>

	<div class="container2">
		<h1>회원가입</h1>
		<form method="post" enctype="multipart/form-data"
			action="<%=request.getContextPath()%>/email_member.do"
			class="joinForm">
			<span>프로필 사진</span> <input type="file" name="profile_img"
				value="프로필 사진"><br> <label class="userId" for="Id">ID</label>
			<input type="text" id="username" name="id" size="20"
				placeholder="아이디 / 영문 + 숫자"><br> <span id="id_check"></span>
			<br>
			<!-- <input type="button" value="아이디중복체크" id="idcheck_btn"> -->
			<label class="password" for="password1">PWD</label> <input
				type="password" id="password1" class="inputpwd" name="pwd"
				placeholder="비밀번호 / 6~12자"><br> <span id="pwd_check"></span>
			<br> <label class="password" for="password2">PWD2</label> <input
				type="password" class="inputpwd" id="password2" class="inputpwd"
				name="pwd_recheck" placeholder="비밀번호 / 6~12자"><br> <span
				id="pwd_recheck"></span> <br> <label class="name" for="name">NickName</label>
			<input type="text" id="name" name="name" placeholder="겜만추에서 사용할 닉네임">
			<!-- <input type="button" value="닉네임중복체크" id="namecheck_btn"> -->
			<span id="name_check"></span> <br> <label class="email"
				for="email">Email</label> <input type="text" id="email" name="email"
				placeholder="Email"><span id="email_check"></span> <br>
			<label class="phone" for="phone">Phone</label> <input type="text"
				id="phone" name="phone" placeholder="전화번호"> <br> <br>
			<label for="lol" class="game1"><input type="checkbox"
				name="lol" value="lol">LOL</label> <br> <label
				class="game-title">선호 게임</label> <label class="game2"
				for="battle_ground"><input type="checkbox"
				name="battle_ground" value="battle_ground">Battleground</label> <br>
			<label for="overwatch" class="game3"><input type="checkbox"
				name="overwatch" value="overwatch">OverWatch2</label> <br> <br>
			<label class="check_join"><input type="checkbox"
				name="check_info" value="check_info" required="required">약관
				동의</label> <br>
			<button id="signup-btn" class="signup submit-btn" type="submit"
				name="join" onclick="">회원가입</button>

		</form>
	</div>
	<%-- <script type="text/javascript">
	let link ="<%=request.getContextPath()%>
		";
	</script> --%>
	<script type="text/javascript"
		src="https://code.jquery.com/jquery-3.6.1.js"></script>
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.5/jquery.validate.js"></script>

	<script type="text/javascript" src="../js/joinValidation.js"></script>
	<script type="text/javascript" src="../js/join.js"></script>
	<script type="text/javascript" src="../js/nickname.js"></script>
	<script type="text/javascript" src="../js/emailCheck.js"></script>

</body>
</html>