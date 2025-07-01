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
<script src="../js/registration.js"></script>
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
		<form:form id="resetPasswordform" class="form-horizontal"
			method="post" action="/sbhima/web/resetPassword"
			modelAttribute="resetPasswordform">
			<div class="form-group">
				<label for="oldPassword" class="control-label col-xs-2">oldPassword</label>
				<div class="col-xs-3">
					<form:input path="oldPassword" class="form-control"
						name="oldPassword" placeholder="Old Password" />
				</div>
			</div>
			<div class="form-group">
				<label for="newPassword" class="control-label col-xs-2">newPassword</label>
				<div class="col-xs-3">
					<form:input path="newPassword" class="form-control"
						name="newPassword" placeholder="New Password" />
				</div>
			</div>
			<div class="form-group">
				<label for="confirmPassword" class="control-label col-xs-2">confirmPassword</label>
				<div class="col-xs-3">
					<form:input path="confirmPassword" class="form-control"
						name="confirmPassword" placeholder="Confirm Password" />
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