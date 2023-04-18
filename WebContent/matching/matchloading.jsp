<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>매칭 대기중...</title>

<script src="https://code.jquery.com/jquery-3.6.3.js"></script>

<%-- <link rel="stylesheet" href="matchloading.css" /> --%>

<style type="text/css">

@charset "UTF-8";

.loading-wrapper {
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

.loading-text {
  font-size: 24px;
  color: #ffffff;
  margin-bottom: 20px;
}

.arrow-wrapper {
  position: relative;
  width: 80px;
  height: 80px;
}

.arrow {
  position: absolute;
  top: 20%;
  left: 25%;
  width: 30px;
  height: 30px;
  border-top: 10px solid #ffffff;
  border-right: 10px solid #ffffff;
  transform: translate(-50%, -50%);
  animation: spin 1s linear infinite;
}

.cancel-btn {
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  background-color: #ffffff;
  color: #000000;
  font-size: 16px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.cancel-btn:hover {
  background-color: #cccccc;
}

.timo {
	size : 20px;
}

@keyframes spin {
  from { transform: rotate(0deg); }
  to { transform: rotate(360deg); }
}

</style>

</head>

<body onload="autoClick()">

	<form method="post" name="f" id="check">
		<c:set var="dto" value="${Cont }" />
		<input type="hidden" name="id" value="${dto.getMember_id() }">
		
		<%-- 새로운 세션 만들어야함 --%>
		<c:set var="mdto" value = "${Match }" />
		<input type="hidden" name="gamename" value="${mdto.getGame_name() }">
		<input type="hidden" name="tier" value="${mdto.getTier() }">
		<input type="hidden" name="matched" value="${mdto.getMatched() }">
		
		
		<div class="loading-wrapper">
		  <div class="loading-text">Loading...</div>
		  <div class="arrow-wrapper">
		    <div class="arrow"></div>
		  </div>
		  <!-- <button class="cancel-btn"> 매칭 취소</button> -->
		  <input type="submit" id = "cancel-btn" value="매칭취소" onclick="alert('조건에 맞는 유저가 없어서 매칭이 취소되었습니다.')"
		  formaction="<%=request.getContextPath() %>/matchingdelete_ok.do">
		</div>
	</form>
	
	
	<script type="text/javascript">
	// 로딩 화면 보이기
	function showLoading() {
	  var loadingWrapper = document.querySelector('.loading-wrapper');
	  loadingWrapper.style.display = 'flex';
	}

	// 로딩 화면 숨기기
	function hideLoading() {
	  var loadingWrapper = document.querySelector('.loading-wrapper');
	  loadingWrapper.style.display = 'none';
	}

	// 취소 버튼 클릭 시 이벤트 핸들러
	function onCancel() {
	  // 취소 버튼 클릭 시 수행할 작업을 여기에 추가합니다.
	  hideLoading(); // 예시로, 로딩 화면을 숨기는 함수를 호출합니다.
	}

	// 취소 버튼에 이벤트 핸들러 추가
	var cancelBtn = document.querySelector('#cancel-btn');
	cancelBtn.addEventListener('click', onCancel);
	
	
	// 5초마다 매칭 조건 검색하여 매칭 돌리기
	let playmatching = setInterval(function() {
		
		$.ajax({
			type : "post",
			url : "./matching/matchingCheck.jsp",
			data : $('#check').serialize(),
			datatype : "jsp",
			success : function(data) {
				if(data == 1){	// 나중에 alert는 삭제해도 될듯하다. 유저가 많으면 검색을 더 많이해야하니 매칭 주기를 1.5초 정도로 줄이는게 좋아보임
					alert("매칭잡힘!" + data);
					location.href = "./matching/matchingYesOrNo.jsp";
				}else{
					alert("매칭안잡힘!" + data);
				}
			},
			
			error : function(data) {
				console.log("데이터 오류");
			}
		});
		
	}, 5000);
	
	
	// 여기에 매칭 안잡힐시 매칭취소버튼 자동클릭 넣으면 될듯?
	// 30초 후에 () 함수를 호출하여 setInterval() 함수를 종료시킴.
	setTimeout(function() {
			clearInterval(playmatching);
	}, 20000);
	
	function autoClick() {
	      setTimeout(function() {
	        document.getElementById("cancel-btn").click();
	      }, 40000); // 3초 후에 자동 클릭
	}
	
	</script>
	
</body>
</html>