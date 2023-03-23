<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
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
	        		<label><input type="checkbox" name="lol" value="lol">leage of legend</label> <br>
	        		<label><input type="checkbox" name="battle ground" value="battle ground">battle ground</label> <br>
	        		<label><input type="checkbox" name="overwatch" value="overwatch">overwatch</label>
	        	</td>
	        </tr>
	        <tr>
	        	
	        	<td colspan="2" align="center">
	        		<label>약관 동의하기<input type="checkbox" name="check_info" value="check_info"></label>
	        	</td>
	        </tr>
	    </table>
	    <br>
	    
	    <input type="submit" name="join" value="회원가입">
	</div>
</body>
</html>