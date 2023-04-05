/**
 * 
 */


$(function() {	
	
		// 닉네임 중복 여부 확인
	$("#namecheck_btn").click(function() {
		$.ajax({
			ContentType : "application/x-www-form-urlencoded; charset=UTF-8",
		    type : "post",
				// 여러 ajax에서 동일하게 사용되는 속성 설정
			url: "/Semi_Prj/nicknameCheck.do",
			data : {id:  $("#name").val()},
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
// ajax end
     

     	