<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Registration Form</title>
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/bootstrap-theme.min.css">
<script src="../js/jquery.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script src="../js/parsley.js"></script>
<script src="../js/login.js"></script>
<style type="text/css">
.bs-example {
	margin: 20px;
}
/* Fix alignment issue of label on extra small devices in Bootstrap 3.2 */
.form-horizontal .control-label {
	padding-top: 7px;
}
</style>
</head>
<body>
	<div class="image">
		<img alt="" src="../images/head.png" width="100%" height="10%"
			style="padding-bottom: 3%"></img>
	</div>
	<div class="bs-example">
		<form:form id="loginForm" class="form-horizontal" method="post"
			modelAttribute="loginForm" action="/sbhima/web/login">
			<div class="form-group">
				<label for="email" class="control-label col-xs-2">Email</label>
				<div class="col-xs-3">
					<form:input path="email" class="form-control" name="email"
						placeholder="Email" />
				</div>
				<div class="col-xs-offset-2 col-xs-2">
					<button type="button" class="btn btn-primary btn-lg">
						<a href="socialsignup?socialType=facebook"
							style="text-decoration: none; color: #ffffff">Signin With
							Facebook</a>
					</button>
				</div>
			</div>
			<div class="form-group">
				<label for="password" class="control-label col-xs-2">Password</label>
				<div class="col-xs-3">
					<form:input path="password" class="form-control" name="password"
						placeholder="Password" />
				</div>
				<div class="col-xs-offset-2 col-xs-2">
					<button type="button" class="btn btn-primary btn-lg">
						<a href="www.twitter.com"
							style="text-decoration: none; color: #ffffff">Signin With
							Twitter</a>
					</button>
				</div>
			</div>
			<div class="form-group">
				<div class="remember-me col-xs-offset-2 col-xs-2">
					<input type="checkbox" id="remember-me" name="remember-me">
					<label for="remember-me">Remember Me</label>
				</div>
				<div class="col-xs-offset-0 col-xs-2 forgot-password">
					<a href="/sbhima/web/forgotPassword">Forgot password?</a>
				</div>
				<div class="col-xs-offset-1 col-xs-2">
					<button type="button" class="btn btn-primary btn-lg">
						<a href="www.twitter.com"
							style="text-decoration: none; color: #ffffff">Signin With
							LinkedIn</a>
					</button>
				</div>
			</div>
			<div class="form-group">
				<div class="col-xs-offset-2 col-xs-3">
					<button type="submit" class="btn btn-primary">Login</button>
				</div>
			</div>
		</form:form>
	</div>
</body>
</html>
