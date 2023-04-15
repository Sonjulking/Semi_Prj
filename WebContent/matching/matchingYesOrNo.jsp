<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">

/* 전체적인 스타일 */
body {
  font-family: 'Helvetica', sans-serif;
}

/* 버튼 컨테이너 스타일 */
.button-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}

/* 버튼 스타일 */
.button {
  display: inline-block;
  padding: 10px 20px;
  background-color: #3d5afe;
  color: #fff;
  text-align: center;
  border-radius: 5px;
  text-decoration: none;
}

.button:hover {
  background-color: #1e40af;
}

/* 수락 버튼 스타일 */
.accept-button {
  background-color: #4caf50;
}

.accept-button:hover {
  background-color: #087f23;
}

/* 취소 버튼 스타일 */
.cancel-button {
  background-color: #f44336;
}

.cancel-button:hover {
  background-color: #a6211b;
}

</style>
</head>
<body>


	<div class="button-container">

		<a href="#" class="button accept-button">수락</a>
		
		<a href="#" class="button cancel-button">취소</a>
		
  	</div>


</body>
</html> 