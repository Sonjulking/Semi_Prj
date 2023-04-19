<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>아이디 찾기</title>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- fontawesome cdn -->
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<link rel="stylesheet" href="../css/login.css" />

</head>
<body>
    <form method="post"
		action="<%=request.getContextPath()%>/find_id.do">

		<div class="container1">
			<span class="title">겜만추&nbsp;<i class="fa fa-thumbs-o-up"
				aria-hidden="true"></i></span> <br> <br> <span class="text1">겜만추에서
				수 많은 사람들과 플레이하고,</span> <span class="text1">레전드 순간을 함께하세요!</span>
		</div>

		<div class="container2">
			<h2>아이디 찾기</h2>


			<label for="id_find">아이디 찾기</label><input type="text" name="id_find" placeholder="이메일을 입력하세요">  <a href=""> <i
				id="site-face" class="fa fa-facebook-square" aria-hidden="true"></i>
			</a> <a href=""> <i id="site-goo" class="fa fa-google-plus-square"
				aria-hidden="true"></i>
			</a>


			<button class="login" type="submit">로그인</button>

			<h6 class="lostpwd">
				<a href="/Semi_Prj/member/findPwd.jsp">비밀번호를 잊으셨나요?</a>
			</h6>

			<a href="/Semi_Prj/member/join.jsp"><button class="signup" type="button">회원가입</button></a>


		</div>

	</form>
	


</body>

</html>