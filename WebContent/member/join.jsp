<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">

 function lol(obj) {
	 var checked = obj.checked;
	 if(checked) {
		 obj.value = "Y"; 
	 } else {
		 obj.value = "N";
	 }
 };
 
 function battle_ground(obj) {
	 var checked = obj.checked;
	 if(checked) {
		 obj.value = "Y"; 
	 } else {
		 obj.value = "N";
	 }
 };
 
 function overwatch(obj) {
	 var checked = obj.checked;
	 if(checked) {
		 obj.value = "Y"; 
	 } else {
		 obj.value = "N";
	 }
 };
 
</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="post" action="<%=request.getContextPath()%>/insert_member.do">
	<div align="center">
	    <table border="1">
	        <tr>
	            <th colspan="2">회원가입</th>
	        </tr>
	        <tr>
	            <th>ID</th>
	            <td>
	                <input type="text" placeholder="아이디" name="id">
	            </td>
	        </tr>
	        <tr>
	            <th>PWD</th>
	            <td>
	                <input type="password" placeholder="비밀번호/9~12자" name="pwd">
	            </td>
	        </tr>
	        <tr>
	            <th>Name</th>
	            <td>
	                <input type="text" placeholder="겜만추에서 사용할 닉네임" name="name">
	            </td>
	        </tr>
	        <tr>
	            <th>Phone</th>
	            <td>
	                <input type="text" placeholder="전화번호" name="phone">
	            </td>
	        </tr>
	        <tr>
	        	<th>선호 게임</th>
	        	<td>
	        		<label for="lol"><input type="checkbox" name="lol" onchange="lol(this);" value="lol">league of legend</label> <br>
	        		<label for="battle_ground"><input type="checkbox" name="battle_ground" onchange="battle_groundI(this);" value="battle ground">battle ground</label> <br>
	        		<label for="overwatch"><input type="checkbox" name="overwatch" onchange="overwatch(this);" value="overwatch">overwatch</label>

	        	</td>
	        </tr>
	        <tr>
	        	
	        	<td colspan="2" align="center">
	        		<label>약관 동의하기<input type="checkbox" name="check_info" value="check_info" required="required"></label>
	        	</td>
	        </tr>
	    </table>
	    <br>
	    
	    <input type="submit" name="join" value="회원가입">
	</div>
</form>
</body>
</html>