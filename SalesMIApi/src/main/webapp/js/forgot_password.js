$(document)
		.ready(
				function() {
					$("#forgotPasswordform").attr("parsley-validate", "");
					$("#email").attr("parsley-type", "email");
					$("#email")
							.attr("parsley-type-email-message",
									"Please enter an email address in the following format: you@example.com.");
					$("#email").attr("parsley-required", "true");
					$("#email").attr("parsley-required-message",
							"Please enter an email address.");

				});
