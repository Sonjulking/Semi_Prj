/**
 * 
 */

// ajax start  
$(function() {     
    	
	// 아이디 중복 여부 확인
	$("#idcheck_btn").click(function() {
		$.ajax({
			ContentType : "application/x-www-form-urlencoded; charset=UTF-8",
		    type : "post",
			url: "/Semi_Prj/idCheck.do",
			data : {id:  $("#username").val()},
			datatype: "text",
			success: function(data) {
				alert(data);
			},
			
			error() {
				alert("데이터 통신 오류입니다.");
			}
		});
	});
});

