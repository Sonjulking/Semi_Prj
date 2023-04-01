/**
 * 
 */

// ajax start  
$(function() {
	// 여러 ajax에서 동일하게 사용되는 속성 설정
	$.ajaxSetup({
		ContentType : "application/x-www-form-urlencoded; charset=UTF-8",
		type : "post"
	});		     
    	
	// 아이디 중복 여부 확인
	$("#username").keyup(function() {
		$.ajax({
			url: "/Semi_Prj/idCheck.do",
			data : {id : $("#username").val()},
			datatype: "text",
			success: function(data) {
				$("#id_check").html(data);
			},
			
			error() {
				alert("데이터 통신 오류입니다.");
			}
		});
	});
	// keyup() end
	
		// 닉네임 중복 여부 확인
	$("#namecheck_btn").on("click", function() {
		$.ajax({
			url: "/Semi_Prj/nicknameCheck.do",
			data : {id : $("#name").val()},
			datatype: "text",
			success: function(data) {
				$("#name").html(data);
			},
			
			error() {
				alert("데이터 통신 오류입니다.");
			}
		});
	});
	// keyup() end
	
 
});
// ajax end
     

     	