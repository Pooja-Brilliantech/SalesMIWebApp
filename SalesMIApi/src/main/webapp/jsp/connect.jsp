<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Example of Twitter Bootstrap 3 Linked List Groups</title>
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/bootstrap-theme.min.css">
<script src="../js/jquery.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<style type="text/css">
.list-group {
	width: 200px;
}

.connect {
	margin: 20px;
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
			<li><a href="home">Home</a></li>
			<li class=active><a href="connect">Connect!</a></li>
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
		<div class="connect">
			<div class="list-group">
				<a href="facebook_connect" class="list-group-item active"> <span
					class="icon-twitter"></span> Facebook <span class="badge">25</span>
				</a> <a href="#" class="list-group-item"> <span
					class="fa fa-linkedin"></span> Twitter <span class="badge">145</span>
				</a> <a href="#" class="list-group-item"> <span
					class="fa fa-facebook"></span> LinkedIn <span class="badge">50</span>
				</a>
			</div>
		</div>
	</div>

</body>
</html>
