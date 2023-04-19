/**
 *
 */

// ajax start  
$(function() {
	//let ref = document.referrer; // 이전 페이지 URL

	let isChecked = false;

	// 아이디 중복 여부 확인
	$("#username").keyup(function() {
		$.ajax({
			ContentType: "application/x-www-form-urlencoded; charset=UTF-8",
			type: "post",
			url: "/Semi_Prj/idCheck.do",
			data: { id: $("#username").val() },
			datatype: "text",
			success: function(data) {
				console.log(data);
				if (data === "중복") {
					// set the response data to an element in the DOM
					isChecked = false;

					$("#id_check").text("중복입니다.");
					console.log(data);
					console.log(isChecked);

				} else {
					// set the response data to an element in the DOM
					isChecked = true;
					console.log(data);
					console.log(isChecked);
					$("#id_check").text("사용가능!");

				}
			},

			error() {
				alert("데이터 통신 오류입니다.");
				isChecked = false;

			}
		});
	});

	$("#signup-btn").click(function(event) {
		//$("#idcheck_btn").click();
		/*		if (isChecked) {
					window.history.back();
		
				} else {
			
				}
		*/


		if (!isChecked) {
			alert("아이디 중복 체크를 먼저 진행해주세요.");
			event.preventDefault(); // 다음 페이지로 이동을 막음
		}
	});







});

