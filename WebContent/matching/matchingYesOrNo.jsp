<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">

.YesNo {
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
          background-color: rgba(0,0,0,0.5);
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

	<div class="YesNo">

    <form method="post" id="YesOrNO">
    
    <c:set var="mdto" value = "${Match }" />
	<input type="hidden" name="id" value="${mdto.getMatching_user_id() }">

	  <input type="submit" id="Yesbutton" value="수락" formaction="<%=request.getContextPath() %>/matchingAccept.do">
	
	  <input type="submit" class="Nobutton" value="취소" formaction="<%=request.getContextPath() %>/matchingdelete_ok.do">
        
    </form>
</div>

<script type="text/javascript">
	
	/* function handleClick(event) {
		event.preventDefault(); // submit 기본 동작 방지
		alert("매칭 수락하셨습니다!");
		
		$.ajax({
			type : "post",
			url : "./matching/matchingAccept.jsp",
			data : $('#YesOrNO').serialize(),
			datatype : "jsp",
			success : function(data) {
				if(data == 1){	// DB가 겹치면 실행되는 곳
					alert("상대방이 수락!" + data);
					location.href = "./matching/userprofile.jsp";
				}else{
					alert("상대방이 취소함!" + data);
				}
			},
			
			error : function(data) {
				alert("데이터 통신 오류 입니다.")
			}
		});
		
	}
	 */

</script>
		

</body>
</html> 