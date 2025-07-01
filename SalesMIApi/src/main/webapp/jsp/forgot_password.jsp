<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<title>Machine Programming</title>
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/bootstrap-theme.min.css">
<script src="../js/jquery.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script src="../js/parsley.js"></script>
<script src="../js/forgot_password.js"></script>
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
	<div class="col-xs-10">
		<form:form id="forgotPasswordform" class="form-horizontal"
			method="post" action="/sbhima/web/forgotPassword"
			modelAttribute="forgotPasswordform">
			<div class="form-group">
				<label for="email" class="control-label col-xs-2">Email</label>
				<div class="col-xs-3">
					<form:input path="email" class="form-control" name="email"
						placeholder="Email" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-xs-offset-2 col-xs-1">
					<button type="submit" class="btn btn-primary">Reset
						Password</button>
				</div>
			</div>
		</form:form>
	</div>
</body>
</html>