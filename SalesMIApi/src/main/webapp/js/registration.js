$(document)
		.ready(
				function() {
					$("#signupForm").attr("parsley-validate", "");
					$("#email").attr("parsley-type", "email");
					$("#email")
							.attr("parsley-type-email-message",
									"Please enter an email address in the following format: you@example.com.");
					$("#email").attr("parsley-required", "true");
					$("#email").attr("parsley-required-message",
							"Please enter an email address.");
					$("#password").attr("parsley-required", "true");
					$("#password")
							.attr("parsley-required-message",
									"Please type a password, and then retype it to confirm.");
					$("#password")
							.attr("pattern",
									"^(?=.*?[a-zA-Z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$");
					$("#password")
							.attr("parsley-regexp-message",
									"Minimum length 8, with a number, <br> a letter and a special character.");
					$("#confirmPassword").attr("parsley-required", "true");
					$("#confirmPassword").attr("parsley-required-message",
							"Please retype the password to confirm.");
					$("#confirmPassword").attr("parsley-equalto", "#password");
					$("#confirmPassword").attr("parsley-equalto-message",
							"The passwords don't match.");
					$("#firstName").attr("parsley-required", "true");
					$("#lastName").attr("parsley-required", "true");
				});
$("#reset").click(function() {
	$("#signupForm").parsley("reset");
});