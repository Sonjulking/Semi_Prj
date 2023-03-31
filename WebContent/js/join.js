/**
 * 
 */

// ajax start  
$(function() {
	let userId = $("#username").val();
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
	
 
});
// ajax end
     

    $("#username").keyup(function() { 
	    // 자바스크립트 정규식
		var checkOk =/^[A-Za-z0-9]*$/;
	 	// 아이디 조합이 영문과 숫자만이 아님
	 	if(!checkOk.html(userId)) {
		    $("id_check").hide();
	 		let warningTxt = '<font color="red">아이디 영문 + 숫자 아님.</font>';
			$("#id_check").text("");
			$("#id_check").show("");
			$("#id_check").append(warningTxt);
			$("#username").val('').focus();
	 	}
	});

$(function joinsubmit () {
	
	
	// password check
     	$("#password").on("click", function() {
     		$("#pwd_check").hide();
     		let userPwd = $("#password").val();
     		let pwdOk = $("#dbpassword").val();
     		
     	// 비밀번호 길이 체크하는 방법
    		if($.trim($(userPwd).val()).length < 6) {
    		    let warningTxt = '<font color="red">비밀번호는 6자 이상이어야 합니다.</font>';
    		    $("#pwd_check").text("");
    		    $("#pwd_check").show("");
    		    $("#pwd_check").append(warningTxt);
    		    $("#password").val('').focus();
    		    return false;
    		} 
     	    if($.trim($(userPwd).val()).length > 16) {
    		    let warningTxt = '<font color="red">비밀번호는 12자 이하이어야 합니다.</font>';
    		    $("#pwd_check").text("");
    		    $("#pwd_check").show("");
    		    $("#pwd_check").append(warningTxt);
    		    $("#password").val('').focus();
    		    return false;
    		}
            // 비밀번호 같은지 확인
	       if (userPwd == pwdOk) {
	           $("#dbpassword").html('비밀번호를 입력해주세요.');
	           alert("비밀번호가 같지 않습니다");
	           $("#password").focus();

	           return false;
	       }
     	});

});
     	