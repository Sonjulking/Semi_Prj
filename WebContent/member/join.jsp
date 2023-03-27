<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
/* 	function lol(obj) {
		var checked = obj.checked;
		if (checked) {
			obj.value = "Y";
		} else {
			obj.value = "N";
		}
	};

	function battle_ground(obj) {
		var checked = obj.checked;
		if (checked) {
			obj.value = "Y";
		} else {
			obj.value = "N";
		}
	};

	function overwatch(obj) {
		var checked = obj.checked;
		if (checked) {
			obj.value = "Y";
		} else {
			obj.value = "N";
		}
	}; */
</script>
<meta charset="UTF-8">
 <title>회원가입</title>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- fontawesome cdn -->
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <link rel="stylesheet" href="../css/join.css" />
    <style>
    
    </style>
</head>
<body>
	<div class="container1">
        <span class="title">겜만추&nbsp;<i class="fa fa-thumbs-o-up" aria-hidden="true"></i></span>
        <br> <br>
        <span class="text1">겜만추에서 수 많은 사람들과 플레이하고,</span>
        <span class="text1">레전드 순간을 함께하세요!</span>
    </div>

	<div class="container2">
		<h1>회원가입</h1>
		<form method="post" action="<%=request.getContextPath()%>/insert_member.do">
					        
	        <label class="username" for="username">ID</label>
	          <input type="text" id="username" name="id" placeholder="아이디">
	        <br>
	
	        <label class="password" for="password">PWD</label>
	          <input type="password" id="password" name="pwd" placeholder="비밀번호 / 9~12자">
	        <br>
	
	        <label class="name" for="name">Name</label>
	          <input type="text" id="name" name="name" placeholder="겜만추에서 사용할 닉네임">
	        <br>
	
	        <label class="phone" for="phone">Phone</label>
	          <input type="text" id="phone" name="phone" placeholder="전화번호">
	        <br>
	        <br>
	        
			<label for="lol" class="game1"><input type="checkbox" name="lol" value="lol">LOL</label>
			<br> 
			<label class="game-title">선호 게임</label>
			<label class="game2" for="battle_ground"><input type="checkbox" name="battle_ground" value="battle_ground">battleground</label>
			<br>
			<label for="overwatch" class="game3"><input type="checkbox" name="overwatch" value="overwatch">overwatch2</label>
			<br>
			<br>
			<label class="check_join"><input type="checkbox" name="check_info" value="check_info" required="required">약관 동의</label>
			<br>
			<button class="signup" type="submit" name="join">회원가입</button>
		</form>	
		</div>
</body>
</html>