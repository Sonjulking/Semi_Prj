$.validator.addMethod("specialChars", function(value, element) {
	// Define the pattern to match special characters
	var pattern = /[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]/;

	// Test the value against the pattern and return true or false
	return pattern.test(value);
});

$.validator.addMethod("capitalLetters", function(value, element) {
	// Define the pattern to match capital letters
	var pattern = /[A-Z]/;

	// Test the value against the pattern and return true or false
	return pattern.test(value);
});
$(".joinForm").validate({


	rules: {

		pwd: {
			required: true,
			minlength: 6,
			maxlength: 12,
			specialChars: true,
			capitalLetters: true,
		},
		pwd_recheck: {
			required: true,
			equalTo: "#password1"
		},

	},

	messages: {
		pwd: {
			required: "이름은 필수 입니다.",
			minlength: "최소 6글자 이상 입력해주세요.",
			maxlength: "12글자를 넘지 말아주세요.",
			capitalLetters: "대문자 하나 입력해주세요",
			specialChars: "특수문자 입력해주세요.",
		},
		pwd_recheck: {
			required: "이름은 필수 입니다.",
			equalTo: "일치하지 않아요...."

		},
	},
	errorElement: "p",
	errorClass: "bad",
	validClass: "good",

	success: function(label) {
		// This function is called when a field passes validation
		label.text("✓").addClass("okayValid");
	},
	onkeyup: function(element) {
		// Trigger validation on keyup event
		$(element).valid();
	},

});


/*$('.inputpwd').keyup(function() {

	let password1 = $("#password1").val();
	let password2 = $("#password2").val();

	if (password1 != "" || password2 != "") {
		if (password1 == password2) {
			$("#pwd_recheck").text('일치했네용');
			$("#pwd_recheck").attr('color', green);

		} else {
			$("#pwd_recheck").text('불일치');
			$("#pwd_recheck").attr('color', red);
		}
	}


})*/