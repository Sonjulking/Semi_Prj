/*$(function() {

	$('form').submit(function() {
		// 버튼을 비활성화
		$('input[type="submit"]', this).prop('disabled', true);
		//input[type="submit"]이 submit인 요소, this는 form 요소.

		//prop() 함수는 선택한 요소의 속성 값을 가져오거나 설정합니다. 
		//여기서는 disabled 속성을 true로 설정하여 제출 버튼을 비활성화합니다.

		//따라서, $('input[type="submit"]', this).prop('disabled', true);
		// 코드는 현재 폼의 제출 버튼을 선택하고, 
		//그 버튼의 disabled 속성을 true로 설정하여 제출 버튼을 비활성화합니다.
		// 이렇게 하면 사용자가 폼을 중복해서 제출하는 것을 방지할 수 있습니다.

		// 중복 제출 방지를 위해 폼 제출을 막음
		return false;
	});
});*/
$(function() {
	let click = true;

	$(".submit-btn").click(function() {
		if (click) {
			console.log("클릭됨");
			click = !click;

			setTimeout(function() {
				click = true;
				$(".submit-btn").attr("disabled", false);
			}, 2000)

		} else {
			$(".submit-btn").attr("disabled", true);
			console.log("중복됨");
		}
	});

});