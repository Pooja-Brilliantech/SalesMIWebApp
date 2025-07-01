<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
	<div class="col-xs-offset-1 col-xs-12">
		<ul class="nav nav-tabs">
			<li class=active><a href="#">Home</a></li>
			<li><a href="connect">Connect!</a></li>
			<li><a href="#">Friends</a></li>
			<li class="drop-down"><a href="#" data-toggle="drop-down"
				class="dropdown-toggle">Settings<b class="caret"></b></a>
				<ul class="dropdown-menu">
					<li><a href="#">Profile</a></li>
					<li class="divider"></li>
					<li><a href="#">Logout</a></li>
				</ul></li>
			<div class="cols-xs-offset-10 col-xs-6">
				<p style="margin-top: 1%; margin-left: 60%">Welcome
					${user.lastName} ${user.firstName}</p>
			</div>
		</ul>
	</div>
</body>
</html>