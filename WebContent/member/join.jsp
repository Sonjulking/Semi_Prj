<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.1.js"></script>
<script type="text/javascript">
     
     // id check
     $(function() {
    	$("#idcheck_btn").on("click", function() {
    		$("#id_check").hide();
    		let userId = $("#username").val();
    		
    		// 아이디 길이 체크하는 방법
    		if($.trim($("#username").val()).length > 16) {
    		    let warningTxt = '<font color="red">아이디는 16자 이하이어야 합니다.</font>';
    		    $("#id_check").text("");
    		    $("#id_check").show("");
    		    $("#id_check").append(warningTxt);
    		    $("#username").val('').focus();
    		    return false;
    		}
    		
    		// 아이디 중복 여부 확인 - Ajax 기술 이용하여 진행
    		$.ajax({
    			type : "post",
    			url : "join_check.jsp",
    			data : {paramId : userId},
    			datatype : "jsp",
    			success : function(data) {
    				if(data == -1) {
    					// DB에 존재하는 아이디가 중복인 경우
    					let warningTxt = '<font color="red">중복된 아이디입니다.</font>';
    	    		    $("#id_check").text("");
    	    		    $("#id_check").show("");
    	    		    $("#id_check").append(warningTxt);
    	    		    $("#username").val('').focus();
    				} else {
    					let warningTxt = '<font color="blue">사용 가능한 아이디입니다.</font>';
    	    		    $("#id_check").text("");
    	    		    $("#id_check").show("");
    	    		    $("#id_check").append(warningTxt);
    				}
    			},
    			
    			error : function(data) {
    				alert("데이터 통신 오류입니다~~");
    			}
    		    
    		});
    	}); 
    	
     });
    
     
     // password check
     $(function() {
     	$("#password").on("click", function() {
     		$("#pwd_check").hide();
     		let userPwd = $("#password").val();
     		
     	// 비밀번호 길이 체크하는 방법
    		if($.trim($("#password").val()).length < 4) {
    		    let warningTxt = '<font color="red">비밀번호는 6자 이상이어야 합니다.</font>';
    		    $("#pwd_check").text("");
    		    $("#pwd_check").show("");
    		    $("#pwd_check").append(warningTxt);
    		    $("#password").val('').focus();
    		    return false;
    		}  
     	    if($.trim($("#password").val()).length > 12) {
    		    let warningTxt = '<font color="red">비밀번호는 12자 이하이어야 합니다.</font>';
    		    $("#pwd_check").text("");
    		    $("#pwd_check").show("");
    		    $("#pwd_check").append(warningTxt);
    		    $("#password").val('').focus();
    		    return false;
    		}
     	})
     	
      });
      
     
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
	          <input type="text" id="username" name="id" size="20" onsubmit="checkId()" placeholder="아이디"><br>
	          <input type="button" value="아이디중복체크" id="idcheck_btn">
	          <span id="id_check"></span>
	        <br>
	
	        <label class="password" for="password">PWD</label>
	          <input type="password" id="password" name="pwd" placeholder="비밀번호 / 9~12자"><br>
	          <span id="pwd_check"></span>
	        <br>
	
	        <label class="name" for="name">Name</label>
	          <input type="text" id="name" name="name" placeholder="겜만추에서 사용할 닉네임">
	          <input type="button" value="닉네임중복체크" id="namecheck_btn">
	          <span id="name_check"></span>
	        <br>
	        
	        <label class="email" for="email">Email</label>
	          <input type="text" id="email" name="email" placeholder="Email">
	        <br>
	
	        <label class="phone" for="phone">Phone</label>
	          <input type="text" id="phone" name="phone" placeholder="전화번호">
	        <br>
	        <br>
	        
			<label for="lol" class="game1"><input type="checkbox" name="lol" value="lol">LOL</label>
			<br> 
			<label class="game-title">선호 게임</label>
			<label class="game2" for="battle_ground"><input type="checkbox" name="battle_ground" value="battle_ground">Battleground</label>
			<br>
			<label for="overwatch" class="game3"><input type="checkbox" name="overwatch" value="overwatch">OverWatch2</label>
			<br>
			<br>
			<label class="check_join"><input type="checkbox" name="check_info" value="check_info" required="required">약관 동의</label>
			<br>
			<button class="signup" type="submit" name="join">회원가입</button>
		</form>	
		</div>
</body>
</html>