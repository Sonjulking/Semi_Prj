$(function() {

	let isChecked = false;

	$(".submit-btn").click(function(event) {
		$("#idcheck_btn").click(function() {
			if (click) {
				console.log("클릭됨");
				click = !click;

				if (!isChecked) {
					alert("닉네임 중복 체크를 먼저 진행해주세요.");
					event.preventDefault(); // 다음 페이지로 이동을 막음
				}

				// 타이밍 추가
				setTimeout(function() {
					click = true;
				}, 2000)

			} else {
				console.log("중복됨");
			}

		});

	});

});